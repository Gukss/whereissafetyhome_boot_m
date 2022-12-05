package com.ssafy.happyhouse.search.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

//관심지역 리스트 출력할 DTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InterestDto {
    @ApiModelProperty(notes = "관심지역 id", example = "1", required = false)
    private int interest_area_no;
    @ApiModelProperty(notes = "멤버 id", example = "1", required = false)
    private int member_no;
    @ApiModelProperty(notes = "시도 이름", example = "서울특별시", required = false)
    private String sidoName;
    @ApiModelProperty(notes = "구군 이름", example = "종로구", required = false)
    private String gugunName;
    @ApiModelProperty(notes = "동 이름", example = "사상구", required = false)
    private String dongName;
}

