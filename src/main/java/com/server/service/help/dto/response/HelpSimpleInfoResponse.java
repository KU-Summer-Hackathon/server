package com.server.service.help.dto.response;

import com.server.domain.help.Help;
import com.server.domain.user.Onboarding;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class HelpSimpleInfoResponse {

    private Long helpId;

    private String userImageUrl;

    private String userName;

    private String content;

    public static HelpSimpleInfoResponse of(Onboarding onboarding, Help help) {
        return HelpSimpleInfoResponse.builder()
                .helpId(help.getId())
                .userImageUrl(onboarding.getImageUrl())
                .userName(onboarding.getName())
                .content(help.getContent())
                .build();
    }
}
