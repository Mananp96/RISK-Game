package com.risk.controllers;
import java.io.FileNotFoundException;

import com.risk.exception.InvalidMapException;

public class Caller {
	public static void main(String args[]) throws FileNotFoundException, InvalidMapException {
		
		RiskLoadMap rm = new RiskLoadMap("Africa.map");
		rm.initializeData();
		
		}
	}