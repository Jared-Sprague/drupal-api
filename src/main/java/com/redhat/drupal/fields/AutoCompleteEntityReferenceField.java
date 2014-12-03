package com.redhat.drupal.fields;

public class AutoCompleteEntityReferenceField extends EntityReferenceField {

	public AutoCompleteEntityReferenceField(String machineName) {
		super(machineName);
	}

	public AutoCompleteEntityReferenceField(String machineName, String xml) {
		super(machineName, xml);
	}

	@Override
	protected String innerPostXml() {
		if (!isSet()) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		
		// Generate the XML based on the entityIds list
		for (Integer entityId : entityIds) {
			// For auto complete fields in drupal it uses a regex to extract the entity_id from a string like " (123)"
			String item = String.format("<item><%s> (%d)</%s></item>", this.itemValueElementName,
					entityId.intValue(), this.itemValueElementName);
			sb.append(item);
		}
		
		return sb.toString();
	}
}
