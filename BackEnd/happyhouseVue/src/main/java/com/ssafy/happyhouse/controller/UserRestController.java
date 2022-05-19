package com.ssafy.happyhouse.controller;

import java.util.HashMap;
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

import com.ssafy.happyhouse.model.IdFindDto;
import com.ssafy.happyhouse.model.IdPwDto;
import com.ssafy.happyhouse.model.PwFindDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.UserService;

import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/userapi")
public class UserRestController {
	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserService userService;
	//회원 가입
	@ApiOperation(value = "회원의 정보를 받아서 회원 등록한다. 성공하면 success 실패하면 fail 문자열을 반환한다. ", response = String.class)
	@PostMapping(value ="/regist")
	public ResponseEntity<String> registUser(@RequestBody UserDto user) throws Exception{
		logger.debug("회원가입 이름 : {}", user.getUsername());
		logger.debug("회원가입 아이디 : {}", user.getUserid());
		logger.debug("회원가입 비밀번호 : {}", user.getUserpw());
		logger.debug("회원가입 이메일 : {}", user.getUseraddress());
		logger.debug("회원 가입 전화번호: {}", user.getUsernumber());
		if(userService.registUser(user) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
//	//내 정보 조회 
//	@ApiOperation(value = "아이디 비밀번호 값을 받아 회원의 정보를 반환한다.")
//	@GetMapping(value = "/myInfo")
//	public ResponseEntity<?> myInfo(@RequestBody Map<String, String> map) throws Exception{
//		logger.debug("map.username : {}", map.get("userid"));
//		logger.debug("map.username : {}", map.get("userpw"));
//		
//		return null;
//	}
	//내 정보 수정
	@ApiOperation(value = "수정된 회원 정보를 받아서 이메일, 전화번호, 비밀번호를 수정한다. 성공하면 success 실패하면 fail 문자열을 반환한다.", response = String.class)
	@PutMapping(value = "/update")
	public ResponseEntity<String> updateUser(@RequestBody UserDto user) throws Exception{
		logger.debug("userName : {}", user.getUsername());
		logger.debug("userId : {}", user.getUserid());
		logger.debug("userPw : {}", user.getUserpw());
		logger.debug("userAddress : {}", user.getUseraddress());
		logger.debug("userNumber : {}", user.getUsernumber());
		if(userService.updateUser(user) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	//회원 탈퇴
	@ApiOperation(value = "아이디와 비밀번호를 받아 회원을 삭제한다. 성공하면 success 실패하면 fail 문자열을 반환한다.", response = String.class)
	@DeleteMapping(value = "/delete")
	public ResponseEntity<String> deleteUser(@RequestBody IdPwDto idPw) throws Exception {
		logger.debug("userid : {}", idPw.getUserid());
		logger.debug("userpw : {}", idPw.getUserpw());
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", idPw.getUserid());
		map.put("userpw", idPw.getUserpw());
		if(userService.login(map) != null) {
			if(userService.deleteUser(map.get("userid")) == 1)  {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK); 
			}
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	//로그인
	@ApiOperation(value = "아이디와 비밀번호를 받아 회원의 정보를 반환한다. 성공하면 로그인 회원의 정보를 반환한다. 실패하면  null값을 반환한다.")
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody IdPwDto idPw) throws Exception{
		logger.debug("userid : {}", idPw.getUserid());
		logger.debug("userpw : {}", idPw.getUserpw());
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", idPw.getUserid());
		map.put("userpw", idPw.getUserpw());
		if(userService.login(map) != null) {
			return new ResponseEntity<UserDto>(userService.login(map), HttpStatus.OK);
		}
		return null;
	}
	//아이디 찾기
	@ApiOperation(value = "이름, 이메일 주소, 전화번호를 받아 아이디를 반환합니다. 성공하면 아이디를 반환하고 실패하면 fail 문자열을 반환합니다.")
	@PostMapping("/idFind")
	public ResponseEntity<String> idFind(@RequestBody IdFindDto idFind) throws Exception{
		logger.debug("username : {}", idFind.getUsername());
		logger.debug("useraddress : {}", idFind.getUseraddress());
		logger.debug("usernumber : {}", idFind.getUsernumber());
		Map<String,String> map = new HashMap<String, String>();
		map.put("username", idFind.getUsername());
		map.put("useraddress", idFind.getUseraddress());
		map.put("usernumber", idFind.getUsernumber());
		String findId = userService.idFind(map);
		if(findId != ""  && findId != null) {
			return new ResponseEntity<String>(findId, HttpStatus.OK);  
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);   
	}
	//비밀번호 찾기
	@ApiOperation(value = "이름, 아이디 , 이메일 주소, 전화번호를 받아서 임시 비밀번호를 발급한다. 성공하면 임시 비밀 번호를 반환한다.실패하면 fail 문자열을 반환한다.")
	@PostMapping("/pwFind")
	public ResponseEntity<String> pwFind(@RequestBody PwFindDto pwFind) throws Exception{
		logger.debug("username : {}", pwFind.getUsername());
		logger.debug("userid : {}", pwFind.getUserid());
		logger.debug("useraddress : {}", pwFind.getUseraddress());
		logger.debug("usernumber : {}", pwFind.getUsernumber());
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", pwFind.getUsername());
		map.put("userid", pwFind.getUserid());
		map.put("useraddress", pwFind.getUseraddress());
		map.put("usernumber", pwFind.getUsernumber());
		String ranPw =  userService.pwFind(map);
		if(ranPw != null && ranPw !="") {
			return new ResponseEntity<String>(ranPw, HttpStatus.OK); 
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK); 
	}
	
	
}
