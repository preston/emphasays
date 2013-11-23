package com.prestonlee.emphasays.calculator;

/**
 * @author Preston Lee <preston@asu.edu>
 */
public abstract class AbstractCalculator implements ICalculator {

	public boolean nullOrEmpty(final String pString) {
		return pString == null || pString.isEmpty();
	}

}
