package edu.sharif.ce.fall92.mir.pa3.wikle.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.sharif.ce.fall92.mir.pa3.wikle.exceptions.TooManyDocsException;

public class DocsCollection implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 110603940692915930L;
	public Map<String, Doc> docs = new HashMap<String, Doc>();
	public int fetchedDocs = 0;
	final public static int MAX_DOCS = 10;
	public final static String path = "docs";

	/**
	 * Updates the pageRank property of all doc's, based on outLinks and inLinks
	 * of them.
	 */
	public void updatePageRanks() {
		float mat[][] = new float[docs.size()][docs.size()];
		Collection<Doc> docList = docs.values();
		//TODO 
	}

	public Doc getOrCreateDocument(String url) throws TooManyDocsException {
		if (docs.containsKey(url))
			return docs.get(url);
		if (docs.size() < MAX_DOCS) {
			Doc doc = new Doc(url, this);
			docs.put(url, doc);
			return doc;
		} else {
			throw new TooManyDocsException();
		}
	}

	public static void save(DocsCollection dc) {
		Serializer.save(dc, path);
	}

	public static DocsCollection load() {
		return (DocsCollection)(Serializer.load(path));
	}
}
