package com.prestonlee.emphasays.calculator.distance;

import com.prestonlee.emphasays.calculator.ICalculator;

/**
 * @author Preston Lee <preston@asu.edu>
 */
public interface IDistanceCalculator extends ICalculator {

	/**
	 * Returns the "distance" between two given Strings as a non-normalized
	 * count. The exact semantics of the count are defined by the
	 * implementation. May throw a {@link RuntimeException} if null is provided.
	 */
	public float calculate(final String pSource, final String pTarget);

}
