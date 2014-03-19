package com.redhat.drupal.fields;

import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.xml.sax.InputSource;

public abstract class Field {
	protected String machineName;

	public abstract void fromXml(String xml);

	protected XPathFactory xpathFactory = XPathFactory.newInstance();
	protected XPath xpath = xpathFactory.newXPath();

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	protected String parseField(String fieldName, String xml) {
		String parsedField = null;
		try {
			parsedField = xpath.evaluate(fieldName, new InputSource(new StringReader(xml)));
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		if (StringUtils.isBlank(parsedField)) {
			parsedField = null;
		}
		return parsedField;
	}

	/**
	 * Wrapper XML that all field types use
	 * 
	 * @param innerXml
	 * @return
	 */
	protected String formatXml(String innerXml) {
		if (innerXml == null) {
			return null;
		}
 		
		StringBuffer sb = new StringBuffer();
		sb.append("<").append(this.machineName).append(">");
		sb.append("<und is_array=\"true\">");
		sb.append("<item>");
		sb.append(innerXml);
		sb.append("</item>");
		sb.append("</und>");
		sb.append("</").append(this.machineName).append(">");
		return sb.toString();
	}

	/**
	 * Return just the XML required for POST operations
	 */
	public String toPostXml() {
		return formatXml(innerPostXml());
	}

	/**
	 * Return just the XML required for PUT operations
	 */
	public String toPutXml() {
		return toPostXml();
	}

	/**
	 * Return the complete XML representation of all properties of this text
	 * field
	 */
	public String toXml() {
		return formatXml(innerAllXml());
	}
	
	protected abstract String innerPostXml();

	protected abstract String innerAllXml();
	
	public abstract boolean isSet();

}
