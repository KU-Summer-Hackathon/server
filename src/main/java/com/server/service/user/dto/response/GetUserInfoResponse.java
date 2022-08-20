package com.server.service.user.dto.response;

import com.server.domain.user.GenderType;
import com.server.domain.user.Onboarding;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class GetUserInfoResponse {

    private String name;

    private GenderType gender;

    private int age;

    private String address;

    private String imageUrl;

    private UserSubInfoResponse userSubInfoResponse;

    public static GetUserInfoResponse of(Onboarding onboarding, UserSubInfoResponse userSubInfoResponse) {
        return GetUserInfoResponse.builder()
                .name(onboarding.getName())
                .gender(onboarding.getGender())
                .age(onboarding.getAge())
                .address(onboarding.getAddress())
                .imageUrl(onboarding.getImageUrl())
                .userSubInfoResponse(userSubInfoResponse)
                .build();
    }
}
