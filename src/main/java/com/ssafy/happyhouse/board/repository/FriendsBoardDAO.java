package com.ssafy.happyhouse.board.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.board.dto.FriendsArticleDto;

@Mapper
public interface FriendsBoardDAO {

	int writeArticle(FriendsArticleDto friendsArticle) throws Exception;
	List<FriendsArticleDto> listArticle(Map<String, Object> param) throws Exception;
	FriendsArticleDto getArticle(int articleNo) throws Exception;
	void updateViews(int articleNo) throws Exception;
	int modifyArticle(FriendsArticleDto friendsArticle) throws Exception;
	int deleteArticle(int articleNo) throws Exception;
	int getTotalArticleCount(Map<String, Object> param);
}
