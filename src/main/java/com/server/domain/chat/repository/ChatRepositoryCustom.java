package com.server.domain.chat.repository;

import com.server.domain.chat.Chat;
import com.server.domain.user.Onboarding;

import java.util.List;

public interface ChatRepositoryCustom {

    Chat findByOnboardingAndOpponentId(Onboarding onboarding, Long opponentId);

    List<Chat> findChatsByOnboarding(Onboarding onboarding);
}
