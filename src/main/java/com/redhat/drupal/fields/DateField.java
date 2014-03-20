package com.redhat.drupal.fields;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

public class DateField extends Field {
	private Date value;
	private String timezone;
	private String timezoneDb;
	private String dateType;
	
	public DateField(String machineName) {
		this.machineName = machineName;
	}
	
	public DateField(String machineName, String xml) {
		this.machineName = machineName;
		fromXml(xml);
	}

	@Override
	protected String innerPostXml() {
		if (!isSet()) {
			return null;
		}
		
		// format for POST and PUT date string: 03/14/2014 - 15:15:15
		String dateString = new SimpleDateFormat("MM/dd/yyyy - HH:mm:ss", Locale.ENGLISH).format(this.value);
		return "<value><date>" + dateString + "</date></value>";
	}

	@Override
	public void fromXml(String xml) {
		// Date format from GET XML: 2014-03-14 14:15:00
		
		String dateString = parseField("//" + this.machineName + "//value", xml);
		
		// if there is no date sting then this isn't valid date field XML so ignore it
		if (StringUtils.isBlank(dateString)) {
			this.value = null;
			this.timezone = null;
			this.timezoneDb = null;
			this.dateType = null;
			return;
		}
		
		try {
			this.value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			this.value = null;
		}
		this.timezone = parseField("//" + this.machineName + "//timezone", xml);
		this.timezoneDb = parseField("//" + this.machineName + "//timezone_db", xml);
		this.dateType = parseField("//" + this.machineName + "//date_type", xml);
	}
	
	@Override
	protected String innerAllXml() {
		if (!isSet()) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(innerPostXml());
		sb.append("<timezone>").append(this.timezone).append("</timezone>");
		sb.append("<timezone_db>").append(this.timezoneDb).append("</timezone_db>");
		sb.append("<date_type>").append(this.dateType).append("</date_type>");
		return sb.toString();
	}
	
	@Override
	public boolean isSet() {
		return this.value != null;
	}

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getTimezoneDb() {
		return timezoneDb;
	}

	public void setTimezoneDb(String timezoneDb) {
		this.timezoneDb = timezoneDb;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
}
