package com.server.service.message;

import com.server.domain.chat.repository.ChatRepository;
import com.server.domain.message.repository.MessageRepository;
import com.server.domain.user.User;
import com.server.domain.user.repository.OnboardingRepository;
import com.server.domain.user.repository.UserRepository;
import com.server.service.message.dto.response.ChatInfoResponse;
import com.server.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MessageRetrieveService {

    private final UserRepository userRepository;
    private final OnboardingRepository onboardingRepository;
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    public List<ChatInfoResponse> retrieveChats(Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        return chatRepository.findChatsByOnboarding(user.getOnboarding()).stream()
                .map(chat -> ChatInfoResponse.of(chat, onboardingRepository.findOnboardingById(chat.getOpponentId()), messageRepository.findRecentMessage(chat)))
                .collect(Collectors.toList());
    }
}
