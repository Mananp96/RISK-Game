package com.risk.controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;
import com.risk.validate.MapValidator;
/**
 * This SaveAndLoadGame class which is used to save the game and load save game.
 */
public class SaveAndLoadGame {
    /**
     * @param players player object
     * @param continent continent object
     * @param territory territory object
     * @param playerTurn current player turn
     */
    Players players;
    Continent continent;
    Territory territory;
    int playerTurn;
    private FileWriter fileWriter;
    /**
     * Default Constructor
     */
    public SaveAndLoadGame() {

    }
    /**
     * This method is to initialize territory, player and continent object as well as playerTurn Variable
     * @param players player object
     * @param continent continent object
     * @param territory territory object
     * @param playerTurn current player turn
     */
    public SaveAndLoadGame(Players players, Continent continent, Territory territory, int playerTurn) {
	super();
	this.players = players;
	this.continent = continent;
	this.territory = territory;
	this.playerTurn = playerTurn;
    }
    /**
     * This Method is use to save current Game
     * @return true if game is successfully save Otherwise false
     */
    public boolean saveGame() {
	JSONObject game = new JSONObject();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	String playerString = gson.toJson(players);
	String continentString = gson.toJson(continent);
	String territoryString = gson.toJson(territory);

	game.put("player", playerString);
	game.put("continent", continentString);
	game.put("territory", territoryString);
	game.put("playerTurn", playerTurn);

	String gameString = gson.toJson(game);

	try {
	    fileWriter = new FileWriter("riskGame_1.json");
	    fileWriter.write(gameString);
	    fileWriter.flush();
	    return true;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return false; 
    }
    /**
     * This Method is used to Load Previously Saved Game
     * @return true if data is validated Otherwise false
     */
    public boolean loadGame() {
	try {
	    JSONParser parser = new JSONParser();
	    Gson gson = new Gson();

	    Object obj = parser.parse(new FileReader("riskGame_1.json"));
	    JSONObject jsonObject = (JSONObject) obj;

	    players = gson.fromJson(String.valueOf(jsonObject.get("player")), Players.class);
	    continent = gson.fromJson(String.valueOf(jsonObject.get("continent")), Continent.class);
	    territory = gson.fromJson(String.valueOf(jsonObject.get("territory")), Territory.class);
	    playerTurn = (int) (long) jsonObject.get("playerTurn");
	    territory.setTerritoryNumber(new HashMap<>());
	    for(int i=0;i<territory.getTerritoryList().size();i++ ) {
		territory.addNumberOfTerritory(territory.getTerritoryList().get(i),i);
	    }
	    MapValidator mapValidator = new MapValidator(continent, territory);
	    boolean flag = mapValidator.validateMap();
	    return flag;
	} catch (Exception e) {
	    e.printStackTrace();
	} 
	return false;

    }
    /**
     * This method is used to get continent object
     * @return continent object
     */
    public Continent getContinent() {
	return continent;
    }
    /**
     * This method is used to set continent object
     * @param continent continent object
     */
    public void setContinent(Continent continent) {
	this.continent = continent;
    }
    /**
     * This method is used to get territory object
     * @return territory object
     */
    public Territory getTerritory() {
	return territory;
    }
    /**
     * This method is used to set Territory object
     * @param territory territory object
     */
    public void setTerritory(Territory territory) {
	this.territory = territory;
    }
    /**
     * This method is used to get player object
     * @return player turn
     */
    public int getPlayerTurn() {
	return playerTurn;
    }
    /**
     * This method is used to set player turn
     * @param playerTurn current player turn
     */
    public void setPlayerTurn(int playerTurn) {
	this.playerTurn = playerTurn;
    }
    /**
     * This method is used to get player object
     * @return player object
     */
    public Players getPlayers() {
	return players;
    }
    /**
     * This method is used to set player object
     * @param players player object
     */
    public void setPlayers(Players players) {
	this.players = players;
    }
}
