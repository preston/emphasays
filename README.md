emphasays
=========

An Open Source Java library of string distance calculators, similarity algorithms, phoneme reducers, dictionary-backed recognition tools, and other classes for combining said components into weighted metrics for tailored named-entity recognition.

Calculators
--------

Emphasays provides a number of "ICalculator" classes divided into two primary categories:

- **IDistanceCalculator** calculators generally measure the number of changes to go from String A to String B. Distance is implied to be measured in some form of unit, such as "the distance from Phoenix to Chicago in miles". It must be a positive real number greater than or equal to zero, but does not necessarily have an upper bound.
- **ISimilarityCalculator** calculators are a normalized 0.0-1.0 metric of how alike String A is to String B, inclusive. Similarity should generally be considered unit-less, such as "I am very similar to my best friend", and has hard lower and upper bounds at 0.0 and 1.0, respectively. 

Both interfaces are "markers" only and share a common **ICalculator** parent, though you should excercise caution when mixing them due to the subtle but important different in semantics.

A number of both distance and similarity algorithms are either directly included or referrenced by the POM, including Levenshtein, DamerauLevenshtein, JaroWinkler, NGram, and others. Most of the *distance* algorithms are ported from public works, while the *similarity* measurements generally adapt those bundled with Apache Lucene. Perhaps more importantly, however, are several special ICalculators that allow you to optimize your use of all others:

- **WeightedCalculator** combines any number of other calculators into a weighted result. 
- **PiecewiseCalculator** creates a piecewise function of any number of calculators, the selection of which is based upon an additional "selector" calculator and given min and max selection values.

Both weighted and piecewise calculators may also be combined arbitrarily to create complex results tailor to your specific needs. All these are more and demonstrated in the tutorial code at https://github.com/preston/emphasays/blob/master/src/main/java/com/prestonlee/emphasays/example/EmphasaysTutorial.java

Recognizers
--------

An **IRecognizer** (such as FileLoadedInMemoryRecognizer) uses an internal dictionary to look for the word most closely matching a provided token using a provided ICalculator for individual comparisons. Using combinations of weighted and piecewise calculators (see above), you can create complex dictionary-backed token recognition systems tuned to your specific lexical needs. Here are a few example to get you thinking:

- Build an acronym detectors using the first few syllables of a word (phonetic reduction) that is then truncated and fed through a string distance algorithm and weighted with raw distance to determine if A is really an acronym for B.
- Determine the most common misspellings of a set of words by filtering out exact matches and sorting by recognizer scores.
- Find a known list of known names in a large block of text.
- Combine Jaro-Winkler (better for short strings) with Damerau-Levenshtein distance calculators in a piecewise function discriminated by mininum string length to build your own more accurate distance algorithm.


Installation
--------

If you're using Maven, try jamming this into your ~/.m2/settings.xml

    <settings>
      ...
     	<repositories>
    		<repository>
    			<snapshots>
    				<enabled>false</enabled>
    			</snapshots>
    			<id>central</id>
    			<name>bintray</name>
    			<url>http://dl.bintray.com/preston/emphasays</url>
    		</repository>
    	</repositories>
    </settings>    

..then, update your pom.xml...

    <project>
        ...
      <dependencies>
    		<dependency>
    			<groupId>com.prestonlee</groupId>
    			<artifactId>emphasays</artifactId>
    			<version>0.2.0</version>
    		</dependency>
    		<dependency>
    			<groupId>net.sf.opencsv</groupId>
    			<artifactId>opencsv</artifactId>
    			<version>2.3</version>
    		</dependency>
    	</dependencies>
    </project>


See the example referenced above for sample code.


Extending Emphasays
========

With the Inversion of Control design used in cases such as **IRecognizer** and delegating **ICalculator**s, you may mix and match your own algorithms alongside those provided. Several abstract parent classes are also provided for your convenience.

Class Diagram
--------
![Emphasays class diagram](https://raw.github.com/preston/emphasays/master/Emphasays%20Class%20Diagram.png)

Attribution
========

This library was written by Preston Lee <preston@asu.edu> at Arizona State University and is distributed under the Apache 2 license.

Portions of this code that were based off of other F/OSS works are attributed as such. Similarity metrics delegate to existing portions of Apache Lucene where possible.

