package com.riskTest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.controller.SaveAndLoadGame;
import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;

public class SaveAndLoadGameTest {
	
	Continent continent = new Continent();
	Players players = new Players();
	Territory territory = new Territory();
	int playerTurn = 2;
	SaveAndLoadGame saveAndLoadGame;
	
	/**
	 * run before each test
	 */
	@BeforeEach
	public void beforeTest() {
		continent = new Continent();
		territory = new Territory();

		continent.setContinentValue("Northern Africa", 4);
		continent.setContinentValue("Western Africa", 5);

		continent.addContinentTerritory("Northern Africa", "Morocco");
		continent.addContinentTerritory("Northern Africa", "Algeria");
		continent.addContinentTerritory("Western Africa", "Western Sahara");
		continent.addContinentTerritory("Western Africa", "Mauritania");

		territory.addTerritory("Morocco");
		territory.addTerritory("Algeria");
		territory.addTerritory("Western Sahara");
		territory.addTerritory("Mauritania");

		territory.addAdjacentTerritory("Morocco", "Algeria");
		territory.addAdjacentTerritory("Morocco", "Western Sahara");
		territory.addAdjacentTerritory("Algeria", "Morocco");
		territory.addAdjacentTerritory("Algeria", "Western Sahara");
		territory.addAdjacentTerritory("Algeria", "Mauritania");
		territory.addAdjacentTerritory("Western Sahara", "Morocco");
		territory.addAdjacentTerritory("Western Sahara", "Algeria");
		territory.addAdjacentTerritory("Western Sahara", "Mauritania");
		territory.addAdjacentTerritory("Mauritania", "Algeria");
		territory.addAdjacentTerritory("Mauritania", "Western Sahara");	

		territory.addNumberOfTerritory("Morocco", 0);
		territory.addNumberOfTerritory("Algeria", 1);
		territory.addNumberOfTerritory("Western Sahara", 2);
		territory.addNumberOfTerritory("Mauritania", 3);
		
		players.addPlayers("manan");
		players.addPlayers("khyati");
		players.addPlayers("shalin");
		players.addPlayers("Himan");
		
		players.setPlayerPlaying(players.getPlayerList());
		for(int i=0;i<players.getPlayerList().size();i++) {
			players.initialArmy(players.getPlayerList().get(i), 30);
		}
		players.setCurrentPhase("Reinforcement");
		territory.updateTerritoryUser("manan", "Algeria");
		territory.updateTerritoryUser("khyati", "Morocco");
		territory.updateTerritoryUser("shalin", "Western Sahara");
		territory.updateTerritoryUser("Himan", "Mauritania");
		territory.updateTerritoryArmy("Algeria", 2, "add");
		territory.updateTerritoryArmy("Morocco", 3, "add");
		territory.updateTerritoryArmy("Western Sahara", 4, "add");
		territory.updateTerritoryArmy("Mauritania", 2, "add");
		saveAndLoadGame = new SaveAndLoadGame(players, continent, territory, playerTurn);
	}
	
	/**
	 * Test the SaveGame method.
	 */
	@Test
	public void testSaveGame() {
		assertTrue(saveAndLoadGame.saveGame());
	}
	
	/**
	 * Test load Game.
	 */
	@Test
	public void testLoadGame() {
		saveAndLoadGame.loadGame();
		assertEquals(players.getCurrentPhase(), saveAndLoadGame.getPlayers().getCurrentPhase());
		assertEquals(continent.getContinentTerritory(), saveAndLoadGame.getContinent().getContinentTerritory());
		assertEquals(territory.getTerritoryArmy(), saveAndLoadGame.getTerritory().getTerritoryArmy());
		assertEquals(playerTurn, saveAndLoadGame.getPlayerTurn());
	}
	
}
