package com.prestonlee.emphasays.calculator.similarity;

import org.apache.lucene.search.spell.NGramDistance;

public class NGramSimilarityCalculator extends AbstractLuceneSimilarityAdapter<NGramDistance> {

	/** N-gram size of 2. */
	public NGramSimilarityCalculator() {
		mLuceneFunction = new NGramDistance();
	}

	/** To manually set the n-gram size. */
	public NGramSimilarityCalculator(final int pSize) {
		mLuceneFunction = new NGramDistance(pSize);
	}

}
