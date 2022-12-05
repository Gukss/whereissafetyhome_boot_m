package com.ssafy.happyhouse.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.board.dto.FriendsArticleDto;
import com.ssafy.happyhouse.board.service.FriendsBoardService;
import com.ssafy.happyhouse.util.PageNavigation;

@RestController
@RequestMapping("/friends")
@ControllerAdvice
public class FriendsBoardController {

	private final Logger logger = LoggerFactory.getLogger(FriendsBoardController.class);

	private FriendsBoardService service;
	
	@Autowired
	public FriendsBoardController(FriendsBoardService friendsBoardService) {
		this.service = friendsBoardService;
	}
	
	/**
	 * 게시글을 작성한다.
	 * 게시글 Dto를 받아서 데이터베이스에 저장한다.
	 *
	 * @param friendsArticleDto
	 * @return 
	 */
	@PostMapping
	public ResponseEntity<?> insertArticle(@RequestBody FriendsArticleDto friendsArticleDto) throws Exception{
		service.writeArticle(friendsArticleDto);
		
		return new ResponseEntity<Object> (HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> listArticle(@RequestParam Map<String, String> map) throws Exception{
		logger.debug("list parameter : {}", map);
		Map<String, Object> resmap = new HashMap<>();
		System.out.println("controller");
		List<FriendsArticleDto> list = service.listArticle(map);
		PageNavigation pageNavigation = service.makePageNavigation(map);
		resmap.put("articles", list);
		resmap.put("navigation", pageNavigation);
		resmap.put("pgno", map.get("pgno"));
		resmap.put("key", map.get("key"));
		resmap.put("word", map.get("word"));
		
		return new ResponseEntity<Map<String, Object>> (resmap, HttpStatus.OK);
		
	}
	
	@GetMapping("{articleNo}")
	public ResponseEntity<?> getArticle(@PathVariable int articleNo) throws Exception{
		ResponseEntity<?> res;
		
		service.updateViews(articleNo);
		FriendsArticleDto friendsArticleDto = service.getArticle(articleNo);
		
		if(friendsArticleDto != null) {
			res = new ResponseEntity<FriendsArticleDto>(friendsArticleDto, HttpStatus.OK);
		}else {
			res = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		return res;
	}
	
	@PutMapping("{articleNo}")
	public ResponseEntity<?> modifyArticle(@PathVariable int articleNo, @RequestBody FriendsArticleDto friendsArticleDto) throws Exception{
		ResponseEntity<?> res;
		
		friendsArticleDto.setFriendsArticleNo(articleNo);
		int modify = service.modifyArticle(friendsArticleDto);
		
		if(modify == 1) {
			res = new ResponseEntity<FriendsArticleDto>(friendsArticleDto, HttpStatus.NO_CONTENT);
		}else {
			res = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		return res;
	}
	
	@DeleteMapping("{articleNo}")
	public ResponseEntity<?> deleteArticle(@PathVariable int articleNo) throws Exception{
		ResponseEntity<?> res;
		
		int delete = service.deleteArticle(articleNo);
		if(delete == 1) {
			res = new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			res = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		return res;
	}
}
