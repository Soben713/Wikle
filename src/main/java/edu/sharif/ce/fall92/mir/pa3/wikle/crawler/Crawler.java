package edu.sharif.ce.fall92.mir.pa3.wikle.crawler;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.regex.Pattern;

import edu.sharif.ce.fall92.mir.pa3.wikle.exceptions.TooManyDocsException;
import edu.sharif.ce.fall92.mir.pa3.wikle.utils.Doc;
import edu.sharif.ce.fall92.mir.pa3.wikle.utils.DocsCollection;

public class Crawler{
	public final static Pattern urlPattern = Pattern.compile("en\\.wikipedia\\.org/wiki/[()a-zA-Z0-9_-]*");
	final String[] seeds = {"http://en.wikipedia.org/wiki/The_Raven_That_Refused_to_Sing_(And_Other_Stories)",
							"http://en.wikipedia.org/wiki/Information_retrieval"};
	Queue<Doc> frontier = new ArrayDeque<Doc>();
	public DocsCollection docsCollection;
	public static final boolean EXECUTE_JS = false;
	
	/**
	 * Crawl the web and set webGraph
	 * @return The WebGraph instance 
	 */
	public void crawl(){
		docsCollection = new DocsCollection();
		
		for(String seed: seeds)
			try{
				Doc doc = docsCollection.getOrCreateDocument(seed);
				doc.inQueue = true;
				frontier.add(doc);
			} catch(TooManyDocsException e){
				System.err.println("Too many seeds");
				return;
			}
		
		while(!frontier.isEmpty()){
			Doc doc = frontier.remove();
			doc.fetchAndParse();
			for(Doc outLink: doc.outLinks){
				if(!outLink.fetched && !outLink.inQueue){
					outLink.inQueue = true;
					frontier.add(outLink);
				}
			}
		}
	}
}
