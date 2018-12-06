package com.yinww.login.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yinww.util.IPUtil;
import com.yinww.web.core.client.AccountFeignClient;
import com.yinww.web.core.domain.Account;
import com.yinww.web.core.domain.Audit;
import com.yinww.web.core.service.AuditService;

@Component("passportAuthenticationSuccessHandler")
public class PassportAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
    private MessageSource messageSource;
	
	@Autowired
	private AuditService auditService;

	@Autowired
	private AccountFeignClient accountFeignClient;

	@Value("${home.url}")
	private String homeUrl;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
	    Object principal = authentication.getPrincipal();
	    if(principal instanceof Account) {
	    	Account account = (Account) principal;
	    	Locale locale = (Locale)request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
	    	if(locale == null) {
				locale = Locale.CHINA;
			}
	    	if(!locale.toString().equals(account.getLanguage())) {
	    		account.setLanguage(locale.toString());
	    		accountFeignClient.updateLanguage(account.getId(), locale.toString());
	    	}
	    	
	    	// 记录登录成功的日志
	    	String ip = request.getParameter("ip");
	    	Audit audit = new Audit();
	    	audit.setAction("login");
	    	audit.setModule("system");
	    	audit.setContent(ip);
	    	audit.setIp(IPUtil.ipToLong(ip));
	    	audit.setOperator(account.getLoginName());
	    	auditService.saveLoginAudit(audit);
	    }
	    
		// 这里可以根据实际情况，来确定是跳转到页面或者json格式。
		Map<String, Object> map = new HashMap<>();
		map.put("code", HttpStatus.OK.value());
		map.put("msg", getMessage("Login.Success", request));
		map.put("url", homeUrl);
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
