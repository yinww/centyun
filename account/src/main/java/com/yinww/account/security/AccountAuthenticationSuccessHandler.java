package com.yinww.account.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component("accountAuthenticationSuccessHandler")
public class AccountAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		// 什么都不做的话，那就直接调用父类的方法
		// super.onAuthenticationSuccess(request, response, authentication);

		// 这里可以根据实际情况，来确定是跳转到页面或者json格式。
		// 如果是返回json格式，那么我们这么写
		Map<String, Object> map = new HashMap<>();
		map.put("code", HttpStatus.OK.value());
		map.put("msg", "登录成功");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(map));
	}
}
