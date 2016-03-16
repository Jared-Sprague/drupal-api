package com.redhat.drupal.fields;
import org.w3c.dom.Node;

public class SelectTextField extends TextField {
	public SelectTextField(String machineName) {
		super(machineName);
	}

	public SelectTextField(String machineName, String xml) {
		super(machineName, xml);
	}

	public SelectTextField(String machineName, Node node) {
		super(machineName, node);
	}	
	
	@Override
	protected String innerPostXml() {
		if (!isSet()) {
			return null;
		}
		
		return "<item><value is_raw=\"true\">" + this.value + "</value></item>";
	}
}
