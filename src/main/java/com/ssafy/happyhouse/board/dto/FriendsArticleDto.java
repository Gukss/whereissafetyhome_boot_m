package com.ssafy.happyhouse.board.dto;

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
public class FriendsArticleDto {
	private int friendsArticleNo;
	private String memberNo;
	private String memberName;
	private String title;
	private String content;
	private int views;
	private String registerTime;
}
