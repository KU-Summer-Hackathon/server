package com.server.common.success;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.server.common.success.SuccessStatusCode.OK;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    /**
     * 200 OK
     */
    SUCCESS(OK, "성공입니다."),

    // 인증
    LOGIN_SUCCESS(OK, "로그인 성공입니다."),
    REISSUE_TOKEN_SUCCESS(OK, "토큰 갱신 성공입니다."),

    GET_USER_SUCCESS(OK, "User - 유저 조회 성공입니다."),
    GET_HELPS_SUCCESS(OK, "Help - 도움 조회 성공입니다."),
    GET_HELP_SUCCESS(OK, "Help - 돕기 신청할 도움 조회 성공입니다."),
    GET_PUSHES_SUCCESS(OK, "Messages - 알림 조회 성공입니다."),
    GET_PUSH_SUCCESS(OK, "Messages - 알림 상세 조회 성공입니다."),
    GET_CHATS_SUCCESS(OK, "Message - 채팅 목록 조회 성공입니다."),
    GET_MESSAGES_SUCCESS(OK, "Message - 채팅 내용 조회 성공입니다."),
    
    /**
     * 201 CREATED
     */

    /**
     * 202 ACCEPTED
     */

    /**
     * 204 NO_CONTENT
     */
    ;

    private final SuccessStatusCode statusCode;
    private final String message;

    public int getStatus() {
        return statusCode.getStatus();
    }
}
