package com.server.domain.push.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.domain.help.Help;
import lombok.RequiredArgsConstructor;

import static com.server.domain.push.QPush.push;

@RequiredArgsConstructor
public class PushRepositoryImpl implements PushRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean helpIsApplied(Long senderId, Help help) {
        return queryFactory.selectFrom(push)
                .where(
                        push.senderId.eq(senderId),
                        push.help.eq(help)
                ).fetchOne() != null;
    }
}
