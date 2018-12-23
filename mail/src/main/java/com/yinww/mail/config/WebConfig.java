package com.yinww.mail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yinww.web.core.interceptor.PassportInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public PassportInterceptor passportInterceptor() {
		return new PassportInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 身份验证拦截器
		registry.addInterceptor(passportInterceptor()).addPathPatterns("/**");
	}

}
