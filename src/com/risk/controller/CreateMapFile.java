package com.risk.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * this class is used to CREATE a map file from scratch.
 *
 */
public class CreateMapFile {
	
	String mapData;
	String fileName = "mapFile.map";
	BufferedWriter bufferedWriter = null;
    FileWriter fileWriter = null;
	
	public CreateMapFile(String mapData) {
		this.mapData = mapData;
	}

	/* If file gets created then the createNewFile() 
	 * method would return true or if the file is 
	 * already present it would return false.
	 */
	 public void createMap() {
	
        try {
	       	    File file = new File(fileName); 
	            boolean isFileCreated = file.createNewFile();
	            
	            if(isFileCreated) {
	            	
	            	fileWriter = new FileWriter(file);
					bufferedWriter = new BufferedWriter(fileWriter);
					bufferedWriter.write(mapData);
					BoardData boardData = new BoardData(fileName);
					boolean isMapValid = boardData.generateBoardData();
					if(isMapValid) {
						System.out.println("Map is Valid.");
					}else {
						System.out.println("map is not valid.");
					}
					
	            	System.out.println("File has been created successfully");
	            	
	            }else{
	       	        System.out.println("File already present at the specified location");
	       	    }
        } catch (Exception exception) {
       		exception.printStackTrace();
	    }
            
        finally {
        	try {
					bufferedWriter.close();
					fileWriter.close();
	        	} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}       
	} 
}
