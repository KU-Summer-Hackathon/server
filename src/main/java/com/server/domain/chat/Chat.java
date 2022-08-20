package com.server.domain.chat;

import com.server.domain.common.AuditingTimeEntity;
import com.server.domain.message.Message;
import com.server.domain.user.Onboarding;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "onboarding_id")
    private Onboarding onboarding;

    @Column(nullable = false)
    private Long opponentId;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Message> messages = new ArrayList<>();

    @Column(nullable = false)
    private boolean isRead;

    @Builder(access = AccessLevel.PACKAGE)
    public Chat(Onboarding onboarding, Long opponentId, boolean isRead) {
        this.onboarding = onboarding;
        this.opponentId = opponentId;
        this.isRead = isRead;
    }

    public static Chat of(Onboarding onboarding, Long opponentId, boolean isRead) {
        return Chat.builder()
                .onboarding(onboarding)
                .opponentId(opponentId)
                .isRead(isRead)
                .build();
    }

    public void updateToRead() {
        this.isRead = true;
    }

    public void updateToUnRead() {
        this.isRead = false;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void updateMessage(Message message) {
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getId().equals(message.getId())) {
                this.messages.set(i, message);
            }
        }
    }

    public void deleteMessage(Message message) {
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getId().equals(message.getId())) {
                this.messages.remove(i);
            }
        }
    }
}
