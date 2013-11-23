package com.prestonlee.emphasays.calculator.distance;


/**
 * @author Preston Lee <preston@asu.edu>
 */
public class LengthDifferenceCalculator implements IDistanceCalculator {

	public float calculate(final String p1, final String p2) {
		int distance = -1;
		if (p1 != null && p2 != null) {
			distance = Math.abs(p1.length() - p2.length());
		}
		return distance;
	}

}
