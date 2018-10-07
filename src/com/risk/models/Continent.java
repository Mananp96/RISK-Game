package com.risk.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Continent {

	Map<String, Integer> continentValue; // value of armies when continent is fully acquired by player.
	Map<String, ArrayList<String>> continentTerritory; // no.of territories for each continent
	Map<String, ArrayList<String>> continentOwnedterritory; // no. of territories owned by player for particular continent
	Map<String, Territory> playerOwnedContTerr;
	public Continent() {
		continentValue = new HashMap<>();
		continentTerritory = new HashMap<>();
		continentOwnedterritory = new HashMap<>();
		playerOwnedContTerr = new HashMap<>();
	}
	public Map<String, Territory> getPlayerOwnedContTerr() {
		return playerOwnedContTerr;
	}
	public void setPlayerOwnedContTerr(Map<String, Territory> playerOwnedContTerr) {
		this.playerOwnedContTerr = playerOwnedContTerr;
	}
	public Map<String, Integer> getContinentValue() {
		return continentValue;
	}
	public void setContinentValue(String continent, int value) {
		if(continentValue.containsKey(continent)) {
			continentValue.replace(continent, continentValue.get(continent), value);
		} else {
			continentValue.put(continent, value);
		}
	}
	/*
	 * used to identify which continent has how many territory
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
	
	public Map<String, ArrayList<String>> getContinentTerritory() {
		return continentTerritory;
	}
	public void setContinentValue(Map<String, Integer> continentValue) {
		this.continentValue = continentValue;
	}
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
	
	public void setContinentTerritory(Map<String, ArrayList<String>> continentTerritory) {
		this.continentTerritory = continentTerritory;
	}
	public Map<String, ArrayList<String>> getContinentOwnedterritory() {
		return continentOwnedterritory;
	}
	public void setContinentOwnedterritory(Map<String, ArrayList<String>> continentOwnedterritory) {
		this.continentOwnedterritory = continentOwnedterritory;
	}
	
}
