package com.warbyparker.test;

import com.warbyparker.*;
import static org.junit.Assert.*;
import org.junit.*;

public class PathTests {

	@Test
	public void EnsurePathMatchesExactNonWildCardPattern() {
		Path path = new Path("alan/likes/warbyparker");
		Pattern pattern = new Pattern("alan,likes,warbyparker");
		assertTrue(path.match(pattern));
	}
	
	@Test
	public void EnsurePathMatchesAllWildCardPattern() {
		Path path = new Path("alan/likes/warbyparker");
		Pattern pattern = new Pattern("*,*,*");
		assertTrue(path.match(pattern));
	}
	
	@Test
	public void EnsurePathMatchesOneWildCardPattern() {
		Path path = new Path("alan/likes/warbyparker");
		Pattern pattern = new Pattern("alan,likes,*");
		assertTrue(path.match(pattern));
	}
	
	@Test
	public void EnsurePathDoesNotMatchesPatternWithMoreEntries() {
		Path path = new Path("alan/likes/warbyparker");
		Pattern pattern = new Pattern("alan,likes,*,*,*");
		assertFalse(path.match(pattern));
	}
	
	@Test
	public void EnsureLeadingAndTrailingSlashIsRemoved() {
		String path = "/x/y/";
		String newpath = Path.trimSlashes(path);
		assertEquals("x/y", newpath);
	}
	
	@Test
	public void EnsureLeadingSlashIsRemoved() {
		String path = "/x/y";
		String newpath = Path.trimSlashes(path);
		assertEquals("x/y", newpath);
	}
	
	@Test
	public void EnsureTrailingSlashIsRemoved() {
		String path = "x/y/";
		String newpath = Path.trimSlashes(path);
		assertEquals("x/y", newpath);
	}
	
	@Test
	public void EnsureNothingIsRemovedWhenNoLeadingTrailingSlashes() {
		String path = "x/y";
		String newpath = Path.trimSlashes(path);
		assertEquals("x/y", newpath);
	}
	
	@Test
	public void EnsurePathIsParsedCorrectly() {
		Path path = new Path("alan/likes/warbyparker");
		String patharray[] = path.getPathArray();
		assertEquals(3, patharray.length);
		assertEquals("alan", patharray[0]);
		assertEquals("likes", patharray[1]);
		assertEquals("warbyparker", patharray[2]);
	}
}
