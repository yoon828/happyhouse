package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.UserDto;

public interface UserService {
	// 로그인
	UserDto login(Map<String, String> map) throws Exception;

	// 회원가입
	int registUser(UserDto userDto) throws Exception;
	//관리자 등록
	int registAdmin(UserDto userDto) throws Exception;
	// 조회
	List<UserDto> listUser() throws Exception;

	// 수정
	int updateUser(UserDto userDto) throws Exception;

	// 삭제
	int deleteUser(String userid) throws Exception;

	// 아이디중복체크
	int idCheck(String checkId) throws Exception;

	// 아이디 찾기
	String idFind(Map<String, String> map) throws Exception;

	// 비밀번호 찾기
	String pwFind(Map<String, String> map) throws Exception;

	// 관심지역 등록
	void addLikeDong(Map<String, String> map) throws Exception;

	// 관심지역 조회
	List<SidoGugunCodeDto> listLikeDong(String userid) throws Exception;

	UserDto detailUser(String userid) throws Exception;

	boolean deleteLikeDong(Map<String, String> map) throws Exception;

	//회원 등급 수정
	int gradeAdmin(Map<String, String> map) throws Exception;

	int updateAdmin(UserDto userDto) throws Exception;

	

}

