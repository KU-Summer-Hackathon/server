package com.server.service.message.dto.response;

import com.server.domain.chat.Chat;
import com.server.domain.message.Message;
import com.server.domain.user.Onboarding;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ChatInfoResponse {

    private Long chatId;

    private String userImageUrl;

    private String userName;

    private String content;

    private boolean isRead;

    public static ChatInfoResponse of(Chat chat, Onboarding opponent, Message message) {
        return ChatInfoResponse.builder()
                .chatId(chat.getId())
                .userImageUrl(opponent.getImageUrl())
                .userName(opponent.getName())
                .content(message.getContent())
                .isRead(chat.isRead())
                .build();
    }
}
