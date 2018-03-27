import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

	/**
	 * The initialization of the photo renamer program
	 * 
	 * @author Zhengming Zhou
	 */
public class TheMainWindow{
	 
	public static void main(String args[]){
		JFrame frame = new JFrame("Photo Manager");
		frame.setLayout(new GridLayout(2,3));
	    DirectorySelector selectDirectory = new DirectorySelector();
	    TagAdderText theInfo = new TagAdderText();
		frame.add(theInfo);
		frame.add(selectDirectory);
		TagChooser boxes = new TagChooser();
		frame.add(boxes);
		GetOldName abc = new GetOldName();
		frame.add(abc);
		JPanel emptyOne = new JPanel();
		frame.add(emptyOne);
		frame.add(selectDirectory);
	    frame.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	          System.exit(0);
	          }
	        });
	    frame.setSize(1400,800);
	    frame.setVisible(true);
	    }
	}

/*
 * Design Pattern
 * 
 * TagAdderText and TagChooser are using strategy design pattern 
 * since those two class share similar role and both of them call
 * the photo class and use the algorithm of addTags.
 * 
 * Another design pattern is Builder find from other source,
 * Builder is a class that build different class objects.
 * Builder is used in Chooser where it read Tags from file and create
 * object of class Tag and list images by finding them recursively in
 * subdirectories and create Photo class using the file that it find.
 */
