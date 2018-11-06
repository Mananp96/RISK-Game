package com.risk.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.risk.strategy.Strategy;

/**
 * This is a model class for playerContinent, playerArmy,
 * playerList, playerPlaying as its data member.
 *
 */
public class Players implements Strategy {
    /**
     * @param playerContinent player had territories in particular continent
     * @param playerArmy player having no. of armies
     * @param playerList no. of player currently playing game
     * @param playerPlaying no. of player playing game
     * @param cards no. of card player has
     * @param territoryCards territory and card type mapping
     * @param isAttackWon return true if players has won attack and acquired territories
     * @param isWonCard return true if players acquired territories in his/her turn and gets card
     * @param tradeInArmies number of tradein armies
     * @param attackerMsg message for attacker
     * @param defenderMsg message for defender
     * @param tradeIn number of tradein is done in game
     */
    Map<String, Continent> playerContinent; 
    Map<String, Integer> playerArmy;   
    ArrayList<String> playerList; 
    ArrayList<String> playerPlaying;
    String currentPhase;
    private boolean isAttackWon;
    private boolean isWonCard;
    int  tradeInArmies = 4;
    String attackerMsg = "";
    String defenderMsg = "";
    int tradeIn = 0;
    Map<String, String> cards;
    Map<String, String> territoryCards;

    public Players() {
	playerContinent = new HashMap<>();
	playerArmy = new HashMap<>();
	playerList = new ArrayList<>();
	playerPlaying = new ArrayList<>();
	cards = new HashMap<>();
	territoryCards = new HashMap<>();
    }
    /**
     * This Method return number of trade in armies generated
     * @return tradeInArmies number of trade in armies
     */
    public int getTradeInArmies() {
	return tradeInArmies;
    }
    /**
     * This Method set  number of trade in armies
     * @param tradeInArmies number of trade in armies
     */
    public void setTradeInArmies(int tradeInArmies) {
	this.tradeInArmies = tradeInArmies;
    }
    /**
     *  This Method return true if players acquired territories in his/her turn and gets card
     * @return true if players acquired territories in his/her turn and gets card
     */
    public boolean isWonCard() {
	return isWonCard;
    }
    /**
     * This method set true if players acquired territories in his/her turn and gets card otherwise false
     * @param isWonCard true if players acquired territories in his/her turn and gets card
     */
    public void setWonCard(boolean isWonCard) {
	this.isWonCard = isWonCard;
    }
    /**
     * This method get territory and card type mapping
     * @return territoryCards territory and card type mapping
     */
    public Map<String, String> getTerritoryCards() {
	return territoryCards;
    }
    /**
     * This method set territory and card type mapping
     * @param territoryCards territory and card type mapping
     */
    public void setTerritoryCards(Map<String, String> territoryCards) {
	this.territoryCards = territoryCards;
    }
    /**
     * 
     * @return playerContinent player had territories in particular continent
     */
    public Map<String, Continent> getPlayerContinent() {
	return playerContinent;
    }
    /**
     * This method set territories in particular continent
     * @param playerContinent Territories in particular continent
     */
    public void setPlayerContinent(Map<String, Continent> playerContinent) {
	this.playerContinent = playerContinent;
    }

    /**
     * 
     * @return a List of Players playing.
     */
    public ArrayList<String> getPlayerPlaying() {
	return playerPlaying;
    }

    public int getTradeIn() {
	return tradeIn;
    }

    public void setTradeIn() {
	this.tradeIn += 1;
    }

    /**
     * 
     * @param playerPlaying a List of Players playing.
     */
    public void setPlayerPlaying(ArrayList<String> playerPlaying) {
	this.playerPlaying = playerPlaying;
    }

    /**
     * 
     * @return a List of Players.
     */
    public ArrayList<String> getPlayerList() {
	return playerList;
    }

    /**
     * 
     * @param playerList a List of Players.
     */
    public void setPlayerList(ArrayList<String> playerList) {
	this.playerList = playerList;
    }

    /**
     * This method create the List of Players.
     * @param name Name of Player.
     * @return playerList.
     */
    public ArrayList<String> addPlayers(String name) {
	playerList.add(name);
	return playerList;
    }

    /**
     * This method selects the Players who will be playing from the playerList
     * and return a list of player playing.
     * @param names contains a List of players.
     * @param count number of players playing.
     * @return a list playerPlaying.
     */
    public ArrayList<String> selectPlayers(ArrayList<String> names, int count) {
	for (int i = 0; i < count; i++) {
	    playerPlaying.add(names.get(i));
	}
	return playerPlaying;
    }

    /**
     * This method update the Map updatePlayerContinent.
     * @param name Name of player
     * @param continent object of model class Continent
     * @return a Map playerContinent
     */
    public Map<String, Continent> updatePlayerContinent(String name, Continent continent){
	playerContinent.replace(name, continent);
	return playerContinent;
    }

    /**
     * This method assign a Player to Particular Continent's Territories.
     * @param name Name of Player
     * @param continent object of model class Continent
     * @return a Map playerContinent
     */
    public Map<String, Continent> addPlayerContinent(String name, Continent continent){
	playerContinent.put(name, continent);
	return playerContinent;
    }
    /**
     * This method returns the Territories acquired by particular player.
     * @param name Name of Player
     * @return a Map PlayerContinent
     */
    public Continent getPlayerContinent(String name){

	return playerContinent.get(name);
    }

    /**
     * This method used to assign the armies by player the name of Player.
     * @param name Name of player
     * @param armyCount Number of Armies 
     * @return a Map playerArmy
     */
    public Map<String, Integer> initialArmy(String name,int armyCount){
	playerArmy.put(name, armyCount);
	return playerArmy;
    }

    /**
     * This method is used to find the number of armies by the name of Player.
     * @param name Name of Player.
     * @return a Map playerArmy
     */
    public int getPlayerArmy(String name){
	return playerArmy.get(name);
    }


    /**
     * This method update the Map playerArmy.
     * @param name Name of Player
     * @param armyCount Number of Armies
     * @param operation Name of Operation
     * @return a Map playerArmy
     */
    public Map<String, Integer> updateArmy(String name,int armyCount,String operation){
	if (operation.equalsIgnoreCase("add")) {
	    playerArmy.replace(name, playerArmy.get(name), playerArmy.get(name) + armyCount);
	} else {
	    playerArmy.replace(name, playerArmy.get(name), playerArmy.get(name) - armyCount);
	}
	return playerArmy;
    }

    /**
     * Returns the List of Players.
     * @param number Number of Players.
     * @return a List of Players.
     */
    public String getPlayers(int number) {
	return playerList.get(number);	
    }

    /**
     * This method is used to assign the current phase of game.
     * @param currentPhase Assign the current phase of game.
     */
    public void setCurrentPhase(String currentPhase) {
	this.currentPhase = currentPhase;
    }

    /**
     * Returns the current phase of game.
     * @return  currentPhase of player.
     */
    public String getCurrentPhase() {
	return currentPhase;
    }

    /**
     * return if attack is won.
     * @return true if attack is won, else false;
     */
    public boolean isAttackWon() {
	return isAttackWon;
    }

    /**
     * set default value of isAttackWon is false;
     * @param isAttackWon attack is won or not?
     */
    public void setAttackWon(boolean isAttackWon) {
	this.isAttackWon = isAttackWon;
    }
    /**
     * This Method return which players has which territory card 
     * @return cards territory card
     */
    public Map<String, String> getCards() {
	return cards;
    }
    /** 
     * This Method set territory card for particular player 
     * @param cards teritory card
     */
    public void setCards(Map<String, String> cards) {
	this.cards = cards;
    }

    /**
     * Attack phase using Strategy Pattern 
     */
    @Override
    public void doAttack(Territory currentTerritory, String fromTerritory, String toTerritory, int attackerDice, int defenderDice) {
	isAttackWon = false;
	setDefenderMsg("");
	setAttackerMsg("");
	ArrayList<Integer> attackerDiceList = new ArrayList<>();
	ArrayList<Integer> defenderDiceList = new ArrayList<>();
	int[] attackerDiceArray = new int[attackerDice];
	int[] defenderDiceArray = new int[defenderDice];
	for (int i = 0; i < attackerDice; i++) {
	    attackerDiceArray[i] = (int) (Math.random() * 6 + 1);
	    attackerMsg += "DIE " + (i + 1) + " : " + attackerDiceArray[i] + "\n";
	    attackerDiceList.add(attackerDiceArray[i]);
	}
	for (int j = 0; j < defenderDice; j++) {
	    defenderDiceArray[j] = (int) (Math.random() * 6 + 1);
	    defenderMsg += "DIE " + (j + 1) + " : " + defenderDiceArray[j] + "\n";
	    defenderDiceList.add(defenderDiceArray[j]);
	}

	Collections.sort(attackerDiceList);
	Collections.sort(defenderDiceList);
	Collections.reverse(attackerDiceList);
	Collections.reverse(defenderDiceList);
	int maximumAttack = attackerDiceList.size() >= defenderDiceList.size() ? defenderDiceList.size() : attackerDiceList.size();  
	System.out.println(attackerDiceList);
	System.out.println(defenderDiceList);
	System.out.println(maximumAttack);

	for(int i=0;i<maximumAttack;i++) {
	    if(attackerDiceList.get(i) > defenderDiceList.get(i)) {
		currentTerritory.updateTerritoryArmy(toTerritory, 1, "DELETE");
	    }else {
		currentTerritory.updateTerritoryArmy(fromTerritory, 1, "DELETE");
	    }
	}

	if(currentTerritory.getTerritoryArmy().get(toTerritory) == 0) {
	    isAttackWon = true;
	    String toContinentPlayer = "";
	    String fromContinentPlayer = "";
	    String toPlayer = currentTerritory.getTerritoryUser().get(toTerritory);
	    String fromPlayer = currentTerritory.getTerritoryUser().get(fromTerritory);
	    for(Entry<String, ArrayList<String>> entry : playerContinent.get(fromPlayer).getContinentOwnedterritory().entrySet()) {
		fromContinentPlayer = entry.getValue().contains(fromTerritory) ? entry.getKey() : fromContinentPlayer;
	    }
	    for(Entry<String, ArrayList<String>> entry : playerContinent.get(toPlayer).getContinentOwnedterritory().entrySet()) {
		toContinentPlayer = entry.getValue().contains(toTerritory) ? entry.getKey() : toContinentPlayer;
	    }
	    System.out.println("To Continent : " + toContinentPlayer);
	    System.out.println("From  Continent : " + fromContinentPlayer);
	    System.out.println("To Player : " + toPlayer);
	    System.out.println("From Player : " + fromPlayer);
	    System.out.println("Territory : " + currentTerritory.getTerritoryCont());
	    System.out.println("Before------> "+playerContinent.get(fromPlayer).getContinentOwnedterritory());
	    System.out.println("Before------> " + playerContinent.get(toPlayer).getContinentOwnedterritory());
	    playerContinent.get(toPlayer).getContinentOwnedterritory().get(toContinentPlayer).remove(toTerritory);
	    playerContinent.get(fromPlayer).getContinentOwnedterritory().get(fromContinentPlayer).add(toTerritory);
	    System.out.println("After------> "+playerContinent.get(fromPlayer).getContinentOwnedterritory().get(fromContinentPlayer));
	    System.out.println("After------> " + playerContinent.get(toPlayer).getContinentOwnedterritory().get(toContinentPlayer));
	    currentTerritory.updateTerritoryUser(currentTerritory.getTerritoryUser().get(fromTerritory),toTerritory);
	    Object randomTerritory = currentTerritory.getTerritoryCard().keySet().toArray()[new Random().nextInt(currentTerritory.getTerritoryCard().keySet().toArray().length)];
	    System.out.println("Territory Card : " + randomTerritory);
	    System.out.println("Card Value " + currentTerritory.getTerritoryCard().get(randomTerritory));
	    if (!isWonCard) {
		cards.put(randomTerritory.toString(), fromPlayer);
		territoryCards.put(randomTerritory.toString().trim(),currentTerritory.getTerritoryCard().get(randomTerritory));
		setWonCard(true);
	    }
	    currentTerritory.getTerritoryCard().remove(randomTerritory);
	    System.out.println("---> " + currentTerritory.getTerritoryUser().get(toTerritory));
	}

    }
    /**
     * This Method used to get message when attack is done
     * @return attackerMsg message which need to be print in logs
     */
    public String getAttackerMsg() {
	return attackerMsg;
    }
    /**
     * This Method used to set message when attack is done
     * @param attackerMsg message which need  to be print in logs
     */
    public void setAttackerMsg(String attackerMsg) {
	this.attackerMsg = attackerMsg;
    }
    /**
     * This Method used to get message when attack is done
     * @return defenderMsg message which need  to be print in logs
     */
    public String getDefenderMsg() {
	return defenderMsg;
    }
    /**
     * This Method used to set message when attack is done
     * @param defenderMsg message which need  to be print in logs
     */
    public void setDefenderMsg(String defenderMsg) {
	this.defenderMsg = defenderMsg;
    }

    /**
     * Fortification phase using Strategy Pattern 
     */
    @Override
    public void doForitification(Territory currentTerritory, String fromTerritory, String toTerritory, int getArmySelect) {
	currentTerritory.updateTerritoryArmy(fromTerritory, getArmySelect, "DELETE");
	currentTerritory.updateTerritoryArmy(toTerritory, getArmySelect, "ADD");

    }

    /**
     * Reinforcement phase using Strategy Pattern 
     */
    @Override
    public void doReinforcement(String currentPlayer, String currentTerritoryName, int army, Territory currentTerritory) {
	updateArmy(currentPlayer, army, "DELETE");
	currentTerritory.updateTerritoryArmy(currentTerritoryName, army, "ADD");	    
    }

    /**
     * generate Reinforcement Army using Strategy Pattern 
     */
    @Override
    public void generateReinforcementArmy(String currentPlayer, Continent currentContinent) {
	int count = 0;
	setCurrentPhase("Reinforcement");
	Map<String, ArrayList<String>> tempData = getPlayerContinent().get(currentPlayer).getContinentOwnedterritory();
	for (Entry<String, ArrayList<String>> entry : tempData.entrySet()) {
	    if (!entry.getValue().isEmpty()) {
		count += entry.getValue().size();
	    }
	}
	Double value = new Double(Math.floor(count / 3));
	updateArmy(currentPlayer, value.intValue() > 3 ? (value.intValue() + checkContinentAcquired(currentPlayer,currentContinent))   : (3 + checkContinentAcquired(currentPlayer, currentContinent)), "ADD");	    
    }
    /**
     * This method add armies to current player if a particular continent is Acquired fully by player
     * @param currentPlayer
     * @param currentContinent
     * @return
     */
    public int checkContinentAcquired(String currentPlayer, Continent currentContinent){
	int count = 0;
	Map<String, ArrayList<String>> tempData = getPlayerContinent(currentPlayer).getContinentOwnedterritory();
	for (Entry<String, ArrayList<String>> entry : tempData.entrySet()) {
	    if (entry.getValue().size() == currentContinent.getContTerrValue().get(entry.getKey())) {
		count += currentContinent.getContinentValue().get(entry.getKey());
		System.out.println("------------>Continent Acquired : " + entry.getKey());
	    }
	}
	return count > 0 ? count : 0;
    }

    /**
     * This Method is used to Move army when attacker successfully capture territory
     */
    @Override
    public void moveArmyAfterAttack(String currentPlayer, Territory currentTerritory, String fromTerritory, String toTerritory, int armies) {
	currentTerritory.updateTerritoryArmy(fromTerritory, armies, "DELETE");
	currentTerritory.updateTerritoryArmy(toTerritory, armies, "ADD");
    }

}
