package com.warbyparker.test;

import com.warbyparker.*;
import static org.junit.Assert.*;
import org.junit.*;

public class PatternTests {

	Pattern patternSix;
	Pattern patternTwoWildCards;
	
	@Before
	public void Setup()
	{
		patternSix = new Pattern("alan,friend,warbyparker,*,five,it");
		patternTwoWildCards = new Pattern("alan,friend,*,*,five,it");
	}
	
	@Test
	public void EnsurePatternIsParsedCorrectly() {
		String patternarray[] = patternSix.getPatternArray();
		assertEquals(6, patternarray.length);
		assertEquals("alan", patternarray[0]);
		assertEquals("friend", patternarray[1]);
		assertEquals("warbyparker", patternarray[2]);
		assertEquals("*", patternarray[3]);
		assertEquals("five", patternarray[4]);
		assertEquals("it", patternarray[5]);
	}
	
	@Test
	public void EnsureWildCardRankisRightWhenPatternHasSingleWildCard() {
		assertEquals(3, patternSix.getWildCardRank());
	}
	
	@Test
	public void EnsureLeftMostWildCardRankIsUsedWhenPatternHasTwoWildCards() {
		assertEquals(2, patternTwoWildCards.getWildCardRank());
	}
}
