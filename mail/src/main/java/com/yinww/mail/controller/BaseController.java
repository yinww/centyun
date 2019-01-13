package com.yinww.mail.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.yinww.web.core.client.LoginFeignClient;
import com.yinww.web.core.domain.Module;

public class BaseController {

	@Autowired
    private MessageSource messageSource;

	@Autowired
	private LoginFeignClient loginFeignClient;
	
	protected String getMessage(String code, HttpServletRequest request) {
		Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		if(locale == null) {
			locale = Locale.CHINA;
		}
		return messageSource.getMessage(code, null, locale);
	}
	
	protected List<Module> getModules(String url) {
		List<Module> modules = loginFeignClient.getAvailableModules();
		for (Module module : modules) {
			List<Module> children = module.getChildren();
			for (Module child : children) {
				if(child.getUrl().endsWith(url)) {
					child.setActive(true);
					module.setActive(true); // 将父节点设置为active
				}
			}
		}
		return modules;
	}
	
}
