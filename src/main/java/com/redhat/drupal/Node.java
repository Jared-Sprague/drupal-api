package com.redhat.drupal;

import org.apache.commons.lang.StringUtils;

import com.redhat.drupal.exceptions.InvalidNodeXmlException;
import com.redhat.drupal.fields.Field;


public abstract class Node {
	protected Integer nid;  // Node ID
	protected Integer uid;  // The users.uid that owns this node; initially, this is the user that created it.
	protected String title;  // Title of the node
	protected String log;  // last revision log message
	protected Boolean status;  // Boolean flag if this has a published revision or not
	protected Integer comment;  // how many comments does this node have
	protected Integer vid;  // current revision ID of this node
	protected String type;  // content type
	protected String language;  // language of this node and revision
	protected Integer created;  // timestamp of when this node was created
	protected Integer changed;  // timestamp of when this node was last updated
	protected Integer tnid;  // parent node id of which this node is a translation
	protected Boolean translate;   // A boolean indicating whether this translation page needs to be updated
	protected Integer revisionTimestamp;  // timestimp of the revision
	protected Integer revisionUid;  // uid of revision author
	protected String accessState;  // access state for private, retired, active
	protected String path;  // full URL path to this node
	

	/**
	 * Parse the out the common node fields from the XML from a services GET request
	 * @param xml
	 */
	public void fromXml(String xml) {
		if (!validNodeXml(xml)) {
			throw new InvalidNodeXmlException("Invalid node XML: " + xml);
		}
		
		this.nid = Utils.safeNewInteger(Utils.parseField("/result/nid", xml));
		this.uid = Utils.safeNewInteger(Utils.parseField("/result/uid", xml));
		this.title = Utils.parseField("/result/title", xml);
		this.log = Utils.parseField("/result/log", xml);
		this.status = safeNewBoolean(Utils.parseField("/result/status", xml));
		this.comment = Utils.safeNewInteger(Utils.parseField("/result/comment", xml));		
		this.vid = Utils.safeNewInteger(Utils.parseField("/result/vid", xml));
		this.type = Utils.parseField("/result/type", xml);
		this.language = Utils.parseField("/result/language", xml);
		this.created = Utils.safeNewInteger(Utils.parseField("/result/created", xml));	
		this.changed = Utils.safeNewInteger(Utils.parseField("/result/changed", xml));
		this.tnid = Utils.safeNewInteger(Utils.parseField("/result/tnid", xml));	
		this.translate = safeNewBoolean(Utils.parseField("/result/translate", xml));
		
		//TODO: move these red hat specific fields to the red hat content types library
		this.revisionTimestamp = Utils.safeNewInteger(Utils.parseField("/result/revision_timestamp", xml));	
		this.revisionUid = Utils.safeNewInteger(Utils.parseField("/result/revision_uid", xml));
		this.accessState = Utils.parseField("/result/access_state", xml);
		this.path = Utils.parseField("/result/path", xml);
	}
	
	private boolean validNodeXml(String xml) {
		// At the minimum it should have nid, type, title
		//TODO: Better xml validation. In the absense of an xsd this validation is pretty weak
		nid = Utils.safeNewInteger(Utils.parseField("/result/nid", xml));
		title = Utils.parseField("/result/title", xml);
		type = Utils.parseField("/result/type", xml);
		
		if (nid == null || title == null || type == null) {
			return false;
		}
		
		return true;
	}

	private Boolean safeNewBoolean(String booleanString) {
		if (!StringUtils.isBlank(booleanString)	&& (booleanString.equals("0") || booleanString.equals("1"))) {
			if (booleanString.equals("1")) {
				return new Boolean(true);
			} else {
				return new Boolean(false);
			}
		}
		return null;
	}

	protected void appendValidXml(StringBuffer sb, Field field) {
		if (field.isSet()) {
			sb.append(field.toPostXml());
		}
	}
	
	protected String generateCommonXml() {
		StringBuffer sb = new StringBuffer();
		if (title != null) {
			sb.append("<title>").append(this.title).append("</title>");
		}
		return sb.toString();
	}

	public Integer getNid() {
		return nid;
	}

	public Integer getUid() {
		return uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Boolean getStatus() {
		return status;
	}

	public Integer getComment() {
		return comment;
	}

	public Integer getVid() {
		return vid;
	}

	public String getType() {
		return type;
	}

	protected void setType(String type) {
		this.type = type;
	}

	public String getLanguage() {
		return language;
	}

	public Integer getCreated() {
		return created;
	}

	public Integer getChanged() {
		return changed;
	}

	public Integer getTnid() {
		return tnid;
	}

	public Boolean getTranslate() {
		return translate;
	}

	public Integer getRevisionTimestamp() {
		return revisionTimestamp;
	}

	public Integer getRevisionUid() {
		return revisionUid;
	}

	public String getAccessState() {
		return accessState;
	}

	public void setAccessState(String accessState) {
		this.accessState = accessState;
	}

	public String getPath() {
		return path;
	}
}
