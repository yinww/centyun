package com.yinww.account.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

public class BaseController {

	@Autowired
    private MessageSource messageSource;
	
	protected String getMessage(String code, HttpServletRequest request) {
		Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		if(locale == null) {
			locale = Locale.CHINA;
		}
		return messageSource.getMessage(code, null, locale);
	}

}
