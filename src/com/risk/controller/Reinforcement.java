package com.risk.controller;

import java.util.Map.Entry;

import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;

public class Reinforcement {
    
    Players players; 
    Territory territory; 
    Continent continent;
    String playerName;

    public Reinforcement(String playerName , Players players, Territory territory, Continent continent) {
	// TODO Auto-generated constructor stub
	this.playerName = playerName;
	this.players = players;
	this.territory = territory;
	this.continent = continent;
	
    }
    
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
