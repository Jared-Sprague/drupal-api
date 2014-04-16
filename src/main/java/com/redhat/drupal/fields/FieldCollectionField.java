package com.redhat.drupal.fields;

public class FieldCollectionField extends IdListField {
	private static final String VALUE_ELEMENT_NAME = "value";

	public FieldCollectionField(String machineName) {
		super(machineName, VALUE_ELEMENT_NAME);
	}
	
	public FieldCollectionField(String machineName, String xml) {
		super(machineName, VALUE_ELEMENT_NAME, xml);
	}
}
