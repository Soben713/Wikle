package edu.sharif.ce.fall92.mir.pa3.wikle.utils;

import java.util.ArrayList;

public class Doc {
	ArrayList<Doc> outLinks = new ArrayList<Doc>();
	ArrayList<Doc> inLinks = new ArrayList<Doc>();
	public Double pageRank = null;
}
