package edu.sharif.ce.fall92.mir.pa3.wikle.crawler;

import java.util.ArrayList;
import java.util.List;

public class FingerPrint {
	private static int N=200;
	private static double minSim=0.8;
	List<List<Integer>> permutations = new ArrayList<List<Integer>>();
	public FingerPrint(){
		for(int i=0; i<N; i++){
			List<Integer> list = new ArrayList<Integer>();
			for(int j=0; j<64; j++)
				list.add(j);
			java.util.Collections.shuffle(list);
			permutations.add(list);
		}
	}
	List<Integer> getFingerPrint(ArrayList<Integer> hashCode){
		List<Integer> fingerPrint=new ArrayList<Integer>();
		for(int i=0; i<N; i++){
			List<Integer> list=permutations.get(i);
			int min=100;
			for(int j=0; j<64; j++){
				if(hashCode.get(list.get(j))==1 ){
					Math.min(min, j);
				}
			}
			fingerPrint.add(min);
		}
		return fingerPrint;
	}
	
	public static boolean calSimilarity(ArrayList<Integer> firstFingerPrint, ArrayList<Integer> secondFingerPrint){
		int counter=0;
		for(int i=0; i<N; i++){
			if(firstFingerPrint.get(i)==secondFingerPrint.get(i))
				counter++;
		}
		return (double)counter/N>minSim;
	}
}
