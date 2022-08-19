package com.server.service.help.dto.response;

import com.server.domain.help.Help;
import com.server.domain.help.HelpImage;
import com.server.domain.user.Onboarding;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class HelpInfoResponse {

    private String userImageUrl;

    private String userName;

    private String content;

    private List<String> helpImageUrls;

    public static HelpInfoResponse of(Onboarding onboarding, Help help) {
        return HelpInfoResponse.builder()
                .userImageUrl(onboarding.getImageUrl())
                .userName(onboarding.getName())
                .content(help.getContent())
                .helpImageUrls(help.getHelpImages().stream()
                        .map(HelpImage::getImageUrl)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
