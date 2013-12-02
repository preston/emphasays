package com.prestonlee.emphasays.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import au.com.bytecode.opencsv.CSVReader;

import com.prestonlee.emphasays.calculator.ICalculator;
import com.prestonlee.emphasays.calculator.PiecewiseCalculator;
import com.prestonlee.emphasays.calculator.WeightedCalculator;
import com.prestonlee.emphasays.calculator.distance.DamerauLevenshteinDistanceCalculator;
import com.prestonlee.emphasays.calculator.distance.DoubleMetaphoneDelegatingCalculator;
import com.prestonlee.emphasays.calculator.distance.MetaphoneDelegatingCalculator;
import com.prestonlee.emphasays.calculator.distance.MinLengthCalculator;
import com.prestonlee.emphasays.calculator.similarity.ISimilarityCalculator;
import com.prestonlee.emphasays.calculator.similarity.JaroWinklerSimilarityCalculator;
import com.prestonlee.emphasays.calculator.similarity.LevenshteinSimilarityCalculator;
import com.prestonlee.emphasays.recognizer.FileLoadedInMemoryRecognizer;
import com.prestonlee.emphasays.recognizer.IGuess;

/**
 * 
 * Generates metrics comparision the use of basic Jaro-Winkler {@link String}
 * comparison for name-entity recognition versus using piecewise functions to
 * find more effective combinations of algorithms.
 * 
 * @author Preston Lee <preston@asu.edu>
 * 
 */
public class ProcessAnnotatedComments {

	public final static float GUESS_THRESHOLD = 0.85f;

	public static void main(String[] args) throws IOException {
		if (args.length == 4) {

			final List<String[]> annotationData = loadAnnotations(args[0]);
			final List<String> stops = loadStopWords(args[1]);
			final FileLoadedInMemoryRecognizer drugDetector = new FileLoadedInMemoryRecognizer(new File(args[2]));
			final FileWriter writer = new FileWriter(args[3]);

			writer.write("Found\tGuess\tScore\tSentence\n");
			{
				writer.write("\n\n# First, we'll use a simple JaroWinkler algorithm.\n");
				final ISimilarityCalculator jaroWinklerSimilarity = new JaroWinklerSimilarityCalculator();
				detectDrugs(drugDetector, annotationData, stops, jaroWinklerSimilarity, writer);
			}
			{
				writer.write("\n\n# Next we'll use a JaroWinkler distance only for short terms, and Metaphone -> JaroWinkler for longer terms.\n");
				final PiecewiseCalculator calculator = new PiecewiseCalculator();
				final ICalculator min = new MinLengthCalculator();
				calculator.addFunction(min, 0, 7, new JaroWinklerSimilarityCalculator());
				calculator.addFunction(min, 7, 1000, new MetaphoneDelegatingCalculator(new JaroWinklerSimilarityCalculator()));
				detectDrugs(drugDetector, annotationData, stops, calculator, writer);
			}
			{
				writer.write("\n\n# Finally, we'll use a JaroWinkler distance only for terms with strong DoubleMetaphone similarity, and weighted Levenshtein + JaraWinkler for longer Strings.\n");
				final PiecewiseCalculator calculator = new PiecewiseCalculator();
				final ICalculator min = new DoubleMetaphoneDelegatingCalculator(new DamerauLevenshteinDistanceCalculator());
				calculator.addFunction(min, 0, 2, new JaroWinklerSimilarityCalculator());
				final WeightedCalculator<ISimilarityCalculator> weightedSimilarityCalculator = new WeightedCalculator<ISimilarityCalculator>();
				weightedSimilarityCalculator.addCalculator(new LevenshteinSimilarityCalculator(), 0.5f);
				weightedSimilarityCalculator.addCalculator(new JaroWinklerSimilarityCalculator(), 0.5f);
				calculator.addFunction(min, 2, 1000, weightedSimilarityCalculator);
				detectDrugs(drugDetector, annotationData, stops, calculator, writer);
			}
			writer.close();
			System.out.println("\n======== Done! ========");
		} else {
			System.err
					.println("\nPlease specify the annotations file, stops words file, and known term list file, and output file as parameters, in that order.\n\n");
		}
	}

	protected static void detectDrugs(final FileLoadedInMemoryRecognizer pDetector, final List<String[]> annotationData, final List<String> stops,
			final ICalculator calculator, final FileWriter pWriter) throws IOException {
//		String annotatedDrug;
		String sentence;
		IGuess guess;
		final Set<String> processedSentences = new HashSet<String>(annotationData.size());
		for (String[] a : annotationData) {
//			annotatedDrug = a[1].trim().toLowerCase();
			sentence = a[3].trim().toLowerCase();
			if (processedSentences.contains(sentence)) {
				// Been there, done that. (So skip it.)
			} else {
				processedSentences.add(sentence);
				final String[] tokens = sentence.split("[\\s,\\.!/;:]+");
				for (int i = 0; i < tokens.length; i++) {
					String token = tokens[i].toLowerCase();
					// System.out.println(t);
					if (stops.contains(token)) {
						// It's a stop word, so skip it.
					} else if (pDetector.getDictionary().contains(token)) {
						// Exact match, so skip it.
					} else {
						guess = pDetector.recognize(token, calculator);
						if (guess.getScore() >= GUESS_THRESHOLD) {
							// System.out.println(annotatedDrug + ": " + token +
							// " -> " + guess.getWord() + " (" +
							// guess.getScore() +
							// "): " + sentence);
							pWriter.write(token + "\t" + guess.getWord() + "\t" + guess.getScore() + "\t" + sentence + "\n");
						}
					}
				}
			}
		}
	}

	protected static List<String[]> loadAnnotations(final String pAnnotationsFile) throws FileNotFoundException, IOException {
		System.out.println("Loading Annotations: " + pAnnotationsFile);
		final FileReader fileReader = new FileReader(new File(pAnnotationsFile));
		final CSVReader reader = new CSVReader(fileReader, '\t');
		List<String[]> myEntries = reader.readAll();
		reader.close();
		return myEntries;
	}

	protected static List<String> loadStopWords(final String pWordsFile) throws FileNotFoundException {
		System.out.println("Loading stop words: " + pWordsFile);
		final List<String> words = new ArrayList<String>();
		final Scanner scanner = new Scanner(new File(pWordsFile));
		String next;
		while (scanner.hasNext()) {
			next = scanner.nextLine().trim();
			if (!next.isEmpty() && !next.startsWith("#")) {
				words.add(next);
				// System.out.println(next);
			}
		}
		scanner.close();
		return words;
	}

}
