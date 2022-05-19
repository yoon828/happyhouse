package com.ssafy.happyhouse.model;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "유저 정보", description = "유저의 정보 아이디, 비밀번호, 이름, 이메일 주소, 전화번호 Domain")
public class UserDto {
	@ApiModelProperty(value = "회원 아이디")
	private String userid;
	@ApiModelProperty(value = "회원 비밀번호")
	private String userpw;
	@ApiModelProperty(value = "회원 이름")
	private String username;
	@ApiModelProperty(value = "회원 이메일 주소")
	private String useraddress;
	@ApiModelProperty(value = "회원 전화 번호")
	private String usernumber;
	
	
	public UserDto() {}
	

	public UserDto(String userid, String userpw, String username, String useraddress, String usernumber) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.useraddress = useraddress;
		this.usernumber = usernumber;
	}


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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getUseraddress() {
		return useraddress;
	}


	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}


	public String getUsernumber() {
		return usernumber;
	}


	public void setUsernumber(String usernumber) {
		this.usernumber = usernumber;
	}


	@Override
	public String toString() {
		return "UserDto [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", useraddress="
				+ useraddress + ", usernumber=" + usernumber + "]";
	}
	
	
	
}
