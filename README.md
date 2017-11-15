CPEN 221 / Fall 2017

Programming Proficiency Test
=========

November 15, 2017

## General Instructions

+ You have 72 minutes (1h 12m) to complete the assigned tasks.
+ Take your time to read the question.
+ Skeleton code can be obtained by cloning this repository. Add JUnit to your build path in Eclipse.
+ Best of luck!

## Submission Instructions

+ Submit your work to the Github classroom repository that was created for your.
+ **Do not alter the directory/folder structure. You should retain the structure as in this repository.**
+ Do not wait until the last minute to push your work to Github. It is a good idea to push your work at intermediate points as well. _I would recommend that you get your Git and Github workflow set up at the start._

## Honour Code

By submitting your work to Github you agree to the following:

+ You did not consult with any other person in completing the examination.
+ You did not aid any other person in the class in completing their examination.
+ If you consulted any external sources, such as resources available on the World Wide Web, in completing the examination then you have cited the source. (You do not need to cite class notes or Sun/Oracle Java documentation.)
+ You are not aware of any infractions of the honour code for this examination.

> Violations of this honour code will be treated as a case of academic misconduct and will dealt with under UBC policies governing such issues. A consequence of this may be to nullify this exam for everyone that submits work for grading!

## Question: Abstract Sequence Pairs and Chromosome Sequences
> The skeleton source code for this question is in the package `sequencepairs`. You may import the provided code as a Gradle project in Eclipse.

An `AbstractSequencePair` is a datatype that represents a two equal-length `char` sequences, `x` and `y`. (From a Java perspective, this is an `abstract` class and cannot be directly instantiated.) It has four methods:

1. (mutator -- to be implemented) `void swapChars(int index)` that allows one to swap the characters in the sequences `x` and `y` at the given `index`;
2. (observer -- provided) `String getXString()` that returns a `String` representation of the `x` sequence;
3. (observer -- provided) `String getYString()` that returns a `String` representation of the `y` sequence;
4. (observer) `int similarity()` that returns an `int` which represents the **similarity** between the `x` and `y` sequences. (From a Java perspective, this is an `abstract` method and **needs** to be implemented by any class that extends the `AbstractSequencePair` class.)

You have to complete the `abstract` class implementation above and then create a subtype `ChromosomePair` with the following requirements:

1. A `ChromosomePair` is an `AbstractSequencePair` with the restriction that the characters in the `x` or `y` sequences are from the set `{A, C, G, T}`.
2. A `ChromosomePair` can be mutated using a method called `crossover`. The method signature is `void crossover(int crossoverLength)`. This method mutates `ChromosomePair` as follows: the first part (the length is `crossoverLength`) of the `x` sequence is replaced with the first part of the `y` sequence, and the first part of the `y` sequence is replaced by the corresponding portion of the `x` sequence as it was prior to the call to `crossover`. The precondition for this method is that `0 <= crossoverLength <= x.length`. For example: If `x` is "ATCG" and `y` is "GGCC" then `crossover(2)` will result in `x` as "GGCG" and `y` as "ATCC".
3. The similarity between the `x` and `y` sequences in a `ChromosomePair` is defined as the length of longest common substring shared by `x` and `y`. If `x` is "AATTTA" and `y` is "GGATTGG" then the similarity is 3 (with "ATT" as the longest common substring).
4. A `ChromosomePair` can only be created using a `static` method called `createChromosomePair`. This method can use a `private` constructor to create a `ChromosomePair` but this approach is needed because `x` and `y` should be of the same length and should only contain characters in the set `{A, C, G, T}`. This method should throw an `IllegalArgumentException` if the arguments `x` and `y` do not follow the requirements for `ChromosomePair`. The signature for this method is `ChromosomePair createChromosomePair(char[] x, char[] y)`. Remember that this is a `static` method and the last line in this method is likely to be `return new ChromosomePair(x, y);` if you used a private constructor.

### Test Cases

```java
@Test
public void test1() {
	char[] x = "ATCG".toCharArray();
	char[] y = "ATCA".toCharArray();
	AbstractSequencePair cp = ChromosomePair.createChromosomePair(x, y);
	assertEquals(3, cp.similarity());
}

@Test
public void test2() {
	char[] x = "AGCGG".toCharArray();
	char[] y = "ATGCC".toCharArray();
	ChromosomePair cp = ChromosomePair.createChromosomePair(x, y);
	cp.crossover(2);
	assertEquals("ATCGG", cp.getXString());
	assertEquals("AGGCC", cp.getYString());
	assertEquals(2, cp.similarity());
}

@Test
public void test3() {
	char[] x = "AACGG".toCharArray();
	char[] y = "ATGCC".toCharArray();
	AbstractSequencePair cp = ChromosomePair.createChromosomePair(x, y);
	assertEquals(1, cp.similarity());
}

@Test
public void test4() {
	char[] x = "GGGGGG".toCharArray();
	char[] y = "AAAAAA".toCharArray();
	AbstractSequencePair cp = ChromosomePair.createChromosomePair(x, y);
	assertEquals(0, cp.similarity());
}

@Test
public void test5() {
	char[] x = "GGGGGG".toCharArray();
	char[] y = "AAAAAA".toCharArray();
	ChromosomePair cp = ChromosomePair.createChromosomePair(x, y);
	cp.crossover(3);
	assertEquals("AAAGGG", cp.getXString());
	assertEquals(3, cp.similarity());
}

@Test
public void test6() {
	char[] x = "GGGGGG".toCharArray();
	char[] y = "AAAAAA".toCharArray();
	ChromosomePair cp = ChromosomePair.createChromosomePair(x, y);
	cp.crossover(4);
	assertEquals("GGGGAA", cp.getYString());
	assertEquals(2, cp.similarity());
}

@Test
public void test7() {
	char[] x = "GGGGGGG".toCharArray();
	char[] y = "AAAAAA".toCharArray();

	try {
		ChromosomePair cp = ChromosomePair.createChromosomePair(x, y);
		fail("Should have seen an exception");
	} catch (IllegalArgumentException e) {
		// correct ... and nothing more to do
	}
}

@Test
public void test8() {
	char[] x = "GGATTGG".toCharArray();
	char[] y = "AAAGATT".toCharArray();
	ChromosomePair cp = ChromosomePair.createChromosomePair(x, y);
	assertEquals(4, cp.similarity());
}
```

## What Should You Implement / Guidelines

+ You should implement all the methods that are indicated with `TODO`.
+ Passing the provided tests is the minimum requirement. Use the tests to identify cases that need to be handled. Passing the provided tests is *not sufficient* to infer that your implementation is complete and that you will get full credit. Additional tests will be used to evaluate your work. The provided tests are to guide you.
+ You can implement additional helper methods if you need to but you should keep these methods `private` to the appropriate classes.
+ You do not need to implement new classes **unless asked to**.
+ You can use additional standard Java libraries by importing them.
+ Do not throw new exceptions unless the specification for the method permits exceptions.

## Answers to FAQs

#### Can I consult Java documentation and other Internet-based sources?

Yes, you can. The point of this test is not to demonstrate mastery over syntax but that you can solve a problem in a reasonable amount of time with reasonable resources.

*If you find useful information online outside the official Java documentation and the course material, you must cite the source. You should do so by adding comments in your source code.*

Naturally you are expected to adhere to all of the course and UBC policies on academic integrity.

#### Isn't one hour too short to produce a working implementation?

The questions are straightforward, and these are not very different from what one might sometimes encounter on a job interview (for example). The difference is that you get less time during an interview (10-15 minutes) with no access to additional resources. So the time allotted is reasonable in that regard and I am expecting that everyone will be able to clear this bar. The goal is that it is possible to say, at a minimal level, what everyone who completes this course can achieve.

#### Why am I not guaranteed full credit if my implementation passes all the provided tests?

It is easy to develop an implementation that passes the provided tests and not much else. A good-faith implementation that passes all the provided tests is very likely to pass other tests too.
