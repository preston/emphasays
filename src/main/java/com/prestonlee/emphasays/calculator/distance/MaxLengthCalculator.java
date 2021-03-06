package com.prestonlee.emphasays.calculator.distance;

import com.prestonlee.emphasays.calculator.AbstractCalculator;

/**
 * Returns the length of the longest given {@link String}.
 * 
 * @author Preston Lee <preston@asu.edu>
 */
public class MaxLengthCalculator extends AbstractCalculator implements IDistanceCalculator {

	public float calculate(final String pSource, final String pTarget) {
		if (pSource == null || pTarget == null) {
			throw new IllegalArgumentException();
		}
		int max = Math.max(pSource.length(), pTarget.length());
		return max;
	}

}
