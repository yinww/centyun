package com.yinww.account.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CaptchaAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String SPRING_SECURITY_CAPTCHA_KEY = "j_captcha";  
    public static final String SESSION_CAPTCHA_KEY = "captcha_key";  
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,  
            HttpServletResponse response) throws AuthenticationException {  
        String captcha = this.obtainGeneratedCaptcha(request);  
        String inputCode = this.obtainCaptcha(request);
        if(!captcha.equalsIgnoreCase(inputCode)) {
			throw new CaptchaException("Captcha.Invalid");
        }
        return super.attemptAuthentication(request, response);  
    }

    private String obtainCaptcha(HttpServletRequest request){
        return request.getParameter(SPRING_SECURITY_CAPTCHA_KEY);  
    }

    protected String obtainGeneratedCaptcha (HttpServletRequest request){  
        return (String)request.getSession().getAttribute(SESSION_CAPTCHA_KEY);  
    }

}
