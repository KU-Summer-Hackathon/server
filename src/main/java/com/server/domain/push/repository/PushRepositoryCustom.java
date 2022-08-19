package com.server.domain.push.repository;

import com.server.domain.help.Help;
import com.server.domain.push.Push;
import com.server.domain.user.Onboarding;

import java.util.List;

public interface PushRepositoryCustom {

    List<Push> findPushesByOnboarding(Onboarding onboarding);

    boolean helpIsApplied(Long senderId, Help help);
}
