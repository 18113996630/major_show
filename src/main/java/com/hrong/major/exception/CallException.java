package com.hrong.major.exception;

/**
 * 方法、http调用异常
 * @author hrong
 */
public class CallException extends BaseException {
	public CallException() {
		super();
	}

	public CallException(String message) {
		super(message);
	}

	public CallException(String message, Throwable cause) {
		super(message, cause);
	}

	public CallException(Throwable cause) {
		super(cause);
	}

	protected CallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
