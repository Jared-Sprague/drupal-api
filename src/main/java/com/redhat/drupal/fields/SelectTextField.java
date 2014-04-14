package com.redhat.drupal.fields;

public class SelectTextField extends TextField {
	public SelectTextField(String machineName) {
		super(machineName);
	}

	public SelectTextField(String machineName, String xml) {
		super(machineName, xml);
	}	
	
	@Override
	protected String innerPostXml() {
		if (!isSet()) {
			return null;
		}
		
		return "<item><value is_raw=\"true\">" + this.value + "</value></item>";
	}
}
