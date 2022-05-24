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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.ReviewDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService happyHouseMapService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido : {}", happyHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(happyHouseMapService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		logger.debug("sido : {}", sido);
		return new ResponseEntity<List<SidoGugunCodeDto>>(happyHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		logger.debug("gugun : {}",gugun);
		return new ResponseEntity<List<HouseInfoDto>>(happyHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		logger.debug("dong : {}", dong);
		return new ResponseEntity<List<HouseInfoDto>>(happyHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	
	@GetMapping("/apt-name")
	public ResponseEntity<List<HouseInfoDto>> aptName(@RequestParam("aptName") String aptName) throws Exception {
		logger.debug("aptName : {}", aptName);
		return new ResponseEntity<List<HouseInfoDto>>(happyHouseMapService.getAptName(aptName), HttpStatus.OK);
	}
	
	@GetMapping("/apt-deals")
	public ResponseEntity<List<HouseDealDto>> aptDeals(@RequestParam("aptCode") String aptCode) throws Exception {
		logger.debug("aptCode : {}", aptCode);
		return new ResponseEntity<List<HouseDealDto>>(happyHouseMapService.getDealsByCode(aptCode), HttpStatus.OK);
	}
	

	@GetMapping("/address")
	public ResponseEntity<SidoGugunCodeDto> getAddressName(@RequestParam("dongcode")String dongcode) throws Exception{
		logger.debug("dongCode : {}", dongcode);
		return new ResponseEntity<SidoGugunCodeDto>(happyHouseMapService.getAddressName(dongcode), HttpStatus.OK);
	}
	
	 @ApiOperation(value = "아파트 조회", notes = "아파트 코드로 아파트 정보와 리뷰 정보를 조회한다.", response = String.class)
		@GetMapping("/aptinfo")
		public ResponseEntity<HouseInfoDto> selectHouse(@RequestParam String aptCode) throws Exception {
			logger.debug("aptCode : {}", aptCode);
			logger.debug("result : {}", happyHouseMapService.selectHouse(aptCode));
			return new ResponseEntity<HouseInfoDto>(happyHouseMapService.selectHouse(aptCode), HttpStatus.OK);
		}
	
	
    @ApiOperation(value = "리뷰 등록", notes = "아파트 리뷰를 등록한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/review")
	public ResponseEntity<String> reviewRegist(@RequestBody ReviewDto reivew) throws Exception {
		logger.debug("map : {}", reivew);
		if(happyHouseMapService.postReview(reivew)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
    
    @ApiOperation(value = "리뷰 등록 조회", notes = "특정 사용자가 특정 아파트에 등록한 리뷰를 조회한다.", response = String.class)
	@GetMapping("/review")
	public ResponseEntity<ReviewDto> reviewSelect(@RequestParam String userid, @RequestParam String aptCode) throws Exception {
		logger.debug("reivew : {}, {}", userid, aptCode);
		 Map<String ,String> map = new HashMap<String, String>();
		 map.put("userid", userid);
		 map.put("aptCode", aptCode);
		logger.debug("map : {}", map);
		logger.debug("result : {}", happyHouseMapService.getReview(map));
		return new ResponseEntity<ReviewDto>(happyHouseMapService.getReview(map), HttpStatus.OK);
	}
}
