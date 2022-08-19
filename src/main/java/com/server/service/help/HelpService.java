package com.server.service.help;

import com.server.domain.help.Help;
import com.server.domain.help.repository.HelpRepository;
import com.server.domain.push.Push;
import com.server.domain.push.PushType;
import com.server.domain.push.repository.PushRepository;
import com.server.domain.user.User;
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
    private final PushRepository pushRepository;
    private final HelpImageService helpImageService;
    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @Transactional
    public void createHelp(CreateHelpRequestDto request, List<MultipartFile> images, Long userId) {
        Help help = helpRepository.save(request.toEntity(UserServiceUtils.findUserById(userRepository, userId).getOnboarding()));
        helpImageService.addHelpImages(help, images);
    }

    @Transactional
    public void applyHelp(Long helpId, Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        Help help = HelpServiceUtils.findHelpById(helpRepository, helpId);
        String title = "새로운 돕기 요청";
        String content = user.getOnboarding().getName() + "님이 돕고 싶어해요";
        try {
            firebaseCloudMessageService.sendMessageTo(help.getOnboarding().getUser().getFcmToken(), title, content);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        pushRepository.save(Push.of(help.getOnboarding(), help, user.getId(), PushType.HELP, content, false, false));
    }
}
