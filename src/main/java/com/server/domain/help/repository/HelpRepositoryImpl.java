package com.server.domain.help.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HelpRepositoryImpl implements HelpRepositoryCustom {

    private final JPAQueryFactory queryFactory;
}
