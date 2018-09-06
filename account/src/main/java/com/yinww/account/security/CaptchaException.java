package com.yinww.account.security;

import org.springframework.security.core.AuthenticationException;

public class CaptchaException extends AuthenticationException {

	private static final long serialVersionUID = -7460064937848109559L;

	public CaptchaException(String msg) {
		super(msg);
	}
}
