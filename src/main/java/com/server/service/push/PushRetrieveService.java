package com.server.service.push;

import com.server.domain.push.repository.PushRepository;
import com.server.domain.user.User;
import com.server.domain.user.repository.UserRepository;
import com.server.service.push.dto.response.GetPushesResponse;
import com.server.service.push.dto.response.PushInfoResponse;
import com.server.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PushRetrieveService {

    private final UserRepository userRepository;
    private final PushRepository pushRepository;

    public GetPushesResponse retrievePushes(Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        return GetPushesResponse.of(pushRepository.findPushesByOnboarding(user.getOnboarding()).stream()
                .map(push -> PushInfoResponse.of(UserServiceUtils.findUserById(userRepository, push.getSenderId()).getOnboarding(), push))
                .collect(Collectors.toList()));
    }
}
