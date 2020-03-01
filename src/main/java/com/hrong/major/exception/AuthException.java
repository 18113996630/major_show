package com.hrong.major.exception;

/**
 * 权限异常
 * @author hrong
 */
public class AuthException extends BaseException {
	public AuthException() {
		super();
	}

	public AuthException(String message) {
		super(message);
	}

	public AuthException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthException(Throwable cause) {
		super(cause);
	}

	protected AuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
