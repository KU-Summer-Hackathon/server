package com.server.service.user;

import com.server.domain.user.Onboarding;
import com.server.domain.user.User;
import com.server.domain.user.UserSubInfo;
import com.server.domain.user.repository.UserRepository;
import com.server.domain.user.repository.UserSubInfoRepository;
import com.server.service.user.dto.request.CreateUserDto;
import com.server.service.user.dto.request.UpdateLampDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserSubInfoRepository userSubInfoRepository;

    @Transactional
    public Long registerUser(CreateUserDto request) {
        UserServiceUtils.validateNotExistsUser(userRepository, request.getSocialId(), request.getSocialType());
        User user = userRepository.save(User.newInstance(request.getSocialId(), request.getSocialType(), request.getFcmToken(), Onboarding.newInstance(), UserSubInfo.newInstance()));
        return user.getId();
    }

    @Transactional
    public void updateLamp(UpdateLampDto request, Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);

        UserSubInfo userSubInfo = user.getUserSubInfo();
        int currentLamp = userSubInfo.getLamp();

        userSubInfo.updateLamp(currentLamp + request.getLampCnt());
        userSubInfoRepository.save(userSubInfo);
    }
}
