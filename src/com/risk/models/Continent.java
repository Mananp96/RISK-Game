package com.risk.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a model class for continentValue, continentTerritory,
 * continentOwnedTerritory, playerOwnedContTerr as its data member.
 *
 */
public class Continent {

	/**
	 * @param continentValue value of armies when continent is fully acquired by player.
	 * @param continentTerritory no.of territories for each continent
	 * @param continentOwnedTerritory no. of territories owned by player for particular continent
	 * @param contTerrValue no. of Territories in Continent	
	 */
	Map<String, Integer> continentValue; 
	Map<String, ArrayList<String>> continentTerritory;
	Map<String, ArrayList<String>> continentOwnedTerritory;
	Map<String, Integer> contTerrValue;

	/**
	 * Continent class constructor
	 */
	public Continent() {
		continentValue = new HashMap<>();
		continentTerritory = new HashMap<>();
		continentOwnedTerritory = new HashMap<>();
		contTerrValue = new HashMap<>();
	}

	/**
	 *  This method increment territory number in particular continent
	 * @param continent continent in which one territory is identified
	 */
	public void  addContTerritoryValue(String continent) {
		if (contTerrValue.containsKey(continent)) {
			contTerrValue.replace(continent, contTerrValue.get(continent), contTerrValue.get(continent) + 1);
		} else {
			contTerrValue.put(continent, 1);
		}
	}

	/**
	 * get method to get territory value
	 * @return contTerrValue 
	 */
	public Map<String, Integer> getContTerrValue() {
		return contTerrValue;
	}

	/**
	 * set method for territory value
	 * @param contTerrValue no. of Territories in Particular Continent
	 */
	public void setContTerrValue(Map<String, Integer> contTerrValue) {
		this.contTerrValue = contTerrValue;
	}


	/**
	 * get method to for continent value
	 * @return Map continentValue 
	 */
	public Map<String, Integer> getContinentValue() {
		return continentValue;
	}

	/**
	 * This method used to create a Map continentValue.
	 * @param continent Name of Continent
	 * @param value value of armies when continent is fully acquired by player
	 */
	public void setContinentValue(String continent, int value) {
		if (continentValue.containsKey(continent)) {
			continentValue.replace(continent, continentValue.get(continent), value);
		} else {
			continentValue.put(continent, value);
		}
	}

	/**
	 * Used to identify which continent has how many territory and Create a Map continentTerritory
	 * @param continent name of Continent
	 * @param territory name of Territory
	 * @return a Map continentTerritory
	 * 
	 */
	public Map<String, ArrayList<String>> addContinentTerritory(String continent, String territory) {
		if (continentTerritory.containsKey(continent)) {
			ArrayList<String> tempArray1 = continentTerritory.get(continent);
			if (!tempArray1.contains(territory)) {
				tempArray1.add(territory);
				continentTerritory.put(continent, tempArray1);
			}
		} else {
			ArrayList<String> tempArray2 = new ArrayList<>();
			tempArray2.add(territory);
			continentTerritory.put(continent, tempArray2);
		}
		return continentTerritory;
	}

	/**
	 * get method for continentTerritory object
	 * @return continentTerritory map object
	 */
	public Map<String, ArrayList<String>> getContinentTerritory() {
		return continentTerritory;
	}

	/**
	 * set method for continent armies value 
	 * @param continentValue a HashMap contains value of armies when continent is fully acquired by player.
	 */
	public void setContinentValue(Map<String, Integer> continentValue) {
		this.continentValue = continentValue;
	}

	/**
	 * Used to identify which continent has how many territory of particular player
	 * and store it to HashMap continentOwnedterritory
	 * used to add or remove  territory from continent of particular player
	 * 
	 * @param continent Name of Continent
	 * @param territory Name of Territory
	 * @param operation boolean value
	 * @return a HashMap continentOwnedterritory
	 */
	public Map<String, ArrayList<String>> addContinentOwnedTerritory(String continent, String territory, boolean operation) {

		if (operation) {
			if (continentOwnedTerritory.containsKey(continent)) {
				ArrayList<String> tempArray1 = continentOwnedTerritory.get(continent);
				if (!tempArray1.contains(territory)) {
					tempArray1.add(territory);
					continentOwnedTerritory.put(continent, tempArray1);
				}
			} else {
				ArrayList<String> tempArray2 = new ArrayList<>();
				tempArray2.add(territory);
				continentOwnedTerritory.put(continent, tempArray2);
			}
		} else {
			if (continentOwnedTerritory.containsKey(continent)) {
				ArrayList<String> tempArray3 = continentOwnedTerritory.get(continent);
				if (tempArray3.contains(territory)) {
					tempArray3.remove(territory);
					if (!tempArray3.isEmpty())
						continentOwnedTerritory.replace(continent, continentOwnedTerritory.get(continent), tempArray3);
					else
						continentOwnedTerritory.remove(continent);
				}
			}
		}
		return continentOwnedTerritory;
	}

	/**
	 * set method for continentTerritory object
	 * @param continentTerritory a Map which contains no.of Territories for each Continent
	 */
	public void setContinentTerritory(Map<String, ArrayList<String>> continentTerritory) {
		this.continentTerritory = continentTerritory;
	}

	/**
	 * get method for continentOwnedTerritory object
	 * @return a map continentOwnedterritory
	 */
	public Map<String, ArrayList<String>> getContinentOwnedterritory() {
		return continentOwnedTerritory;
	}

	/**
	 * set method for continentOwnedterritory map object
	 * @param continentOwnedterritory no. of Territories owned by Player for particular Continent
	 */
	public void setContinentOwnedterritory(Map<String, ArrayList<String>> continentOwnedterritory) {
		this.continentOwnedTerritory = continentOwnedterritory;
	}

	/**
	 *  This method used to update keys and value of map
	 * @param oldContinent Old key
	 * @param newContinent New key which need to replace by old key
	 * @param newValue New value of Continent
	 */
	public void updateContinentValue(String oldContinent, String newContinent, int newValue) {
		continentValue.remove(oldContinent);
		continentValue.put(newContinent, newValue);
	}

}
