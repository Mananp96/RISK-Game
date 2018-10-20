package com.riskTest.models;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.models.ArmiesSelection;

/**
 * ArmiesSelection Model Test class.
 *
 */
public class ArmiesSelectionTest {
	int twoPlayerArmies = 40;
	int threePlayerArmies = 35;
	int fourPlayerArmies = 30;
	int fivePlayerArmies = 25;

	ArmiesSelection twoPlayer;
	ArmiesSelection threePlayer;
	ArmiesSelection fourPlayer;
	ArmiesSelection fivePlayer;
	
	/**
	 * This method is invoked at the start of all the test methods.
	 */
	@BeforeEach public void beforeTest() {
		this.twoPlayer = new ArmiesSelection(2);
		this.threePlayer = new ArmiesSelection(3);
		this.fourPlayer = new ArmiesSelection(4);
		this.fivePlayer = new ArmiesSelection(5);
	}
	
	/**
	 * This method is to test the selection of Number of initial armies according to Player.
	 * 
	 */
	@Test public void testArmiesSelection() {
		assertEquals(twoPlayerArmies,twoPlayer.getPlayerArmies());
		assertEquals(threePlayerArmies,threePlayer.getPlayerArmies());
		assertEquals(fourPlayerArmies,fourPlayer.getPlayerArmies());
		assertEquals(fivePlayerArmies,fivePlayer.getPlayerArmies());
	}
}
