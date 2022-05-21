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


import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.UserService;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


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
	@ApiOperation(value = "아이디를 받아 회원을 삭제한다. 성공하면 success 실패하면 fail 문자열을 반환한다.", response = String.class)
	@DeleteMapping(value = "/delete/{userid}")
	public ResponseEntity<String> deleteUser(@PathVariable String userid) throws Exception {
		logger.debug("userid : {}", userid);
		if(userService.deleteUser(userid) == 1)  {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK); 
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	//로그인
	@ApiOperation(value = "아이디와 비밀번호를 입력시 로그인에 성공하면 유저 정보를 받는다. 실패시 fail값 ")
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody UserDto userDto) throws Exception{
		logger.debug("userid : {}", userDto.getUserid());
		logger.debug("userpw : {}", userDto.getUserpw());
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userDto.getUserid());
		map.put("userpw", userDto.getUserpw());
		UserDto loginUser = userService.login(map);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(loginUser != null) {
			resultMap.put("userInfo", loginUser);
			resultMap.put("message", SUCCESS);
		} 
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} 

	
	//아이디 찾기
	@ApiOperation(value = "이름, 이메일 주소, 전화번호를 받아 아이디를 반환합니다. 성공하면 아이디를 반환하고 실패하면 fail 문자열을 반환합니다.")
	@PostMapping("/idFind")
	public ResponseEntity<String> idFind(@RequestBody UserDto userDto) throws Exception{
		logger.debug("username : {}", userDto.getUsername());
		logger.debug("useraddress : {}", userDto.getUseraddress());
		logger.debug("usernumber : {}", userDto.getUsernumber());
		Map<String,String> map = new HashMap<String, String>();
		map.put("username", userDto.getUsername());
		map.put("useraddress", userDto.getUseraddress());
		map.put("usernumber", userDto.getUsernumber());
		String findId = userService.idFind(map);
		if(findId != ""  && findId != null) {
			return new ResponseEntity<String>(findId, HttpStatus.OK);  
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);   
	}
	
	//비밀번호 찾기
	@ApiOperation(value = "이름, 아이디 , 이메일 주소, 전화번호를 받아서 임시 비밀번호를 발급한다. 성공하면 임시 비밀 번호를 반환한다.실패하면 fail 문자열을 반환한다.")
	@PostMapping("/pwFind")
	public ResponseEntity<String> pwFind(@RequestBody UserDto userDto) throws Exception{
		logger.debug("username : {}", userDto.getUsername());
		logger.debug("userid : {}", userDto.getUserid());
		logger.debug("useraddress : {}", userDto.getUseraddress());
		logger.debug("usernumber : {}", userDto.getUsernumber());
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", userDto.getUsername());
		map.put("userid", userDto.getUserid());
		map.put("useraddress", userDto.getUseraddress());
		map.put("usernumber", userDto.getUsernumber());
		String ranPw =  userService.pwFind(map);
		if(ranPw != null && ranPw !="") {
			return new ResponseEntity<String>(ranPw, HttpStatus.OK); 
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK); 
	}
	
	//관심지역 추가
	@ApiOperation(value = "유저 아이디로 관심지역을 추가한다.")
	@PostMapping("/addLikedong")
	public ResponseEntity<String> addLikedong(@RequestBody Map<String, String> map) throws Exception{
		logger.debug("map : {}",map);
		try {
			userService.addLikeDong(map);
		}catch(Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK); 
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK); 
	}
	//내 관심지역 조회
	@ApiOperation(value = "회원의 아이디를 받아서 관심지역 리스트를 반환한다.")
	@GetMapping(value = "/myInfo")
	public ResponseEntity<List<SidoGugunCodeDto>> myInfo(@RequestBody String userid) throws Exception{
		logger.debug("userid : {}", userid);
		return new ResponseEntity<List<SidoGugunCodeDto>>(userService.listLikeDong(userid), HttpStatus.OK); 
	}
	
}
