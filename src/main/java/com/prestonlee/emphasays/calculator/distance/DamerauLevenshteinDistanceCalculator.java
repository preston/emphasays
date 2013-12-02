package com.prestonlee.emphasays.calculator.distance;

import java.util.TreeMap;

import com.prestonlee.emphasays.calculator.AbstractCalculator;

/**
 * Adapted from C# code on the Wikipedia page on the algorithm by Preston Lee.
 * {@link https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance}
 * 
 * @author Preston Lee <preston@asu.edu>
 */
public class DamerauLevenshteinDistanceCalculator extends AbstractCalculator implements IDistanceCalculator {

	public float calculate(final String source, final String target) {
		if (source == null || target == null) {
			throw new IllegalArgumentException();
		}
		int result;
		if (source.isEmpty() && target.isEmpty()) {
			result = 0;
		} else if (source.isEmpty()) {
			result = target.length();
		} else if (target.isEmpty()) {
			result = source.length();
		} else {

			final int[][] score = new int[source.length() + 2][target.length() + 2];

			final int inf = source.length() + target.length();
			score[0][0] = inf;
			for (int i = 0; i <= source.length(); i++) {
				score[i + 1][1] = i;
				score[i + 1][0] = inf;
			}
			for (int j = 0; j <= target.length(); j++) {
				score[1][j + 1] = j;
				score[0][j + 1] = inf;
			}

			final TreeMap<Character, Integer> sd = new TreeMap<Character, Integer>();
			final char[] chars = (source + target).toCharArray();
			for (int i = 0, n = chars.length; i < n; i++) {
				if (!sd.containsKey(chars[i]))
					sd.put(chars[i], 0);
			}

			for (int i = 1; i <= source.length(); i++) {
				int DB = 0;
				for (int j = 1; j <= target.length(); j++) {
					final int i1 = sd.get(target.charAt(j - 1)); // IS THIS
																	// RIGHT??
					int j1 = DB;

					if (source.charAt(i - 1) == target.charAt(j - 1)) {
						score[i + 1][j + 1] = score[i][j];
						DB = j;
					} else {
						score[i + 1][j + 1] = Math.min(score[i][j], Math.min(score[i + 1][j], score[i][j + 1])) + 1;
					}

					score[i + 1][j + 1] = Math.min(score[i + 1][j + 1], score[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1));
				}

				sd.put(source.charAt(i - 1), i);
			}

			result = score[source.length() + 1][target.length() + 1];
		}
		return result;
	}

}
