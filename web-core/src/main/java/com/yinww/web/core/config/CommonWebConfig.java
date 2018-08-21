package com.yinww.web.core.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import com.yinww.web.core.interceptor.AntiInjectInterceptor;
import com.yinww.web.core.xss.XssFilter;
import com.yinww.web.core.xss.XssUrlPathHelper;

@Configuration
public class CommonWebConfig implements WebMvcConfigurer {
	@Bean
	public AntiInjectInterceptor antiInjectInterceptor() {
		return new AntiInjectInterceptor();
	}
	/*
	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
    */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 防注入拦截器
		registry.addInterceptor(antiInjectInterceptor()).addPathPatterns("/**");
		// Login
//		registry.addInterceptor(loginInterceptor()).addPathPatterns("/**/user/**");
//		super.addInterceptors(registry);
	}

	@Bean
	public FilterRegistrationBean<XssFilter> getXssFilter(){
		XssFilter xssFilter = new XssFilter();
		FilterRegistrationBean<XssFilter> registrationBean = new FilterRegistrationBean<XssFilter>();
		registrationBean.setFilter(xssFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/*");  // 拦截路径，可以添加多个
		registrationBean.setUrlPatterns(urlPatterns);
		registrationBean.setOrder(1);
		return registrationBean;
	}
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		UrlPathHelper urlPathHelper = new XssUrlPathHelper();
//        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
	}

}
