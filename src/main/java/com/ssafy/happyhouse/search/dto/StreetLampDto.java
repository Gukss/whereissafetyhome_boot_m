package com.ssafy.happyhouse.search.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StreetLampDto {
    private String mng_no;
    private double lat;
    private double lng;
}
