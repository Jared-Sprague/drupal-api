package com.redhat.drupal.test.entities;

import java.io.File;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;

import com.redhat.drupal.entities.TaxonomyVocabulary;

public class TaxonomyVocabularyTest extends TestCase {
	public void testFromJson() throws Exception {
		String json = FileUtils.readFileToString(new File("src/test/resources/taxonomy_vocabulary_single.json"));
		
		TaxonomyVocabulary vocabulary = TaxonomyVocabulary.fromJson(json);
		
		assertEquals(3, vocabulary.getVid().intValue());
		assertEquals("Tags", vocabulary.getName());
		assertEquals("tags", vocabulary.getMachine_name());
		assertEquals("generic tags", vocabulary.getDescription());
	}
}
