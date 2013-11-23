package com.prestonlee.emphasays.calculator.distance;

import com.prestonlee.emphasays.calculator.AbstractCalculator;

/**
 * @author Preston Lee <preston@asu.edu>
 */
public class MinLengthCalculator extends AbstractCalculator implements IDistanceCalculator {

	public float calculate(final String pSource, final String pTarget) {
		if (pSource == null || pTarget == null) {
			throw new IllegalArgumentException();
		}
		int max = Math.min(pSource.length(), pTarget.length());
		return max;
	}

}
