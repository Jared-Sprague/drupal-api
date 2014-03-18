package com.redhat.drupal.fields;

import com.redhat.drupal.Utils;

public class DecimalField extends Field {
	private Float value;
	
	public DecimalField(String machineName) {
		this.machineName = machineName;
	}
	
	public DecimalField(String machineName, String xml) {
		this.machineName = machineName;
		fromXml(xml);
	}

	@Override
	public void fromXml(String xml) {
		this.value = Utils.safeNewFloat(parseField("//" + this.machineName + "//value", xml));
	}

	@Override
	protected String innerPostXml() {
		return "<value>" + this.value + "</value>";
	}

	@Override
	protected String innerAllXml() {
		// for this type there is no difference between post and all xml
		return innerPostXml();
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}
}
