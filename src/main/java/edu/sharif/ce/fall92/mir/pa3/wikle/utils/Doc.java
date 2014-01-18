package edu.sharif.ce.fall92.mir.pa3.wikle.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

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
		try {
			Print.print("Fetching %s", this.url);
			java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
	        
			WebClient webClient = new WebClient();
			webClient.getOptions().setCssEnabled(false);
			if(!Crawler.EXECUTE_JS)
				webClient.getOptions().setJavaScriptEnabled(false);
			else
				webClient.waitForBackgroundJavaScript(50 * 1000);
	        HtmlPage page = webClient.getPage(url);
	        this.content = page.asText();
	        
	        List<DomElement> links = page.getElementsByTagName("a");
	        ArrayList<String> validHrefs = new ArrayList<String>();
	        for(DomElement element: links){
	        	String elink = element.getAttribute("href"); 
	        	String link = page.getFullyQualifiedUrl(elink).toString();
	        	Matcher m = Crawler.urlPattern.matcher(link);
	        	if(m.find()){
	        		validHrefs.add("http://" + m.group());
	        	}
	        }
	        return validHrefs;
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
