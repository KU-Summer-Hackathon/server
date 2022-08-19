package com.server.service.help.dto.request;

import com.server.domain.help.Help;
import com.server.domain.user.Onboarding;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateHelpRequestDto {

    private String content;

    public Help toEntity(Onboarding onboarding) {
        return Help.of(onboarding, content);
    }
}
