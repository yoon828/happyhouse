package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;

import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "유저 아이디 비밀번호", description = "유저의 정보 아이디, 비밀번호")
public class IdPwDto {
	@ApiModelProperty(value = "회원 아이디")
	private String userid;
	@ApiModelProperty(value = "회원 비밀번호")
	private String userpw;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	
	
}
