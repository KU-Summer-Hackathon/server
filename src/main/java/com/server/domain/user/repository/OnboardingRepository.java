package com.server.domain.user.repository;

import com.server.domain.user.Onboarding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnboardingRepository extends JpaRepository<Onboarding, Long>, OnboardingRepositoryCustom {
}
