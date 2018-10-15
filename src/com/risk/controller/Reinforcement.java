package com.risk.controller;

import java.util.Map;
import java.util.Random;

import com.risk.models.ArmiesSelection;
import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;

public class Reinforcement {
	
	Players players;
	Continent continent;
	Territory territory;
	ArmiesSelection armies;
	
	public Reinforcement(Players players, Continent continent, Territory territory) {
		// TODO Auto-generated constructor stub
		this.continent = continent;
		this.territory = territory;
		this.players = players;
	}


	public void initialReinforcement() {
		int playerSize = players.getPlayerList().size();
		armies = new ArmiesSelection(playerSize);
		System.out.println("Initial Reinforcement Started");
		System.out.println("Player in Game : " +playerSize);
		for(int i = 0 ; i < playerSize ; i++ ) {
			String playerName = players.getPlayerList().get(i);
			players.addPlayerContinent(playerName,new Continent());
			players.initialArmy(playerName, armies.getPlayerArmies());
			System.out.println(playerName +" Assigned with Continent Object");
			System.out.println(playerName +" Assigned with Initial Army of " + players.getPlayerArmy(playerName));		
		}
		Map<String, String> territoryContinent = territory.getTerritoryCont();
		int playerCount = 0;
		for(int i=0;i< territory.getTerritoryList().size();i++) {
        	    	String playerName = players.getPlayerList().get(playerCount);
        			// Retrieve Random Territory
        		Object randomTerritory = territoryContinent.keySet().toArray()[new Random().nextInt(territoryContinent.keySet().toArray().length)];
        	    	if(players.getPlayerArmy(playerName) >= 1) {
        		    Continent tempContinent = players.getPlayerContinent(playerName);
        		    tempContinent.addContinentOwnedTerritory(territoryContinent.get(randomTerritory),randomTerritory.toString(), true); 	
        		    territoryContinent.remove(randomTerritory);
        		    territory.updateTerritoryArmy(randomTerritory.toString(), 1, "ADD");
        		    territory.updateTerritoryUser(playerName, randomTerritory.toString());
        		    players.updateArmy(playerName, 1, "Remove");
        		    players.updatePlayerContinent(playerName, tempContinent);
        		    playerCount++;
        		    if(playerCount >= playerSize)
        		    		playerCount = 0;
        		} else {
        		    territory.updateTerritoryArmy(randomTerritory.toString(), 0, "ADD");
        		    territory.updateTerritoryUser("No Player", randomTerritory.toString());
        		}
		}
				
	}
}
