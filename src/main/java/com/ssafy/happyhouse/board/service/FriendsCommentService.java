package com.ssafy.happyhouse.board.service;

import com.ssafy.happyhouse.board.dto.FriendsCommentDto;

import java.util.List;
import java.util.Map;

public interface FriendsCommentService {
    int writeComment(FriendsCommentDto friendsCommentDto);
    List<FriendsCommentDto> listComment(int friendsArticleNo);
    void modifyComment(FriendsCommentDto friendsCommentDto);
    void deleteComment(int friendsBoardCommentNo);
}
