package com.server.service.user;

import com.server.domain.user.User;
import com.server.domain.user.repository.UserRepository;
import com.server.service.user.dto.response.GetUserRetreiveResponse;
import com.server.service.user.dto.response.UserSubInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserRetreiveService {

    private final UserRepository userRepository;

    public GetUserRetreiveResponse retrieveUser(Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        return GetUserRetreiveResponse.of(
                user.getOnboarding().getName(),
                user.getOnboarding().getGender(),
                user.getOnboarding().getAge(),
                user.getOnboarding().getAddress(),
                user.getOnboarding().getImageUrl(),
                UserSubInfoResponse.of(user.getUserSubInfo())
        );
    }
}
