package com.semanticsquare.thrillio.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.semanticsquare.thrillio.managers.BookmarkManager;

public class WebLinkTest {

	@Test
	public void testIsKidFriendlyEligible() {
		// Test 1: Porn is url --- false
		WebLink weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger",
				"http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",
				"http://www.javaworld.com");
		boolean isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertFalse("For porn in url - isKidFriendlyEligible() must retrun false", isKidFriendlyEligible);

		// Test 2: porn in title --- false
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming porn",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertFalse("For porn in title - isKidFriendlyEligible() must retrun false", isKidFriendlyEligible);

		// Test 3 adult in host --- false
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.adult.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertFalse("For adult in host- isKidFriendlyEligible() must retrun false", isKidFriendlyEligible);

		// Test 4 adult in url, but not in host part --- true
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger",
				"http://www.javaworld.com/article/2072759/core-java/taming-adult--part-2.html",
				"http://www.javaworld.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertTrue("For adult in url but not in host - isKidFriendlyEligible() must retrun true",
				isKidFriendlyEligible);

		// Test 5 adult in title only --- true
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming adult",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertTrue("For porn in url - isKidFriendlyEligible() must retrun true", isKidFriendlyEligible);
	}

}
