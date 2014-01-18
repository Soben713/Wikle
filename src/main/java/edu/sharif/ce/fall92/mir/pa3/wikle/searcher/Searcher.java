package edu.sharif.ce.fall92.mir.pa3.wikle.searcher;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;

import edu.sharif.ce.fall92.mir.pa3.wikle.indexer.Indexer;
import edu.sharif.ce.fall92.mir.pa3.wikle.utils.Doc;
import edu.sharif.ce.fall92.mir.pa3.wikle.utils.Print;
import edu.sharif.ce.fall92.mir.pa3.wikle.utils.Serializer;

public class Searcher {
	public static void search(String q) {
		IndexSearcher is;
		try {
			is = new IndexSearcher(Indexer.indexDir);
			QueryParser parser = new QueryParser(Version.LUCENE_30, "content",
					new StandardAnalyzer(Version.LUCENE_30));
			Query query = parser.parse(q);
			TopDocs hits = is.search(query, 10);
			@SuppressWarnings("unchecked")
			ArrayList<Doc> docs = (ArrayList<Doc>)(Serializer.load(Indexer.docOrderPath));
			for (ScoreDoc scoreDoc : hits.scoreDocs) {
				if(scoreDoc != null)
					Print.print("[%s]: %s", scoreDoc.score, docs.get(scoreDoc.doc).url);
			}
			is.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
}
