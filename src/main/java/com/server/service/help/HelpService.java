package com.server.service.help;

import com.server.domain.help.Help;
import com.server.domain.help.repository.HelpRepository;
import com.server.domain.user.repository.UserRepository;
import com.server.service.help.dto.request.CreateHelpRequestDto;
import com.server.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class HelpService {

    private final UserRepository userRepository;
    private final HelpRepository helpRepository;
    private final HelpImageService helpImageService;

    @Transactional
    public void createHelp(CreateHelpRequestDto request, List<MultipartFile> images, Long userId) {
        Help help = helpRepository.save(request.toEntity(UserServiceUtils.findUserById(userRepository, userId).getOnboarding()));
        helpImageService.addHelpImages(help, images);
    }
}
