package com.ssafy.happyhouse.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NaverApiServiceImpl implements NaverApiService {

	@Override
	public String newsList() {
		String clientId = "phcDjj6MZ2WhkC7K5gyL";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "xZn1hH2PJr";// 애플리케이션 클라이언트 시크릿값";
		Random random = new Random();
		int start = random.nextInt(150) + 20;
		try {
			String text = URLEncoder.encode("주택", "UTF-8"); // 검색어";
			String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text
					+ "&display=10&start=" + start + "&sort=sim"; // 뉴스의 json 결과
			// String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
			// // 블로그의 xml 결과
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine + "\n");
			}
			br.close();
			return response.toString();
		} catch (Exception e) {
			return null;
		}
		}

}
