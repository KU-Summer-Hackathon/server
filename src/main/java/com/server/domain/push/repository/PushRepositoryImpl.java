package com.server.domain.push.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.domain.help.Help;
import com.server.domain.user.Onboarding;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.server.domain.help.QHelp.help;

@RequiredArgsConstructor
public class PushRepositoryImpl implements PushRepositoryCustom {

    private final JPAQueryFactory queryFactory;
}
