package com.prestonlee.emphasays.calculator;

import org.apache.commons.codec.language.Metaphone;

/**
 * Reduces arguments to their phonetic representation using the
 * {@link Metaphone} algorithm before delegating to the provided
 * {@link ICalculator}.
 * 
 * @author Preston Lee <preston@asu.edu>
 */
public class MetaphoneDelegatingCalculator extends AbstractCalculator implements ICalculator {

	protected final static Metaphone mMetaphone = new Metaphone();

	protected final ICalculator mCalculator;

	public MetaphoneDelegatingCalculator(final ICalculator pCalculator) {
		mCalculator = pCalculator;
	}

	public float calculate(final String pSource, final String pTarget) {
		if (pSource == null || pTarget == null) {
			throw new IllegalArgumentException();
		}
		String m1 = mMetaphone.metaphone(pSource);
		String m2 = mMetaphone.metaphone(pTarget);
		if (m1 == null) {
			m1 = "";
		}
		if (m2 == null) {
			m2 = "";
		}
		final float res = mCalculator.calculate(m1, m2);
		return res;
	}

}
