package com.redhat.drupal;

import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
	/* This method is copied from TermReferenceField class 
         * so that it is available to all  other class. 
         * This method is still present in TermReferenceField,
         * Author will decide to keep the duplicate copies or not.   
          */
        
        public static NodeList parseNodeList(String exp, String xml) {
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
        
        
}
