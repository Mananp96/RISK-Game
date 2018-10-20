package com.riskTest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.risk.controller.Reinforcement;
import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;

/**
 * {@link Reinforcement} Test Class
 */
public class ReinforcementTest {
	Continent continent;
	Players players;
	Territory territory;
	Reinforcement reinforcement;

	/**
	 * This method is to test number of reinforcement armies of Player.
	 * Calculation : reinforcement armies = Number of territory owned by Player / 3.
	 * e.g Number of Territory owned by Player : 12
	 * 	   Reinforcement Armies  = 12/3 = 4 armies
	 */
	@Test
	public void testGenerateArmy() {
		territory = new Territory();
		for(int i = 0; i < 12; i++) {
			territory.updateTerritoryUser("Player1", "Territory"+i);
		}
		reinforcement = new Reinforcement("Player1", players, territory, continent);
		assertEquals(4,reinforcement.generateArmy());	
	}
}
