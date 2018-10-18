package com.risk.validate;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import com.risk.exception.InvalidMapException;
import com.risk.models.Continent;
import com.risk.models.Territory;
import com.riskTest.validate.ConnectedGraph;

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
	Map<String, ArrayList<String>> duplicateTerritoryContinent;
	Map<String, Integer> continentValue;
	Map<String, ArrayList<String>> adjcentTerritories;

	public MapValidator(Continent continentObject, Territory territoryObject) {

		this.continentTerritories =  continentObject.getContinentTerritory();
		this.continentValue = continentObject.getContinentValue();
		this.adjcentTerritories = territoryObject.getAdjacentTerritory();
		this.duplicateTerritoryContinent = territoryObject.getDuplicateTerritoryContinent();
	}

	/**
	 * Validate the Content of Map.
	 * @return isMapValid
	 * @throws InvalidMapException 
	 *
	 *
	 */
	public boolean validateMap() throws InvalidMapException {
		System.out.println(continentValue);
		System.out.println(continentTerritories);
		System.out.println(adjcentTerritories);
		System.out.println("---"+duplicateTerritoryContinent);
		//		System.out.println(territoriesMap);
		//		System.out.println(adjacentTerritoriesMap);

		if(continentValue != null) {
			isMapValid = validateContinentValue();
		} else { 
			this.isMapValid = false;
			throw new InvalidMapException("Map should contain atleast one Continent.");
		}
		return isMapValid;
	}


	/**
	 * Validate continent Winning Value. Winning Value must be at-least one.
	 * 
	 * @throws InvalidMapException
	 *             invalid map exception
	 */
	public boolean validateContinentValue() throws InvalidMapException {

		for (Entry<String, Integer> entry : continentValue.entrySet()) {
			String key = entry.getKey();
			if(continentValue.get(key) >= 1) {

				isMapValid = validateContinent();
				this.isMapValid = true;

			}else {
				this.isMapValid = false;
				throw new InvalidMapException("Continent winning value should be more than one.");
			}

		}
		return isMapValid;
	}

	/**
	 * This method validate the Continents. 
	 * Continents at the upper parts with value and Continents at bottom parts with Territories 
	 * should be equals in size and also according to names.
	 * 
	 * @return isMapValid returns true if continents are valid,else false.
	 * @throws InvalidMapException
	 */
	public boolean validateContinent() throws InvalidMapException {
		if(continentTerritories != null) {
			if(continentTerritories.size() == continentValue.size()) {

				for(Entry<String, ArrayList<String>> entryTerritory : continentTerritories.entrySet()) {
					String key = entryTerritory.getKey();
					if(continentValue.containsKey(key)) {
						isMapValid = true;
					}else {
						isMapValid = false;
						throw new InvalidMapException("Continents are not Valid");
					}
					validateTerritories();
				}

			}else {
				isMapValid = false;
				throw new InvalidMapException("Continent are not Compatible");
			}
		}else {
			isMapValid = false;
			throw new InvalidMapException("Territories should not be null");
		}


		return isMapValid;
	}
	
	/**
	 * Validate continent. It should have at-least one territory.
	 * and territory should not be in unique Continent.
	 * 
	 * @throws InvalidMapException
	 *             invalid map exception
	 */
	public boolean validateTerritories() throws InvalidMapException {

		if(continentTerritories.size() > 0) {

			for(Entry<String, ArrayList<String>> entryContinent : continentTerritories.entrySet()) {
				if(entryContinent.getValue().size() > 0) {
					for(Entry<String, ArrayList<String>> entryTerritory : duplicateTerritoryContinent.entrySet()) {
						if(entryTerritory.getValue().size() == 1) {
							this.isMapValid = true;
						}else {
							this.isMapValid = false;
							throw new InvalidMapException("Error: Territory Duplicacy."
									+ "One Territory should be in One Continent only");
						}
					}
				}else {
					this.isMapValid = false;
					throw new InvalidMapException("Continent should have at-least one Territory");
				}
			}
			
			isMapValid = validateAdjcentTerritories();
		}else {
			this.isMapValid = false;
			throw new InvalidMapException("Territories should not be null");

		}
		System.out.println("3."+isMapValid);

		return isMapValid;
	}


	/**
	 * Validate Territories. It should have at-least one adjacent territory.
	 *
	 * @throws InvalidMapException
	 *             invalid map exception
	 */
	public boolean validateAdjcentTerritories() throws InvalidMapException {

		if(adjcentTerritories != null) {
			for (Entry<String, ArrayList<String>> entry : adjcentTerritories.entrySet()) {
				
				if(entry.getValue().size() > 0) {
					this.isMapValid = true;
				}else {
					this.isMapValid = false;
					throw new InvalidMapException("adjacent territories should not be null");
				}
			}
		}	
		
		return isMapValid;
	}

	/**
	 * this method is used to check if Graph is Connected or not.
	 * returns true if it is connected else returns false.
	 * 
	 * @return isMapValid  returns true if graph is connected else false
	 * @throws InvalidMapException
	 */
	public boolean isGraphConnected() {
		
		ConnectedGraph connectedGraph = new ConnectedGraph(adjcentTerritories);
		for(Entry<String,ArrayList<String>> entry : adjcentTerritories.entrySet()) {
			String key = entry.getKey();
			for(int i = 0; i<entry.getValue().size(); i++) {
				
			}
		}
		
		
		return isMapValid;

	}

}
