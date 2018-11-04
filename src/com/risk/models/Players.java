package com.risk.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import com.risk.strategy.Strategy;

/**
 * This is a model class for playerContinent, playerArmy,
 * playerList, playerPlaying as its data member.
 *
 */
public class Players implements Strategy {
	/**
	 * @param playerContinent player had territories in particular continent
	 * @param playerArmy player having no. of armies
	 * @param playerList no. of player currently playing game
	 * @param playerPlaying no. of player playing game
	 * @param playerCard no. of card player has
	 */
	Map<String, Continent> playerContinent; 
	Map<String, Integer> playerArmy;   
	ArrayList<String> playerList; 
	ArrayList<String> playerPlaying;
	String currentPhase;
	Map<String,Territory> playerCard;
	private boolean isAttackWon;

	public Players() {
		playerContinent = new HashMap<>();
		playerArmy = new HashMap<>();
		playerList = new ArrayList<>();
		playerPlaying = new ArrayList<>();
	}

	/**
	 * 
	 * @return playerContinent player had territories in particular continent
	 */
	public Map<String, Continent> getPlayerContinent() {
	    return playerContinent;
	}
	/**
	 * This method set territories in particular continent
	 * @param playerContinent Territories in particular continent
	 */
	public void setPlayerContinent(Map<String, Continent> playerContinent) {
	    this.playerContinent = playerContinent;
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
 * This method get number of card player has.
 * @return playerCard number of card which player has
 */
	public Map<String, Territory> getPlayerCard() {
	    return playerCard;
	}
	/**
	 * This method is used to set card for player
	 * @param playerCard card which you need to add in player
	 */
	public void setPlayerCard(Map<String, Territory> playerCard) {
	    this.playerCard = playerCard;
	}

	/**
	 * This method returns the Territories acquired by particular player.
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

	/**
	 * return if attack is won.
	 * @return true if attack is won, else false;
	 */
	public boolean isAttackWon() {
		return isAttackWon;
	}

	/**
	 * set default value of isAttackWon is false;
	 * @param isAttackWon attack is won or not?
	 */
	public void setAttackWon(boolean isAttackWon) {
		this.isAttackWon = isAttackWon;
	}

	/**
	 * Attack phase
	 */
	@Override
	public void doAttack(Territory currentTerritory, String fromTerritory, String toTerritory, int attackerDice, int defenderDice) {
	    // TODO Auto-generated method stub
		isAttackWon = false;
		ArrayList<Integer> attackerDiceList = new ArrayList<>();
		ArrayList<Integer> defenderDiceList = new ArrayList<>();
		int[] attackerDiceArray = new int[attackerDice];
		int[] defenderDiceArray = new int[defenderDice];
		for(int i=0;i<attackerDice;i++) {
			attackerDiceArray[i] = (int)(Math.random()*6+1);
			System.out.println("DIE "+i+" "+attackerDiceArray[i]);
			attackerDiceList.add(attackerDiceArray[i]);
			
		}
		
		for(int j=0;j<defenderDice;j++) {
			defenderDiceArray[j] = (int)(Math.random()*6+1);
			System.out.println("DIE "+j+" "+defenderDiceArray[j]);
			defenderDiceList.add(defenderDiceArray[j]);
		}
	    
		Collections.sort(attackerDiceList);
		Collections.sort(defenderDiceList);
		Collections.reverse(attackerDiceList);
		Collections.reverse(defenderDiceList);
		int maximumAttack = attackerDiceList.size() >= defenderDiceList.size() ? defenderDiceList.size() : attackerDiceList.size();  
		System.out.println(attackerDiceList);
		System.out.println(defenderDiceList);
		System.out.println(maximumAttack);
		
		for(int i=0;i<maximumAttack;i++) {
			if(attackerDiceList.get(i) > defenderDiceList.get(i)) {
				currentTerritory.updateTerritoryArmy(toTerritory, 1, "DELETE");
			}else {
				currentTerritory.updateTerritoryArmy(fromTerritory, 1, "DELETE");
			}
		}
		
		if(currentTerritory.getTerritoryArmy().get(toTerritory) == 0) {
			isAttackWon = true;
			currentTerritory.updateTerritoryUser(currentTerritory.getTerritoryUser().get(fromTerritory),toTerritory);
		}
		
	}
	
	/**
	 * do Fortification
	 */
	@Override
	public void doForitification(Territory currentTerritory, String fromTerritory, String toTerritory, int getArmySelect) {
	    currentTerritory.updateTerritoryArmy(fromTerritory, getArmySelect, "DELETE");
	    currentTerritory.updateTerritoryArmy(toTerritory, getArmySelect, "ADD");

	}
	
	/**
	 * do Reinforcement
	 */
	@Override
	public void doReinforcement(String currentPlayer, String currentTerritoryName, int army, Territory currentTerritory) {
	    updateArmy(currentPlayer, army, "DELETE");
	    currentTerritory.updateTerritoryArmy(currentTerritoryName, army, "ADD");	    
	}
	
	/**
	 * generate Reinforcement Army
	 */
	@Override
	public void generateReinforcementArmy(String currentPlayer, Continent currentContinent) {
	    int count = 0;
	    setCurrentPhase("Reinforcement");
	    Map<String, ArrayList<String>> tempData = getPlayerContinent().get(currentPlayer).getContinentOwnedterritory(); 
	    for(Entry<String, ArrayList<String>> entry : tempData.entrySet()) {
		if(!entry.getValue().isEmpty()) {
		    count+=entry.getValue().size();
		}
	    }

	    Double value = new Double(Math.floor(count/3));
	    updateArmy(currentPlayer, value.intValue() > 3 ? (value.intValue() + checkContinentAcquired(currentPlayer,currentContinent))   : (3 + checkContinentAcquired(currentPlayer, currentContinent)), "ADD");	    
	}
	/**
	 * This method add armies to current player if a particular continent is Acquired fully by player
	 * @param currentPlayer
	 * @param currentContinent
	 * @return
	 */
	public int checkContinentAcquired(String currentPlayer, Continent currentContinent){
	    int count = 0;
	    Map<String, ArrayList<String>> tempData = getPlayerContinent(currentPlayer).getContinentOwnedterritory();
	    for(Entry<String,ArrayList<String>> entry : tempData.entrySet()) {
		if(entry.getValue().size() == currentContinent.getContTerrValue().get(entry.getKey())) {
		    count += currentContinent.getContinentValue().get(entry.getKey());
		}
	    }
	    return count > 0 ? count : 0;
	}
}
