package com.redhat.drupal.fields;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.redhat.drupal.Utils;
import com.redhat.drupal.entities.TaxonomyTerm;

public class TermReferenceField extends Field {
	private List<TaxonomyTerm> terms = new ArrayList<TaxonomyTerm>();
	private String vocabularyName;
	
	public TermReferenceField(String machineName, String vocabularyName) {
		this.machineName = machineName;
		this.vocabularyName = vocabularyName;
	}

	public TermReferenceField(String machineName, String vocabularyName, String xml) {
		this.machineName = machineName;
		this.vocabularyName = vocabularyName;
		
		// parse XML into list of terms
		fromXml(xml);
	}

	public String getVocabularyName() {
		return vocabularyName;
	}

	public void setVocabularyName(String vocabularyName) {
		this.vocabularyName = vocabularyName;
	}

	@Override
	public void fromXml(String xml) {
		if (StringUtils.isBlank(xml)) {
			return;
		}

		// Parse the term elements into a new term
		NodeList nodes = parseNodeList("//" + this.machineName + "/und/item", xml);
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			String name = parseField("./name", node);
			Integer tid = Utils.safeNewInteger(parseField("./tid", node));
			Integer vid = Utils.safeNewInteger(parseField("./vid", node));
			
			// minimum required value is the tid
			if (tid != null && tid.intValue() > 0) {
				TaxonomyTerm term = new TaxonomyTerm(tid, vid, name);
				this.terms.add(term);  // add this term to the list of terms
			}
		}
	}

	@Override
	protected String innerPostXml() {
		if (!isSet()) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		
		// Generate the XML based on the terms list
		for (TaxonomyTerm term : terms) {
			sb.append("<item>");
			sb.append("<tid is_raw=\"true\">").append(term.getTid()).append("</tid>");
			sb.append("</item>");
		}
		
		return sb.toString();
	}

	@Override
	protected String innerAllXml() {
		return innerPostXml();
	}

	@Override
	public boolean isSet() {
		if (terms != null) {
			for (TaxonomyTerm term : terms) {
				if (term.getTid() != null && term.getTid() > 0) {
					// we have at least 1 tid set
					return true;
				}
			}
		}
		
		return false;
	}

	public List<TaxonomyTerm> getTerms() {
		return terms;
	}

	public void setTerms(List<TaxonomyTerm> terms) {
		this.terms = terms;
	}
}
