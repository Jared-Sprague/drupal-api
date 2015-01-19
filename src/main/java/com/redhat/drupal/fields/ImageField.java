package com.redhat.drupal.fields;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.redhat.drupal.Utils;

public abstract class ImageField extends Field {
        protected Integer fid;
        protected Integer uid;
        protected String fileName;
        protected String uri;
        protected String mimeType;
        protected Integer fileSize;
        protected Integer height;
        protected Integer width;
        
	
	public ImageField(String machineName) {
		this.machineName = machineName;
	}
	
	public ImageField(String machineName, String xml) {
		this.machineName = machineName;
		fromXml(xml);
	}

	@Override
	public void fromXml(String xml) {
		if (StringUtils.isBlank(xml)) {
			return;
		}

		// Parse the term elements into a new term
		NodeList nodes = parseNodeList("//" + this.machineName + "/und/item", xml);
                if (null == nodes){
                    return;
                }
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			this.fid = Utils.safeNewInteger(Utils.parseField("./fid", node));
			this.uid = Utils.safeNewInteger(Utils.parseField("./uid", node));
			this.fileName = Utils.parseField("./fileName", node);
			this.mimeType = Utils.parseField("./filemime", node);
			this.fileSize = Utils.safeNewInteger(Utils.parseField("./filesize", node));
			this.height = Utils.safeNewInteger(Utils.parseField("./height", node));
			this.uri = Utils.parseField("./uri", node);
			this.width= Utils.safeNewInteger(Utils.parseField("./width", node));
		}
	}

	@Override
	protected String innerPostXml() {
		if (!isSet()) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		
		// Generate the XML based on the terms list
                sb.append("<item>");
                sb.append("<fid is_raw=\"true\">").append(this.getFid()).append("</fid>");
                sb.append("<uid is_raw=\"true\">").append(this.getUid()).append("</uid>");
                sb.append("<filename is_raw=\"true\">").append(this.getFileName()).append("</tid>");
                sb.append("<filesize is_raw=\"true\">").append(this.getFileSize()).append("</filesize>");
                sb.append("<filemime is_raw=\"true\">").append(this.getMimeType()).append("</filemime>");
                sb.append("<height is_raw=\"true\">").append(this.getHeight()).append("</height>");
                sb.append("<width is_raw=\"true\">").append(this.getWidth()).append("</width>");
                sb.append("<uri is_raw=\"true\">").append(this.getUri()).append("</uri>");
                sb.append("</item>");
		
		return sb.toString();
	}

	@Override
	protected String innerAllXml() {
		return innerPostXml();
	}

	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Integer getFileSize() {
		return fileSize;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
}
