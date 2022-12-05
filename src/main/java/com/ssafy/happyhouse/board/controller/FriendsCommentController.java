package com.ssafy.happyhouse.board.controller;

import com.ssafy.happyhouse.board.dto.FriendsArticleDto;
import com.ssafy.happyhouse.board.dto.FriendsCommentDto;
import com.ssafy.happyhouse.board.service.FriendsBoardService;
import com.ssafy.happyhouse.board.service.FriendsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@ControllerAdvice
public class FriendsCommentController {

	private FriendsCommentService service;

	@Autowired
	public FriendsCommentController(FriendsCommentService friendsCommentService) {
		this.service = friendsCommentService;
	}

	/**
	 * friendsArticleNo에 해당하는 게시글에 댓글 추가하는 메소드
	 *
	 * @param friendsArticleNo friends_board 테이블의 pk, friends_board_comment 테이블의 fk
	 * @param friendsCommentDto memberNo, memberName, commentText, registerTime은 프론트에서 담아서, friendsArticleNo은 이 메소드에서 set해주고, friendsBoardCommentNo는 없이 input받는다.
	 * @return
	 */
	@PostMapping("{friendsArticleNo}")
	public ResponseEntity<?> insertComment(@PathVariable int friendsArticleNo, @RequestBody FriendsCommentDto friendsCommentDto){
		friendsCommentDto.setFriendsArticleNo(friendsArticleNo);
		service.writeComment(friendsCommentDto);
		//todo 예외처리: 무조건 created return 하지말고 예외처리해주기
		return new ResponseEntity<Object> (HttpStatus.CREATED);
		
	}

	/**
	 * friendsArticleNo에 해당하는 게시글의 댓글을 모두 리스트로 반환한다.
	 *
	 * @param friendsArticleNo
	 * @return friendsArticleNo에 해당하는 게시글의 댓글을 모두 리스트로 반환한다. friendsBoardCommtnNo, memberNo, memberName, commentText, registerTime, reiendsArticleNo
	 */
	@GetMapping("{friendsArticleNo}")
	public ResponseEntity<?> listComment(@PathVariable int friendsArticleNo){
		return new ResponseEntity<List<FriendsCommentDto>> (service.listComment(friendsArticleNo), HttpStatus.OK);
	}

	/**
	 * 댓글을 수정한다.
	 *
	 * @param friendsBoardCommentNo friends_board_comment 테이블의 pk
	 * @param friendsCommentDto friendsBoardCommtnNo, memberNo, memberName, commentText, registerTime, reiendsArticleNo
	 * @return
	 */
	@PutMapping("{friendsBoardCommentNo}")
	public ResponseEntity<?> modifyComment(@PathVariable int friendsBoardCommentNo, @RequestBody FriendsCommentDto friendsCommentDto){
		friendsCommentDto.setFriendsBoardCommentNo(friendsBoardCommentNo);
		service.modifyComment(friendsCommentDto);
		System.out.println("수정된 댓글 " + friendsCommentDto);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/**
	 * 댓글을 삭제한다.
	 *
	 * @param friendsBoardCommentNo friends_board_comment 테이블의 pk
	 * @return
	 */
	@DeleteMapping("{friendsBoardCommentNo}")
	public ResponseEntity<?> deleteArticle(@PathVariable int friendsBoardCommentNo){
		service.deleteComment(friendsBoardCommentNo);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
