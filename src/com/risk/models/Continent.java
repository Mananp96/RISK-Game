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
	 * @param continentOwnedterritory no. of territories owned by player for particular continent
	 * @param playerOwnedContTerr no. of territory owned by player
	 */
	Map<String, Integer> continentValue; 
	Map<String, ArrayList<String>> continentTerritory;
	Map<String, ArrayList<String>> continentOwnedterritory; 
	Map<String, Territory> playerOwnedContTerr;

	public Continent() {
		continentValue = new HashMap<>();
		continentTerritory = new HashMap<>();
		continentOwnedterritory = new HashMap<>();
		playerOwnedContTerr = new HashMap<>();
	}

	/**
	 * @return Map playerOwnedTerr
	 */
	public Map<String, Territory> getPlayerOwnedContTerr() {
		return playerOwnedContTerr;
	}

	/**
	 * @param playerOwnedContTerr a HashMap contains No. of territories owned by player for particular continent
	 */
	public void setPlayerOwnedContTerr(Map<String, Territory> playerOwnedContTerr) {
		this.playerOwnedContTerr = playerOwnedContTerr;
	}

	/**
	 * 
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
		if(continentValue.containsKey(continent)) {
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
	 */
	public Map<String, ArrayList<String>> addContinentTerritory(String continent, String territory) {
		if(continentTerritory.containsKey(continent)) {
			ArrayList<String> tempArray1 = continentTerritory.get(continent);
			if(!tempArray1.contains(territory)){
				tempArray1.add(territory);
				continentTerritory.put(continent,tempArray1);
			}
		} else {
			ArrayList<String> tempArray2 = new ArrayList<>();
			tempArray2.add(territory);
			continentTerritory.put(continent, tempArray2);
		}
		return continentTerritory;
	}

	/*
	 * used to identify which continent has how many territory of particular player
	 * used to add or remove  territory of particular player 
	 */
	/**
	 * 
	 * @return a Map continentTerritory
	 */
	public Map<String, ArrayList<String>> getContinentTerritory() {
		return continentTerritory;
	}

	/**
	 * @param continentValue a HashMap contains value of armies when continent is fully acquired by player.
	 */
	public void setContinentValue(Map<String, Integer> continentValue) {
		this.continentValue = continentValue;
	}

	/**
	 * used to identify which continent has how many territory of particular player
	 * and store it to HashMap continentOwnedterritory
	 * used to add or remove  territory of particular player
	 * 
	 * @param continent Name of Continent
	 * @param territory Name of Territory
	 * @param operation 
	 * @return a map continentOwnedterritory
	 */
	public Map<String, ArrayList<String>> addContinentOwnedTerritory(String continent, String territory, boolean operation) {

		if(operation) {
			if(continentOwnedterritory.containsKey(continent)) {
				ArrayList<String> tempArray1 = continentOwnedterritory.get(continent);
				if(!tempArray1.contains(territory)){
					tempArray1.add(territory);
					continentOwnedterritory.put(continent,tempArray1);
				}
			} else {
				ArrayList<String> tempArray2 = new ArrayList<>();
				tempArray2.add(territory);
				continentOwnedterritory.put(continent, tempArray2);
			}
		} else {
			if(continentOwnedterritory.containsKey(continent)) {
				ArrayList<String> tempArray3 = continentOwnedterritory.get(continent);
				if(tempArray3.contains(territory)){
					tempArray3.remove(territory);
					if(!tempArray3.isEmpty())
						continentOwnedterritory.replace(continent, continentOwnedterritory.get(continent), tempArray3);
					else
						continentOwnedterritory.remove(continent);
				}
			}
		}
		return continentOwnedterritory;
	}

	/**
	 * 
	 * @param continentTerritory a Map which contains no.of Territories for each Continent
	 */
	public void setContinentTerritory(Map<String, ArrayList<String>> continentTerritory) {
		this.continentTerritory = continentTerritory;
	}

	/**
	 * 
	 * @return a map continentOwnedterritory
	 */
	public Map<String, ArrayList<String>> getContinentOwnedterritory() {
		return continentOwnedterritory;
	}

	/**
	 * 
	 * @param continentOwnedterritory no. of Territories owned by Player for particular Continent
	 */
	public void setContinentOwnedterritory(Map<String, ArrayList<String>> continentOwnedterritory) {
		this.continentOwnedterritory = continentOwnedterritory;
	}

}
