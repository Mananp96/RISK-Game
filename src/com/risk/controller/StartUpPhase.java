package com.risk.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.risk.models.ArmiesSelection;
import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;
import com.risk.view.GamePanels;

public class StartUpPhase extends GamePanels {

	Players players;
	Continent continent;
	Territory territory;
	ArmiesSelection armies;

	/**
	 * This constructor is used to set the model object continent, players, territory.
	 * @param players object of model Players.
	 * @param continent object of model Continent.
	 * @param territory object of model Territory.

	 */
	public StartUpPhase(Players players, Continent continent, Territory territory) {
		this.continent = continent;
		this.territory = territory;
		this.players = players;
	}

	/**
	 * This method is used to start initial Reinforcement process:
	 * Players are allocated a number of initial armies, depending on the number of players.
	 * round-robin fashion, the players place their given armies one by one on their own countries. 
	 * 
	 */
	public void initialStartUpPhase() {
		int playerSize = players.getPlayerList().size();
		if (players.getPlayerList().get(2).equals("Neutral Player")){
			armies = new ArmiesSelection(2);
		}else {
			armies = new ArmiesSelection(playerSize);
		}
		riskLogger("Player in Game : " + playerSize);
		riskLogger("------------------");
		for (int i = 0; i < playerSize; i++) {
			String playerName = players.getPlayerList().get(i);
			players.setCurrentPhase("StartUp Phase");
			players.addPlayerContinent(playerName, new Continent());
			players.initialArmy(playerName, armies.getPlayerArmies());
			riskLogger(playerName + " Assigned with Initial Army of " + players.getPlayerArmy(playerName));
		}
		riskLogger("------------------");
		riskLogger("Initial StartUp Started");
		Map<String, String> territoryContinent = territory.getTerritoryCont();
		int playerCount = 0;
		for (int i = 0; i < territory.getTerritoryList().size(); i++) {
			String playerName = players.getPlayerList().get(playerCount);
			// Retrieve Random Territory 
			Object randomTerritory = territoryContinent.keySet().toArray()[new Random().nextInt(territoryContinent.keySet().toArray().length)];
			if (players.getPlayerArmy(playerName) >= 1) {
				Continent tempContinent = players.getPlayerContinent(playerName);
				tempContinent.addContinentOwnedTerritory(territoryContinent.get(randomTerritory), randomTerritory.toString(), true);
				riskLogger(playerName+" assigned Territory: "+ randomTerritory);
				territoryContinent.remove(randomTerritory);
				territory.updateTerritoryArmy(randomTerritory.toString(), 1, "ADD");
				territory.updateTerritoryUser(playerName, randomTerritory.toString());
				players.updateArmy(playerName, 1, "Remove");
				players.updatePlayerContinent(playerName, tempContinent);
				playerCount++;
				if (playerCount >= playerSize)
					playerCount = 0;
			} else {
				territory.updateTerritoryArmy(randomTerritory.toString(), 0, "ADD");
				territory.updateTerritoryUser("No Player", randomTerritory.toString());
			}
		}
		
		for(int i = 0;i < playerSize;i++) {
		    String name = players.getPlayers(i);
		    int armiesNo = players.getPlayerArmy(name);
		    if(armiesNo > 0) {
			do {
			    for(Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
				if(entry.getValue().equalsIgnoreCase(name) &&  players.getPlayerArmy(name) > 0) {
				    territory.updateTerritoryArmy(entry.getKey(), 1, "ADD");
				    players.updateArmy(name, 1, "DELETE");
				}
			    }
			    armiesNo = players.getPlayerArmy(name);
			} while(armiesNo > 0);
			
		    }

		}
		players.setCurrentPhase("Reinforcement");
		riskLogger(" ");
		riskLogger("Reinforcement phase started");
	}
}
