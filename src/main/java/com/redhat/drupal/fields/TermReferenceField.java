package com.redhat.drupal.fields;

public class TermReferenceField extends IdListField {
	private static final String VALUE_ELEMENT_NAME = "tid";

	public TermReferenceField(String machineName, String xml) {
		super(machineName, VALUE_ELEMENT_NAME, xml);
	}

	public TermReferenceField(String machineName) {
		super(machineName, VALUE_ELEMENT_NAME);
	}
}
