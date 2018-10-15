package com.risk.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.risk.exception.InvalidMapException;
import com.risk.models.Continent;
import com.risk.models.Territory;
import com.risk.validate.MapValidator;

public class BoardData {

	private String filePath;
	StringBuilder stringBuilder;
	boolean contFlag = false; 	//Continents flag
	boolean terrFlag = false;	//Territories flag
	boolean isMapValid = false;
	
	String[] continentsArray;
	String[] territoriesArray;
	
	ArrayList<String> continentsList;
	ArrayList<String> territoriesList;

	HashMap<String, ArrayList<String>> contTerr; 						//for storing [Continents] and it's [Territories].
	HashMap<String, Integer> contValue; 								//for storing [Continents] and it's [Winning Value].
	HashMap<String, ArrayList<String>> adjcentTerr;						//for storing [Territories] and it's [Adjacent Territories].
	HashMap<String, HashMap<String, ArrayList<String>>> userOwnedTerr;	//for storing [User] and it's owned [Continents] [Territories].
	HashMap<String, String> territories;
	HashMap<String, String> adjacentTerritories;
	
	private BufferedReader reader;
	
	Continent continentObject;
	Territory territoryObject;
	
	public BoardData(String filePath) {
		super();
		this.filePath = filePath;
	}
	public boolean generateBoardData() {

		continentObject = new Continent();
		territoryObject = new Territory();
		String currentLine;
		contValue = new HashMap<>();
		contTerr = new HashMap<>();
		adjcentTerr = new HashMap<>();
		
		try 
		{
			reader = new BufferedReader(new FileReader(filePath));
			stringBuilder = new StringBuilder();
			
			while((currentLine = reader.readLine()) != null) {		
				if(currentLine.equals("[Continents]")) {
					
					contFlag = true;
					terrFlag = false;
				}
				if(currentLine.equals("[Territories]")) {
					
					terrFlag = true;
					contFlag = false;
				}
				
				//assign Continents List.
				if(contFlag && ! (currentLine.isEmpty()) && ! currentLine.equalsIgnoreCase("[Continents]")) {
					
					continentsArray = currentLine.split("=");
					continentObject.setContinentValue(continentsArray[0], Integer.parseInt(continentsArray[1]));
					
				}
				
				//assign Territories List.
				if(terrFlag && ! (currentLine.isEmpty() || currentLine.equals("[Territories]"))) {
					territoriesList = new ArrayList<>(); 
					territoriesArray = currentLine.split(",");
					
					//to assign Adjacent Territories HashMap
					for(int i = 4; i < territoriesArray.length ; i++) {
						territoryObject.addAdjacentTerritory(territoriesArray[0], territoriesArray[i]);
//						if(territoryObject.isFlag()) {
//							break;
//						}
//						territoryObject.addAdjacentTerritoriesMap(territoriesArray[i],territoriesArray[i]);
					}
					territoryObject.addTerritory(territoriesArray[0]);
//					territoryObject.addTerritoriesMap(territoriesArray[0], territoriesArray[0]);
//					if(territoryObject.isFlag()) {
//						break;
//					}
					territoryObject.addTerritoryCont(territoriesArray[0], territoriesArray[3]);
					continentObject.addContinentTerritory(territoriesArray[3], territoriesArray[0]);
				}
				
			}
			
//			if(!territoryObject.isFlag()) {
				MapValidator mapValidator = new MapValidator(continentObject,territoryObject);
				isMapValid = mapValidator.validateMap();
//			}else {
//				return false;
//			}
			
			
			
		}catch(IOException | InvalidMapException e) {
			e.printStackTrace();
		}
		
//		System.out.println("isMapValid--------------"+isMapValid);
		return isMapValid;
		
	}
	
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
