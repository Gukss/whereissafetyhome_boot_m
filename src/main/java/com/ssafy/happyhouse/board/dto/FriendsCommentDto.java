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
public class FriendsCommentDto {
	private int friendsBoardCommentNo;
	private String memberNo;
	private String memberName;
	private String commentText;
	private String registerTime;
	private int friendsArticleNo;
}
