package com.yinww.account.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component("captchaAuthenticationFailHander")
public class CaptchaAuthenticationFailHander extends SimpleUrlAuthenticationFailureHandler {
	
    private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
        log.info("captch error", exception);
//        setDefaultFailureUrl("/login?error=true");
//        super.onAuthenticationFailure(request, response, exception);

        Map<String, Object> map = new HashMap<>();
		map.put("code", HttpStatus.BAD_REQUEST.value());
		map.put("msg", "Login.Fail");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(map));
	}

}
