import java.util.*;
import java.io.*;
import com.warbyparker.*;

public class Main {

	private static List<Pattern> allPatterns;
	private static List<Path> allPaths;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		allPatterns = new LinkedList<Pattern>();
		allPaths = new LinkedList<Path>();
		if (args.length > 0 && args[0].equals("-e"))
		{
			easyInitialize(); // run this if you don't want to have to input everything from the console prompt
		}
		else
		{
			if (!manualInitialize()) return; // run this if you are you going to type everything in the command prompt
		}
		for (Path path : allPaths)
		{
			Pattern bestPatternMatch = null;
			boolean exactmatch = false;
			for (Pattern pattern : allPatterns)
			{
				if (path.match(pattern))
				{
					// With no wild cards this is an exact match, we have found the pattern we're looking for.
					if (pattern.hasNoWildCards())
					{
						bestPatternMatch = pattern;
						exactmatch = true;
						break;
					}
					// If this is a wild card match, then we set this as the pattern only if it's the first pattern match we've found, 
					// Or if one has been found already, this pattern's wild card rank has to be better than that of the best one found so far.
					else if (bestPatternMatch == null || pattern.getWildCardRank() > bestPatternMatch.getWildCardRank())
					{
						// With some wild cards in the pattern, we may have a better match down the line, so can't quit yet.
						bestPatternMatch = pattern;
					}
				}
			}
			
			// System.out.println("Path '" + path.toString() + (exactmatch ? "' exactly matches " : "' best matches ") + "Pattern '" + (bestPatternMatch == null ? "NO MATCH" : bestPatternMatch.toString()) + "'");
			System.out.println(bestPatternMatch == null ? "NO MATCH" : bestPatternMatch.toString());
		}
	}
	
	private static boolean manualInitialize()
	{
		try
		{
			DataInput in = new DataInputStream(System.in);
			int numPatterns = Integer.parseInt(in.readLine());
			for (int i = 0; i < numPatterns; i++)
			{
				allPatterns.add(new Pattern(in.readLine()));
			}
			int numPaths = Integer.parseInt(in.readLine());
			for (int i = 0; i < numPaths; i++)
			{
				allPaths.add(new Path(in.readLine()));
			}
			return true;
		}
		catch (IOException ioe)
		{
			System.out.println("error occured inputting information for the parser: " + ioe.getMessage());
			return false;
		}
	}
	
	private static void easyInitialize()
	{
		allPatterns.add(new Pattern("*,*,c"));
		allPatterns.add(new Pattern("A,*,B,*,C"));
		allPatterns.add(new Pattern("users"));
		allPatterns.add(new Pattern("users,*"));
		allPatterns.add(new Pattern("*,b,*"));
		allPatterns.add(new Pattern("users,*,purchases"));
		allPatterns.add(new Pattern("users,*,purchases,*"));
		allPatterns.add(new Pattern("x,*,*"));
		allPatterns.add(new Pattern("home"));
		allPatterns.add(new Pattern("*"));
		allPatterns.add(new Pattern("*,*,z"));
		allPatterns.add(new Pattern("search,*,*"));
		allPatterns.add(new Pattern("*,gallery,*"));
		allPatterns.add(new Pattern("a,*,*"));
		
		allPaths.add(new Path("/users/1234"));
		allPaths.add(new Path("/users/1234/purchases"));
		allPaths.add(new Path("/users"));
		allPaths.add(new Path("/users/1234/purchases/9999"));
		allPaths.add(new Path("about"));
		allPaths.add(new Path("/about/jobs"));
		allPaths.add(new Path("a/b/c"));
		allPaths.add(new Path("/home/"));
		allPaths.add(new Path("x/y/z/"));
		allPaths.add(new Path("/privacy"));
		allPaths.add(new Path("/search/gallery/private"));
		allPaths.add(new Path("/users/gallery/234232"));
		allPaths.add(new Path("A/foo/B/bar/C"));
		allPaths.add(new Path("A/123/B/456/C"));
		allPaths.add(new Path("A/B/C"));
		allPaths.add(new Path("A/foo/bar/B/baz/C"));
		allPaths.add(new Path("foo/B/bar/C"));
	}
}
