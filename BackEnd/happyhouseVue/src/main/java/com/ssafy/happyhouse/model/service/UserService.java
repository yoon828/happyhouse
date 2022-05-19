package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.UserDto;

public interface UserService {
	//로그인
	UserDto login(Map<String, String> map) throws Exception;
	//회원가입
	void registUser(UserDto userDto) throws Exception;
	//조회
	List<UserDto> listUser() throws Exception;
	//수정
	int updateUser(UserDto userDto) throws Exception;
	//삭제
	void deleteUser(String userid) throws Exception;
	//아이디중복체크
	int idCheck(String checkId) throws Exception;
	//아이디 찾기  
	String idFind(Map<String, String> map) throws Exception;
	//비밀번호 찾기 
	String pwFind(Map<String, String> map) throws Exception;
 }
