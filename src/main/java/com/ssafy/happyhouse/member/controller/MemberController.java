package com.ssafy.happyhouse.member.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.member.dto.LoginRequest;
import com.ssafy.happyhouse.member.dto.MemberDto;
import com.ssafy.happyhouse.member.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

@RestController
@RequestMapping("/member")
@ControllerAdvice
@Api("멤버 컨트롤러 API V1")
public class MemberController {
	

	private final MemberService service;
	private final SecurityService securityService;

	@Autowired
	public MemberController(MemberService service, SecurityService securityService) {
		this.service = service;
		this.securityService = securityService;
	}


	@PostMapping
	@ApiOperation(value = "회원 등록", notes = "회원의 정보를 받아 등록한다")
	public ResponseEntity<?> insertMember(@RequestBody Map<String, String> map) throws Exception{
		System.out.println(map);
		service.insertMember(map.get("id"), map.get("pw"), map.get("name"), map.get("email"), map.get("phone"));
		
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}
	
	@GetMapping
	@ApiOperation(value = "회원 정보 조회", notes = "한 회원에 대한 정보를 읽어온다.")
	@ApiImplicitParam(name = "id", value = "아이디", required = true, dataType = "String")
	public ResponseEntity<?> selectMember(@RequestParam("member_no") int member_no) throws Exception {
		ResponseEntity<?> res;

		 MemberDto member = service.selectMember(member_no);
		if(member != null) {
			res = new ResponseEntity<MemberDto>(member, HttpStatus.OK);
		}else {
			res = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
			
		return res;
	}

	@PutMapping
	@ApiOperation(value = "회원 정보 수정", notes = "회원정보를 수정한다.")
	public ResponseEntity<?> updateMember(@RequestBody Map<String, Object> map) throws Exception{
		ResponseEntity<?> res;
		
		int updateMember = service.updateMember((String)map.get("id"), (String)map.get("pw"), (String)map.get("name"), (String)map.get("email"), (String)map.get("phone"));
		if(updateMember == 1) {
			res = new ResponseEntity<>(map, HttpStatus.NO_CONTENT);
			System.out.println(res);
		}else {
			res = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);						
		}
		
		return res;
	}
	
	@DeleteMapping
	@ApiOperation(value = "회원 정보 삭제", notes = "회원정보를 삭제한다.")
	public ResponseEntity<?> deleteMember(@RequestParam("member_no") int member_no) throws Exception{
		ResponseEntity<?> res;
		
		int deleteMember = service.deleteMember(member_no);
		if(deleteMember == 1) {
			res = new ResponseEntity<Void>(HttpStatus.OK);				
		}else {
			res = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);						
		}
	
		return res;
	}
	
	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "회원이 로그인한다.")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginReq, HttpSession session, HttpServletResponse response) throws Exception	{
		ResponseEntity<?> res;
		System.out.println(loginReq);
		MemberDto member = service.login(loginReq);
		String jwtToken = securityService.createToken(member.getId(), 30*60*1000); //30분
		member.setId(jwtToken);

		if(member != null) {
			session.setAttribute("login", member);
			res = new ResponseEntity<MemberDto>(member, HttpStatus.OK);
		}else {
			res = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return res;
	}

	@PostMapping("/validation")
	public ResponseEntity<?> validateToken(@RequestBody LoginRequest loginReq){
		ResponseEntity<?> res;
		boolean isValid = securityService.validateToken(loginReq.getId());
		if(isValid){
			res = new ResponseEntity<Void>(HttpStatus.OK);
		}else{
			res = new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		return res;
	}
	
	@GetMapping("/logout")
	@ApiOperation(value = "로그아웃", notes = "회원이 로그아웃한다.")
	public void logout(HttpSession session){
		Object attribute = session.getAttribute("login");
		System.out.println(attribute);
		System.out.println("로그아웃");
		session.invalidate();

	}
}
