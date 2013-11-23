package com.prestonlee.emphasays.calculator.similarity;

import org.apache.lucene.search.spell.LevensteinDistance;

/**
 * Adapter for Lucene's implementation, provided to avoid confusion with term
 * "distance".
 * 
 * @author Preston Lee <preston@asu.edu>
 * 
 */
public class LevenshteinSimilarityCalculator extends AbstractLuceneSimilarityAdapter<LevensteinDistance> {

	public LevenshteinSimilarityCalculator() {
		mLuceneFunction = new LevensteinDistance();
	}

}
