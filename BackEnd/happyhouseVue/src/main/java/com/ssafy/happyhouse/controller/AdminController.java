package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService userService;
	//회원 상세 조회
	@ApiOperation(value = "상세 조회한 유저의 정보를 반환한다. 실패 하면 fail 문자열을 반환한다.")
	@GetMapping(value = "{userid}")
	public ResponseEntity<?> detailUser(@PathVariable String userid) throws Exception {
		logger.debug("detailUser 호출");
		if(userService.detailUser(userid) != null) {
			return new ResponseEntity<UserDto>(userService.detailUser(userid), HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	//관리자 등록
//	@ApiOperation(value = "관리자 정보를 받아서 관리자 등록한다. 성공하면 success 실패하면 fail 문자열을 반환한다. ", response = String.class)
//	@PostMapping(value = "/regist")
//	public ResponseEntity<String> registAdmin(@RequestBody UserDto user) throws Exception {
//		logger.debug("관리자 이름 : {}", user.getUsername());
//		logger.debug("관리자 아이디 : {}", user.getUserid());
//		logger.debug("관리자 비밀번호 : {}", user.getUserpw());
//		logger.debug("관리자 이메일 : {}", user.getUseraddress());
//		logger.debug("관리자 전화번호: {}", user.getUsernumber());
//		if (userService.registAdmin(user) == 1) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}

	@ApiOperation(value = "회원아이디를 받아서 회원의  등급을 변경한다. 성공하면 success 실패하면 fail 문자열을 반환한다. ", response = String.class)
	@PutMapping(value = "/grade")
	public ResponseEntity<String> registAdmin(@RequestParam String userid, @RequestParam String type ) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("type", type);
		if (userService.gradeAdmin(map) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	// 전체 회원 조회
	@ApiOperation(value = "전체 회원의 리스트를 반환한다.")
	@GetMapping(value = "/list")
	public ResponseEntity<List<UserDto>> listUser() throws Exception {
		logger.debug("userList 호출");
		return new ResponseEntity<List<UserDto>>(userService.listUser(), HttpStatus.OK);
	}




	
}
