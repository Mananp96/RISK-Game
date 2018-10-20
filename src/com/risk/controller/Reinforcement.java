package com.risk.controller;

import java.util.Map.Entry;

import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;

/**
 * This class is used to generate armies of player in Reinforcement phase.
 * 
 */
public class Reinforcement {

    public Players getPlayers() {
	return players;
    }

    public void setPlayers(Players players) {
	this.players = players;
    }

    public Territory getTerritory() {
	return territory;
    }

    public void setTerritory(Territory territory) {
	this.territory = territory;
    }

    public Continent getContinent() {
	return continent;
    }

    public void setContinent(Continent continent) {
	this.continent = continent;
    }
    Players players; 
    Territory territory; 
    Continent continent;
    String playerName;
    String phase = "Reinforcement";

    /**
     * Constructor used to initialized data member of class.
     * @param playerName Name of player
     * @param players object of Players Model
     * @param territory object of Territory Model
     * @param continent object of Continent Model
     */
    public Reinforcement(String playerName , Players players, Territory territory, Continent continent) {
	this.playerName = playerName;
	this.players = players;
	this.territory = territory;
	this.continent = continent;
    }

    /**
     * This method calculate the player armies for Reinforcement phase.
     * Calculation method : Count of User-Owned Territory/3
     * @return number of reinforcement armies
     */
    public int generateArmy() {
	int count = 0;
	for(Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
	    if(entry.getValue().equalsIgnoreCase(playerName)) {
		count++;
	    }
	}

	Double value = new Double(Math.floor(count/3));
	return value.intValue() > 3 ? value.intValue() : 3 ;
    }
    /**
     * This method allow player to perform Reinforcement Phase of Game
     * @param currentPlayer Player Name 
     * @param territoryName Territory Name
     * @param army Number of Armies
     */
    public void startReinforcement(String currentPlayer,String territoryName, int army) {
	players.setCurrentPhase(getPhase());
	players.updateArmy(currentPlayer,army , "DELETE");
	territory.updateTerritoryArmy(territoryName, army, "ADD");
    }
    /**
     * This method return the current phase
     * @return Current Phase of Player
     */
    public String getPhase() {
	return phase;
    }
    /**
     * This method set the current phase
     * @param phase Set Current Phase
     */
    public void setPhase(String phase) {
	this.phase = phase;
    }


}
