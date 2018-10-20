package com.risk.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a model class for playerContinent, playerArmy,
 * playerList, playerPlaying as its data member.
 *
 */
public class Players {

	/**
	 * @param playerContinent player had territories in particular continent
	 * @param playerArmy player having no. of armies
	 * @param playerList no. of player currently playing game
	 * @param playerPlaying no. of player playing game
	 */
	Map<String, Continent> playerContinent; // 
	Map<String, Integer> playerArmy;  // 
	ArrayList<String> playerList; 
	ArrayList<String> playerPlaying;
	String currentPhase;

	public Players() {
		playerContinent = new HashMap<>();
		playerArmy = new HashMap<>();
		playerList = new ArrayList<>();
		playerPlaying = new ArrayList<>();
	}

	/**
	 * 
	 * @return a List of Players playing.
	 */
	public ArrayList<String> getPlayerPlaying() {
		return playerPlaying;
	}

	/**
	 * 
	 * @param playerPlaying a List of Players playing.
	 */
	public void setPlayerPlaying(ArrayList<String> playerPlaying) {
		this.playerPlaying = playerPlaying;
	}

	/**
	 * 
	 * @return a List of Players.
	 */
	public ArrayList<String> getPlayerList() {
		return playerList;
	}

	/**
	 * 
	 * @param playerList a List of Players.
	 */
	public void setPlayerList(ArrayList<String> playerList) {
		this.playerList = playerList;
	}

	/**
	 * This method create the List of Players.
	 * @param name Name of Player.
	 * @return playerList.
	 */
	public ArrayList<String> addPlayers(String name) {
		playerList.add(name);
		return playerList;
	}

	/**
	 * This method selects the Players who will be playing from the playerList
	 * and return a list of player playing.
	 * @param names contains a List of players.
	 * @param count number of players playing.
	 * @return a list playerPlaying.
	 */
	public ArrayList<String> selectPlayers(ArrayList<String> names , int count) {

		for(int i = 0 ; i < count ; i++ ) {
			playerPlaying.add(names.get(i));
		}
		return playerPlaying;
	}

	/**
	 * This method update the Map updatePlayerContinent.
	 * @param name Name of player
	 * @param continent object of model class Continent
	 * @return a Map playerContinent
	 */
	public Map<String, Continent> updatePlayerContinent(String name, Continent continent){
		playerContinent.replace(name, continent);
		return playerContinent;
	}

	/**
	 * This method assign a Player to Particular Continent's Territories.
	 * @param name Name of Player
	 * @param continent object of model class Continent
	 * @return a Map playerContinent
	 */
	public Map<String, Continent> addPlayerContinent(String name, Continent continent){
		playerContinent.put(name, continent);
		return playerContinent;
	}

	/**
	 * This method returns the Territories aquired by particular player.
	 * @param name Name of Player
	 * @return a Map PlayerContinent
	 */
	public Continent getPlayerContinent(String name){

		return playerContinent.get(name);
	}

	/**
	 * This method used to assign the armies by player the name of Player.
	 * @param name Name of player
	 * @param armyCount Number of Armies 
	 * @return a Map playerArmy
	 */
	public Map<String, Integer> initialArmy(String name,int armyCount){
		playerArmy.put(name, armyCount);
		return playerArmy;
	}

	/**
	 * This method is used to find the number of armies by the name of Player.
	 * @param name Name of Player.
	 * @return a Map playerArmy
	 */
	public int getPlayerArmy(String name){
		return playerArmy.get(name);
	}

	
	/**
	 * This method update the Map playerArmy.
	 * @param name Name of Player
	 * @param armyCount Number of Armies
	 * @param operation Name of Operation
	 * @return a Map playerArmy
	 */
	public Map<String, Integer> updateArmy(String name,int armyCount,String operation){
		if(operation.equalsIgnoreCase("add")) {
			playerArmy.replace(name, playerArmy.get(name), playerArmy.get(name) + armyCount);
		} else {
			playerArmy.replace(name, playerArmy.get(name), playerArmy.get(name) - armyCount);
		}
		return playerArmy;
	}

	/**
	 * Returns the List of Players.
	 * @param number Number of Players.
	 * @return a List of Players.
	 */
	public String getPlayers(int number) {
		return playerList.get(number);	
	}

	/**
	 * This method is used to assign the current phase of game.
	 * @param currentPhase Assign the current phase of game.
	 */
    public void setCurrentPhase(String currentPhase) {
		this.currentPhase = currentPhase;
    }

	/**
	 * Returns the current phase of game.
	 * @return  currentPhase of player.
	 */
	public String getCurrentPhase() {
    	return currentPhase;
	}
}
