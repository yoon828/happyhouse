package com.ssafy.happyhouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.NaverApiService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/naverapi")
public class NaverApiController {
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NaverApiService naverApiService;
	
	@ApiOperation(value= "주택 관련 뉴스를 조회한다.", response = String.class)
	@GetMapping(value ="/news")
	public ResponseEntity<?> newsList() throws Exception{
		logger.debug("주택 관련 뉴스 호출");
		if(naverApiService.newsList() != null) {
			return new ResponseEntity<String>(naverApiService.newsList(), HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value="맛집 검색 결과를 조회한다.",response = String.class)
	@GetMapping(value = "/food/{search}")
	public ResponseEntity<?> foodList(@PathVariable String search) throws Exception{
		logger.debug("맛집 검색 결과 호출");
		if(naverApiService.foodList(search) != null) {
			return new ResponseEntity<String>(naverApiService.foodList(search), HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
