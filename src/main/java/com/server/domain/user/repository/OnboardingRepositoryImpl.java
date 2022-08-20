package com.server.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.domain.user.Onboarding;
import lombok.RequiredArgsConstructor;

import static com.server.domain.user.QOnboarding.onboarding;

@RequiredArgsConstructor
public class OnboardingRepositoryImpl implements OnboardingRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Onboarding findOnboardingById(Long onboardingId) {
        return queryFactory.selectFrom(onboarding).distinct()
                .where(onboarding.id.eq(onboardingId))
                .fetchOne();
    }
}
