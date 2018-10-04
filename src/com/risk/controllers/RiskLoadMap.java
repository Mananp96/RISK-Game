package com.risk.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.risk.exception.InvalidMapException;
import com.risk.validate.MapValidator;


/**
 * @author Manan
 * @version 1.0
 * This class is used to load .map file content.
 * 
 */
public class RiskLoadMap {

	String mapFile;
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
	
	/**
	 * This is the constructor to set File path.
	 */
	RiskLoadMap(String fileName) {
		this.mapFile = fileName;
	}
	
	/**
	 * method to read .map file and assign the 
	 * value to HashMap.
	 * @throws FileNotFoundException 
	 * @throws InvalidMapException 
	 */
	public void initializeData() throws FileNotFoundException, InvalidMapException {
		
		String currentLine;
		contValue = new HashMap<>();
		contTerr = new HashMap<>();
		adjcentTerr = new HashMap<>();
		
		try 
		{
			reader = new BufferedReader(new FileReader(mapFile));
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
					contValue.put(continentsArray[0], Integer.parseInt(continentsArray[1]));
				}
				
				//assign Territories List.
				if(terrFlag && ! (currentLine.isEmpty() || currentLine.equals("[Territories]"))) {
					territoriesList = new ArrayList<>();
					ArrayList<String> tempList = new ArrayList<>(); 
					
					territoriesArray = currentLine.split(",");
					
					//to assign Adjacent Territories HashMap
					for(int i = 4; i < territoriesArray.length ; i++) {
						tempList.add(territoriesArray[i]);
					}
					adjcentTerr.put(territoriesArray[0], tempList);
					
					//to assign Continent Territories HashMap
					territoriesList.add(territoriesArray[0]);
					if(contTerr.get(territoriesArray[3]) == null) {
						ArrayList<String> tempArr1= new ArrayList<>();
						tempArr1.add(territoriesArray[0]);
						contTerr.put(territoriesArray[3], tempArr1);
					}
					else if(contTerr.get(territoriesArray[3]) != null){
						ArrayList<String> tempArr2 = contTerr.get(territoriesArray[3]);
						tempArr2.add(territoriesArray[0]);
						contTerr.put(territoriesArray[3], tempArr2);
					}
					
				}
				
			}
			
			
			System.out.println("Continents--&--Value");
			System.out.println(contValue); 
			System.out.println("Territories--&--Adjacent Territories");
			System.out.println(adjcentTerr); 
			System.out.println("Continents--&--Territories");
			System.out.println(contTerr); 
			
			boolean isValid = this.mapValidate();
			System.out.println(isValid);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean mapValidate() throws InvalidMapException {
		MapValidator mapValidator = new MapValidator(contTerr,contValue,adjcentTerr);
		boolean isMapValid = false;
		try {
			isMapValid = mapValidator.validateMap();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isMapValid;
	}


}
