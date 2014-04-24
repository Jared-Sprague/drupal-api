package com.redhat.drupal.test.indexes;

import java.io.File;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;

import com.redhat.drupal.entities.TaxonomyTerm;
import com.redhat.drupal.indexes.TaxonomyTermIndex;

public class TaxonomyTermIndexTest extends TestCase {
	public void testPopulateIndexfromJson() throws Exception {
		String json = FileUtils.readFileToString(new File("src/test/resources/taxonomy_term_index.json"));
		
		TaxonomyTermIndex.populateIndexfromJson(json);
		List<TaxonomyTerm> terms = TaxonomyTermIndex.getTerms();
		TaxonomyTerm term = terms.get(0);
		
		assertEquals(675, terms.size());
		assertEquals(10523, term.getTid().intValue());
		assertEquals(3, term.getVid().intValue());
		assertEquals("10-gige", term.getName());
	}
	
	public void testGenerateGetTreeJson() throws Exception {
		String json = TaxonomyTermIndex.generateGetTreeJson(3);
		System.out.println(json);
		assertEquals("{\"vid\":3}", json);
	}
}
