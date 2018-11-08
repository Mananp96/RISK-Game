package com.riskTest.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;
import com.risk.view.GamePanels;

/**
 * Players Model Test class.
 *
 */
public class PlayersTest {
	
	Players players;
	Territory territory;
	Continent continent;
	GamePanels gamePanels;
	Map<String, Integer> playerArmy;
	Map<String, Integer> territoryArmy;
	Map<String, Integer> continentTerrValue;
	ArrayList<String> playerList;
	String quebec = "Qubec"; 
	String ontario = "Ontario";
	int attackerDice = 3;
	int defenderDice = 2;
	Map<String, String> territoryCard;
	Map<String, String> territoryUser;
	
	/**
	 * This method is invoked at the start of all the test methods.
	 */
	@BeforeEach
	public void beforeTest() {
		players = new Players();
		territory = new Territory();
		continent = new Continent();
		gamePanels = new GamePanels();
		playerArmy = new HashMap<>();
		territoryArmy = new HashMap<>();
		territoryCard = new HashMap<>();
		territoryUser = new HashMap<>();
		continentTerrValue = new HashMap<>();
		playerList = new ArrayList<>();
		continent.addContinentTerritory("NA", quebec);
		continent.addContinentTerritory("SA", ontario);
		continent.addContinentOwnedTerritory("NA", quebec, true);
		continent.addContinentOwnedTerritory("SA", ontario, true);
		territory.addAdjacentTerritory(quebec, ontario);
		playerList.add("manan");
		playerList.add("prince");
		playerArmy.put("manan", 5);
		playerArmy.put("prince", 2);
		players.initialArmy("manan", 4);
		players.initialArmy("prince", 3);
		territory.updateTerritoryUser("manan", quebec);
		territory.updateTerritoryUser("prince", ontario);
		territory.addTerritoryCont(quebec, "NA");
		territory.addTerritoryCont(ontario, "SA");
		players.addPlayerContinent("manan", continent);
		players.addPlayerContinent("prince", continent);
		territory.addTerritoryCard(quebec, "cardName");
		players.setPlayerList(playerList);
		
	}

	/**
	 * This method is to test updation of armies of Player in particular territory.
	 */
	@Test
	public void testUpdateArmy() {
		
		players.updateArmy("manan", 1, "add");
		assertEquals(playerArmy, players.updateArmy("prince", 1, "delete"));
	}
	
	/**
	 * This method is used to test doReinforecement method of Players Model class.
	 */
	@Test
	public void testDoReinforcement() {
		territoryArmy.put(quebec, 2);
		assertEquals(territoryArmy,territory.updateTerritoryArmy(quebec, 2, "add"));
		players.doReinforcement("manan",quebec, 4, territory);
		territoryArmy.put(quebec, 6);
		assertEquals(territoryArmy,territory.getTerritoryArmy() );
	}
	
	/**
	 * This method is used to test valid move after conquering.
	 */
	@Test
	public void testConquer() {
		territory.updateTerritoryArmy(quebec, 4, "add");
		territory.updateTerritoryArmy(ontario, 2, "add");
		territoryArmy.put(quebec, 1);
		territoryArmy.put(ontario, 3);
		players.doAttack(territory, quebec, ontario, attackerDice, defenderDice);
		if(players.isAttackWon()) {
			players.moveArmyAfterAttack("manan",territory, quebec, ontario, 3);
			assertEquals(territoryArmy, territory.getTerritoryArmy());
		}
	}
	
	/**
	 * This method is used to test end of game scenario.
	 */
	@Test
	public void testEndOfGame() {
		territory.updateTerritoryArmy(quebec, 4, "add");
		territory.updateTerritoryArmy(ontario, 2, "add");
		territoryArmy.put(quebec, 1);
		territoryArmy.put(ontario, 3);
		players.doAttack(territory, quebec, ontario, attackerDice, defenderDice);
		if(players.isAttackWon()) {
			players.moveArmyAfterAttack("manan",territory, quebec, ontario, 3);
			assertTrue(gamePanels.checkTestWonGame());
			
		}	
	}
	
	/**
	 * This method is used to validate attacker.
	 */
	@Test
	public void testAttackerValidation() {
		territoryUser.put(quebec,"manan");
		territoryUser.put(ontario, "manan");
		territory.updateTerritoryArmy(quebec, 4, "add");
		territory.updateTerritoryArmy(ontario, 2, "add");
		territoryArmy.put(quebec, 1);
		territoryArmy.put(ontario, 3);
		players.doAttack(territory, quebec, ontario, attackerDice, defenderDice);
		if(players.isAttackWon()) {
			assertEquals(territoryUser,territory.getTerritoryUser());
		}	
	}
	
	/**
	 * This method is used to validate defender.
	 */
	@Test
	public void testDefenderValidation() {
		territoryUser.put(quebec,"manan");
		territoryUser.put(ontario, "prince");
		territory.updateTerritoryArmy(quebec, 4, "add");
		territory.updateTerritoryArmy(ontario, 2, "add");
		territoryArmy.put(quebec, 1);
		territoryArmy.put(ontario, 3);
		players.doAttack(territory, quebec, ontario, attackerDice, defenderDice);
		if(!players.isAttackWon()) {
			assertEquals(territoryUser,territory.getTerritoryUser());
		}
	}
	
	/**
	 * This method is used to test doFortification method of Players Model class.
	 */
	@Test
	public void testDoFortification() {
		territory.updateTerritoryArmy(quebec, 4, "add");
		territory.updateTerritoryArmy(ontario, 2, "add");
		players.doForitification(territory, quebec, ontario, 3);
		territoryArmy.put(quebec, 1);
		territoryArmy.put(ontario, 5);
		assertEquals(territoryArmy,territory.getTerritoryArmy());
	}
	
	/**
	 * test generateReinforcementArmy method.
	 */
	@Test
	public void testGenerateReinforcementArmy() {
		playerList.add("john");
		players.initialArmy("john", 1);
		continent.setContinentValue("NA", 0);
		continent.setContinentValue("SA",0);
		continentTerrValue.put("NA", 2);
		continentTerrValue.put("SA", 1);
		continent.setContTerrValue(continentTerrValue);
		
		for(int i = 0;i<12;i++) {
			continent.addContinentTerritory("NA", "halifax"+i);
			continent.addContinentOwnedTerritory("NA", "halifax"+i, true);
			territory.updateTerritoryUser("john", "halifax"+i);
		}
		
		players.addPlayerContinent("john", continent);
		players.setPlayerList(playerList);
		players.generateReinforcementArmy("john", continent);
		assertEquals(4,players.value.intValue());	
	}
}
