package com.ssafy.happyhouse.board.repository;

import com.ssafy.happyhouse.board.dto.FriendsCommentDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FriendsCommentDAO {
	int writeComment(FriendsCommentDto friendsCommentDto);
	List<FriendsCommentDto> listComment(int friendsArticleNo);
	void modifyComment(FriendsCommentDto friendsCommentDto);
	void deleteComment(int friendsBoardCommentNo);
}
