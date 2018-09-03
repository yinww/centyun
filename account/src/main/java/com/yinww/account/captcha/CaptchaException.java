package com.yinww.account.captcha;

import org.springframework.security.core.AuthenticationException;

public class CaptchaException extends AuthenticationException {

	private static final long serialVersionUID = -6179014944931139844L;

	public CaptchaException(String msg) {
		super(msg);
	}
}
