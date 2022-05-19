package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.UserDto;

@Mapper
public interface UserMapper {
	//회원 가입
	void registUser(UserDto userDto) throws Exception;
	//로그인
	UserDto login(Map<String, String> map) throws Exception;
	//수정
	int updateUser(UserDto userDto) throws Exception;
	//삭제 
	void deleteUser(String userId) throws Exception;
	//조회
	List<UserDto> listUser() throws Exception;
	//아이디 중복체크
	int idCheck(String checkId) throws Exception;
	//아이디를 주면 비밀번호 받아오기
	String selectId(String userId) throws Exception;
	//아이디 찾기
	String idFind(Map<String, String> map) throws Exception;
	//비밀번호 찾기 
	UserDto pwFind(Map<String, String> map) throws Exception;
}
