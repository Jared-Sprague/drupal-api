package com.redhat.drupal;

import org.apache.commons.lang.StringUtils;

public class Utils {
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
}
