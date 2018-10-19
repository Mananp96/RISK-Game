package com.risk.controller;

import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;
import com.risk.view.GamePanels;

/**
 * This class is used to invoke Initial Reinforcement process if data of map file is valid.
 * 
 */
public class InitializeData extends GamePanels {

	String filePath;
	int playerCount;
	int armies;
	Continent continent;
	Territory territory;
	Players players;

	/**
	 * This constructor is used to set the data members of class.
	 * @param filePath	path of map File.
	 * @param playerCount	Number of Players in game.
	 * @param armies	Number of armies per Player.
	 * @param players	model object of Players.
	 */
	public InitializeData(String filePath, int playerCount, int armies, Players players) {
		this.filePath = filePath;
		this.playerCount = playerCount;
		this.armies = armies;
		this.players = players;
	}

	/**
	 * This method returns the variable armies.
	 * @return Number of armies per player.
	 */
	public int getArmies() {
		return armies;
	}

	/**
	 * Set the armies.
	 * @param armies Number of armies per player.
	 */
	public void setArmies(int armies) {
		this.armies = armies;
	}

	/**
	 * return filePath.
	 * @return path of map File.
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Set filePath.
	 * @param filePath path of map File.
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Return playerCount.
	 * @return Number of Players in game.
	 */
	public int getPlayerCount() {
		return playerCount;
	}

	/**
	 * Set playerCount.
	 * @param playerCount Number of Players in game.
	 */
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	/**
	 * Return object continent.
	 * @return a object of Model Continent.
	 */
	public Continent getContinent() {
		return continent;
	}

	/**
	 * Set the object continent.
	 * @param continent object of Model Continent.
	 */
	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	/**
	 * Return the object territory of Model Territory.
	 * @return a object territory of Model Territory.
	 */
	public Territory getTerritory() {
		return territory;
	}

	/**
	 * Set the object territory of Model Territory.
	 * @param territory object of Model Territory.
	 */
	public void setTerritory(Territory territory) {
		this.territory = territory;
	}

	/**
	 * Return the object players of Model Players.
	 * @return a object players of Model Players.
	 */
	public Players getPlayers() {
		return players;
	}

	/**
	 * Set the object players of Model Players.
	 * @param players object of Model Players
	 */
	public void setPlayers(Players players) {
		this.players = players;
	}

	/**
	 * This method gives the path of map file to BoardData class, invoke the method 
	 * {@link BoardData#generateBoardData()} of class BoardData
	 * for the purpose of Generate Data and Validate map Data from map file. 
	 * if map is valid then it starts the process of Initial Reinforcement: invoke
	 * the method {@linkplain Reinforcement#generateArmy()} of class Reinforcement.
	 *
	 * @return true if map Data is Valid else, return false.
	 */
	public boolean generateData(){

		BoardData boardData = new BoardData(filePath);
		boolean isMapValid = boardData.generateBoardData();
		System.out.println(isMapValid);
		riskLogger("isMapValid "+ isMapValid);
		if(isMapValid) {
			//System.out.println("Model Data Continent Value: - " + boardData.continentObject.getContinentValue());
			riskLogger("Model Data Continent Value: - " + boardData.continentObject.getContinentValue());
			//System.out.println("Model Data Territory with Adjacent: - " + boardData.territoryObject.getAdjacentTerritory());
			riskLogger("Model Data Territory with Adjacent: - " + boardData.territoryObject.getAdjacentTerritory());
			riskLogger("Model Data Territory with Adjacent Size: - " + boardData.territoryObject.getAdjacentTerritory().size());
			riskLogger("Model Data Continent with Territory: - " + boardData.continentObject.getContinentTerritory());
			riskLogger("Model Data of Territory List : - " + boardData.territoryObject.getTerritoryList());
			riskLogger("Model Data of Territory List Size : - " + boardData.territoryObject.getTerritoryList().size());
			riskLogger("Model Data of Territory with Continents List : - " + boardData.territoryObject.getTerritoryCont());
			riskLogger("Model Data of Territory with Continents List Size : - " + boardData.territoryObject.getTerritoryCont().size());

			continent = boardData.continentObject;
			territory = boardData.territoryObject;
			players.selectPlayers(players.getPlayerList(), playerCount);
			StartUpPhase startUpPhase = new StartUpPhase(players,continent,territory);
			startUpPhase.initialStartUpPhase();
			setContinent(startUpPhase.continent);
			setTerritory(startUpPhase.territory);
			Reinforcement reinforcement = new Reinforcement(players.getPlayers(0),players, getTerritory(), getContinent());
			players.updateArmy(players.getPlayers(0), reinforcement.generateArmy(),"ADD");
			setPlayers(startUpPhase.players);

		}else {
			riskLogger("Map is not Valid");
		}
		return isMapValid;
	}
}
