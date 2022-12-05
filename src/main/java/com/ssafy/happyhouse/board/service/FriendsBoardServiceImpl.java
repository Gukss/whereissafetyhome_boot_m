package com.ssafy.happyhouse.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.board.dto.FriendsArticleDto;
import com.ssafy.happyhouse.board.repository.FriendsBoardDAO;
import com.ssafy.happyhouse.util.PageNavigation;
import com.ssafy.happyhouse.util.SizeConstant;

@Service
public class FriendsBoardServiceImpl implements FriendsBoardService {
	
	private FriendsBoardDAO dao;
	
	@Autowired
	public FriendsBoardServiceImpl(FriendsBoardDAO friendsBoardDAO) {
		this.dao = friendsBoardDAO;
	}

	@Override
	public int writeArticle(FriendsArticleDto friendsArticleDto) throws Exception {
		return dao.writeArticle(friendsArticleDto);
	}

	@Override
	public List<FriendsArticleDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		System.out.println("service");
		return dao.listArticle(param);
	}

	@Override
	public FriendsArticleDto getArticle(int articleNo) throws Exception {
		FriendsArticleDto modifyDto = dao.getArticle(articleNo);
		int modifyViews = modifyDto.getViews() + 1;
		modifyDto.setViews(modifyViews);
		this.modifyArticle(modifyDto);
		return modifyDto;
	}

	@Override
	public void updateViews(int articleNo) throws Exception {
		dao.updateViews(articleNo);
	}

	@Override
	public int modifyArticle(FriendsArticleDto friendsArticleDto) throws Exception {
		return dao.modifyArticle(friendsArticleDto);
	}

	@Override
	public int deleteArticle(int articleNo) throws Exception {
		return dao.deleteArticle(articleNo);
	}
	
	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if ("memberid".equals(key))
			key = "memberid";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		System.out.println(param);
		int totalCount = dao.getTotalArticleCount(param);
		System.out.println("데이터 갯수 " + totalCount);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

}
