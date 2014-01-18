package edu.sharif.ce.fall92.mir.pa3.wikle.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import edu.sharif.ce.fall92.mir.pa3.wikle.exceptions.TooManyDocsException;

public class DocsCollection implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 110603940692915930L;
	public Map<String, Doc> docs = new HashMap<String, Doc>();
	public int fetchedDocs = 0;
	final public static int MAX_DOCS = 5;
	public final static String path = "docs.ser";
	final float alpha = 0.1F;

	/**
	 * Updates the pageRank property of all doc's, based on outLinks and inLinks
	 * of them.
	 */
	public void updatePageRanks() {
		double mat[][] = new double[docs.size()][docs.size()];
		
		ArrayList<Doc> docList = new ArrayList<Doc>();
		for(Doc doc: docs.values()){
			docList.add(doc);
		}
		
		for(int i=0; i<docList.size(); i++)
			docList.get(i).index = i;
		
		for(int i=0; i<docList.size(); i++){
			Doc doc = docList.get(i);
			if(doc.outLinks.size() == 0){
				for(int j=0; j<docList.size(); j++)
					mat[i][j] = (1F-alpha) * 1F/docList.size();
			} else {
				for(Doc outLink: doc.outLinks){
					mat[i][outLink.index] = (1F-alpha) * 1F/doc.outLinks.size();
				}
			}
		}
		for(int i=0; i<docList.size(); i++)
			for(int j=0; j<docList.size(); j++)
				mat[i][j] += alpha / docList.size();

		RealMatrix P = new Array2DRowRealMatrix(mat);
		EigenDecomposition ed = new EigenDecomposition(P.transpose());
		double d[] = ed.getRealEigenvalues();
		
		int nearOneIndex = -1;
		double nearOneValue = Double.MAX_VALUE;
		for(int i=0; i<d.length; i++)
			if(Math.abs(d[i]-1F) < nearOneValue){
				nearOneValue = Math.abs(d[i]-1F);
				nearOneIndex = i;
			}
		RealVector pageRank = ed.getEigenvector(nearOneIndex);
		for(int i=0; i<docList.size(); i++){
			docList.get(i).pageRank = (float) pageRank.getEntry(i);
		}
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
