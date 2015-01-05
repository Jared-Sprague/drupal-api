package com.redhat.drupal.fields;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.redhat.drupal.Utils;

public class MultiSelectTextField extends Field {
	private List<String> values = new ArrayList<String>();
	
	public MultiSelectTextField(String machineName) {
		this.machineName = machineName;
	}

	public MultiSelectTextField(String machineName, String xml) {
		this.machineName = machineName;
		
		// parse XML into list of values
		fromXml(xml);
	}

	@Override
	public void fromXml(String xml) {
		if (StringUtils.isBlank(xml)) {
			return;
		}

		// Parse the value elements into a new value
		NodeList nodes = parseNodeList("//" + this.machineName + "/und/item", xml);
                if (null == nodes){
                    return;
                }
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			String value = Utils.parseField("./value", node);
			
			if (value != null) {
				this.values.add(value);  // add this value to the list of values
			}
		}
	}

	@Override
	protected String innerPostXml() {
		if (!isSet()) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		
		// Generate the XML based on the values list
		for (String value : values) {
			sb.append("<item>");
			sb.append("<value is_raw=\"true\">").append(value).append("</value>");
			sb.append("</item>");
		}
		
		return sb.toString();
	}

	@Override
	protected String innerAllXml() {
		return innerPostXml();
	}

	@Override
	public boolean isSet() {
		if (values != null) {
			for (String value : values) {
				if (value != null) {
					// we have at least 1 tid set
					return true;
				}
			}
		}
		
		return false;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
}
