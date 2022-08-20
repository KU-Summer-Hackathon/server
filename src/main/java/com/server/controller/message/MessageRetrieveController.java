package com.server.controller.message;

import com.server.common.dto.ErrorResponse;
import com.server.common.dto.SuccessResponse;
import com.server.common.success.SuccessCode;
import com.server.config.interceptor.Auth;
import com.server.config.resolver.UserId;
import com.server.service.message.MessageRetrieveService;
import com.server.service.message.dto.response.ChatInfoResponse;
import com.server.service.message.dto.response.MessageInfoResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "Message")
@RequiredArgsConstructor
@RestController
public class MessageRetrieveController {

    private final MessageRetrieveService messageRetrieveService;

    @ApiOperation("Message - 채팅 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Message - 채팅 목록 조회 성공입니다."),
            @ApiResponse(code = 401, message = "유효하지 않은 토큰입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @GetMapping("/v1/chats")
    public SuccessResponse<List<ChatInfoResponse>> retrieveChats(@ApiIgnore @UserId Long userId) {
        return SuccessResponse.success(SuccessCode.GET_CHATS_SUCCESS, messageRetrieveService.retrieveChats(userId));
    }

    @ApiOperation("Message - 채팅 내용을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Message - 채팅 내용 조회 성공입니다."),
            @ApiResponse(code = 401, message = "유효하지 않은 토큰입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @GetMapping("/v1/chat/{chatId}/messages")
    public SuccessResponse<List<MessageInfoResponse>> retrieveMessages(@ApiParam(name = "chatId", value = "조회할 Chat id", required = true)
                                                                       @PathVariable Long chatId) {
        return SuccessResponse.success(SuccessCode.GET_MESSAGES_SUCCESS, messageRetrieveService.retrieveMessages(chatId));
    }
}
