package com.ssafy.happyhouse.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.member.dto.LoginRequest;
import com.ssafy.happyhouse.member.dto.MemberDto;
import com.ssafy.happyhouse.member.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO dao;

	@Override
	public int insertMember(String id, String pw, String name, String addr, String age) throws Exception {
		return dao.insertMember(new MemberDto(id, pw, name, addr, age));
	}
	
	@Override
	public List<MemberDto> listMember() throws Exception {
		return dao.listMember();
	}

	@Override
	public MemberDto selectMember(int member_no) throws Exception {
		return dao.selectMember(member_no);
	}

	@Override
	public int updateMember(String id, String pw, String name, String email, String phone) throws Exception {
		return dao.updateMember(new MemberDto(id, pw, name, email, phone));
	}

	@Override
	public int deleteMember(int member_no) throws Exception {
		return dao.deleteMember(member_no);
	}

	@Override
	public MemberDto login(LoginRequest loginReq) throws Exception {
		MemberDto login = null;
		login = dao.login(loginReq);
		return login;
	}

}
