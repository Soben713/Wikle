package edu.sharif.ce.fall92.mir.pa3.wikle.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;



public class UI extends JFrame implements ActionListener{
	JButton crawl;
	JButton loadCrawled;
	JButton index;
	JButton loadIndexed;
	JButton search;
	JTextField searchField;
	public UI() {
    	Container con=this.getContentPane();
    	setTitle("Search engine");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 420);
		this.setLayout(new FlowLayout());
	
		JPanel crawlPanel=new JPanel();
		Border crawlBorder=BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Crawler");
		crawlPanel.setBorder(crawlBorder);
		con.add(crawlPanel);
		
        crawl = new JButton("Start crawling");
        crawl.setPreferredSize(new Dimension(180,70));
        crawl.addActionListener(this);
        crawl.setLayout(null);
        crawlPanel.add(crawl);
        
        loadCrawled = new JButton("Load crawled pages");
        loadCrawled.setPreferredSize(new Dimension(180,70));
        loadCrawled.addActionListener(this);
        crawlPanel.add(loadCrawled);
        
		JPanel indexPanel=new JPanel();
		Border indexBorder=BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Indexer");
		indexPanel.setBorder(indexBorder);
		con.add(indexPanel);
        
        index = new JButton("Start indexing");
        index.setPreferredSize(new Dimension(180,70));
        index.addActionListener(this);
        indexPanel.add(index);
        
        loadIndexed = new JButton("Load indexed data");
        loadIndexed.setPreferredSize(new Dimension(180,70));
        loadIndexed.addActionListener(this);
    //    loadIndexed.enable(false);
        indexPanel.add(loadIndexed);

        Border border=BorderFactory.createEmptyBorder(50, 10, 20, 10);
        JPanel margin=new JPanel();
        margin.setBorder(border);
        con.add(margin);
		JPanel searchPanel=new JPanel();
		Border searchBorder=BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Search");
		searchPanel.setBorder(searchBorder);
		margin.add(searchPanel);
        
        searchField=new JTextField("type here",30);
        searchField.setMinimumSize(new Dimension(300, 70));
        searchPanel.add(searchField);
        
        search=new JButton("Search");
        search.setPreferredSize(new Dimension(100,50));
        search.addActionListener(this);
        searchPanel.add(search);
        
        setVisible(true);
    }




	public void actionPerformed(ActionEvent e) {
		String command= e.getActionCommand();
		switch(command) {
			case "Start crawling":
				startCrawl();
				break;
			case "Load crawled pages":
				loadCrawled();
				break;
			case "Start indexing":
				startIndex();
				break;
				
			case "Load indexed data":
				loadIndexed();
				break;
			
			case "Search":
				search();
				break;
		}
		
	}

	private void loadIndexed() {
		// TODO Auto-generated method stub
		
	}

	private void search() {
		String txt=searchField.getText();
		JOptionPane.showMessageDialog(this, txt);
	}

	private void startIndex() {
		// TODO Auto-generated method stub
		
	}

	private void loadCrawled() {
		// TODO Auto-generated method stub
		
	}

	private void startCrawl() {
		JOptionPane.showMessageDialog(this, "Hello, World!!!!!");
		
	}


}