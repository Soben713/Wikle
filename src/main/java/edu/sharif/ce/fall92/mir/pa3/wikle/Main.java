package edu.sharif.ce.fall92.mir.pa3.wikle;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import edu.sharif.ce.fall92.mir.pa3.wikle.ui.UI;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
    	/*
       JFrame frame=new JFrame("kufte kari");
       JFrame.setDefaultLookAndFeelDecorated(true);
       frame.setSize(500,500);
       
       JButton b=new JButton("test");
       b.setPreferredSize(new Dimension(100, 100));
       b.setMaximumSize(new Dimension(100,100));
       frame.setLayout(new FlowLayout());
       frame.getContentPane().add(b);
       frame.setVisible(true);
       */
    	UI ui=new UI();
    }
}
