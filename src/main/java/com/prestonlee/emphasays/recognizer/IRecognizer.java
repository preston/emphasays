package com.prestonlee.emphasays.recognizer;

import com.prestonlee.emphasays.calculator.ICalculator;

/**
 * 
 * @author Preston Lee <preston@asu.edu>
 * 
 */
public interface IRecognizer {

	public IGuess recognize(final String pUnknown, final ICalculator pCalculator);
}
