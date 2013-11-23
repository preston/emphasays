package com.prestonlee.emphasays.calculator.similarity;

import com.prestonlee.emphasays.calculator.ICalculator;

/**
 * @author Preston Lee <preston@asu.edu>
 */
public interface ISimilarityCalculator extends ICalculator {

	/**
	 * Calculates the similarity of two Strings, expressed as a numeric value
	 * between 0.0 and 1.0, inclusive. Values across different implementations
	 * are not comparable by definition.
	 */
	public float calculate(final String pSource, final String pTarget);

}
