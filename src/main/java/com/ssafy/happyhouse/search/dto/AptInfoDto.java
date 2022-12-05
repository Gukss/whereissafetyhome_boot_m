package com.ssafy.happyhouse.search.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

//아파트 정보 거래시에 사용
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value="AptInfoDto: 아파트 정보", description = "아파트 정보 거래시에 사용")
public class AptInfoDto {
    @ApiModelProperty(value = "시도 이름")
    private String sido;

    @ApiModelProperty(value = "구군 이름")
    private String gugun;

    @ApiModelProperty(value = "동 이름")
    private String dong;

    @ApiModelProperty(value = "조회할 연도")
    private String year;

    @ApiModelProperty(value = "조회할 월")
    private String month;
}