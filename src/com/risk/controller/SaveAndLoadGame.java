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

public class SaveAndLoadGame {

    Players players;
    Continent continent;
    Territory territory;
    int playerTurn;
    private FileWriter fileWriter;
    public SaveAndLoadGame() {

    }
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
    public int getPlayerTurn() {
	return playerTurn;
    }
    public void setPlayerTurn(int playerTurn) {
	this.playerTurn = playerTurn;
    }
    public Players getPlayers() {
	return players;
    }
    public void setPlayers(Players players) {
	this.players = players;
    }
}
