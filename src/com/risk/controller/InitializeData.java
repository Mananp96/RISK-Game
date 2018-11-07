package com.risk.controller;

import java.util.logging.Logger;

import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;
import com.risk.strategy.Context;
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
    private static final Logger LOGGER = Logger.getLogger(InitializeData.class.getName());

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
    @Override
    public Continent getContinent() {
	return continent;
    }

    /**
     * Set the object continent.
     * @param continent object of Model Continent.
     */
    @Override
    public void setContinent(Continent continent) {
	this.continent = continent;
    }

    /**
     * Return the object territory of Model Territory.
     * @return a object territory of Model Territory.
     */
    @Override
    public Territory getTerritory() {
	return territory;
    }

    /**
     * Set the object territory of Model Territory.
     * @param territory object of Model Territory.
     */
    @Override
    public void setTerritory(Territory territory) {
	this.territory = territory;
    }

    /**
     * Return the object players of Model Players.
     * @return a object players of Model Players.
     */
    @Override
    public Players getPlayers() {
	return players;
    }

    /**
     * Set the object players of Model Players.
     * @param players object of Model Players
     */
    @Override
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
    public boolean generateData() {

	BoardData boardData = new BoardData(filePath);
	boolean isMapValid = boardData.generateBoardData();

	if (isMapValid) {
	    continent = boardData.continentObject;
	    territory = boardData.territoryObject;
	    players.selectPlayers(players.getPlayerList(), playerCount);
	    StartUpPhase startUpPhase = new StartUpPhase(players, continent, territory);
	    startUpPhase.initialStartUpPhase();
	    setContinent(startUpPhase.continent);
	    setTerritory(startUpPhase.territory);
	    setPlayers(startUpPhase.players);
	    Context context = new Context(players);
	    context.executeReinforcementArmy(players.getPlayers(0), continent);
	    LOGGER.info("Map is Validated and Data is Generated");
	}
	return isMapValid;
    }
}
