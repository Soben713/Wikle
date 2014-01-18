package edu.sharif.ce.fall92.mir.pa3.wikle.crawler;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.ce.fall92.mir.pa3.wikle.utils.Doc;

public class FingerPrint {
	private static int N=5;
	private static double minSim=0.8;
	private static int codeSize=31;
	List<List<Integer>> permutations = new ArrayList<List<Integer>>();
	public FingerPrint(){
		for(int i=0; i<N; i++){
			List<Integer> list = new ArrayList<Integer>();
			for(int j=0; j<codeSize; j++)
				list.add(j);
			java.util.Collections.shuffle(list);
			permutations.add(list);
		}
	}
	public ArrayList<Integer>  getFingerPrint(Doc doc){
		ArrayList<Integer> fingerPrint=new ArrayList<Integer>();
		String words[]=doc.content.split(" ");
		List<String> shingles=new ArrayList<String>();
		for(int i=0; i<words.length-4; i++){
			String str="";
			for(int j=0; j<4; j++)
				str+=words[i+j];
			shingles.add(str);
		}
		for(int i=0; i<N; i++){
			int min=Integer.MAX_VALUE;
			for(int j=0; j<shingles.size(); j++){
				int num=shingles.get(j).hashCode();
				min=Math.min(min, permiutedNum(num, i));
			}
			fingerPrint.add(min);
		}
		return fingerPrint;
	}
	
	private int permiutedNum(int num, int l) {
		String stringNum=Integer.toBinaryString(num);
		int[] permNum=new int[codeSize];
		for(int i=0; i<stringNum.length()-1; i++){
			if(stringNum.charAt(i)=='1'){
				permNum[permutations.get(l).get(i)]= 1;
			}
		}
		String ans="";
		for(int i=0; i<permNum.length; i++)
			ans+=permNum[i];
		
		return Integer.parseInt(ans, 2);
	}
	
	public static boolean calSimilarity(ArrayList<Integer> firstFingerPrint, ArrayList<Integer> secondFingerPrint){
		double counter=0;
		for(int i=0; i<N; i++){
			if(firstFingerPrint.get(i).equals(secondFingerPrint.get(i))){
				counter++;
			}
		}
		return ((double)counter)/(double)N>minSim;
		
	}
}
