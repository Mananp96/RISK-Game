package com.risk.validate;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map.Entry;

import com.risk.exception.InvalidMapException;

/**
 * This class is used to Validate Map.
 * @author Manan
 * @version 1.0
 */
public class MapValidator {
	
	boolean isMapValid = false;
	
	HashMap<String, ArrayList<String>> continentTerritories;
	HashMap<String, Integer> continentValue;
	HashMap<String, ArrayList<String>> adjcentTerritories;
	
	//Constructor to set all HashMap.
	public MapValidator(HashMap<String, ArrayList<String>> contTerr, HashMap<String, Integer> contValue,
			HashMap<String, ArrayList<String>> adjcentTerr) {
		// TODO Auto-generated constructor stub
		 this.continentTerritories = contTerr;
		 this.continentValue = contValue;
		 this.adjcentTerritories = adjcentTerr;
	}

	/**
	 * Validate the Content of Map.
	 * @return isMapValid
	 * @throws InvalidMapException 
	 *
	 *
	 */
	public boolean validateMap() throws InvalidMapException {
		if(continentValue != null) {
			validateContinentValue();
		}else { 
			this.isMapValid = false;
			throw new InvalidMapException("Map should contain atleast one Continent.");
		}
		
		return isMapValid;
	}
	
	/**
	 * Validate continent Winning Value. Winning Value must be atleast one.
	 * 
	 * @throws InvalidMapException
	 *             invalid map exception
	 */
	public boolean validateContinentValue() throws InvalidMapException {
		
		for (Entry<String, Integer> entry : continentValue.entrySet()) {
		    String key = entry.getKey();
		    
		    if(continentValue.get(key) >= 1) {
		    	validateTerritories();
		    	
		    }else {
		    	this.isMapValid = false;
		    	throw new InvalidMapException("Continent winning value should be more than one.");
		    }
		}
		return isMapValid;
	}
	
	
	/**
	 * Validate continent. It should have atleast one territory.
	 * 
	 * @throws InvalidMapException
	 *             invalid map exception
	 */
	public boolean validateTerritories() throws InvalidMapException {
		
		if(continentTerritories.size() > 0) {
			
			if(continentValue.size() == continentTerritories.size()) {
				
					for (Entry<String, ArrayList<String>> entry : continentTerritories.entrySet()) {
					    String key = entry.getKey();
					    
					    if(entry.getValue().size() > 0) {
						    validateAdjcentTerritories();
					    }else {
					    	this.isMapValid = false;
					    	throw new InvalidMapException("Territories should be 1 or more.");
					    }		    
					}
					
			}else {
				this.isMapValid = false;
				throw new InvalidMapException("Continent is Not compatible");
			}
			
		}else {
			this.isMapValid = false;
			throw new InvalidMapException("Territories should not be null");

		}
		
		return isMapValid;
	}

	
	/**
	 * Validate Territories. It should have atleast one adjacent territory.
	 *
	 * @throws InvalidMapException
	 *             invalid map exception
	 */
	public boolean validateAdjcentTerritories() throws InvalidMapException {
		
		if(adjcentTerritories != null) {
			for (Entry<String, ArrayList<String>> entry : adjcentTerritories.entrySet()) {
			    String key = entry.getKey();
			    if(adjcentTerritories.get(key).size() > 0) {
			    	this.isMapValid = true;
			    }else {
			    	this.isMapValid = false;
			    	throw new InvalidMapException("adjacent territories should not be null");
			    }
			}
		}	
		
		return isMapValid;
	}
	

}
