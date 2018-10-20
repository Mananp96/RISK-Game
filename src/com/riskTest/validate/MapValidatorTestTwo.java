package com.riskTest.validate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

import com.risk.controller.BoardData;
import com.risk.exception.InvalidMapException;

/**
 * MapValidator Test class
 * Reading invalid map file test case.
 *
 */
public class MapValidatorTestTwo {
	BoardData boardDataOne;
	BoardData boardDataTwo;
	BoardData boardDataThree;

	String invalidMapFileOne = "risk/maps/incorrectMapOne.map";
	String invalidMapFileTwo = "risk/maps/incorrectMapTwo.map";
	String invalidMapFileThree = "risk/maps/incorrectMapThree.map";

	/**
	 * This method is used to test MapValidator.java.
	 * Returns false if map file is not valid.
	 * @throws InvalidMapException map file should not be null.
	 */
	@Test
	public void testValidateMap() throws InvalidMapException {
		boardDataOne = new BoardData(invalidMapFileOne);
		boardDataTwo = new BoardData(invalidMapFileTwo);
		boardDataThree = new BoardData(invalidMapFileThree);
		assertFalse(boardDataOne.generateBoardData());
		assertFalse(boardDataTwo.generateBoardData());
		assertFalse(boardDataThree.generateBoardData());
	}
}
