package com.server.domain.chat.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.domain.chat.Chat;
import com.server.domain.user.Onboarding;
import lombok.RequiredArgsConstructor;

import static com.server.domain.chat.QChat.chat;

@RequiredArgsConstructor
public class ChatRepositoryImpl implements ChatRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Chat findByOnboardingAndOpponentId(Onboarding onboarding, Long opponentId) {
        return queryFactory.selectFrom(chat).distinct()
                .where(
                        chat.onboarding.eq(onboarding),
                        chat.opponentId.eq(opponentId)
                ).fetchOne();
    }
}
