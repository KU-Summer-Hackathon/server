package com.server.domain.message;

import com.server.domain.chat.Chat;
import com.server.domain.common.AuditingTimeEntity;
import com.server.domain.help.Help;
import com.server.domain.user.Onboarding;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "onboarding_id")
    private Onboarding sender;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "help_id")
    private Help help;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Column(nullable = false, length = 100)
    private String content;

    @Column(nullable = false)
    private boolean isRead;

    @Builder(access = AccessLevel.PACKAGE)
    public Message(Chat chat, Onboarding sender, Help help, MessageType type, String content, boolean isRead) {
        this.chat = chat;
        this.sender = sender;
        this.help = help;
        this.type = type;
        this.content = content;
        this.isRead = isRead;
    }

    public static Message of(Chat chat, Onboarding sender, Help help, MessageType type, String content, boolean isRead) {
        return Message.builder()
                .chat(chat)
                .sender(sender)
                .help(help)
                .type(type)
                .content(content)
                .isRead(isRead)
                .build();
    }
}
