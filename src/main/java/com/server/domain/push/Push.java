package com.server.domain.push;

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
public class Push extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "onboarding_id")
    private Onboarding onboarding;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "help_id")
    private Help help;

    @Column(nullable = false)
    private Long senderId;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private PushType type;

    @Column(nullable = false, length = 100)
    private String content;

    @Column(nullable = false)
    private boolean isRead;

    @Column(nullable = false)
    private boolean isReplied;

    @Builder(access = AccessLevel.PACKAGE)
    public Push(Onboarding onboarding, Help help, Long senderId, PushType type, String content, boolean isRead, boolean isReplied) {
        this.onboarding = onboarding;
        this.help = help;
        this.senderId = senderId;
        this.type = type;
        this.content = content;
        this.isRead = isRead;
        this.isReplied = isReplied;
    }

    public static Push of(Onboarding onboarding, Help help, Long senderId, PushType type, String content, boolean isRead, boolean isReplied) {
        return Push.builder()
                .onboarding(onboarding)
                .help(help)
                .senderId(senderId)
                .type(type)
                .content(content)
                .isRead(isRead)
                .isReplied(isReplied)
                .build();
    }
}
