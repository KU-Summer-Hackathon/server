package com.server.controller.message;

import com.server.common.dto.ErrorResponse;
import com.server.common.dto.SuccessResponse;
import com.server.config.interceptor.Auth;
import com.server.config.resolver.UserId;
import com.server.service.message.MessageService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Message")
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @ApiOperation("Message - 돕기 요청을 수락합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Message - 돕기 수락 성공입니다."),
            @ApiResponse(code = 401, message = "유효하지 않은 토큰입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @PostMapping("/v1/message/{messageId}/help/accept")
    public SuccessResponse<String> acceptHelp(@ApiParam(name = "messageId", value = "수락할 Message id", required = true)
                                              @PathVariable Long messageId,
                                              @ApiIgnore @UserId Long userId) {
        messageService.acceptHelp(messageId, userId);
        return SuccessResponse.SUCCESS;
    }

    @ApiOperation("Message - 도움을 완료합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Message - 도움 완료 성공입니다."),
            @ApiResponse(code = 401, message = "유효하지 않은 토큰입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @PostMapping("/v1/message/{messageId}/help/complete")
    public SuccessResponse<String> completeHelp(@ApiParam(name = "messageId", value = "완료할 Message id", required = true)
                                                @PathVariable Long messageId,
                                                @ApiIgnore @UserId Long userId) {
        messageService.completeHelp(messageId, userId);
        return SuccessResponse.SUCCESS;
    }
}
