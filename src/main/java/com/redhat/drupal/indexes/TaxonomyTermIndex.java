package com.redhat.drupal.indexes;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.redhat.drupal.entities.TaxonomyTerm;

public class TaxonomyTermIndex {
	private static List<TaxonomyTerm> terms = new ArrayList<TaxonomyTerm>();
	
	/**
	 * Populate the list of taxonomy terms from json
	 * @param json
	 */
	public static void populateIndexfromJson(String json) {
		Gson gson = new Gson();
		Type listType = new TypeToken<List<TaxonomyTerm>>(){}.getType();
		
		terms = gson.fromJson(json, listType);
	}

	public static List<TaxonomyTerm> getTerms() {
		return terms;
	}
}
