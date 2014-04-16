package com.redhat.drupal.fields;

public class EntityReferenceField extends IdListField {

	private static final String VALUE_ELEMENT_NAME = "target_id";

	public EntityReferenceField(String machineName) {
		super(machineName, VALUE_ELEMENT_NAME);
	}
	
	public EntityReferenceField(String machineName, String xml) {
		super(machineName, VALUE_ELEMENT_NAME, xml);
	}

}
