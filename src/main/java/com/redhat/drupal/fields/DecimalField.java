package com.redhat.drupal.fields;

import com.redhat.drupal.Utils;

public class DecimalField extends Field {
	private Double value;
	
	public DecimalField(String machineName) {
		this.machineName = machineName;
	}
	
	public DecimalField(String machineName, String xml) {
		this.machineName = machineName;
		fromXml(xml);
	}

	@Override
	public void fromXml(String xml) {
		this.value = Utils.safeNewDouble(parseField("//" + this.machineName + "//value", xml));
	}

	@Override
	protected String innerPostXml() {
		if (!isSet()) {
			return null;
		}
		
		return "<value>" + this.value + "</value>";
	}

	@Override
	protected String innerAllXml() {
		// for this type there is no difference between post and all xml
		return innerPostXml();
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	@Override
	public boolean isSet() {
		return this.value != null;
	}
}
