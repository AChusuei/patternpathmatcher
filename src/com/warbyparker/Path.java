package com.warbyparker;

public class Path {
	
	private static final String DELIMITER = "/";
	
	private String pathArray[];
	private String path;
	
	public String[] getPathArray()
	{
		return pathArray;
	}
	
	public String toString()
	{
		return path;
	}
	
	public Path(String path)
	{
		this.path = path;
		pathArray = trimSlashes(path).split(DELIMITER);
	}
	
	public static String trimSlashes(String s)
	{
		String temp = s;
		if (temp.charAt(0) == DELIMITER.charAt(0)) temp = temp.substring(1);
		if (temp.charAt(temp.length() - 1) == DELIMITER.charAt(0)) temp = temp.substring(0, temp.length() - 1);
		return temp;
	}
	
	public boolean match(Pattern pattern)
	{
		String[] patternArray = pattern.getPatternArray();
		// path and pattern must have the same number of entries.
		if (pathArray.length != patternArray.length) return false;
		// check respective path and pattern array, taking wild card into account.
		for (int i = 0; i < pathArray.length ; i++)
		{
			if (!patternArray[i].equals(Pattern.WILDCARD) && !pathArray[i].equals(patternArray[i])) return false;
		}
		// we ran through all entries, and no mismatches, so this is a match.
		return true;
	}
}
