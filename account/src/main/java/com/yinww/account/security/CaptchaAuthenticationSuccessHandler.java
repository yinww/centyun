package com.yinww.account.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component("captchaAuthenticationSuccessHandler")
public class CaptchaAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
    private MessageSource messageSource;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		// 这里可以根据实际情况，来确定是跳转到页面或者json格式。
		Map<String, Object> map = new HashMap<>();
		map.put("code", HttpStatus.OK.value());
		map.put("msg", getMessage("Login.Success", request));
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(map));
		
		//如果是要跳转到某个页面的，比如我们的那个whoim的则
		// getRedirectStrategy().sendRedirect(request, response, "/whoim");
	}
	
	private String getMessage(String code, HttpServletRequest request) {
		Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		if(locale == null) {
			locale = Locale.CHINA;
		}
		return messageSource.getMessage(code, null, locale);
	}
}
