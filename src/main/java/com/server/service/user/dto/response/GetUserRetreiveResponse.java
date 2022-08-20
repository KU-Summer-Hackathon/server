package com.server.service.user.dto.response;

import com.server.domain.user.GenderType;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class GetUserRetreiveResponse {

    private String name;

    private GenderType gender;

    private int age;

    private String address;

    private String imageUrl;

    private UserSubInfoResponse userSubInfoResponse;

    public static GetUserRetreiveResponse of(String name, GenderType gender, int age, String address, String imageUrl, UserSubInfoResponse userSubInfoResponse) {
        return GetUserRetreiveResponse.builder()
                .name(name)
                .gender(gender)
                .age(age)
                .address(address)
                .imageUrl(imageUrl)
                .userSubInfoResponse(userSubInfoResponse)
                .build();
    }
}
