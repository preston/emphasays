package com.prestonlee.emphasays.calculator.similarity;

import org.apache.lucene.search.spell.JaroWinklerDistance;

/**
 * Adapter for Lucene's implementation, provided to avoid confusion with term
 * "distance".
 * 
 * @author Preston Lee <preston@asu.edu>
 * 
 */
public class JaroWinklerSimilarityCalculator extends AbstractLuceneSimilarityAdapter<JaroWinklerDistance> {

	public JaroWinklerSimilarityCalculator() {
		mLuceneFunction = new JaroWinklerDistance();
	}

	public JaroWinklerSimilarityCalculator(final float pThreshold) {
		this();
		mLuceneFunction.setThreshold(pThreshold);
	}

}
