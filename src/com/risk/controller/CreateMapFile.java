package com.risk.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is used to <b>create a new map file</b> from scratch.
 */
public class CreateMapFile {

	String mapData;
	String fileName = "mapFile.map";
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
	 * This method is used to create a new File and write the mapData (User input to create a new map file)
	 * to file. checks if newly created file has valid map data or not? 
	 */
	public void createMap() {
		
		try {
			File file = new File(fileName); 
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
					System.out.println("File has been created successfully");
				} else {
					System.out.println("map is not valid.");
				}

			} else {
				System.out.println("File already present at the specified location");
			}

		} catch (IOException exception) {
			exception.printStackTrace();
		}       
	} 
}
