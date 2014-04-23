package com.redhat.drupal.test.entities;

import java.io.File;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;

import com.redhat.drupal.entities.TaxonomyTerm;

public class TaxonomyTermTest extends TestCase {
	public void testFromJson() throws Exception {
		String json = FileUtils.readFileToString(new File("src/test/resources/taxonomy_term_single.json"));
		
		TaxonomyTerm term = TaxonomyTerm.fromJson(json);
		
		assertEquals(10523, term.getTid().intValue());
		assertEquals(3, term.getVid().intValue());
		assertEquals("10-gige", term.getName());
	}
}
