package com.redhat.drupal.indexes;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.redhat.drupal.entities.TaxonomyVocabulary;

public class TaxonomyVocabularyIndex {
	private static List<TaxonomyVocabulary> vocabularies = new ArrayList<TaxonomyVocabulary>();
	
	/**
	 * Populate the list of vocabularies from json
	 * @param json
	 */
	public static void populateIndexfromJson(String json) {
		Gson gson = new Gson();
		Type listType = new TypeToken<List<TaxonomyVocabulary>>(){}.getType();
		
		vocabularies = gson.fromJson(json, listType);
	}

	public static List<TaxonomyVocabulary> getVocabularies() {
		return vocabularies;
	}
}
