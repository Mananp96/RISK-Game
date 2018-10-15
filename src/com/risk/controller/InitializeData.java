package com.risk.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;


public class InitializeData {
	
	String filePath;
	int playerCount;
	int armies;
	
	HashMap<String, ArrayList<String>> contTerr; 		//for storing [Continents] and it's [Territories].
	HashMap<String, Integer> contValue; 				//for storing [Continents] and it's [Winning Value].
	HashMap<String, ArrayList<String>> adjcentTerr;		//for storing [Territories] and it's [Adjacent Territories].
	
	Continent continent;
	Territory territory;
	Players players;
	
	
	public InitializeData(String filePath, int playerCount, int armies, Players players) {
		this.filePath = filePath;
		this.playerCount = playerCount;
		this.armies = armies;
		this.players = players;
		System.out.println("FilePath ------------- : "+filePath);
		System.out.println("Player Playing ------------- : "+playerCount);
		System.out.println("Armies for Each Player ------------- : "+armies);
		System.out.println("No. of Player ------------- : "+players.getPlayerPlaying());
	}

	public int getArmies() {
		return armies;
	}
	public HashMap<String, ArrayList<String>> getContTerr() {
		return contTerr;
	}
	public void setContTerr(HashMap<String, ArrayList<String>> contTerr) {
		this.contTerr = contTerr;
	}
	public HashMap<String, Integer> getContValue() {
		return contValue;
	}
	public void setContValue(HashMap<String, Integer> contValue) {
		this.contValue = contValue;
	}
	public HashMap<String, ArrayList<String>> getAdjcentTerr() {
		return adjcentTerr;
	}
	public void setAdjcentTerr(HashMap<String, ArrayList<String>> adjcentTerr) {
		this.adjcentTerr = adjcentTerr;
	}
	public void setArmies(int armies) {
		this.armies = armies;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getPlayerCount() {
		return playerCount;
	}
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}
	
	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	public Territory getTerritory() {
		return territory;
	}

	public void setTerritory(Territory territory) {
		this.territory = territory;
	}

	public Players getPlayers() {
		return players;
	}

	public void setPlayers(Players players) {
		this.players = players;
	}
	
	/*
	 * this method is used to generate and validate the data of .map file.
	 * return true if data isValid or else return false.
	 */
	public boolean generateData(){
		
		BoardData boardData = new BoardData(filePath);
		boolean isMapValid = boardData.generateBoardData();

			System.out.println("Model Data Continent Value: - " + boardData.continentObject.getContinentValue());
			System.out.println("Model Data Territory with Adjacent: - " + boardData.territoryObject.getAdjacentTerritory());
			System.out.println("Model Data Territory with Adjacent Size: - " + boardData.territoryObject.getAdjacentTerritory().size());
			System.out.println("Model Data Continent with Territory: - " + boardData.continentObject.getContinentTerritory());
			System.out.println("Model Data of Territory List : - " + boardData.territoryObject.getTerritoryList());
			System.out.println("Model Data of Territory List Size : - " + boardData.territoryObject.getTerritoryList().size());
			System.out.println("Model Data of Territory with Continents List : - " + boardData.territoryObject.getTerritoryCont());
			System.out.println("Model Data of Territory with Continents List Size : - " + boardData.territoryObject.getTerritoryCont().size());
			
			continent = boardData.continentObject;
			territory = boardData.territoryObject;
			players.selectPlayers(players.getPlayerList(), playerCount);
			Reinforcement reinforcement = new Reinforcement(players,continent,territory);
			reinforcement.initialReinforcement();
			setContinent(reinforcement.continent);
			setPlayers(reinforcement.players);
			setTerritory(reinforcement.territory);

		
		//System.out.println("Territory After Initial Reinforcement User" + territory.getTerritoryUser());
		//System.out.println("Territory After Initial Reinforcement Army" + territory.getTerritoryArmy());
		//System.out.println("-->" + players.getPlayerContinent("Manan").getContinentOwnedterritory());
		return isMapValid;
	}

	
	
	

}
