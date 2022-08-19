package com.server.controller.help;

import com.server.common.dto.ErrorResponse;
import com.server.common.dto.SuccessResponse;
import com.server.common.success.SuccessCode;
import com.server.config.interceptor.Auth;
import com.server.config.resolver.UserId;
import com.server.service.help.HelpRetrieveService;
import com.server.service.help.dto.response.GetHelpsResponse;
import com.server.service.help.dto.response.HelpSimpleInfoResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Help")
@RequiredArgsConstructor
@RestController
public class HelpRetrieveController {

    private final HelpRetrieveService helpRetrieveService;

    @ApiOperation("Help - 도움을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Help - 도움 조회 성공입니다."),
            @ApiResponse(code = 401, message = "유효하지 않은 토큰입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @GetMapping("/v1/help")
    public SuccessResponse<GetHelpsResponse> retrieveHelp(@ApiIgnore @UserId Long userId) {
        return SuccessResponse.success(SuccessCode.GET_HELPS_SUCCESS, helpRetrieveService.retrieveHelp(userId));
    }

    @ApiOperation("Help - 돕기 신청할 도움을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Help - 돕기 신청할 도움 조회 성공입니다."),
            @ApiResponse(code = 401, message = "유효하지 않은 토큰입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @GetMapping("/v1/help/{helpId}")
    public SuccessResponse<HelpSimpleInfoResponse> retrieveHelpById(@ApiParam(name = "helpId", value = "돕기 신청할 Help id", required = true)
                                                                    @PathVariable Long helpId) {
        return SuccessResponse.success(SuccessCode.GET_HELP_SUCCESS, helpRetrieveService.retrieveHelpById(helpId));
    }
}
