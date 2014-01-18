package edu.sharif.ce.fall92.mir.pa3.wikle.indexer;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import edu.sharif.ce.fall92.mir.pa3.wikle.utils.Doc;
import edu.sharif.ce.fall92.mir.pa3.wikle.utils.DocsCollection;
import edu.sharif.ce.fall92.mir.pa3.wikle.utils.Serializer;

public class Indexer {
	public static FSDirectory indexDir;
	public final static String docOrderPath = "docOrderPath.ser";
	public IndexWriter writer;

	static {
		try {
			indexDir = FSDirectory.open(new File("index"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Indexer(){
		try {
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
			writer = new IndexWriter(indexDir, analyzer, true, IndexWriter.MaxFieldLength.UNLIMITED);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Indexes documents in @webGraph
	 */
	public void updateIndex(DocsCollection docsCollection) {
		docsCollection.updatePageRanks();
		try {
			ArrayList<Doc> docs = new ArrayList<Doc>();
			for (Doc doc : docsCollection.docs.values()) {
				docs.add(doc);
				Document luceneDoc = new Document();
				Field contentField = new Field("content", new StringReader(doc.content));
				Field urlField = new Field("url", new StringReader(doc.url));
				luceneDoc.add(contentField);
				luceneDoc.add(urlField);
				luceneDoc.setBoost(doc.pageRank);
				writer.addDocument(luceneDoc);
			}
			writer.commit();
			writer.close();
			Serializer.save(docs, docOrderPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
