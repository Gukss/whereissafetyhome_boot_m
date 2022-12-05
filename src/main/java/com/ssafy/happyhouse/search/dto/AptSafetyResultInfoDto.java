package com.ssafy.happyhouse.search.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

//테이블에 정보 뿌려줄 dto
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AptSafetyResultInfoDto {
    @ApiModelProperty(notes = "시도이름", example = "서울특별시", required = false)
    private String sidoName; //시도 이름
    @ApiModelProperty(notes = "구군 이름", example = "종로구", required = false)
    private String gugunName; //구군 이름
    @ApiModelProperty(notes = "동 이름", example = "사직동", required = false)
    private String dongName; //동 이름
    @ApiModelProperty(notes = "아파트 이름", example = "String", required = false)
    private String aptName; //아파트 이름
    @ApiModelProperty(notes = "위도", example = "String", required = false)
    private String lat; //위도
    @ApiModelProperty(notes = "경도", example = "String", required = false)
    private String lng; //경도
    @ApiModelProperty(notes = "거래금액", example = "String", required = false)
    private String dealAmount; //거래 금액
    @ApiModelProperty(notes = "면적", example = "String", required = false)
    private String area; //면적
    @ApiModelProperty(notes = "층", example = "String", required = false)
    private String floor; //층
    @ApiModelProperty(notes = "도로명", example = "String", required = false)
    private String roadName;//도로명
    @ApiModelProperty(notes = "도로명 주소 본번", example = "String", required = false)
    private String roadNameBonbun; //도로명 주소 본번
    @ApiModelProperty(notes = "도로명 주소 부번", example = "String", required = false)
    private String roadNameBubun; //부번
    @ApiModelProperty
    private double lampCount;
    @ApiModelProperty
    private double CCTVCount;
    @ApiModelProperty
    private double policeCount;

}
