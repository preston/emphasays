package com.prestonlee.emphasays.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.prestonlee.emphasays.calculator.distance.LevenshteinDistanceCalculator;
import com.prestonlee.emphasays.example.EmphasaysTutorial;

public class TestLevenshtheinCalculator {

	@Test
	public void testDistance() {
		final LevenshteinDistanceCalculator calc = new LevenshteinDistanceCalculator();
		int diff = (int)calc.calculate(EmphasaysTutorial.S1_SOURCE, EmphasaysTutorial.S1_TARGET);
		assertSame(2, diff);
		diff = (int)calc.calculate(EmphasaysTutorial.S2_SOURCE, EmphasaysTutorial.S2_TARGET);
		assertSame(4, diff);
		
	}

}
