package com.riskTest.validate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.exception.InvalidMapException;
import com.risk.models.Continent;
import com.risk.models.Territory;
import com.risk.validate.MapValidator;

/**
 * MapValidator Test class
 *
 * @version 1.0
 */
public class MapValidatorTest {
	
	boolean isMapValid;
	
	MapValidator mapValidator;
	Continent continent;
	Territory territory;
	
	
	/**
	 * This method is used to initialize all Map Data and
	 * invoked at the start of all the test methods.
	 */
	@BeforeEach
	public void beforeTest() {
		continent = new Continent();
		territory = new Territory();
		
		continent.setContinentValue("Northern Africa", 4);
		continent.setContinentValue("Western Africa", 5);
		
		continent.addContinentTerritory("Northern Africa", "Morocco");
		continent.addContinentTerritory("Northern Africa", "Algeria");
		continent.addContinentTerritory("Western Africa", "Western Sahara");
		continent.addContinentTerritory("Western Africa", "Mauritania");
		
		territory.addAdjacentTerritory("Morocco", "Algeria");
		territory.addAdjacentTerritory("Morocco", "Western Sahara");
		territory.addAdjacentTerritory("Algeria", "Morocco");
		territory.addAdjacentTerritory("Algeria", "Western Sahara");
		territory.addAdjacentTerritory("Algeria", "Mauritania");
		territory.addAdjacentTerritory("Western Sahara", "Morocco");
		territory.addAdjacentTerritory("Western Sahara", "Algeria");
		territory.addAdjacentTerritory("Western Sahara", "Mauritania");
		territory.addAdjacentTerritory("Mauritania", "Algeria");
		territory.addAdjacentTerritory("Mauritania", "Western Sahara");	
		
		territory.addNumberOfTerritory("Morocco", 0);
		territory.addNumberOfTerritory("Algeria", 1);
		territory.addNumberOfTerritory("Western Sahara", 2);
		territory.addNumberOfTerritory("Mauritania", 3);
	}
	
	/**
	 * This method is used to test
	 * {@linkplain com.risk.validate.MapValidator#validateMap()} method of MapValidator.java.
	 * @throws InvalidMapException map file should not be null.
	 */
	@Test
	public void testValidateMap() throws InvalidMapException {
		mapValidator = new MapValidator(continent, territory);
		assertTrue(mapValidator.validateMap());
	}
	
	/**
	 * This method is used to test
	 * {@link com.risk.validate.MapValidator#validateContinentValue()} method of MapValidator.java.
	 * @throws InvalidMapException Continent Control value should be more than 1.
	 */
	@Test
	public void testValidateContinentValue() throws InvalidMapException {
		mapValidator = new MapValidator(continent, territory);
		assertTrue(mapValidator.validateContinentValue());
//		Assertions.assertThrows(InvalidMapException.class, () -> {
//		    mapValidator.validateContinentValue();
//		  });
	}
	
	/**
	 * This method is used to test
	 * {@link com.risk.validate.MapValidator#validateContinent()} method of MapValidator.java.
	 * @throws InvalidMapException Continent should be unique
	 */
	@Test
	public void testValidateContinent() throws InvalidMapException {
		mapValidator = new MapValidator(continent, territory);
		assertTrue(mapValidator.validateContinent());
	}
	/**
	 * This method is used to test
	 * {@link com.risk.validate.MapValidator#validateTerritories()} method of MapValidator.java.
	 * @throws InvalidMapException Territory should not be null. 
	 */
	@Test
	public void testValidateTerritories() throws InvalidMapException {
		mapValidator = new MapValidator(continent, territory);
		assertTrue(mapValidator.validateTerritories());
		
//		Assertions.assertThrows(InvalidMapException.class, () -> {
//		    mapValidator.validateTerritories();
//		  });
	}
	
	/**
	 * This method is used to test
	 * {@link com.risk.validate.MapValidator#validateAdjcentTerritories()} method of MapValidator.java.
	 * @throws InvalidMapException Adjacent should not be null.
	 */
	@Test
	public void testValidateAdjcentTerritories() throws InvalidMapException {
		mapValidator = new MapValidator(continent, territory);
		assertTrue(mapValidator.validateAdjcentTerritories());
	}
	
	/**
	 * This method is used to test
	 * {@link com.risk.validate.MapValidator#isGraphConnected()} method of MapValidator.java.
	 * @throws InvalidMapException 
	 * 							Graph is not Connected.
	 */
	@Test
	public void testIsGraphConnected() throws InvalidMapException {
		mapValidator = new MapValidator(continent, territory);
		assertTrue(mapValidator.isGraphConnected());
	}
	
}
