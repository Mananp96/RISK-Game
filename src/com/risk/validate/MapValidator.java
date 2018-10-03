package com.risk.validate;

import java.util.ArrayList;
import java.util.HashMap;

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
			if(continentValue.size() > 0) {
				
			}
		}else {
			throw new InvalidMapException("Map should contain atleast one Continent.");
		}
		
		return isMapValid;
	}
}
