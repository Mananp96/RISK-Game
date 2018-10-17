package com.riskTest.validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 * MapValidator Test class
 *
 * @version 1.0
 */
public class MapValidator {
	
	boolean isMapValid;
	
	Map<String, ArrayList<String>> continentTerritories;
	Map<String, Integer> continentValue;
	Map<String, ArrayList<String>> adjcentTerritories;
	Map<String,String> territoriesMap;
	Map<String,String> adjacentTerritoriesMap;
	
	/**
	 * This method is used to initialize all Map Data.
	 */
	@Before
	public void beforeTest() {
		continentTerritories = new HashMap<>();
		continentValue = new HashMap<>();
		adjcentTerritories = new HashMap<>();
		territoriesMap = new HashMap<>();
		adjacentTerritoriesMap = new HashMap<>();
		
		
	}
	
	/**
	 * This method is used to test
	 * {@link com.risk.validate.MapValidator#validateMap()} method of MapValidator.java.
	 */
	@Test
	public void testValidateMap() {
		
	}
	
	/**
	 * This method is used to test
	 * {@link com.risk.validate.MapValidator#validateContinentValue()} method of MapValidator.java.
	 */
	@Test
	public void testValidateContinentValue() {
		
	}
	
	/**
	 * This method is used to test
	 * {@link com.risk.validate.MapValidator#validateTerritories()} method of MapValidator.java.
	 */
	@Test
	public void testValidateTerritories() {
		
	}
	
	/**
	 * This method is used to test
	 * {@link com.risk.validate.MapValidator#validateAdjcentTerritories()} method of MapValidator.java.
	 */
	@Test
	public void testValidateAdjcentTerritories() {
		
	}
	
	/**
	 * This method is used to test
	 * {@link com.risk.validate.MapValidator#isGraphConnected()} method of MapValidator.java.
	 */
	@Test
	public void testIsGraphConnected() {
		
	}
	
}
