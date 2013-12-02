emphasays
=========

An Open Source Java library of string distance calculators, phoneme reducers, and tools for combining said algorithms into weighted metrics for tailored name-entity recognition.

Emphasays provides a number of "ICalculator" classes divided into two primary categories:

- **IDistanceCalculator** calculators generally measure the number of changes to go from String A to String B. Distance is implied to be measured in some form of unit, such as "the distance from Phoenix to Chicago in miles". It must be a positive real number greater than or equal to zero, but does not necessarily have an upper bound.
- **ISimilarityCalculator** calculators are a normalized 0.0-1.0 metric of how alike String A is to String B, inclusive. Similarity should generally be considered unit-less, such as "I am very similar to my best friend", and has hard lower and upper bounds at 0.0 and 1.0, respectively. 

A number of algorithms are either directly included or referrenced by the POM, including Levenshtein, DamerauLevenshtein, JaroWinkler, NGram, and others, which fall in one or both of the above categories. Perhaps more importantly, however, are several special ICalculators that allow you to optimize your use of all others:

- **WeightedCalculator** combines any number of other calculators into a weighted result. 
- **PiecewiseCalculator** creates a piecewise function of any number of calculators, the selection of which is based upon an additional "selector" calculator and given min and max selection values.

Both weighted and piecewise calculators may also be combined arbitrarily to create complex results tailor to your specific needs. All these are more and demonstrated in the tutorial code at https://github.com/preston/emphasays/blob/master/src/main/java/com/prestonlee/emphasays/example/EmphasaysTutorial.java

TODO
========
- Implement dictionary-backed calculators.
- Add complex piecewise + sub-weighting example to tutorial class.

Attribution
========

This library was written by Preston Lee <preston@asu.edu> at Arizona State University and is distributed under the Apache 2 license.

Portions of this code that were based off of other F/OSS works are attributed as such. Similarity metrics delegate to existing portions of Apache Lucene where possible.

