package com.prestonlee.emphasays.calculator.similarity;

import org.apache.lucene.search.spell.LuceneLevenshteinDistance;

/**
 * Adapter for Lucene's implementation, provided to avoid confusion with term
 * "distance".
 * 
 * @author Preston Lee <preston@asu.edu>
 * 
 */
public class LuceneLevenshteinSimilarityCalculator extends AbstractLuceneSimilarityAdapter<LuceneLevenshteinDistance> {

	public LuceneLevenshteinSimilarityCalculator() {
		mLuceneFunction = new LuceneLevenshteinDistance();
	}

}
