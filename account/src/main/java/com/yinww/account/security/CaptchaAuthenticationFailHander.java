package com.yinww.account.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component("captchaAuthenticationFailHander")
public class CaptchaAuthenticationFailHander extends SimpleUrlAuthenticationFailureHandler {
	
    private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
        log.info("captch error", exception);
        setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
	}

}
