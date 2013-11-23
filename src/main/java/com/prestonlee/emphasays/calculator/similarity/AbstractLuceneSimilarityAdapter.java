package com.prestonlee.emphasays.calculator.similarity;

import org.apache.lucene.search.spell.StringDistance;

/**
 * Adapter for Lucene's implementation, provided to avoid confusion with term
 * "distance".
 * 
 * @author Preston Lee <preston@asu.edu>
 * 
 */
public abstract class AbstractLuceneSimilarityAdapter<T extends StringDistance> implements ISimilarityCalculator {

	protected T mLuceneFunction;

	public float calculate(String pSource, String pTarget) {
		return mLuceneFunction.getDistance(pSource, pTarget);
	}
}
