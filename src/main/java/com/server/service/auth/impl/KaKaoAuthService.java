package com.server.service.auth.impl;

import com.server.common.util.HttpHeaderUtils;
import com.server.domain.user.User;
import com.server.domain.user.UserSocialType;
import com.server.domain.user.repository.UserRepository;
import com.server.external.client.kakao.KakaoApiClient;
import com.server.external.client.kakao.dto.response.KakaoProfileResponse;
import com.server.service.auth.AuthService;
import com.server.service.auth.dto.request.LoginDto;
import com.server.service.user.UserService;
import com.server.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KaKaoAuthService implements AuthService {

    private static final UserSocialType socialType = UserSocialType.KAKAO;

    private final KakaoApiClient kaKaoApiCaller;

    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public Long login(LoginDto request) {
        KakaoProfileResponse response = kaKaoApiCaller.getProfileInfo(HttpHeaderUtils.withBearerToken(request.getToken()));
        User user = UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, response.getId(), socialType);
        if (user == null) return userService.registerUser(request.toCreateUserDto(response.getId()));
        user.updateFcmToken(request.getFcmToken());
        userRepository.save(user);
        return user.getId();
    }
}
