package com.riskTest.models;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.risk.models.Continent;

/**
 * Continent Model Test class.
 * @version 1.0.0
 */
public class ContinentTest {
	
	Continent continent;
	
	String continentName = "Northern Africa";
	int controlValue = 5;
	Map<String, Integer> continentValue;
	
	/**
	 * This method is invoked at the start of all the test methods.
	 */
	@Before
	public void beforeTest() {
		continent = new Continent();
		
	}
	
	/**
	 * This method is to test set continent and it's control value functionality.
	 */
	@Test
	public void testSetContinentValue() {
		continentValue = new HashMap<>();
		continentValue.put(continentName, controlValue);
		continent.setContinentValue(continentName,controlValue);
		assertEquals(continentValue,continent.getContinentValue());
	}
	
	/**
	 * This method is to test Add Territory of Continent functionality.
	 */
	@Test
	public void testAddContinentTerritory() {
		
	}
	
	/**
	 * This method is to test AddContinentOwnedTerritory() method.
	 */
	@Test
	public void testAddContinentOwnedTerritory() {
		
	}
}
