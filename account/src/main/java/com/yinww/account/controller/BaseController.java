package com.yinww.account.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.yinww.account.domain.Module;
import com.yinww.account.service.ManagerService;

public class BaseController {

	@Autowired
    private MessageSource messageSource;

	@Autowired
	protected ManagerService managerService;
	
	protected String getMessage(String code, HttpServletRequest request) {
		Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		if(locale == null) {
			locale = Locale.CHINA;
		}
		return messageSource.getMessage(code, null, locale);
	}
	
	protected List<Module> getModules(String url) {
		List<Module> modules = managerService.getModules();
		for (Module module : modules) {
			if(url.equals(module.getUrl())) {
				module.setActive(true);
			}
		}
		return modules;
	}

}
