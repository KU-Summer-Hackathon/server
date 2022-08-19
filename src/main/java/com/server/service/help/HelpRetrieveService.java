package com.server.service.help;

import com.server.domain.help.repository.HelpRepository;
import com.server.domain.user.User;
import com.server.domain.user.repository.UserRepository;
import com.server.service.help.dto.response.GetHelpsResponse;
import com.server.service.help.dto.response.HelpInfoResponse;
import com.server.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class HelpRetrieveService {

    private final UserRepository userRepository;
    private final HelpRepository helpRepository;

    public GetHelpsResponse retrieveHelp(Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        return GetHelpsResponse.of(
                user.getOnboarding().getAddress(),
                helpRepository.findOtherHelps(user.getOnboarding()).stream()
                        .map(help -> HelpInfoResponse.of(user.getOnboarding(), help))
                        .collect(Collectors.toList())
        );
    }
}
