package com.redhat.drupal.fields;

import com.redhat.drupal.Utils;

public class BooleanField extends Field {
	private Boolean value;

	public BooleanField(String machineName) {
		this.machineName = machineName;
	}

	public BooleanField(String machineName, String xml) {
		this.machineName = machineName;
		fromXml(xml);
	}

	@Override
	public void fromXml(String xml) {
		Integer intValue = Utils.safeNewInteger(Utils.parseField("//" + this.machineName + "//value", xml));
                if (intValue != null) {
                    if (intValue == 0) {
                            this.value = false;
                    } else {
                            this.value = true;
                    }
                }
	}

	@Override
	protected String innerPostXml() {
		if (!isSet()) {
			return null;
		}

		int intValue = this.value ? 1 : 0;

		return "<item><value>" + intValue + "</value></item>";
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

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}


}
