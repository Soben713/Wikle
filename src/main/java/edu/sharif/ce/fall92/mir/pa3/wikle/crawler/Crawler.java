package edu.sharif.ce.fall92.mir.pa3.wikle.crawler;

import edu.sharif.ce.fall92.mir.pa3.wikle.utils.WebGraph;

public class Crawler {
	final String urlRegex = "‫‪http://en.wikipedia.org/wiki/*";
	final String[] seeds = {"http://en.wikipedia.org/wiki/Information_retrieval"};
	
	/**
	 * Crawl the web and return a WebGraph instance, starting at seeds
	 * @return The WebGraph instance 
	 */
	public WebGraph generateWebGraph(){
		return new WebGraph();
	}
}
