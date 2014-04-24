package com.redhat.drupal.indexes;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	/**
	 * Returns the json needed to POST to the getTree operation of taxonomy_vocabulary resource
	 * to get a list of terms for a given vid.
	 * @param vid - Vocabulary ID
	 * @return
	 */
	public static String generateGetTreeJson(int vid) {
		Gson gson = new Gson();
		Map<String, Integer> vidMap = new HashMap<>();
		vidMap.put("vid", vid);
		return gson.toJson(vidMap);
	}
}
