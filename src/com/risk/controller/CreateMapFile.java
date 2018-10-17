package com.risk.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * This class is used to <b>create a new map file</b> from scratch.
 */
public class CreateMapFile {

	String mapData;
	String fileName;
	BufferedWriter bufferedWriter = null;
	FileWriter fileWriter = null;
	
	/**
	 * Set the mapData.
	 * @param newMapData User entered Input to create a map file.
	 */
	public CreateMapFile(String newMapData) {
		this.mapData = newMapData;
	}
	/**
	 * method used to get File Name which is created
	 * @return fileName
	 */
	public String getFileName() {
	    return fileName;
	}
	/**
	 * method used to set fileName
	 * @param fileName name of file
	 */
	public void setFileName(String fileName) {
	    this.fileName = fileName;
	}


	/**
	 * This method is used to create a new File and write the mapData (User input to create a new map file)
	 * to file. checks if newly created file has valid map data or not? 
	 */
	public boolean createMap() {
		
		try {
		    	
		    	Random random = new Random();
			fileName = "mapFile_"+ random.nextInt(1000)+".map";
		    	File file = new File(fileName); 
		    	System.out.println("File Name ---> "+fileName);
			boolean isFileCreated = file.createNewFile();

			if(isFileCreated) {
				fileWriter = new FileWriter(file);
				bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(mapData);
				bufferedWriter.flush();
				bufferedWriter.close();
				BoardData boardData = new BoardData(fileName);
				boolean isMapValid = boardData.generateBoardData();

				if(isMapValid) {
					System.out.println("Map is Valid.");
					System.out.println("File has been created successfully with File Name " + fileName);
					return true;
				} else {
					System.out.println("map is not valid.");
					return false;
				}

			} else {
				System.out.println("File already present at the specified location");
			}

		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return false;       
	} 
}
