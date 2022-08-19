package com.server.service.push.dto.response;

import com.server.domain.push.Push;
import com.server.domain.user.Onboarding;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class PushInfoResponse {

    private Long pushId;

    private String senderImageUrl;

    private String senderName;

    private String content;

    private boolean isRead;

    private boolean isReplied;

    public static PushInfoResponse of(Onboarding onboarding, Push push) {
        return PushInfoResponse.builder()
                .pushId(push.getId())
                .senderImageUrl(onboarding.getImageUrl())
                .senderName(onboarding.getName())
                .content(push.getContent())
                .isRead(push.isRead())
                .isReplied(push.isReplied())
                .build();
    }
}
