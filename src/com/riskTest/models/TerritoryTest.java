package com.riskTest.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
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
	@BeforeEach
	public void beforeTest() {
		territory = new Territory();
		adjacentTerritory = new HashMap<>();
		territoryList = new ArrayList<>();
		territoryList.add(territoryTwo);
		territoryList.add(territoryThree);
		adjacentTerritory.put(territoryOne, territoryList);
		
		territoryList = new ArrayList<>();
		territoryList.add(territoryThree);
		territoryList.add(territoryFour);
		territoryList.add(territoryOne);
		adjacentTerritory.put(territoryTwo, territoryList);
	}
	
	/**
	 * This method is to test add Adjacent Territory functionality.
	 */
	@Test
	public void testAddAdjacentTerritory() {
		
		territory.addAdjacentTerritory(territoryOne,territoryTwo);
		territory.addAdjacentTerritory(territoryOne, territoryThree);
		territory.addAdjacentTerritory(territoryTwo, territoryThree);
		territory.addAdjacentTerritory(territoryTwo, territoryFour);
		territory.addAdjacentTerritory(territoryTwo, territoryOne);
		
		assertEquals(adjacentTerritory,territory.getAdjacentTerritory());
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
