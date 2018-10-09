package com.risk.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Territory {

	Map<String, String> territoryUser;// Territory belong to which user
	Map<String, String> territoryCont; // territory  belong to which continent
	Map<String, Integer> territoryArmy; // Armies for particular territory
	Map<String, ArrayList<String>> adjacentTerritory; // adjacent territory of territory
	ArrayList<String> territoryList; // List of total territory
	
	public Territory() {
		territoryUser = new HashMap<>();
		territoryCont = new HashMap<>();
		territoryArmy = new HashMap<>();
		adjacentTerritory = new HashMap<>();
		territoryList = new ArrayList<>();
	}
	
	public ArrayList<String> getTerritoryList() {
		return territoryList;
	}
	
	public void setTerritoryList(ArrayList<String> territoryList) {
		this.territoryList = territoryList;
	}
	
	public Map<String, String> getTerritoryUser() {
		return territoryUser;
	}
	
	public void setTerritoryUser(Map<String, String> territoryUser) {
		this.territoryUser = territoryUser;
	}
	
	public Map<String, String> getTerritoryCont() {
		return territoryCont;
	}
	
	public void setTerritoryCont(Map<String, String> territoryCont) {
		this.territoryCont = territoryCont;
	}
	
	public Map<String, Integer> getTerritoryArmy() {
		return territoryArmy;
	}
	public void setTerritoryArmy(Map<String, Integer> territoryArmy) {
		this.territoryArmy = territoryArmy;
	}
	
	/*
	 * update count of army for each territory 
	 * used for initial startup phase of Game
	 * used to  update count of army during reinforcement
	 */
	
	public Map<String, Integer> updateTerritoryArmy (String territory,int army, String operation){
		if(operation.equalsIgnoreCase("add")) {
			if(territoryArmy.containsKey(territory)) {
				territoryArmy.replace(territory, territoryArmy.get(territory), territoryArmy.get(territory) + army);
			} else {
				territoryArmy.put(territory, army);
			}
		} else {
			territoryArmy.replace(territory, territoryArmy.get(territory), territoryArmy.get(territory) - army);
		}
		
		return territoryArmy;
	}
	
	/*
	 * used to identify territory which belongs to which continent
	 */
	
	public Map<String, String> addTerritoryCont (String territory,String continent){
		territoryCont.put(territory, continent);
		return territoryCont;
	}
	
	/*
	 * used to identify which territory belongs to which user
	 * used to update user when a particular territory wins by user 
	 */
	
	public Map<String, String> updateTerritoryUser (String user,String territory){
		if(territoryUser.containsKey(territory)) {
			territoryUser.replace(territory, territoryUser.get(territory), user);
		} else {
			territoryUser.put(territory, user);
		}
		return territoryUser;
	}
	
	/*
	 * used to add adjacent territory of territory
	 */
	
	public Map<String, ArrayList<String>> addAdjacentTerritory(String territory, String adjTerritory){
		if(adjacentTerritory.containsKey(territory)) {
			adjacentTerritory.get(territory).add(adjTerritory);
		} else {
			ArrayList<String> tempArray= new ArrayList<>();
			tempArray.add(adjTerritory);
			adjacentTerritory.put(territory,tempArray);
		}
			
		return adjacentTerritory;
	}
	
	public Map<String, ArrayList<String>> getAdjacentTerritory() {
		return adjacentTerritory;
	}
	
	public void setAdjacentTerritory(Map<String, ArrayList<String>> adjacentTerritory) {
		this.adjacentTerritory = adjacentTerritory;
	}
	
	/*
	 *  List of Territory in List
	 */
	public ArrayList<String> addTerritory(String territory){
		territoryList.add(territory);
		return territoryList;
	}
	
}
