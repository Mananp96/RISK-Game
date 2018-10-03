package com.risk.controller;

import java.util.ArrayList;
import java.util.HashMap;

public class Reinforcement {
	
	private ArrayList<String> currentPlayers;
	private HashMap<String, ArrayList<String>> contTerr; 						//for storing [Continents] and it's [Territories].
	private HashMap<String, Integer> contValue; 								//for storing [Continents] and it's [Winning Value].
	private HashMap<String, ArrayList<String>> adjcentTerr;		
	

	public Reinforcement(ArrayList<String> currentPlayers, HashMap<String, ArrayList<String>> contTerr,
			HashMap<String, Integer> contValue, HashMap<String, ArrayList<String>> adjcentTerr) {
		super();
		this.currentPlayers = currentPlayers;
		this.contTerr = contTerr;
		this.contValue = contValue;
		this.adjcentTerr = adjcentTerr;
	}


	public void initialReinforcement() {
		
	}
}
