package com.warbyparker;

public class Pattern {
	
	private static final String DELIMITER = ",";
	public static final String WILDCARD = "*";
	private static final int NOWILDCARD = -1;
	
	// Indicates a numerical wild card rank of this pattern.
	// Higher wild card ranks indicate better matches.
	// NOWILDCARD (-1) means no wild card exists in this pattern
	private int wildCardRank; 
	private String patternArray[];
	private String pattern;
	
	public String toString()
	{
		return pattern;
	}
	
	public String[] getPatternArray()
	{
		return patternArray;
	}
	
	public int getWildCardRank()
	{
		return wildCardRank;
	}
	
	public boolean hasNoWildCards()
	{
		return (wildCardRank == NOWILDCARD);
	}
	
	public Pattern(String pattern)
	{
		this.pattern = pattern;
		patternArray = pattern.split(DELIMITER);
		
		// Set wild card rank
		wildCardRank = NOWILDCARD;
		for (int i = 0; i < patternArray.length; i++)
		{
			if (patternArray[i].equals(WILDCARD))
			{
				wildCardRank = i;
				break;
			}
		}
	}
}
