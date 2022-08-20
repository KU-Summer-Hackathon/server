package com.server.service.user.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateLampDto {

    @ApiModelProperty(value = "구매 Lamp 개수", example = "5")
    private int lampCnt;
}
