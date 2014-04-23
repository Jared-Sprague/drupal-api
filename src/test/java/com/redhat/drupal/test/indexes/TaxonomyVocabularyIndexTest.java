package com.redhat.drupal.test.indexes;

import java.io.File;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;

import com.redhat.drupal.entities.TaxonomyVocabulary;
import com.redhat.drupal.indexes.TaxonomyVocabularyIndex;

public class TaxonomyVocabularyIndexTest extends TestCase {
	public void testPopulateIndexfromJson() throws Exception {
		String json = FileUtils.readFileToString(new File("src/test/resources/taxonomy_vocabulary_index.json"));
		
		TaxonomyVocabularyIndex.populateIndexfromJson(json);
		List<TaxonomyVocabulary> vocabularies = TaxonomyVocabularyIndex.getVocabularies();
		TaxonomyVocabulary vocabulary = vocabularies.get(0);
		
		assertEquals(18, vocabularies.size());
		assertEquals(63, vocabulary.getVid().intValue());
		assertEquals("Userpoints", vocabulary.getName());
		assertEquals("userpoints", vocabulary.getMachine_name());
		assertEquals("Automatically created by the userpoints module", vocabulary.getDescription());
	}
}
