package com.prestonlee.emphasays.calculator.distance;


/**
 * A simple implementation adapted from publicly available works by Preston Lee.
 * This is fairly naïve implementation and is not time efficient, so it'll fall
 * over for long strings.
 * 
 * {@link https://en.wikipedia.org/wiki/Levenshtein_distance}
 */
public class LevenshteinDistanceCalculator implements IDistanceCalculator {

	public float calculate(final String p1, final String p2) {
		final int[] costs = new int[p2.length() + 1];
		for (int i = 0; i <= p1.length(); i++) {
			int last = i;
			for (int j = 0; j <= p2.length(); j++) {
				if (i == 0) {
					costs[j] = j;
				} else {
					if (j > 0) {
						int newValue = costs[j - 1];
						if (p1.charAt(i - 1) != p2.charAt(j - 1)) {
							newValue = Math.min(Math.min(newValue, last), costs[j]) + 1;
						}
						costs[j - 1] = last;
						last = newValue;
					}
				}
			}
			if (i > 0) {
				costs[p2.length()] = last;
			}
		}
		return costs[p2.length()];
	}

}
