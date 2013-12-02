package com.prestonlee.emphasays.calculator;

import org.apache.commons.codec.language.DoubleMetaphone;

/**
 * Reduces arguments to their phonetic representation using the
 * {@link DoubleMetaphone} algorithm before delegating to the provided
 * {@link ICalculator}.
 * 
 * @author Preston Lee <preston@asu.edu>
 */
public class DoubleMetaphoneDelegatingCalculator extends AbstractCalculator implements ICalculator {

	protected final static DoubleMetaphone mDoubleMetaphone = new DoubleMetaphone();

	protected final ICalculator mCalculator;

	public DoubleMetaphoneDelegatingCalculator(final ICalculator pCalculator) {
		mCalculator = pCalculator;
	}

	public float calculate(final String pSource, final String pTarget) {
		if (pSource == null || pTarget == null) {
			throw new IllegalArgumentException();
		}
		 String m1 = mDoubleMetaphone.doubleMetaphone(pSource);
		String m2 = mDoubleMetaphone.doubleMetaphone(pTarget);
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