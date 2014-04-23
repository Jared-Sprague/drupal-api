package com.redhat.drupal.fields;

public class TermReferenceField extends IdListField {
	private static final String VALUE_ELEMENT_NAME = "tid";
	private String vocabularyName;

	public TermReferenceField(String machineName, String vocabularyName, String xml) {
		super(machineName, VALUE_ELEMENT_NAME, xml);
		this.vocabularyName = vocabularyName;
	}

	public TermReferenceField(String machineName, String vocabularyName) {
		super(machineName, VALUE_ELEMENT_NAME);
		this.vocabularyName = vocabularyName;
	}

	public String getVocabularyName() {
		return vocabularyName;
	}

	public void setVocabularyName(String vocabularyName) {
		this.vocabularyName = vocabularyName;
	}

	public static String getValueElementName() {
		return VALUE_ELEMENT_NAME;
	}
}
