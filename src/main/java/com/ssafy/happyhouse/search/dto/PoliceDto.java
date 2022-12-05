package com.ssafy.happyhouse.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PoliceDto {
	private String name;
	private String department;
	private double lat;
	private double lng;
}
