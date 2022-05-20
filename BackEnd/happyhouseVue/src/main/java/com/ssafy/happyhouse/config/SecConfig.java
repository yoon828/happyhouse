package com.ssafy.happyhouse.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder getPasswordEncoder() { //스프링 시큐리티의 인터페이스 객체
		return new BCryptPasswordEncoder(); //암호화 알고리즘 : BCrypt 사용
	}
	
	 @Override
	   protected void configure(HttpSecurity http) throws Exception {
	      http.cors().disable()
	         .csrf().disable()
	         .formLogin().disable()
	         .headers().frameOptions().disable();
	   }
}
