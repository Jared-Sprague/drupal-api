package com.redhat.drupal.fields;

public class FieldCollectionField extends EntityReferencesField {
	public FieldCollectionField(String machineName) {
		super(machineName, "value");
	}
	
	public FieldCollectionField(String machineName, String xml) {
		super(machineName, "value", xml);
	}
}
