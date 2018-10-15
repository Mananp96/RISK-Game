package com.risk.validate;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.risk.controller.BoardData;
import com.risk.exception.InvalidMapException;
import com.risk.models.Continent;
import com.risk.models.Territory;

/**
 * This class is used to Validate Map.
 * @author Manan
 * @version 1.0
 */
public class MapValidator {
	
	boolean isMapValid;
	Continent continent;
	Territory territory;
	Map<String, ArrayList<String>> continentTerritories;
	Map<String, Integer> continentValue;
	Map<String, ArrayList<String>> adjcentTerritories;
	
	//Constructor to set all HashMap.
	public MapValidator() {
		// TODO Auto-generated constructor stub
		BoardData boardData= new BoardData(null);
		territory = new Territory();
		continent = new Continent();
		this.continentTerritories =  boardData.continentObject.getContinentTerritory();
		this.continentValue = continent.getContinentValue();
		this.adjcentTerritories = territory.getAdjacentTerritory();
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
			isMapValid = validateContinentValue();
		} else { 
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
		    	
		    	isMapValid = validateTerritories();
		    	this.isMapValid = true;
		    	
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
						    isMapValid = validateAdjcentTerritories();
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
		System.out.println("3."+isMapValid);
		
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
		System.out.println("4."+isMapValid);
		return isMapValid;
	}
	

}
