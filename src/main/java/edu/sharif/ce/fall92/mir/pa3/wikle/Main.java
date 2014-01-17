package edu.sharif.ce.fall92.mir.pa3.wikle;

import edu.sharif.ce.fall92.mir.pa3.wikle.crawler.Crawler;
import edu.sharif.ce.fall92.mir.pa3.wikle.indexer.Indexer;
import edu.sharif.ce.fall92.mir.pa3.wikle.searcher.Searcher;
import edu.sharif.ce.fall92.mir.pa3.wikle.utils.Doc;
import edu.sharif.ce.fall92.mir.pa3.wikle.utils.DocsCollection;

/**
 * Hello world!
 * 
 */
public class Main {
	public static void main(String[] args) {
		DocsCollection dc;
		/**
		Crawler crawler = new Crawler();
		crawler.crawl();
		DocsCollection.save(crawler.docsCollection);
		dc = crawler.docsCollection;
		/**/
		dc = DocsCollection.load();
		/**/
		Indexer indexer = new Indexer();
		indexer.updateIndex(dc);
		
		Searcher.search("search engines");
	}
}
