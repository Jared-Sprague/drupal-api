package com.redhat.drupal.fields;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.redhat.drupal.Utils;

public abstract class IdListField extends Field {
	private List<Integer> entityIds = new ArrayList<Integer>(); 
	private String itemValueElementName;
	
	public IdListField(String machineName, String itemValueElementName) {
		this.machineName = machineName;
		this.itemValueElementName = itemValueElementName;
	}
	
	public IdListField(String machineName, String itemValueElementName, String xml) {
		this.machineName = machineName;
		this.itemValueElementName = itemValueElementName;
		fromXml(xml);
	}

	@Override
	public void fromXml(String xml) {
		if (StringUtils.isBlank(xml)) {
			return;
		}

		// Parse the list of IDs into the entityIds list
		NodeList nodes = parseNodeList("//" + this.machineName + "/und/item", xml);
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			Integer value = Utils.safeNewInteger(parseField("./" + this.itemValueElementName, node));
			if (value != null) {
				entityIds.add(value);
			}
		}
	}

	@Override
	protected String innerPostXml() {
		if (!isSet()) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		
		// Generate the XML based on the entityIds list
		for (Integer entityId : entityIds) {
			String item = String.format("<item><%s>%d</%s></item>", this.itemValueElementName,
					entityId.intValue(), this.itemValueElementName);
			sb.append(item);
		}
		
		return sb.toString();
	}

	@Override
	protected String innerAllXml() {
		return innerPostXml();
	}

	@Override
	public boolean isSet() {
		return (this.entityIds != null && entityIds.size() > 0);
	}

	public List<Integer> getEntityIds() {
		return entityIds;
	}

	public void setEntityIds(List<Integer> entityIds) {
		this.entityIds = entityIds;
	}
}
