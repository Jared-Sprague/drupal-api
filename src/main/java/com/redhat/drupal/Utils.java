package com.redhat.drupal;

import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class Utils {
	private static XPathFactory xpathFactory = XPathFactory.newInstance();
	private static XPath xpath = xpathFactory.newXPath();
	
	public static Integer safeNewInteger(String intString) {
		if (!StringUtils.isBlank(intString) && StringUtils.isNumeric(intString)) {
			return new Integer(intString);
		}
		return null;
	}

	public static Double safeNewDouble(String doubleString) {
		if (!StringUtils.isBlank(doubleString)) {
			try {
				return new Double(doubleString);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String parseField(String fieldName, String xml) {
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
	
	public static String parseField(String fieldName, Node node) {
		String parsedField = null;
		try {
			parsedField = xpath.evaluate(fieldName, node);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		if (StringUtils.isBlank(parsedField)) {
			parsedField = null;
		}
		return parsedField;
	}
}
