package com.ssafy.happyhouse.naverapi;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class test {

	public static void main(String[] args) {
		String clientId = "phcDjj6MZ2WhkC7K5gyL";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "xZn1hH2PJr";// 애플리케이션 클라이언트 시크릿값";
		try {
			String text = URLEncoder.encode("광주광역시광산구송정동피부과", "UTF-8"); // 검색어";
			String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + text
					+ "&display=5&start=1&sort=comment"; // 뉴스의 json 결과
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
			System.out.println(response.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
