package com.riskTest.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.controller.Reinforcement;
import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;
import com.risk.observer.Subject;

/**
 * Players Model Test class.
 *
 */
public class PlayersTest {
	Players players;
	Territory territory;
	Continent continent;
	Map<String, Integer> playerArmy;
	Map<String, Integer> territoryArmy;
	String quebec = "Qubec"; 
	String ontario = "Ontario";
	int attackerDice = 3;
	int defenderDice = 2;
	/**
	 * This method is invoked at the start of all the test methods.
	 */
	@BeforeEach
	public void beforeTest() {
		players = new Players();
		territory = new Territory();
		continent = new Continent();
		playerArmy = new HashMap<>();
		territoryArmy = new HashMap<>();
		playerArmy.put("manan", 5);
		playerArmy.put("prince", 2);
		players.initialArmy("manan", 4);
		players.initialArmy("prince", 3);
		
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
	 * This method is used to test doAttack method of Players Model class.
	 */
	@Ignore
	public void testDoAttack() {
		territory.updateTerritoryArmy(quebec, 4, "add");
		territory.updateTerritoryArmy(ontario, 2, "add");
		players.doAttack(territory, quebec, ontario, attackerDice, defenderDice);
		assertTrue(players.isAttackWon());
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
		for(int i = 0; i < 12; i++) {
			continent.setContinentValue("continent"+i, i);
			continent.addContTerritoryValue("continent"+i);
			players.addPlayerContinent("Player"+i, continent);
			territory.updateTerritoryUser("Player1", "Territory"+i);
		}
		players.generateReinforcementArmy("Player1", continent);
		assertEquals(4,players.getPlayerArmy("Player1"));	
	}
}
