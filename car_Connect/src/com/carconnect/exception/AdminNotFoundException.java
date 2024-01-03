package com.carconnect.exception;

public class AdminNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public AdminNotFoundException() {

	}

	public AdminNotFoundException(String message) {
		super(message);
	}

}
