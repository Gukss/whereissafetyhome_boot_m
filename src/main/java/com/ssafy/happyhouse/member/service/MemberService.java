package com.ssafy.happyhouse.member.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.member.dto.LoginRequest;
import com.ssafy.happyhouse.member.dto.MemberDto;

public interface MemberService {
	
	int insertMember(String id, String pw, String name, String addr, String age) throws Exception;
	
	List<MemberDto> listMember() throws Exception ;

	MemberDto selectMember(int member_no) throws Exception;
	
	int updateMember(String id, String pw, String name, String addr, String age) throws Exception;
	
	int deleteMember(int member_no) throws Exception;

	MemberDto login(LoginRequest loginReq) throws Exception;

//	boolean login(String id, String pw) throws Exception;


}
