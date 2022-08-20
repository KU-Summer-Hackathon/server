package com.server.service.user;

import com.server.domain.user.User;
import com.server.domain.user.repository.UserRepository;
import com.server.service.user.dto.response.GetUserInfoResponse;
import com.server.service.user.dto.response.UserSubInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserRetreiveService {

    private final UserRepository userRepository;

    public GetUserInfoResponse retrieveUserInfo(Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        return GetUserInfoResponse.of(user.getOnboarding(), UserSubInfoResponse.of(user.getUserSubInfo()));
    }
}
