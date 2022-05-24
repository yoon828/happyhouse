package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.ReviewDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptName(String aptName) throws Exception;
//	List<HouseInfoDto> getAptByPrice(int start, int end, String dong) throws Exception;
	List<HouseDealDto> getDealsByCode(String aptCode) throws Exception;
	SidoGugunCodeDto getAddressName(String dongCode) throws Exception;
	boolean postReview(ReviewDto reivew)throws Exception;
	ReviewDto getReview(Map<String, String> map)throws Exception;
	HouseInfoDto selectHouse(String aptCode)throws Exception;
}
