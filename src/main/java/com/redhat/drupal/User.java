package com.redhat.drupal;

import com.redhat.drupal.exceptions.InvalidUserXmlException;


public abstract class User {
	protected Integer uid;  // User ID
	protected String name;  // username
	protected String mail;  // email


	/**
	 * Parse the out the common user fields from the XML from a services GET request
	 * @param xml
	 */
	public void fromXml(String xml) {
		if (!validUserXml(xml)) {
			throw new InvalidUserXmlException("Invalid user XML: " + xml);
		}

		this.uid = Utils.safeNewInteger(Utils.parseField("/result/uid", xml));
		this.name = Utils.parseField("/result/name", xml);
		this.mail = Utils.parseField("/result/mail", xml);
	}

	private boolean validUserXml(String xml) {
		// At the minimum it should have uid, name, mail
		uid = Utils.safeNewInteger(Utils.parseField("/result/uid", xml));
		name = Utils.parseField("/result/name", xml);
		mail = Utils.parseField("/result/mail", xml);

		if (uid == null || name == null || mail == null) {
			return false;
		}

		return true;
	}

	public Integer getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}
