package edu.sharif.ce.fall92.mir.pa3.wikle.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.sharif.ce.fall92.mir.pa3.wikle.crawler.Crawler;
import edu.sharif.ce.fall92.mir.pa3.wikle.exceptions.TooManyDocsException;

public class Doc implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7050526803205940857L;
	public ArrayList<Doc> outLinks = new ArrayList<Doc>(),
			inLinks = new ArrayList<Doc>();
	public float pageRank = 1F;
	public String url, content;
	public boolean fetched = false, inQueue = false;
	DocsCollection docsCollection;

	public Doc(String url, DocsCollection docsCollection) {
		this.url = url;
		this.docsCollection = docsCollection;
	}

	/**
	 * Fetch this.url and finally set fetched = True, after fetching parse data
	 * and put parsed data into this.content, also set outLinks of this doc and 
	 * inLinks of outLink docs. 
	 */
	
	private ArrayList<String> fetch(){
		Document doc;
		try {
			Print.print("Fetching %s", this.url);
			doc = Jsoup.connect(url).get();
			Elements links = doc.select("a[href]");
	        this.content = doc.text();
	        ArrayList<String> hrefs = new ArrayList<String>();
	        for (Element link : links) {
	        	String url = link.attr("abs:href");
	        	Matcher m = Crawler.urlPattern.matcher(url);
	        	if(m.find()){
	        		hrefs.add("http://" + m.group());
	        	}
	        }
	        return hrefs;
		} catch (IOException e) {
			System.err.println("Bad URL");
			e.printStackTrace();
		}
		return null;
	}
	
	public void fetchAndParse(){
		if(!fetched){
			fetched = true;
			docsCollection.fetchedDocs++;
		}
		ArrayList<String> validLinks = this.fetch();
		// Set outLinks.
		for(String link: validLinks){
			try {
				Doc outLink = docsCollection.getOrCreateDocument(link);
				outLink.inLinks.add(this);
				this.outLinks.add(outLink);
			} catch (TooManyDocsException e) {
				// Just ignore the outLink :)
			}
		}
	}
}
