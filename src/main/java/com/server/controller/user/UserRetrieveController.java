package com.server.controller.user;

import com.server.common.dto.ErrorResponse;
import com.server.common.dto.SuccessResponse;
import com.server.common.success.SuccessCode;
import com.server.config.interceptor.Auth;
import com.server.config.resolver.UserId;
import com.server.service.user.UserRetreiveService;
import com.server.service.user.dto.response.GetUserInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@Api(tags = "User")
@RequiredArgsConstructor
@RestController
public class UserRetrieveController {

    private final UserRetreiveService userRetreiveService;

    @ApiOperation("User - 유저 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User - 유저 조회 성공입니다."),
            @ApiResponse(code = 401, message = "유효하지 않은 토큰입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @GetMapping("/v1/user")
    public SuccessResponse<GetUserInfoResponse> retrieveUserInfo(@ApiIgnore @UserId Long userId) {
        return SuccessResponse.success(SuccessCode.GET_USER_SUCCESS, userRetreiveService.retrieveUserInfo(userId));
    }
}


