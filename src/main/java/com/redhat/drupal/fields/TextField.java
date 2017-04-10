package com.redhat.drupal.fields;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.redhat.drupal.Utils;
import org.w3c.dom.Node;


public class TextField extends Field {
	protected String value;
	private String safeValue;
	private String format;

	public TextField(String machineName) {
		this.machineName = machineName;
	}

	public TextField(String machineName, String xml) {
		this.machineName = machineName;
		fromXml(xml);
	}

	public TextField(String machineName, Node node) {
		this.machineName = machineName;
		fromNode(node);
	}

	@Override
	protected String innerPostXml() {
		if (!isSet()) {
			return null;
		}

		String xml = "<item><value>" + StringEscapeUtils.escapeXml(this.value) + "</value>";
		if (!StringUtils.isBlank(format)) {
			xml += "<format>" + this.format + "</format>";
		}
		xml += "</item>";

		return xml;
	}

	@Override
	protected String innerAllXml() {
		if (!isSet()) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		sb.append(innerPostXml());
		sb.append("<safe_value>").append(StringEscapeUtils.escapeXml(this.safeValue)).append("</safe_value>");
		sb.append("<format>").append(this.format).append("</format>");
		return sb.toString();
	}

	@Override
	public void fromXml(String xml) {
		this.value = Utils.parseField("//" + this.machineName + "//value", xml);
		this.safeValue = Utils.parseField("//" + this.machineName + "//safe_value", xml);
		this.format = Utils.parseField("//" + this.machineName + "//format", xml);
	}

	public void fromNode(Node node) {
		this.value = Utils.parseField("//" + this.machineName + "//value", node);
		this.safeValue = Utils.parseField("//" + this.machineName + "//safe_value", node);
		this.format = Utils.parseField("//" + this.machineName + "//format", node);
	}

	@Override
	public boolean isSet() {
		return this.value != null;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSafeValue() {
		return safeValue;
	}

	public void setSafeValue(String safeValue) {
		this.safeValue = safeValue;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
