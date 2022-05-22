package com.ssafy.happyhouse.model;

public class HouseInfoDto {

	private String aptCode;
	private String roadName;
	private String roadNameBonbun;
	private String roadNameBubun;
	private String roadNameSeq;
	private String roadNameCode;
	private int buildYear;
	private String bonbun;
	private String apartmentName;
	private String dongCode;
	private String dong;
	private String jibun;
	private String lat;
	private String lng;
	private String sidoname;
	private String gugunname;
	private String avgPrice;
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getRoadNameBonbun() {
		return roadNameBonbun;
	}
	public void setRoadNameBonbun(String roadNameBonbun) {
		this.roadNameBonbun = roadNameBonbun;
	}
	public String getRoadNameBubun() {
		return roadNameBubun;
	}
	public void setRoadNameBubun(String roadNameBubun) {
		this.roadNameBubun = roadNameBubun;
	}
	public String getRoadNameSeq() {
		return roadNameSeq;
	}
	public void setRoadNameSeq(String roadNameSeq) {
		this.roadNameSeq = roadNameSeq;
	}
	public String getRoadNameCode() {
		return roadNameCode;
	}
	public void setRoadNameCode(String roadNameCode) {
		this.roadNameCode = roadNameCode;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getBonbun() {
		return bonbun;
	}
	public void setBonbun(String bonbun) {
		this.bonbun = bonbun;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getSidoname() {
		return sidoname;
	}
	public void setSidoname(String sidoname) {
		this.sidoname = sidoname;
	}
	public String getGugunname() {
		return gugunname;
	}
	public void setGugunname(String gugunname) {
		this.gugunname = gugunname;
	}
	public String getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(String recentPrice) {
		this.avgPrice = recentPrice;
	}
	@Override
	public String toString() {
		return "HouseInfoDto [aptCode=" + aptCode + ", roadName=" + roadName + ", roadNameBonbun=" + roadNameBonbun
				+ ", roadNameBubun=" + roadNameBubun + ", roadNameSeq=" + roadNameSeq + ", roadNameCode=" + roadNameCode
				+ ", buildYear=" + buildYear + ", bonbun=" + bonbun + ", apartmentName=" + apartmentName + ", dongCode="
				+ dongCode + ", dong=" + dong + ", jibun=" + jibun + ", lat=" + lat + ", lng=" + lng + ", sidoname="
				+ sidoname + ", gugunname=" + gugunname + ", avgPrice=" + avgPrice + "]";
	}
}
