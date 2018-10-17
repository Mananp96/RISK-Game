package com.riskTest.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.risk.models.Territory;

/**
 * Territory Model Test class.
 *
 */
public class TerritoryTest {
	
	String territoryOne = "Morocco";
	String territoryTwo = "Algeria";
	String territoryThree = "Western Sahara";
	String territoryFour = "Mauritania";
	
	Territory territory;
	
	Map<String, ArrayList<String>> adjacentTerritory; 
	ArrayList<String> territoryList; 
	/**
	 * This method is invoked at the start of all the test methods.
	 */
	@Before
	public void beforeTest() {
		territory = new Territory();
		adjacentTerritory = new HashMap<>();
	}
	
	/**
	 * This method is to test add Adjacent Territory functionality.
	 */
	@Test
	public void testAddAdjacentTerritory() {
		
	}
	
	/**
	 * This method is to test update armies of particular Territory functionality.
	 */
	@Test
	public void testUpdateTerritoryArmy() {
		
	}
	
	/**
	 * This method is to test update Player of Territory functionality.
	 */
	@Test
	public void testUpdateTerritoryUser() {
		
	}
	
	
}
