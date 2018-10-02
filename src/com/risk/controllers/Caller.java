package com.risk.controllers;

import java.io.FileNotFoundException;

public class Caller {
	public static void main(String args[]) throws FileNotFoundException {
		
		RiskLoadMap rm = new RiskLoadMap("Africa.map");
		rm.initializeData();
	}
	
}
