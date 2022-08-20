package com.server.controller.help;

import com.server.common.dto.ErrorResponse;
import com.server.common.dto.SuccessResponse;
import com.server.config.interceptor.Auth;
import com.server.config.resolver.UserId;
import com.server.service.help.HelpService;
import com.server.service.help.dto.request.CreateHelpRequestDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "Help")
@RestController
@RequiredArgsConstructor
public class HelpController {

    private final HelpService helpService;

    @ApiOperation("Help - 도움을 요청합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Help - 도움 요청 성공입니다."),
            @ApiResponse(code = 401, message = "유효하지 않은 토큰입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @PostMapping("/v1/help")
    public SuccessResponse<String> createHelp(CreateHelpRequestDto request,
                                              @ApiParam(name = "images", value = "도움 요청 이미지 파일", required = true)
                                              @RequestPart List<MultipartFile> images,
                                              @ApiIgnore @UserId Long userId) {
        helpService.createHelp(request, images, userId);
        return SuccessResponse.SUCCESS;
    }

    @ApiOperation("Help - 돕기를 신청합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Help - 돕기 신청 성공입니다."),
            @ApiResponse(code = 401, message = "유효하지 않은 토큰입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @PostMapping("/v1/help/{helpId}")
    public SuccessResponse<String> applyHelp(@ApiParam(name = "helpId", value = "돕기 신청할 Help id", required = true)
                                             @PathVariable Long helpId,
                                             @ApiIgnore @UserId Long userId) {
        helpService.applyHelp(helpId, userId);
        return SuccessResponse.SUCCESS;
    }
}
