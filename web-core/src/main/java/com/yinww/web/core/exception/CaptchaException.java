package com.yinww.web.core.exception;

import org.springframework.security.core.AuthenticationException;

public class CaptchaException extends AuthenticationException {

	private static final long serialVersionUID = 7376983482681354970L;

	public CaptchaException(String msg) {
		super(msg);
	}
}
