package com.ssafy.happyhouse.search.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

//관심지역의 아파트 정보
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InterestAptInfoDto {
    @ApiModelProperty(notes = "아파트 이름", example = "String", required = false)
    private String aptName;//아파트 이름
    @ApiModelProperty(notes = "아파트 크기", example = "String", required = false)
    private String aptArea;//아파트 크기
    @ApiModelProperty(notes = "아파트 층", example = "String", required = false)
    private String aptFloor;//아파트 층
    @ApiModelProperty(notes = "아파트 가격", example = "String", required = false)
    private String aptPrice;//아파트 가격
    @ApiModelProperty(notes = "아파트 위도", example = "String", required = false)
    private String aptLat;//아파트 위도
    @ApiModelProperty(notes = "아파트 경도", example = "String", required = false)
    private String aptLng;//아파트 경도
}
