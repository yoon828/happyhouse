package com.ssafy.happyhouse.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.happyhouse.interceptor.BearerAuthInterceptor;

@Configuration
//@EnableAspectJAutoProxy
@MapperScan(basePackages = { "com.ssafy.**.mapper" })
public class WebMvcConfiguration implements WebMvcConfigurer {

	private final BearerAuthInterceptor bearerAuthInterceptor;

	public WebMvcConfiguration(BearerAuthInterceptor bearerAuthInterceptor) {
		this.bearerAuthInterceptor = bearerAuthInterceptor;
	}

//	// 인터셉터 등록하기
//	public void addInterceptors(InterceptorRegistry registry) {
//		System.out.println(">>> 인터셉터 등록");
//		registry.addInterceptor(bearerAuthInterceptor)
//		.addPathPatterns("/userapi/update");
//	}
}
