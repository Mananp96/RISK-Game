package com.risk.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.risk.models.Continent;
import com.risk.models.Territory;

public class BoardData {

	private String filePath;
	StringBuilder stringBuilder;
	boolean contFlag = false; 							//Continents flag
	boolean terrFlag = false;							//Territories flag
	
	String[] continentsArray;
	String[] territoriesArray;
	
	ArrayList<String> continentsList;
	ArrayList<String> territoriesList;

	HashMap<String, ArrayList<String>> contTerr; 						//for storing [Continents] and it's [Territories].
	HashMap<String, Integer> contValue; 								//for storing [Continents] and it's [Winning Value].
	HashMap<String, ArrayList<String>> adjcentTerr;						//for storing [Territories] and it's [Adjacent Territories].
	HashMap<String, HashMap<String, ArrayList<String>>> userOwnedTerr;	//for storing [User] and it's owned [Continents] [Territories].
	private BufferedReader reader;
	
	Continent continentObject;
	Territory territoryObject;
	

	public BoardData(String filePath) {
		super();
		this.filePath = filePath;
	}
	public void generateBoardData() {

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
					//contValue.put(continentsArray[0], Integer.parseInt(continentsArray[1]));
				}
				
				//assign Territories List.
				if(terrFlag && ! (currentLine.isEmpty() || currentLine.equals("[Territories]"))) {
					territoriesList = new ArrayList<>();
					//ArrayList<String> tempList = new ArrayList<>(); 
					
					territoriesArray = currentLine.split(",");
					
					//to assign Adjacent Territories HashMap
					for(int i = 4; i < territoriesArray.length ; i++) {
						territoryObject.addAdjacentTerritory(territoriesArray[0], territoriesArray[i]);
						//	tempList.add(territoriesArray[i]);
					}
					//System.out.println(territoryObject.getAdjacentTerritory());
				//	adjcentTerr.put(territoriesArray[0], tempList);
					
					//to assign Continent Territories HashMap
					//territoriesList.add(territoriesArray[0]);
					
					territoryObject.addTerritory(territoriesArray[0]);
					territoryObject.addTerritoryCont(territoriesArray[0], territoriesArray[3]);
					continentObject.addContinentTerritory(territoriesArray[3], territoriesArray[0]);
					/*if(contTerr.get(territoriesArray[3]) == null) {
						ArrayList<String> tempArr1= new ArrayList<>();
						tempArr1.add(territoriesArray[0]);
						contTerr.put(territoriesArray[3], tempArr1);
					}
					else if(contTerr.get(territoriesArray[3]) != null){
						ArrayList<String> tempArr2 = contTerr.get(territoriesArray[3]);
						tempArr2.add(territoriesArray[0]);
						contTerr.put(territoriesArray[3], tempArr2);
					}*/
					
				}
				
			}
			
			/*
			System.out.println("Continents--&--Value");
			System.out.println(contValue); 
			System.out.println("Territories--&--Adjacent Territories");
			System.out.println(adjcentTerr); 
			System.out.println("Continents--&--Territories");
			System.out.println(contTerr); 
			*/
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
