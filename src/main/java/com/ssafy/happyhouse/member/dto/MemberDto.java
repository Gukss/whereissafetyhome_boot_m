package com.ssafy.happyhouse.member.dto;

import io.swagger.annotations.ApiModelProperty;
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
public class MemberDto {
	@ApiModelProperty(example = "1L")
	private Long member_no;
	@ApiModelProperty(example = "spring")
	private String id;
	@ApiModelProperty(example = "springpw")
	private String pw;
	@ApiModelProperty(example = "스프링")
	private String name;
	@ApiModelProperty(example = "spring@ssafy.com")
	private String email;
	@ApiModelProperty(example = "010-1234-1234")
	private String phone;

	public MemberDto(String id, String pw, String name, String email, String phone) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
}
