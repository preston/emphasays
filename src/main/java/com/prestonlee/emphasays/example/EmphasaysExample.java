package com.prestonlee.emphasays.example;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.prestonlee.emphasays.calculator.ICalculator;

public abstract class EmphasaysExample {

	public final static String S1_SOURCE = "emphasays";
	public final static String S1_TARGET = "emphases";
	public final static String S2_SOURCE = "olanzapine";
	public final static String S2_TARGET = "ollanzapeen";
	public final static String S3_SOURCE = "trazodone";
	public final static String S3_TARGET = "trasehdon";
	public final static String S4_SOURCE = "haha";
	public final static String S4_TARGET = "ahahaha!";

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
