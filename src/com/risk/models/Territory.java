package com.risk.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Territory Model class.
 *
 */
public class Territory {

	/**
	 * @param territoryUser Territory belong to which user
	 * @param territoryCont territory belong to which continent
	 * @param territoryArmy Armies for particular territory
	 * @param adjacentTerritory Adjacent territory of territory
	 * @param territoryList List of total territory
	 * @param territoryCard card for particular territory
	 * @param cardValue armies for particular card
	 */
	Map<String, String> territoryUser; 
	Map<String, String> territoryCont; 
	Map<String, Integer> territoryArmy; 
	Map<String, ArrayList<String>> adjacentTerritory; 
	Map<String, String> territoryCard;
	Map<String, ArrayList<String>> duplicateTerritoryContinent; //to be used only for validation to check duplicacy.
	Map<String, Integer> territoryNumber; // territory with Number
	Map<Integer,String> cardValue;
	ArrayList<String> territoryList; 

	boolean flag = false;

	/** 
	 * Constructor
	 */
	public Territory() {
		flag = false;
		territoryUser = new HashMap<>();
		territoryCont = new HashMap<>();
		territoryArmy = new HashMap<>();
		adjacentTerritory = new HashMap<>();
		territoryCard = new HashMap<>();
		cardValue = new HashMap<>();
		territoryList = new ArrayList<>();
		duplicateTerritoryContinent = new HashMap<>();
		territoryNumber = new HashMap<>();
	}

	/**
	 * get method for card value
	 * @return cardValue number of armies for particular card
	 */
	public Map<Integer, String> getCardValue() {
		return cardValue;
	}
	/**
	 * This method set card with armies 
	 * @param cardValue armies for particular card
	 */
	public void setCardValue(Map<Integer, String> cardValue) {
		this.cardValue = cardValue;
	}

	/**
	 * This method add armies for particular card
	 * @param cardName name of card
	 * @param armies no. of armies for particular card
	 */
	public void addCardValue(String cardName, int armies) {
		cardValue.put(armies, cardName);
	}

	/**
	 * get method for territory list
	 * @return list of all territories
	 */
	public ArrayList<String> getTerritoryList() {
		return territoryList;
	}

	/**
	 * set method for territory list
	 * @param territoryList list of all territories
	 */
	public void setTerritoryList(ArrayList<String> territoryList) {
		this.territoryList = territoryList;
	}

	/**
	 * This Method is used to get card of territory
	 * @return territorycard card of territory
	 */
	public Map<String, String> getTerritoryCard() {
		return territoryCard;
	}
	/**
	 * This method is used to set card for particular territory.
	 * @param territoryCard card for particular territory
	 */
	public void setTerritoryCard(Map<String, String> territoryCard) {
		this.territoryCard = territoryCard;
	}
	/**
	 * This method add card for particular territory 
	 * @param territory Territory Name
	 * @param cardName Card Name
	 */
	public void addTerritoryCard(String territory, String cardName) {
		territoryCard.put(territory, cardName);
	}
	/**
	 * get method for user owned territory
	 * @return User owned Territories
	 */
	public Map<String, String> getTerritoryUser() {
		return territoryUser;
	}

	/**
	 * set method for user owned territory
	 * @param territoryUser User owned Territories
	 */
	public void setTerritoryUser(Map<String, String> territoryUser) {
		this.territoryUser = territoryUser;
	}

	/**
	 * get method for continent of territory
	 * @return Continent of particular Territory.
	 */
	public Map<String, String> getTerritoryCont() {
		return territoryCont;
	}

	/**
	 * set method for continent of territory
	 * @param territoryCont Continent of particular Territory.
	 */
	public void setTerritoryCont(Map<String, String> territoryCont) {
		this.territoryCont = territoryCont;
	}

	/**
	 * get method for list of armies in territory 
	 * @return territoryArmy a HashMap which contains Territories and it's armies.
	 */
	public Map<String, Integer> getTerritoryArmy() {
		return territoryArmy;
	}

	/**
	 * set method for armies in territories
	 * @param territoryArmy a HashMap which contains Territories and it's armies.
	 */
	public void setTerritoryArmy(Map<String, Integer> territoryArmy) {
		this.territoryArmy = territoryArmy;
	}

	/**
	 * This method is used to:
	 * <ul>
	 * <li>Update count of army for each territory.</li>
	 * <li>for initial startup phase of Game.</li>
	 * <li>Update count of army during reinforcement.</li>
	 * </ul>
	 * 
	 * @param territory Name of Territory
	 * @param army Number of armies
	 * @param operation Name of operation
	 * @return a HashMap which contains Territories and it's armies.
	 */
	public Map<String, Integer> updateTerritoryArmy (String territory,int army, String operation){
		if (operation.equalsIgnoreCase("add")) {
			if (territoryArmy.containsKey(territory)) {
				territoryArmy.replace(territory, territoryArmy.get(territory), territoryArmy.get(territory) + army);
			} else {
				territoryArmy.put(territory, army);
			}
		} else {
			territoryArmy.replace(territory, territoryArmy.get(territory), territoryArmy.get(territory) - army);
		}

		return territoryArmy;
	}

	/**
	 * This method is used to identify Territory which belongs to which Continent.
	 * @param territory Name of Territory
	 * @param continent Name of Continent
	 * @return a HashMap contains Continent and it's Territory.
	 */
	public Map<String, String> addTerritoryCont (String territory,String continent){
		territoryCont.put(territory, continent);
		return territoryCont;
	}


	/**
	 * This method is used to:
	 * <ul>
	 * <li>Identify which Territory belongs to which Player</li>
	 * <li>Update Player when a particular Territory wins by Player</li>
	 * </ul>
	 *
	 * @param user Name of Player. 
	 * @param territory Name of Territory.
	 * @return a HashMap contains Players and it's Territories.
	 */
	public Map<String, String> updateTerritoryUser (String user,String territory){
		if (territoryUser.containsKey(territory)) {
			territoryUser.replace(territory, territoryUser.get(territory), user);
		} else {
			territoryUser.put(territory, user);
		}
		return territoryUser;
	}

	/**
	 * This method is used to add adjacent Territories of a Territory.
	 * @param territory Name of Territory.
	 * @param adjTerritory Name of adjacent Territory.
	 * @return a HashMap with Territory and it's adjacent Territories.
	 */
	public Map<String, ArrayList<String>> addAdjacentTerritory(String territory, String adjTerritory){
		if (adjacentTerritory.containsKey(territory)) {
			adjacentTerritory.get(territory).add(adjTerritory);
		} else {
			ArrayList<String> tempArray = new ArrayList<>();
			tempArray.add(adjTerritory);
			adjacentTerritory.put(territory, tempArray);
		}

		return adjacentTerritory;
	}

	/**
	 * get method for adjacent territories
	 * @return adjacentTerritory a HashMap with Territory and it's adjacent Territories.
	 */
	public Map<String, ArrayList<String>> getAdjacentTerritory() {
		return adjacentTerritory;
	}

	/**
	 * set method for adjacent territories
	 * @param adjacentTerritory a HashMap with Territory and it's adjacent Territories.
	 */
	public void setAdjacentTerritory(Map<String, ArrayList<String>> adjacentTerritory) {
		this.adjacentTerritory = adjacentTerritory;
	}

	/**
	 * This method is used to add Territory to Territories List.
	 * @param territory Name of Territory.
	 * @return List of Territories.
	 */
	public ArrayList<String> addTerritory(String territory){
		territoryList.add(territory);
		return territoryList;
	}

	/**
	 * This method is used to check if particular Territory is having multiple Continents?
	 * To be used for map validation purpose only.
	 * 
	 * @param territory Name of Territory
	 * @param continent Name of Continent
	 */
	public void addDuplicateTerritoryContinent(String territory, String continent) {
		if (duplicateTerritoryContinent.containsKey(territory)) {
			duplicateTerritoryContinent.get(territory).add(continent);
		} else {
			ArrayList<String> continentList = new ArrayList<>();
			continentList.add(continent);
			duplicateTerritoryContinent.put(territory, continentList);
		}
	}

	/**
	 * get method for Territory and it's Continent 
	 * @return duplicateTerritoryContinent a HashMap with Territory and it's Continent.
	 */
	public Map<String,ArrayList<String>> getDuplicateTerritoryContinent(){
		return duplicateTerritoryContinent;
	}

	/**
	 * Give a Number to Territory
	 * @param territory Name of Territory
	 * @param territoryNum Number starting from 0
	 */
	public void addNumberOfTerritory(String territory, int territoryNum) {
		territoryNumber.put(territory, territoryNum);
	}

	/**
	 * get method for territory number
	 * @return territoryNumber a HashMap contains number of territory.
	 */
	public Map<String,Integer> getNumberOfTerritory(){
		return territoryNumber;
	}

	/**
	 * get method for territory number
	 * @return territoryNumber a HashMap contains number of territory
	 */
	public Map<String, Integer> getTerritoryNumber() {
		return territoryNumber;
	}

	/**
	 * set method for territory number
	 * @param territoryNumber a HashMap contains number of territory
	 */
	public void setTerritoryNumber(Map<String, Integer> territoryNumber) {
		this.territoryNumber = territoryNumber;
	}
	/**
	 * method updates new continent name
	 * @param oldContinent Continent Name
	 * @param newContinent Continent Name which replace old continent
	 */
	public void updateTerritoryContinent(String oldContinent, String newContinent) {
		for (Entry<String, String> entry : territoryCont.entrySet()) {
			if (entry.getValue().equalsIgnoreCase(oldContinent)) {
				entry.setValue(newContinent);
			}
		}
	}

}
