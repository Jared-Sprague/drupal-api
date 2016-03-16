package com.redhat.drupal.fields;

import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public abstract class Field {
	protected String machineName;

	public abstract void fromXml(String xml);

	protected XPathFactory xpathFactory = XPathFactory.newInstance();
	protected XPath xpath = xpathFactory.newXPath();

	public String getMachineName() {
		return machineName;
	}

	protected NodeList parseNodeList(String exp, String xml) {
		NodeList parsedNodeList = null;

		if (StringUtils.isBlank(xml)) {
			return null;
		}

		try {
			parsedNodeList = (NodeList) xpath.evaluate(exp, new InputSource(new StringReader(xml)),
					XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		if (parsedNodeList != null && parsedNodeList.getLength() == 0) {
			parsedNodeList = null;
		}

		return parsedNodeList;
	}

	protected NodeList parseNodeListFromNode(String exp, Node node) {
		NodeList parsedNodeList = null;

		try {
			parsedNodeList = (NodeList) xpath.evaluate(exp, node,
					XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		if (parsedNodeList != null && parsedNodeList.getLength() == 0) {
			parsedNodeList = null;
		}

		return parsedNodeList;
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
		sb.append(innerXml);
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
