package com.server.controller.user;


import com.server.common.dto.ErrorResponse;
import com.server.common.dto.SuccessResponse;
import com.server.config.interceptor.Auth;
import com.server.config.resolver.UserId;
import com.server.service.user.UserService;
import com.server.service.user.dto.request.UpdateLampDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "User")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("User - 램프를 구매합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User - Lamp 구매 성공입니다."),
            @ApiResponse(code = 401, message = "유효하지 않은 토큰입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @PutMapping("/v1/user/lamp")
    public SuccessResponse<String> updateLamp(@RequestBody UpdateLampDto request,
                                              @ApiIgnore @UserId Long userId) {
        userService.updateLamp(request, userId);
        return SuccessResponse.SUCCESS;
    }
}
