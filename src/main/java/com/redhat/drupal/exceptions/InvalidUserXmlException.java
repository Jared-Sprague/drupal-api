package com.redhat.drupal.exceptions;

public class InvalidUserXmlException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidUserXmlException(String msg) {
		super(msg);
	}

	public InvalidUserXmlException() {
		super();
	}
}
