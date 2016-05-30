package com.areguig.kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by akli on 29/05/16.
 */
public class StringCalculatorTest {


	@Test
	public void testAddComma() throws Exception {
		assertEquals(0, StringCalculator.add(""));
		assertEquals(1, StringCalculator.add("1"));
		assertEquals(3, StringCalculator.add("1,2"));
		assertEquals(1 + 2 + 3 + 4 + 5 + 6 + 8 + 0, StringCalculator.add("1,2,3,4,5,6,8,0"));
	}

	@Test
	public void testAddNewLine() throws Exception {
		assertEquals(0, StringCalculator.add(""));
		assertEquals(1, StringCalculator.add("1"));
		assertEquals(3, StringCalculator.add("1\n2"));
		assertEquals(1 + 2 + 3 + 4 + 5 + 6 + 8 + 0, StringCalculator.add("1\n2\n3\n4\n5\n6\n8\n0"));
	}

	@Test
	public void testAddDelimiters() throws Exception {
		assertEquals(1, StringCalculator.add("1\n"));
		assertEquals(1, StringCalculator.add("1,"));
		assertEquals(3, StringCalculator.add("1,2"));
		assertEquals(1 + 2 + 3 + 4 + 5 + 6 + 8 + 0, StringCalculator.add("1\n2\n3\n4\n5\n6\n8\n0"));
	}

	@Test
	public void testAddDelimiterOnFirstLine() throws Exception {
		assertEquals(1, StringCalculator.add("//;\n1"));

	}

	@Test
	public void testExceptionOnNegativeNumbers() {
		try {
			StringCalculator.add("-1;2;-3;19");
		} catch (Exception e) {
			assertEquals("negatives not allowed -1,-3",e.getMessage());
		}
	}

	@Test
	public void testIgnoreNumbersBiggerThan1000() throws Exception {
		assertEquals(2,StringCalculator.add("2,1000"));
		assertEquals(2+4,StringCalculator.add("2\n4\n1000"));
	}

	@Test
	public void testDelimiterAnyFormat() throws Exception{
		assertEquals(6,StringCalculator.add("//[***]\n1***2***3"));
	}

	@Test
	public void testMultiDelimiters() throws Exception{
		assertEquals(1+2+3,StringCalculator.add("//[*][%]\n1*2%3"));
		assertEquals(1+2+3,StringCalculator.add("//[---][%]\n1---2%3"));
	}
}