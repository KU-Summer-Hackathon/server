package com.server.domain.help.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.domain.help.Help;
import com.server.domain.user.Onboarding;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.server.domain.help.QHelp.help;

@RequiredArgsConstructor
public class HelpRepositoryImpl implements HelpRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Help> findOtherHelps(Onboarding onboarding) {
        return queryFactory
                .selectFrom(help).distinct()
                .where(
                        help.onboarding.ne(onboarding)
                )
                .fetch();
    }
}
