package com.server.domain.user.repository;

import com.server.domain.user.Onboarding;

public interface OnboardingRepositoryCustom {

    Onboarding findOnboardingById(Long onboardingId);
}
