package com.server.domain.message.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.domain.chat.Chat;
import com.server.domain.help.Help;
import com.server.domain.message.Message;
import com.server.domain.message.MessageType;
import com.server.domain.user.Onboarding;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
                        message.type.in(MessageType.REQUEST_HELP, MessageType.ACCEPT_HELP, MessageType.PENDING_HELP)
                ).fetch().size() > 0;
    }

    @Override
    public Message findRecentMessage(Chat chat) {
        List<Message> messages = queryFactory.selectFrom(message).distinct()
                .where(
                        message.chat.eq(chat)
                )
                .orderBy(message.createdAt.desc())
                .fetch();
        return messages.get(0);
    }

    @Override
    public List<Message> findMessagesByChat(Chat chat) {
        return queryFactory.selectFrom(message).distinct()
                .where(message.chat.eq(chat))
                .orderBy(message.createdAt.asc())
                .fetch();
    }

    @Override
    public Message findMessageById(Long messageId) {
        return queryFactory.selectFrom(message).distinct()
                .where(message.id.eq(messageId))
                .fetchOne();
    }

    @Override
    public Message findMessageByChatAndHelpAndOnboarding(Chat chat, Help help, Onboarding onboarding) {
        return queryFactory.selectFrom(message).distinct()
                .where(
                        message.chat.ne(chat),
                        message.help.eq(help),
                        message.sender.eq(onboarding)
                )
                .fetchOne();
    }

    @Override
    public Message findMessageByChatAndHelpAndOnboardingAndType(Chat chat, Help help, Onboarding onboarding, MessageType type) {
        return queryFactory.selectFrom(message).distinct()
                .where(
                        message.chat.ne(chat),
                        message.help.eq(help),
                        message.sender.eq(onboarding),
                        message.type.eq(type)
                )
                .fetchOne();
    }
}
