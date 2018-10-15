package com.risk.controller;

import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;


public class InitializeData {
	
	String filePath;
	int playerCount;
	int armies;
	Continent continent;
	Territory territory;
	Players players;
	
	
	public InitializeData(String filePath, int playerCount, int armies, Players players) {
		this.filePath = filePath;
		this.playerCount = playerCount;
		this.armies = armies;
		this.players = players;
	}

	public int getArmies() {
		return armies;
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
		return isMapValid;
	}
}
