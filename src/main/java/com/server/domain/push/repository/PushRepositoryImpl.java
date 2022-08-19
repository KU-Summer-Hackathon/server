package com.server.domain.push.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.domain.help.Help;
import com.server.domain.push.Push;
import com.server.domain.user.Onboarding;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.server.domain.push.QPush.push;

@RequiredArgsConstructor
public class PushRepositoryImpl implements PushRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Push> findPushesByOnboarding(Onboarding onboarding) {
        return queryFactory.selectFrom(push)
                .where(
                        push.onboarding.eq(onboarding)
                )
                .orderBy(push.createdAt.desc())
                .fetch();
    }

    @Override
    public boolean helpIsApplied(Long senderId, Help help) {
        return queryFactory.selectFrom(push)
                .where(
                        push.senderId.eq(senderId),
                        push.help.eq(help)
                ).fetchOne() != null;
    }
}
