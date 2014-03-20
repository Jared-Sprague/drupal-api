package com.redhat.drupal.exceptions;

public class InvalidNodeXmlException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidNodeXmlException(String msg) {
		super(msg);
	}

	public InvalidNodeXmlException() {
		super();
	}
}
