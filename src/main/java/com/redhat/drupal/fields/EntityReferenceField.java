package com.redhat.drupal.fields;
import org.w3c.dom.Node;

public class EntityReferenceField extends IdListField {

	private static final String VALUE_ELEMENT_NAME = "target_id";

	public EntityReferenceField(String machineName) {
		super(machineName, VALUE_ELEMENT_NAME);
	}
	
	public EntityReferenceField(String machineName, String xml) {
		super(machineName, VALUE_ELEMENT_NAME, xml);
	}

	public EntityReferenceField(String machineName, Node node) {
		super(machineName, VALUE_ELEMENT_NAME, node);
	}

}
