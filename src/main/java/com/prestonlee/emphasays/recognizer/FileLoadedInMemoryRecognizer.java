package com.prestonlee.emphasays.recognizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import com.prestonlee.emphasays.calculator.ICalculator;

/**
 * Base class for calculators based on existing dictionaries. This
 * implementation expects a {@link File} contains a list of words, one per line,
 * and creates an in-memory dictionary. A reference to the given data file is
 * not retained. Blank and empty lines will be ignored, though all others will
 * be included *without* any processing (except for removing the newline
 * character). For example, the {@link String}s "foobar", " foobar", and
 * "FOOBAR" are all distinct and will be treated as such!
 * 
 * 
 * @author Preston Lee <preston@asu.edu>
 * 
 */
public class FileLoadedInMemoryRecognizer implements IRecognizer {

	protected final SortedSet<String> mDictionary = new TreeSet<String>();

	public SortedSet<String> getDictionary() {
		return this.mDictionary;
	}

	public FileLoadedInMemoryRecognizer(final File pFile) {
		if (pFile.canRead() && pFile.isFile() && pFile.exists()) {
			loadFile(pFile);
		} else {
			throw new IllegalArgumentException("Couldn't read file. Maybe it doesn't exist or has insufficient permissions.");
		}
	}

	protected void loadFile(final File pSource) {
		try {
			Scanner scanner = new Scanner(pSource);
			String cur;
			while (scanner.hasNext()) {
				cur = scanner.next();
				System.out.println(cur);
				getDictionary().add(cur);
			}
			scanner.close();
			// System.out.println("Loaded " + getDictionary().size() +
			// " words.");
		} catch (final FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public IGuess recognize(final String pUnknown, final ICalculator pCalculator) {
		String bestWord = null;
		float bestScore = 0f;
		if (pUnknown == null) {
			// Intentionally do nothing.
		} else if (mDictionary.contains(pUnknown)) {
			// Exact match. Sweet!
			bestWord = pUnknown;
			bestScore = 1.0f;
		} else {
			// REFACTOR: Waaaaay too slow for large dictionaries!
			float sScore;
			for (String s : mDictionary) {
				sScore = pCalculator.calculate(pUnknown, s);
				if (sScore > bestScore) {
					bestWord = s;
					bestScore = sScore;
				}
			}
		}

		RecognizerGuess guess = null;
		if (bestWord != null) {
			guess = new RecognizerGuess();
			guess.setWord(bestWord);
			guess.setScore(bestScore);
		}
		return guess;
	}

}
