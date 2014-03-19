package com.redhat.drupal.fields;

import com.redhat.drupal.Utils;

public class IntegerField extends Field {
	private Integer value;
	
	public IntegerField(String machineName) {
		this.machineName = machineName;
	}
	
	public IntegerField(String machineName, String xml) {
		this.machineName = machineName;
		fromXml(xml);
	}

	@Override
	public void fromXml(String xml) {
		this.value = Utils.safeNewInteger(parseField("//" + this.machineName + "//value", xml));
	}

	@Override
	protected String innerPostXml() {
		if (!isSet()) {
			return null;
		}
		
		return "<value>" + this.value + "</value>";
	}
	
	@Override
	public boolean isSet() {
		return this.value != null;
	}

	@Override
	protected String innerAllXml() {
		// for this type there is no difference between post and all xml
		return innerPostXml();
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	
}
