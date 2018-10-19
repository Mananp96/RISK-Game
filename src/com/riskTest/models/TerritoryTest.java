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
	Map<String, Integer> territoryArmy;
	/**
	 * This method is invoked at the start of all the test methods.
	 */
	@BeforeEach
	public void beforeTest() {
		territory = new Territory();
		adjacentTerritory = new HashMap<>();
		territoryList = new ArrayList<>();
		territoryArmy = new HashMap<>();
		
		territoryList.add(territoryTwo);
		territoryList.add(territoryThree);
		adjacentTerritory.put(territoryOne, territoryList);
		
		territoryList = new ArrayList<>();
		territoryList.add(territoryThree);
		territoryList.add(territoryFour);
		territoryList.add(territoryOne);
		adjacentTerritory.put(territoryTwo, territoryList);
		
		territoryArmy.put(territoryOne, 3);
		territoryArmy.put(territoryTwo, 1);
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
		territory.updateTerritoryArmy(territoryOne, 3, "add");
		territory.updateTerritoryArmy(territoryTwo, 1, "add");
		assertEquals(territoryArmy,territory.getTerritoryArmy());
		
		territoryArmy.replace(territoryOne, 3, 2);
		territory.updateTerritoryArmy(territoryOne, 1, "update");
		assertEquals(territoryArmy,territory.getTerritoryArmy());
	}
	
}
