package com.risk.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	 */
	Map<String, String> territoryUser; 
	Map<String, String> territoryCont; 
	Map<String, Integer> territoryArmy; 
	Map<String, ArrayList<String>> adjacentTerritory; 
	ArrayList<String> territoryList; 
	Map<String, String> territoriesMap;
	Map<String,String> adjacentTerritoriesMap;
	boolean flag = false;
	
	
	public Territory() {
		flag = false;
		territoryUser = new HashMap<>();
		territoryCont = new HashMap<>();
		territoryArmy = new HashMap<>();
		adjacentTerritory = new HashMap<>();
		territoryList = new ArrayList<>();
		territoriesMap = new HashMap<>();
		adjacentTerritoriesMap = new HashMap<>();
	}
	
	
//	public boolean isFlag() {
//		return flag;
//	}
//
//	public void setFlag(boolean flag) {
//		this.flag = flag;
//	}
	
//	/**
//	 * 
//	 * @return territoriesMap 
//	 */
//	public Map<String, String> getTerritoriesMap() {
//		return territoriesMap;
//	}
//	
//	/**
//	 * 
//	 * @param territoriesMap 
//	 */
//	public void setTerritoriesMap(Map<String, String> territoriesMap) {
//		this.territoriesMap = territoriesMap;
//	}
//
//	public void addTerritoriesMap(String key, String value) {
//		if(territoriesMap.size() == 0 || territoriesMap.isEmpty()) {
//			territoriesMap.put(key, value);
//		}else {
//			if(territoriesMap.containsKey(key)) {
//				this.flag = true;
//			}else {
//				territoriesMap.put(key, value);
//			}
//		}
//		
//	}
//	
//	public Map<String, String> getAdjacentTerritoriesMap() {
//		return adjacentTerritoriesMap;
//	}
//
//	public void setAdjacentTerritoriesMap(Map<String, String> adjacentTerritoriesMap) {
//		this.adjacentTerritoriesMap = adjacentTerritoriesMap;
//	}
//	
//	public void addAdjacentTerritoriesMap(String key,  String value) {
//		System.out.println(key);
//		if(adjacentTerritoriesMap.size() == 0 || adjacentTerritoriesMap.isEmpty()) {
//			adjacentTerritoriesMap.put(key, value);
//		
//		}else {
//			if(adjacentTerritoriesMap.containsKey(key)) {
//				this.flag = true;
//				System.out.println("++"+key);
//			}else {
//				adjacentTerritoriesMap.put(key, value);
//			}
//		}
//		
//	}
	
	/**
	 * 
	 * @return list of all territories
	 */
	public ArrayList<String> getTerritoryList() {
		return territoryList;
	}
	
	/**
	 * 
	 * @param territoryList list of all territories
	 */
	public void setTerritoryList(ArrayList<String> territoryList) {
		this.territoryList = territoryList;
	}
	
	/**
	 * 
	 * @return User owned Territories
	 */
	public Map<String, String> getTerritoryUser() {
		return territoryUser;
	}
	
	/**
	 * 
	 * @param territoryUser User owned Territories
	 */
	public void setTerritoryUser(Map<String, String> territoryUser) {
		this.territoryUser = territoryUser;
	}
	
	/**
	 * 
	 * @return Continent of particular Territory.
	 */
	public Map<String, String> getTerritoryCont() {
		return territoryCont;
	}
	
	/**
	 * 
	 * @param territoryCont Continent of particular Territory.
	 */
	public void setTerritoryCont(Map<String, String> territoryCont) {
		this.territoryCont = territoryCont;
	}
	
	/**
	 * 
	 * @return a HashMap which contains Territories and it's armies.
	 */
	public Map<String, Integer> getTerritoryArmy() {
		return territoryArmy;
	}
	
	/**
	 * 
	 * @param territoryArmy a HashMap which contains Territories and it's armies.
	 */
	public void setTerritoryArmy(Map<String, Integer> territoryArmy) {
		this.territoryArmy = territoryArmy;
	}
	
	/**
	 * This method is used to:
	 * <ul>
	 * <li>update count of army for each territory</li>
	 * <li>used for initial startup phase of Game</li>
	 * <li>used to update count of army during reinforcement</li>
	 * </ul>
	 * 
	 * @param territory Name of Territory
	 * @param army Number of armies
	 * @param operation Name of operation
	 * @return a HashMap which contains Territories and it's armies.
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
	 * <ul>
	 * @param user Name of Player. 
	 * @param territory Name of Territory.
	 * @return a HashMap contains Players and it's Territories.
	 */
	public Map<String, String> updateTerritoryUser (String user,String territory){
		if(territoryUser.containsKey(territory)) {
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
		if(adjacentTerritory.containsKey(territory)) {
			adjacentTerritory.get(territory).add(adjTerritory);
		} else {
			ArrayList<String> tempArray= new ArrayList<>();
			tempArray.add(adjTerritory);
			adjacentTerritory.put(territory,tempArray);
		}
			
		return adjacentTerritory;
	}
	
	/**
	 * 
	 * @return a HashMap with Territory and it's adjacent Territories.
	 */
	public Map<String, ArrayList<String>> getAdjacentTerritory() {
		return adjacentTerritory;
	}
	
	/**
	 * 
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
	
}
