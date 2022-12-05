package com.ssafy.happyhouse.board.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.board.dto.FriendsArticleDto;
import com.ssafy.happyhouse.util.PageNavigation;

public interface FriendsBoardService {

	int writeArticle(FriendsArticleDto friendsArticle) throws Exception;
	List<FriendsArticleDto> listArticle(Map<String, String> map) throws Exception;
	FriendsArticleDto getArticle(int articleNo) throws Exception;
	void updateViews(int articleNo) throws Exception;
	int modifyArticle(FriendsArticleDto friendsArticle) throws Exception;
	int deleteArticle(int articleNo) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	
}
