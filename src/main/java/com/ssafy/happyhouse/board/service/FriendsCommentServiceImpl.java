package com.ssafy.happyhouse.board.service;

import com.ssafy.happyhouse.board.dto.FriendsCommentDto;
import com.ssafy.happyhouse.board.repository.FriendsCommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FriendsCommentServiceImpl implements FriendsCommentService{

    private FriendsCommentDAO dao;

    @Autowired
    public FriendsCommentServiceImpl(FriendsCommentDAO dao) {
        this.dao = dao;
    }

    @Override
    public int writeComment(FriendsCommentDto friendsCommentDto) {
        return dao.writeComment(friendsCommentDto);
    }

    @Override
    public List<FriendsCommentDto> listComment(int friendsArticleNo) {
        return dao.listComment(friendsArticleNo);
    }

    @Override
    public void modifyComment(FriendsCommentDto friendsCommentDto) {
        dao.modifyComment(friendsCommentDto);
        return;
    }

    @Override
    public void deleteComment(int commentNo) {
        dao.deleteComment(commentNo);
        return;
    }
}
