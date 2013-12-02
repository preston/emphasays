package com.prestonlee.emphasays.example;

import org.apache.commons.codec.language.DoubleMetaphone;

public class MetaphoneUsage {
	public static void main(String[] args) {
		// Metaphone m = new Metaphone();
		DoubleMetaphone dm = new DoubleMetaphone();
		System.out.println(dm.doubleMetaphone("drowsey"));
		System.out.println(dm.doubleMetaphone("trazodone"));
	}
}
