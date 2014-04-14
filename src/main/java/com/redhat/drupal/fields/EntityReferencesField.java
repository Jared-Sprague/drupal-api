package com.redhat.drupal.fields;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.NodeList;

public class EntityReferencesField extends Field {
	private List<Integer> entityIds = new ArrayList<Integer>(); 
	private String itemValueElementName;
	
	public EntityReferencesField(String machineName, String itemValueElementName) {
		this.machineName = machineName;
		this.itemValueElementName = itemValueElementName;
	}
	
	public EntityReferencesField(String machineName, String itemValueElementName, String xml) {
		this.machineName = machineName;
		this.itemValueElementName = itemValueElementName;
		fromXml(xml);
	}

	@Override
	public void fromXml(String xml) {
		if (StringUtils.isBlank(xml)) {
			return;
		}

		NodeList nodes = parseNodeList("//" + this.machineName + "/und/item", xml);
		//TODO: finish implementing
	}

	@Override
	protected String innerPostXml() {
		if (!isSet()) {
			return null;
		}
		
		//TODO: actually implement this to generate the real list based on entityIds
		return "<item><" + this.itemValueElementName + ">123</" + this.itemValueElementName + "></item>";
	}

	@Override
	protected String innerAllXml() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSet() {
		return (this.entityIds != null);
	}

	public List<Integer> getEntityIds() {
		return entityIds;
	}

	public void setEntityIds(List<Integer> entityIds) {
		this.entityIds = entityIds;
	}
}
