package com.riskTest.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.models.Continent;
import com.risk.models.Players;

/**
 * Players Model Test class.
 *
 */
public class PlayersTest {
	
	Players players;
	Map<String, Integer> playerArmy; 
	
	/**
	 * This method is invoked at the start of all the test methods.
	 */
	@BeforeEach
	public void beforeTest() {
		players = new Players();
		playerArmy = new HashMap<>();
		playerArmy.put("manan", 5);
		playerArmy.put("prince", 2);
	}
	
	/**
	 * This method is to test updation of armies of Player in particular territory.
	 */
	@Test
	public void testUpdateArmy() {
		players.initialArmy("manan", 4);
		players.initialArmy("prince", 3);
		players.updateArmy("manan", 1, "add");
		assertEquals(playerArmy, players.updateArmy("prince", 1, "delete"));
	}
}
