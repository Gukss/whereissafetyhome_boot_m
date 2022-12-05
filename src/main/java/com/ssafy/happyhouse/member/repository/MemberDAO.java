package com.ssafy.happyhouse.member.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.member.dto.LoginRequest;
import com.ssafy.happyhouse.member.dto.MemberDto;

@Mapper
public interface MemberDAO {

	int insertMember(MemberDto m) throws Exception;

	List<MemberDto> listMember() throws Exception;
	
	MemberDto selectMember(int member_no) throws Exception;
	
	int updateMember(MemberDto m) throws Exception;

	int deleteMember(int member_no) throws Exception;

	MemberDto login(LoginRequest loginReq) throws Exception;

}
