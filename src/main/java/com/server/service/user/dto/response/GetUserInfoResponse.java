package com.server.service.user.dto.response;

import com.server.domain.user.GenderType;
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

    public static GetUserInfoResponse of(String name, GenderType gender, int age, String address, String imageUrl, UserSubInfoResponse userSubInfoResponse) {
        return GetUserInfoResponse.builder()
                .name(name)
                .gender(gender)
                .age(age)
                .address(address)
                .imageUrl(imageUrl)
                .userSubInfoResponse(userSubInfoResponse)
                .build();
    }
}
