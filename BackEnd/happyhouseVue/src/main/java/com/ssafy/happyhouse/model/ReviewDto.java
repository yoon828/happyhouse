package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Revie (리뷰 정보)", description = "아파트코드, 사용자, 평점, 리뷰내용을 가진   Domain Class")
public class ReviewDto {
	@ApiModelProperty(value = "아파트 코드")
	private String aptCode;
	@ApiModelProperty(value = "리뷰 작성 아이디")
	private String userid;
	@ApiModelProperty(value = "평점")
	private double rate;
	@ApiModelProperty(value = "내용")
	private String content;
	public ReviewDto(String aptCode, String userid, double rate, String content) {
		super();
		this.aptCode = aptCode;
		this.userid = userid;
		this.rate = rate;
		this.content = content;
	}
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "ReviewDto [aptCode=" + aptCode + ", userid=" + userid + ", rate=" + rate + ", content=" + content + "]";
	}

}
