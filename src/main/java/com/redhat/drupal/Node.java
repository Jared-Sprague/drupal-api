package com.redhat.drupal;

public abstract class Node {
	private int nid;
	private int uid;
	private String title;
	private String log;
	private int status;
	private int comment;
	private int vid;
	private String type;
	private String language;
	private int created;
	private int changed;
	private int tnid;
	private int translate;
	private int revisionTimestamp;
	private int revisionUid;
	private String accessState;
	private String path;
	private String name;
	
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getCreated() {
		return created;
	}
	public void setCreated(int created) {
		this.created = created;
	}
	public int getChanged() {
		return changed;
	}
	public void setChanged(int changed) {
		this.changed = changed;
	}
	public int getTnid() {
		return tnid;
	}
	public void setTnid(int tnid) {
		this.tnid = tnid;
	}
	public int getTranslate() {
		return translate;
	}
	public void setTranslate(int translate) {
		this.translate = translate;
	}
	public int getRevisionTimestamp() {
		return revisionTimestamp;
	}
	public void setRevisionTimestamp(int revisionTimestamp) {
		this.revisionTimestamp = revisionTimestamp;
	}
	public int getRevisionUid() {
		return revisionUid;
	}
	public void setRevisionUid(int revisionUid) {
		this.revisionUid = revisionUid;
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
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
