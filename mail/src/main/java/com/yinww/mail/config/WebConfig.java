package com.yinww.mail.config;

import java.nio.charset.Charset;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
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

	@Bean
    public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
	}
}
