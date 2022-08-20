package com.server.service.message;

import com.server.domain.chat.Chat;
import com.server.domain.chat.repository.ChatRepository;
import com.server.domain.message.Message;
import com.server.domain.message.MessageType;
import com.server.domain.message.repository.MessageRepository;
import com.server.domain.user.Onboarding;
import com.server.domain.user.User;
import com.server.domain.user.repository.OnboardingRepository;
import com.server.domain.user.repository.UserRepository;
import com.server.service.firebase.FirebaseCloudMessageService;
import com.server.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MessageService {

    private final UserRepository userRepository;
    private final OnboardingRepository onboardingRepository;
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @Transactional
    public void acceptHelp(Long messageId, Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        Message myMessage = messageRepository.findMessageById(messageId);
        Chat myChat = myMessage.getChat();
        Message opponentMessage = messageRepository.findMessageByChatAndHelpAndOnboarding(myChat, myMessage.getHelp(), onboardingRepository.findOnboardingById(myChat.getOpponentId()));
        Chat opponentChat = opponentMessage.getChat();
        myMessage.updateToAcceptHelp();
        opponentMessage.updateToAcceptedHelp();
        messageRepository.save(myMessage);
        messageRepository.save((opponentMessage));
        myChat.updateMessage(myMessage);
        opponentChat.updateMessage(opponentMessage);
        Onboarding opponentOnboarding = onboardingRepository.findOnboardingById(myChat.getOpponentId());
        myChat.addMessage(messageRepository.save(Message.of(myChat, user.getOnboarding(), myMessage.getHelp(), MessageType.CHECK_HELP, "오늘 하루 어땠는지 물어보기", true)));
        opponentChat.addMessage(messageRepository.save(Message.of(opponentChat, user.getOnboarding(), myMessage.getHelp(), MessageType.PENDING_MISSION, "오늘 하루 어땠는지 물어보기", false)));
        myChat.updateToRead();
        opponentChat.updateToUnRead();
        chatRepository.save(myChat);
        chatRepository.save(opponentChat);
        String title = "새로운 돕기 수락";
        String content = user.getOnboarding().getName() + "님이 돕기 요청을 수락했어요";
        try {
            firebaseCloudMessageService.sendMessageTo(opponentOnboarding.getUser().getFcmToken(), title, content);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public void completeHelp(Long messageId, Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        Message myMessage = messageRepository.findMessageById(messageId);
        Chat myChat = myMessage.getChat();
        Message opponentMessage = messageRepository.findMessageByChatAndHelpAndOnboarding(myChat, myMessage.getHelp(), onboardingRepository.findOnboardingById(myChat.getOpponentId()));
        Chat opponentChat = opponentMessage.getChat();
        myChat.deleteMessage(myMessage);
        Message myCompleteMessage = messageRepository.save(Message.of(myChat, myChat.getOnboarding(), myMessage.getHelp(), MessageType.COMPLETE_HELP, "도움 해결 완료!", true));
        Message opponentCompleteMessage = messageRepository.save(Message.of(opponentChat, myChat.getOnboarding(), myMessage.getHelp(), MessageType.COMPLETE_HELP, "도움 해결 완료!", false));
        myChat.addMessage(myCompleteMessage);
        opponentChat.addMessage(opponentCompleteMessage);
        myChat.updateToRead();
        opponentChat.updateToUnRead();
        chatRepository.save(myChat);
        chatRepository.save(opponentChat);
        Onboarding opponentOnboarding = opponentChat.getOnboarding();
        String title = "도와주기 완료";
        String content = "램프 1개가 지급되었어요";
        try {
            firebaseCloudMessageService.sendMessageTo(opponentOnboarding.getUser().getFcmToken(), title, content);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
