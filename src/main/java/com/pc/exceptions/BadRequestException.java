package com.pc.exceptions;


public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -6708890053308542941L;

	public BadRequestException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BadRequestException(String msg) {
		super(msg);
	}

	public BadRequestException(Throwable cause) {
		super(cause);
	}


}
