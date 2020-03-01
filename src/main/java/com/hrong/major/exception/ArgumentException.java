package com.hrong.major.exception;

/**
 * 参数异常
 * @author hrong
 */
public class ArgumentException extends BaseException {
	public ArgumentException() {
		super();
	}

	public ArgumentException(String message) {
		super(message);
	}

	public ArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public ArgumentException(Throwable cause) {
		super(cause);
	}

	protected ArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
