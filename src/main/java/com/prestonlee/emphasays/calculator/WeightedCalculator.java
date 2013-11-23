package com.prestonlee.emphasays.calculator;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Preston Lee <preston@asu.edu>
 */
public class WeightedCalculator<T extends ICalculator> extends AbstractCalculator implements ICalculator {

	protected Set<Map.Entry<T, Float>> mCalculators = new HashSet<Map.Entry<T, Float>>();

	public void addCalculator(final T pCalculator, final float pWeight) {
		mCalculators.add(new AbstractMap.SimpleEntry<T, Float>(pCalculator, pWeight));
	}

	public float totalWeightValue() {
		float total = 0.0f;
		for (Map.Entry<T, Float> e : getCalculators()) {
			total += e.getValue();
		}
		return total;
	}

	public Set<Map.Entry<T, Float>> getCalculators() {
		return mCalculators;
	}

	public float calculate(final String pSource, final String pTarget) {
		final float totalWeight = this.totalWeightValue();
		float totalValue = 0.0f;
		float localValue;
		float normalizedWeight;
		if (totalWeight > 0.0f) { // to prevent divide-by-zero errors
			for (Map.Entry<T, Float> e : getCalculators()) {
				normalizedWeight = e.getValue() / totalWeight;
				localValue = e.getKey().calculate(pSource, pTarget);
				totalValue += localValue * normalizedWeight;
			}
		}
		return totalValue;
	}

}
