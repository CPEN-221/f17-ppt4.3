package sequencepairs;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChromosomePairTest {

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
		}
		catch (IllegalArgumentException e) {
			// correct ... and nothing more to do
		}
	}

}
