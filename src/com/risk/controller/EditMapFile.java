package com.risk.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * This Class is used to edit map which are loaded by user
 * @author Himen Sidhpura
 *
 */
public class EditMapFile {
    /**
     * @param filePath Store path of file loaded by user
     * @param continentData Store Continent Data read from file
     * @param territoryData Store Territory Data read from file
     */
    String filePath;
    StringBuilder continentData;
    StringBuilder territoryData;
    
    /**
     * Default Constructor which one parameter, filePath
     * @param filePath path of file loaded by user
     */
    public EditMapFile(String filePath) {
	super();
	this.filePath = filePath;
	continentData = new StringBuilder();
	territoryData = new StringBuilder();
    }
    
    /**
     * This method used to generate Map Data and Valid File content
     * @return true if file data is validated corrected Otherwise return false
     */
    public boolean generateData() {
	BoardData boardData = new BoardData(filePath);
	boolean isMapValid = boardData.generateBoardData();
	if(isMapValid) {
	    continentData = boardData.getContinentData();
	    territoryData = boardData.getTerritoryData();
	}
	return isMapValid;
    }
    
    /**
     * 
     * @param mapData Map content read from file
     * @return true if edited data is validated corrected Otherwise return false
     */
    public boolean editMap(String mapData) {
	try {
	    	
	    	Random random = new Random();
		String fileName = "mapFile_"+ random.nextInt(1000)+".map";
	    	File file = new File(fileName); 
	    	System.out.println("File Name ---> "+fileName);
		boolean isFileCreated = file.createNewFile();

		if(isFileCreated) {
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(mapData);
			bufferedWriter.flush();
			bufferedWriter.close();
			BoardData boardData = new BoardData(fileName);
			boolean isMapValid = boardData.generateBoardData();

			if(isMapValid) {
				System.out.println("Map is Valid.");
				System.out.println("File has been Update successfully with File Name " + fileName);
				bufferedWriter = new BufferedWriter(new FileWriter(filePath));
				bufferedWriter.write(mapData);
				bufferedWriter.flush();
				bufferedWriter.close();
				return true;
			} else {
				System.out.println("map is not valid.");
				return false;
			}

		} else {
			System.out.println("File already present at the specified location");
		}

	} catch (IOException exception) {
		System.out.println("Exception --------->  IO Exception is thrown");
	}

	return false;
    }
    /**
     * 
     * @return filePath path of file 
     */
    public String getFilePath() {
        return filePath;
    }
    /**
     * 
     * @param filePath path of file 
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    /**
     * 
     * @return continentData Continent Data read from file
     */
    public StringBuilder getContinentData() {
        return continentData;
    }
    /**
     * 
     * @param continentData Continent Data read from file
     */
    public void setContinentData(StringBuilder continentData) {
        this.continentData = continentData;
    }
    /**
     * 
     * @return territoryData Continent Data read from file
     */
    public StringBuilder getTerritoryData() {
        return territoryData;
    }
    /**
     * 
     * @param territoryData Continent Data read from file
     */
    public void setTerritoryData(StringBuilder territoryData) {
        this.territoryData = territoryData;
    }

}
