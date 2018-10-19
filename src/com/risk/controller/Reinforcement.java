package com.risk.controller;

import java.util.Map.Entry;

import org.omg.CORBA.INITIALIZE;

import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;

/**
 * This class is used to generate armies of player in Reinforcement phase.
 * 
 */
public class Reinforcement {

	Players players; 
	Territory territory; 
	Continent continent;
	String playerName;

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
}
