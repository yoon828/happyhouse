package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

//@RestController // 비동기로 얻어옴
@Controller
@RequestMapping("/plus")
@CrossOrigin("*")
public class SmartSearchController {
	//private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	
	@Autowired
	private HouseMapService houseMapService;

//	@Autowired
//	public void setHouseMapService(HouseMapService houseMapService) {
//		this.houseMapService = houseMapService;
//	}

	@GetMapping("/smartSearch")
	public String smartSearch() {
		return "plus/smartSearch";
	}

	@ResponseBody
	@GetMapping("/price")
	public List<HouseInfoDto> selectAptByPrice(@RequestParam int start, @RequestParam int end, @RequestParam String dong) throws Exception{
		System.out.println(start);
		System.out.println(end);
		return houseMapService.getAptByPrice(start,end,dong);
	}

}