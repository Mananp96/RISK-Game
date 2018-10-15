package com.risk.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.risk.exception.InvalidMapException;
import com.risk.models.Continent;
import com.risk.models.Territory;
import com.risk.validate.MapValidator;

/**
 * This class is used to <b>Parse map file</b>, <b>Generate Data</b> 
 * and send the request to <b>validate map file</b>. 
 *
 */
public class BoardData {

	private String filePath;

	boolean continentFlag = false; 	
	boolean territoriesFlag = false;
	boolean isMapValid = false;

	String[] continentsArray;
	String[] territoriesArray;

	BufferedReader reader;

	public Continent continentObject;
	public Territory territoryObject;

	/**
	 * This constructor set the User specified path of map File.
	 * @param filePath path of map File.
	 */
	public BoardData(String filePath) {
		super();
		this.filePath = filePath;
	}

	/**
	 * This method is used to Read and Generate Data from map file,
	 * set the data to Continent and Territory Model, and
	 * calls mapValidate() method to check if Generated Data is Correct or not?.
	 * 
	 * @return true if map data is valid, else return false.
	 */
	public boolean generateBoardData() {

		continentObject = new Continent();
		territoryObject = new Territory();
		String currentLine;
		try 
		{
			reader = new BufferedReader(new FileReader(filePath));
			while((currentLine = reader.readLine()) != null) {	

				if(currentLine.equals("[Continents]")) {
					continentFlag = true;
					territoriesFlag = false;
				}
				if(currentLine.equals("[Territories]")) {
					territoriesFlag = true;
					continentFlag = false;
				}

				//assign Continents List.
				if(continentFlag && ! (currentLine.isEmpty()) && ! currentLine.equalsIgnoreCase("[Continents]")) {

					continentsArray = currentLine.split("=");
					continentObject.setContinentValue(continentsArray[0], Integer.parseInt(continentsArray[1]));

				}

				//assign Territories List.
				if(territoriesFlag && ! (currentLine.isEmpty() || currentLine.equals("[Territories]"))) {
					territoriesArray = currentLine.split(",");
					//to assign Adjacent Territories HashMap
					for(int i = 4; i < territoriesArray.length ; i++) {
						territoryObject.addAdjacentTerritory(territoriesArray[0], territoriesArray[i]);						
					}
					territoryObject.addTerritory(territoriesArray[0]);
					territoryObject.addTerritoryCont(territoriesArray[0], territoriesArray[3]);
					continentObject.addContinentTerritory(territoriesArray[3], territoriesArray[0]);
				}

			}

			MapValidator mapValidator = new MapValidator(continentObject,territoryObject);
			isMapValid = mapValidator.validateMap();
			reader.close();
		}catch(IOException | InvalidMapException e) {
			e.printStackTrace();
		}

		return isMapValid;

	}

	/**
	 * This method returns the path of map File.
	 * @return filePath path of map File.
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * This method sets the filePath.
	 * @param filePath path of map File.
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
