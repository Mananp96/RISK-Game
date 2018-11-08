package com.riskTest.validate;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.controller.BoardData;
import com.risk.exception.InvalidMapException;

/**
 * MapValidator Test class
 * Reading invalid map file test case.
 *
 */
public class MapValidatorTestIncorrectMap {
	BoardData boardDataOne;
	BoardData boardDataTwo;
	BoardData boardDataThree;
	String invalidMapFileOne;
	String invalidMapFileTwo;
	String invalidMapFileThree;
	
	/**
	 * before every test.
	 */
	@BeforeEach
	public void beforeTest() {
		invalidMapFileOne = "maps/incorrectMapOne.map";
		invalidMapFileTwo = "maps/incorrectMapTwo.map";
		invalidMapFileThree = "maps/incorrectMapThree.map";
	}
	
	/**
	 * This method is used to test MapValidator.java.
	 * Returns false if map file is not valid.
	 * @throws InvalidMapException map file should not be null.
	 */
	@Test
	public void testValidateMap2() throws InvalidMapException {
		boardDataOne = new BoardData(invalidMapFileOne);
		boardDataTwo = new BoardData(invalidMapFileTwo);
		boardDataThree = new BoardData(invalidMapFileThree);
		assertFalse(boardDataOne.generateBoardData());
		assertFalse(boardDataTwo.generateBoardData());
		assertFalse(boardDataThree.generateBoardData());
	}
}
