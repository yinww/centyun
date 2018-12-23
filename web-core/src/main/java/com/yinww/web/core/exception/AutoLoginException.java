package com.yinww.web.core.exception;

import org.springframework.security.core.AuthenticationException;

public class AutoLoginException extends AuthenticationException {

	private static final long serialVersionUID = 1108404053533963377L;

	public AutoLoginException(String msg) {
		super(msg);
	}

}
