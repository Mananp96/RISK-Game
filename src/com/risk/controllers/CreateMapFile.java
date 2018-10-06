package com.risk.controllers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CreateMapFile {
	
	public CreateMapFile() {
		// TODO Auto-generated constructor stub
	}

	 public void createMap(String mapData) {
	        
	              BufferedWriter bw = null;
	        		FileWriter fw = null;
	        		
	                
	                try {
	           	     File file = new File("mapFile.map");
	           	     /*If file gets created then the createNewFile() 
	           	      * method would return true or if the file is 
	           	      * already present it would return false
	           	      */
	                        boolean fvar = file.createNewFile();
	                        
	                      
	                        fw = new FileWriter(file);
	            			bw = new BufferedWriter(fw);
	            			bw.write(mapData);
	            			
	            			System.out.println("Done");

	                        
	           	     if (fvar){
	           	          System.out.println("File has been created successfully");
	           	     }
	           	     else{
	           	          System.out.println("File already present at the specified location");
	           	     }
	               	} catch (Exception e1) {
	               		System.out.println("Exception Occurred:");
	           	        e1.printStackTrace();
	           	  }
	                
	                finally {

	        			try {

	        				if (bw != null)
	        					bw.close();

	        				if (fw != null)
	        					fw.close();

	        			} catch (Exception ex) {

	        				ex.printStackTrace();

	        			}

	        		}
	            
	 } 

}
