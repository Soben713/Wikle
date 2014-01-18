package edu.sharif.ce.fall92.mir.pa3.wikle;

import edu.sharif.ce.fall92.mir.pa3.wikle.crawler.Crawler;
import edu.sharif.ce.fall92.mir.pa3.wikle.indexer.Indexer;
import edu.sharif.ce.fall92.mir.pa3.wikle.searcher.Searcher;
import edu.sharif.ce.fall92.mir.pa3.wikle.utils.DocsCollection;

public class Main {
	public static void main(String[] args) {
		/*
		 * JFrame frame=new JFrame("kufte kari");
		 * JFrame.setDefaultLookAndFeelDecorated(true); frame.setSize(500,500);
		 * 
		 * JButton b=new JButton("test"); b.setPreferredSize(new Dimension(100,
		 * 100)); b.setMaximumSize(new Dimension(100,100)); frame.setLayout(new
		 * FlowLayout()); frame.getContentPane().add(b); frame.setVisible(true);
		 */
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
		
		 Searcher.search("Steven");
		
//		UI ui = new UI();
	}
}
