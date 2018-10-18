package com.riskTest.models;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.models.Continent;

/**
 * Continent Model Test class.
 * @version 1.0.0
 */
public class ContinentTest {
	
	Continent continent;
	
	String continentNameOne = "Northern Africa";
	String continentNameTwo = "Western Africa";
	
	String NATerritoryOne = "Morocco";
	String NATerritoryTwo = "Algeria";
	String WATerritoryOne = "Western Sahara";
	String WATerritoryTwo = "Mauritania";
	
	
	int controlValueOne = 5;
	int controlValueTwo = 6;
	
	Map<String, Integer> continentValue;
	Map<String, ArrayList<String>> continentTerritory;
	
	ArrayList<String> territoryList;
	
	/**
	 * This method is invoked at the start of all the test methods.
	 */
	@BeforeEach
	public void beforeTest() {
		continent = new Continent();
		continentValue = new HashMap<>();
		continentTerritory = new HashMap<>();
		territoryList = new ArrayList<>();
	}
	
	/**
	 * This method is to test set continent and it's control value functionality.
	 */
	@Test
	public void testSetContinentValue() {
	
		
		continentValue.put(continentNameOne, controlValueOne);
		continentValue.put(continentNameTwo, controlValueTwo);
		continent.setContinentValue(continentNameOne,controlValueOne);
		continent.setContinentValue(continentNameTwo, controlValueTwo);
		
		assertEquals(continentValue,continent.getContinentValue());
	}
	
	/**
	 * This method is to test Add Territory of Continent functionality.
	 */
	@Test
	public void testAddContinentTerritory() {
		
		territoryList.add(NATerritoryOne);
		territoryList.add(NATerritoryTwo);
	    continentTerritory.put(continentNameOne,territoryList);
	    territoryList= new ArrayList<>();
	    territoryList.add(WATerritoryOne);
	    territoryList.add(WATerritoryTwo);
	    continentTerritory.put(continentNameTwo, territoryList);
		
		continent.addContinentTerritory(continentNameOne, NATerritoryOne);
		continent.addContinentTerritory(continentNameOne, NATerritoryTwo);
		continent.addContinentTerritory(continentNameTwo, WATerritoryOne);
		continent.addContinentTerritory(continentNameTwo, WATerritoryTwo);
		
		assertNotNull(continent.getContinentTerritory());
		assertEquals(continentTerritory,continent.getContinentTerritory());
	}
	
	
}
