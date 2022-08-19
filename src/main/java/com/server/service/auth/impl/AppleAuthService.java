package com.server.service.auth.impl;

import com.server.domain.user.User;
import com.server.domain.user.UserSocialType;
import com.server.domain.user.repository.UserRepository;
import com.server.external.client.apple.AppleTokenProvider;
import com.server.service.auth.AuthService;
import com.server.service.auth.dto.request.LoginDto;
import com.server.service.user.UserService;
import com.server.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppleAuthService implements AuthService {

    private static final UserSocialType socialType = UserSocialType.APPLE;

    private final AppleTokenProvider appleTokenDecoder;

    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public Long login(LoginDto request) {
        String socialId = appleTokenDecoder.getSocialIdFromIdToken(request.getToken());
        User user = UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, socialId, socialType);
        if (user == null) return userService.registerUser(request.toCreateUserDto(socialId));
        user.updateFcmToken(request.getFcmToken());
        userRepository.save(user);
        return user.getId();
    }
}
