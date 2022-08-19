package com.server.service.auth.dto.request;

import com.server.domain.user.UserSocialType;
import com.server.service.user.dto.request.CreateUserDto;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginDto {

    private UserSocialType socialType;

    private String token;

    private String fcmToken;

    public static LoginDto of(UserSocialType socialType, String token, String fcmToken) {
        return new LoginDto(socialType, token, fcmToken);
    }

    public CreateUserDto toCreateUserDto(String socialId) {
        return CreateUserDto.of(socialId, socialType, fcmToken);
    }
}
