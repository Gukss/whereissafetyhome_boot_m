package com.ssafy.happyhouse.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
// https://velog.io/@sorzzzzy/Spring-Boot5-9.-API-%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC
@RestControllerAdvice(basePackages = "com.ssafy.happyhouse")
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleException(Exception e){
		Map<String, Object> map = new HashMap<>();
		map.put("Exception Message", e.getMessage());
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
	}
}
