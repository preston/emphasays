package com.prestonlee.emphasays.example;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.prestonlee.emphasays.calculator.ICalculator;
import com.prestonlee.emphasays.calculator.WeightedCalculator;
import com.prestonlee.emphasays.calculator.distance.DamerauLevenshteinDistanceCalculator;
import com.prestonlee.emphasays.calculator.distance.IDistanceCalculator;
import com.prestonlee.emphasays.calculator.distance.LengthDifferenceCalculator;
import com.prestonlee.emphasays.calculator.distance.LevenshteinDistanceCalculator;
import com.prestonlee.emphasays.calculator.distance.MaxLengthCalculator;
import com.prestonlee.emphasays.calculator.distance.MinLengthCalculator;
import com.prestonlee.emphasays.calculator.similarity.ISimilarityCalculator;
import com.prestonlee.emphasays.calculator.similarity.JaroWinklerSimilarityCalculator;
import com.prestonlee.emphasays.calculator.similarity.LevenshteinSimilarityCalculator;
import com.prestonlee.emphasays.calculator.similarity.LuceneLevenshteinSimilarityCalculator;
import com.prestonlee.emphasays.calculator.similarity.NGramSimilarityCalculator;

/**
 * 
 * @author Preston Lee <preston@asu.edu>
 * 
 */
public class EmphasaysTutorial {

	public final static String S1_SOURCE = "emphasays";
	public final static String S1_TARGET = "emphases";
	public final static String S2_SOURCE = "olanzapine";
	public final static String S2_TARGET = "ollanzapeen";
	public final static String S3_SOURCE = "trazodone";
	public final static String S3_TARGET = "trasehdon";
	public final static String S4_SOURCE = "haha";
	public final static String S4_TARGET = "ahahaha!";

	public static void main(final String[] args) {

		// We'll start by creating a few basic string "distance" functions.
		final IDistanceCalculator length = new LengthDifferenceCalculator();
		final IDistanceCalculator levenshtein = new LevenshteinDistanceCalculator();
		final IDistanceCalculator damerauLevenshtein = new DamerauLevenshteinDistanceCalculator();
		final IDistanceCalculator min = new MinLengthCalculator();
		final IDistanceCalculator max = new MaxLengthCalculator();

		// A small sample data set is baked in for convenience, so we'll run our
		// functions against the sample data:
		System.out.println("\n======== DISTANCE CALCULATIONS ========");
		for (Map.Entry<String, String> e : exampleData()) {
			System.out.println(e.getKey() + " -> " + e.getValue());
			showCalculation(length, e.getKey(), e.getValue());
			showCalculation(levenshtein, e.getKey(), e.getValue());
			showCalculation(damerauLevenshtein, e.getKey(), e.getValue());
			showCalculation(min, e.getKey(), e.getValue());
			showCalculation(max, e.getKey(), e.getValue());
		}

		// If we want to get clever, we can use multiple algorithms together,
		// and balance their contribution weighted manner. Notice that the total
		// weight add up to more than 1.0, but that's ok as weights are
		// normalized automatically.
		final WeightedCalculator<IDistanceCalculator> weightedDistance = new WeightedCalculator<IDistanceCalculator>();
		weightedDistance.addCalculator(length, .2f);
		weightedDistance.addCalculator(levenshtein, .3f);
		weightedDistance.addCalculator(damerauLevenshtein, .3f);
		System.out.println("\n======== WEIGHTED DISTANCES ========");
		for (Map.Entry<String, String> e : exampleData()) {
			System.out.println(e.getKey() + " -> " + e.getValue());
			showCalculation(weightedDistance, e.getKey(), e.getValue());
		}

		// Now let's chance gears and compute some *similarity* values, which
		// are normalized into 0.0-1.0 scale, inclusive.
		final ISimilarityCalculator jaroWinkler = new JaroWinklerSimilarityCalculator();
		final ISimilarityCalculator levenshteinSimilarity = new LevenshteinSimilarityCalculator();
		final ISimilarityCalculator luceneLevenshteinSimilarity = new LuceneLevenshteinSimilarityCalculator();
		final ISimilarityCalculator nGram = new NGramSimilarityCalculator();
		System.out.println("\n======== SIMILARITY CALCULATIONS ========");
		for (Map.Entry<String, String> e : exampleData()) {
			System.out.println("Similarity: " + e.getKey() + " -> " + e.getValue());
			showCalculation(jaroWinkler, e.getKey(), e.getValue());
			showCalculation(levenshteinSimilarity, e.getKey(), e.getValue());
			showCalculation(luceneLevenshteinSimilarity, e.getKey(), e.getValue());
			showCalculation(nGram, e.getKey(), e.getValue());
		}

		// We can reuse the same weighting mechanism for similarity scores!
		final WeightedCalculator<ISimilarityCalculator> weightedSimilarity = new WeightedCalculator<ISimilarityCalculator>();
		weightedSimilarity.addCalculator(jaroWinkler, .6f);
		weightedSimilarity.addCalculator(levenshteinSimilarity, .4f);
		weightedSimilarity.addCalculator(luceneLevenshteinSimilarity, .2f);
		weightedSimilarity.addCalculator(nGram, .0f);
		System.out.println("\n======== WEIGHTED SIMILARITIES ========");
		for (Map.Entry<String, String> e : exampleData()) {
			System.out.println("Similarity: " + e.getKey() + " -> " + e.getValue());
			showCalculation(weightedSimilarity, e.getKey(), e.getValue());
		}

		System.out.println("\n======== Done! ========");
	}

	public static Set<Map.Entry<String, String>> exampleData() {
		Set<Map.Entry<String, String>> data = new HashSet<Map.Entry<String, String>>();
		data.add(new AbstractMap.SimpleEntry<String, String>(S1_SOURCE, S1_TARGET));
		data.add(new AbstractMap.SimpleEntry<String, String>(S2_SOURCE, S2_TARGET));
		data.add(new AbstractMap.SimpleEntry<String, String>(S3_SOURCE, S3_TARGET));
		data.add(new AbstractMap.SimpleEntry<String, String>(S4_SOURCE, S4_TARGET));
		return data;
	}

	public static void showCalculation(final ICalculator pCalculator, final String pSource, final String pTarget) {
		float result = pCalculator.calculate(pSource, pTarget);
		System.out.println("\t" + pCalculator.getClass().getSimpleName() + ":\t" + result);
	}
}
