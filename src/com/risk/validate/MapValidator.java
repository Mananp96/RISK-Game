package com.risk.validate;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
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
	Map<String, ArrayList<String>> duplicateTerritoryContinent;
	Map<String, Integer> continentValue;
	Map<String, ArrayList<String>> adjcentTerritories;
	Map<String, Integer> territoryNumber;

	public MapValidator(Continent continentObject, Territory territoryObject) {

		this.continentTerritories =  continentObject.getContinentTerritory();
		this.continentValue = continentObject.getContinentValue();
		this.adjcentTerritories = territoryObject.getAdjacentTerritory();
		this.duplicateTerritoryContinent = territoryObject.getDuplicateTerritoryContinent();
		this.territoryNumber = territoryObject.getNumberOfTerritory();
	}

	/**
	 * Validate the Content of Map.
	 * @throws InvalidMapException throws exception when map is invalid
	 * @return true if map is valid after validation else false
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
	 * Validate continent Winning Value. Winning Value must be at-least one.
	 * 
	 * @throws InvalidMapException throws exception when map is invalid
	 * @return true if continent value is greater than zero else false
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
	 * @throws InvalidMapException throws exception when map is invalid
	 * @return isMapValid returns true if continents configurations are valid else false.
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
	 * @throws InvalidMapException invalid map exception
	 * @return true if one territory is present in one continent else false
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
	 * @throws InvalidMapException invalid map exception
	 * @return true if adjacent territories configurations are valid else false
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
			this.isGraphConnected();
		}	
		return isMapValid;
	}

	/**
	 * this method is used to check if Graph is Connected or not.
	 *
	 * @throws InvalidMapException invalid map exception
	 * @return isMapValid  returns true if graph is connected else false
	 */
	public boolean isGraphConnected() throws InvalidMapException {

		ConnectedGraph graph = new ConnectedGraph(territoryNumber.size());
		for(Entry<String,ArrayList<String>> entry : adjcentTerritories.entrySet()) {
			for(int i = 0; i<entry.getValue().size() ; i++) {
				graph.addConnectionLine(territoryNumber.get(entry.getKey()),
						territoryNumber.get(entry.getValue().get(i)));						
			}
		}
		if(graph.isGraphStronglyConnected()) {
			System.out.println("Graph is Connected.");
			this.isMapValid = true;
		}else {
			System.out.println("Graph is not Connected.");
			this.isMapValid = false;
			throw new InvalidMapException("Graph is not connected");
		}
		return isMapValid;
	}

}
