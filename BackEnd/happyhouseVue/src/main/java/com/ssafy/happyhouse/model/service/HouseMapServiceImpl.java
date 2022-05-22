package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return houseMapMapper.getAptInDong(dong);
	}
	
	@Override
	public List<HouseInfoDto> getAptName(String aptName) throws Exception {
		return houseMapMapper.getAptName(aptName);
	}
	
	@Override
	public List<HouseDealDto> getDealsByCode(String aptCode) throws Exception {
		return houseMapMapper.getDealsByCode(aptCode);
	}

//	@Override
//	public List<HouseInfoDto> getAptByPrice(int start, int end, String dong) throws Exception {
//		return houseMapMapper.getAptByDong(dong);
//		List<HouseDealDto> allList = houseMapDao.getAptByDong(dong);		
//		List<HouseDealDto> priceList = new LinkedList<HouseDealDto>();
//		Iterator<HouseDealDto> iter = allList.iterator();
//		while (iter.hasNext()) {
//			HouseDealDto house = iter.next();
//			int price = Integer.parseInt(house.getDealAmount().replace(",", "").trim());
//			System.out.println(price);
//			if (start <= price && price <= end) {
//				priceList.add(house);
//			}
//			if (price > end) {
//				break;
//			}
//		}
//		return priceList;
//	}
}
