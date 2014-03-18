package com.redhat.drupal;

import org.apache.commons.lang.StringUtils;

public class Utils {
	public static Integer safeNewInteger(String intString) {
		if (!StringUtils.isBlank(intString) && StringUtils.isNumeric(intString)) {
			return new Integer(intString);
		}
		return null;
	}

	public static Float safeNewFloat(String floatString) {
		if (!StringUtils.isBlank(floatString)) {
			try {
				return new Float(floatString);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
