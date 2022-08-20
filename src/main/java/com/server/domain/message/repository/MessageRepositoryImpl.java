package com.server.domain.message.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.domain.chat.Chat;
import com.server.domain.help.Help;
import com.server.domain.message.Message;
import com.server.domain.message.MessageType;
import com.server.domain.user.Onboarding;
import lombok.RequiredArgsConstructor;

import static com.server.domain.message.QMessage.message;

@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isRequested(Onboarding sender, Help help) {
        return queryFactory.selectFrom(message).distinct()
                .where(
                        message.sender.eq(sender),
                        message.help.eq(help),
                        message.type.in(MessageType.REQUEST_HELP, MessageType.ACCEPT_HELP, MessageType.REJECT_HELP)
                ).fetchOne() != null;
    }

    @Override
    public Message findRecentMessage(Chat chat) {
        return queryFactory.selectFrom(message).distinct()
                .where(
                        message.chat.eq(chat)
                )
                .orderBy(message.createdAt.desc())
                .fetchOne();
    }
}
