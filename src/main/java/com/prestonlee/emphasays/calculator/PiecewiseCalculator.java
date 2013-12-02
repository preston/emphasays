package com.prestonlee.emphasays.calculator;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Preston Lee <preston@asu.edu>
 */
public class PiecewiseCalculator implements ICalculator {

	class PiecewiseEntry {
		protected ICalculator mSelector;
		protected ICalculator mFunction;
		protected float mSelectorMin;
		protected float mSelectorWindowSize;

		public PiecewiseEntry(final ICalculator pSelector, final float pSelectorMin, final float pSelectorWindowSize, final ICalculator pFunction) {
			mSelector = pSelector;
			mFunction = pFunction;
			mSelectorMin = pSelectorMin;
			mSelectorWindowSize = pSelectorWindowSize;
		}

		public ICalculator getSelector() {
			return mSelector;
		}

		public ICalculator getFunction() {
			return mFunction;
		}

		public float getSelectorMin() {
			return mSelectorMin;
		}

		public float getSelectorWindowSize() {
			return mSelectorWindowSize;
		}

	}

	protected Set<PiecewiseEntry> mPieces = new HashSet<PiecewiseEntry>(2);

	/**
	 * Adds a sub- {@link ICalculator} to the piecewise calculator. Note: It is
	 * your responsibility to use a non-overlapping set of filter values. If you
	 * end up setting overlapping values, the calculator that matches first
	 * wins!
	 * 
	 * @param pSelector
	 *            An {@link ICalculator} that determines when the function
	 *            applies.
	 * @param pSelectorMin
	 *            The minimum filter value that causes the selector to match,
	 *            inclusive.
	 * @param pSelectorWindowSize
	 *            This plus the min value determines the max value that causes
	 *            the selector to match, exclusive.
	 * @param pCalculator
	 *            The {@link ICalculator} to apply when the selector function
	 *            matches.
	 */
	public void addFunction(final ICalculator pSelector, final float pSelectorMin, final float pSelectorWindowSize, final ICalculator pCalculator) {
		mPieces.add(new PiecewiseEntry(pSelector, pSelectorMin, pSelectorWindowSize, pCalculator));
	}

	public float calculate(final String pSource, final String pTarget) {
		float result = .0f;
		float filterValue;
		for (PiecewiseEntry p : mPieces) {
			filterValue = p.getSelector().calculate(pSource, pTarget);
			if (filterValue >= p.getSelectorMin() && filterValue < (p.getSelectorMin() + p.getSelectorWindowSize())) {
//				System.out.println("\tUsing " + p.getFunction().getClass().getSimpleName());
				result = p.getFunction().calculate(pSource, pTarget);
				break;
			}
		}
		return result;
	}
}
