package com.risk.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Players {

	Map<String, Continent> playerContinent; // player had territories in particular continent
	Map<String, Integer> playerArmy;  // player having no. of armies
	ArrayList<String> playerList;
	ArrayList<String> playerPlaying;
	
	public Players() {
		playerContinent = new HashMap<>();
		playerArmy = new HashMap<>();
		playerList = new ArrayList<>();
		playerPlaying = new ArrayList<>();
	}
	
	public ArrayList<String> getPlayerPlaying() {
		return playerPlaying;
	}

	public void setPlayerPlaying(ArrayList<String> playerPlaying) {
		this.playerPlaying = playerPlaying;
	}

	public ArrayList<String> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<String> playerList) {
		this.playerList = playerList;
	}

	
	public ArrayList<String> addPlayers(String name) {
		playerList.add(name);
		return playerList;
	}
	
	public ArrayList<String> selectPlayers(ArrayList<String> names , int count) {
		
		for(int i = 0 ; i < count ; i++ ) {
			playerPlaying.add(names.get(i));
		}
		return playerPlaying;
	}
	/*
	 * 
	 * 
	 */
	
	public Map<String, Continent> updatePlayerContinent(String name, Continent continent){
		playerContinent.replace(name, continent);
		return playerContinent;
	}
	
	public Map<String, Continent> addPlayerContinent(String name, Continent continent){
		playerContinent.put(name, continent);
		return playerContinent;
	}
	public Continent getPlayerContinent(String name){
		
		return playerContinent.get(name);
	}
	
/*	public Map<String, ArrayList<String>>  updateUserContinent(String player,String continent){
		if(!playerContinent.containsKey(continent)) {
			ArrayList<String> tempArray = new ArrayList<>();
			tempArray.add(continent);
			playerContinent.put(continent, tempArray);
		}
		return playerContinent;
	}
*/	
	public Map<String, Integer> initialArmy(String name,int armyCount){
		playerArmy.put(name, armyCount);
		return playerArmy;
	}
	public int getPlayerArmy(String name){
		return playerArmy.get(name);
	}
	
	public Map<String, Integer> updateArmy(String name,int armyCount,String operation){
		if(operation.equalsIgnoreCase("add")) {
			playerArmy.replace(name, playerArmy.get(name), playerArmy.get(name) + armyCount);
		} else {
			playerArmy.replace(name, playerArmy.get(name), playerArmy.get(name) - armyCount);
		}
		return playerArmy;
	}
	public String getPlayers(int number) {
		return playerList.get(number);	
	}
	
	
}
