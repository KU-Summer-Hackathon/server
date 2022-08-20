package com.server.service.help;

import com.server.domain.chat.Chat;
import com.server.domain.chat.repository.ChatRepository;
import com.server.domain.help.Help;
import com.server.domain.help.repository.HelpRepository;
import com.server.domain.message.Message;
import com.server.domain.message.MessageType;
import com.server.domain.message.repository.MessageRepository;
import com.server.domain.user.User;
import com.server.domain.user.UserSubInfo;
import com.server.domain.user.repository.UserRepository;
import com.server.service.firebase.FirebaseCloudMessageService;
import com.server.service.help.dto.request.CreateHelpRequestDto;
import com.server.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class HelpService {

    private final UserRepository userRepository;
    private final HelpRepository helpRepository;
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final HelpImageService helpImageService;
    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @Transactional
    public boolean createHelp(CreateHelpRequestDto request, List<MultipartFile> images, Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        if (user.getUserSubInfo().getLamp() < 1) return false;
        UserSubInfo userSubInfo = user.getUserSubInfo();
        userSubInfo.updateLamp(userSubInfo.getLamp() - 1);
        Help help = helpRepository.save(request.toEntity(user.getOnboarding()));
        helpImageService.addHelpImages(help, images);
        return true;
    }

    @Transactional
    public void applyHelp(Long helpId, Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        Help help = HelpServiceUtils.findHelpById(helpRepository, helpId);
        Chat myChat = chatRepository.findByOnboardingAndOpponentId(user.getOnboarding(), help.getOnboarding().getUser().getId());
        Chat opponentChat = chatRepository.findByOnboardingAndOpponentId(help.getOnboarding(), user.getId());
        if (myChat == null) {
            myChat = chatRepository.save(Chat.of(user.getOnboarding(), help.getOnboarding().getUser().getId(), true));
        }
        if (opponentChat == null) {
            opponentChat = chatRepository.save(Chat.of(help.getOnboarding(), user.getId(), false));
        }
        String myContent = "수락 대기 중입니다";
        String opponentContent = user.getOnboarding().getName() + "님이 돕고 싶어해요";
        myChat.addMessage(messageRepository.save(Message.of(myChat, user.getOnboarding(), help, MessageType.PENDING_HELP, myContent, true)));
        opponentChat.addMessage(messageRepository.save(Message.of(opponentChat, user.getOnboarding(), help, MessageType.REQUEST_HELP, opponentContent, false)));
        myChat.updateToRead();
        opponentChat.updateToUnRead();
        String title = "새로운 돕기 요청";
        String content = user.getOnboarding().getName() + "님이 돕고 싶어해요";
        try {
            firebaseCloudMessageService.sendMessageTo(help.getOnboarding().getUser().getFcmToken(), title, content);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
