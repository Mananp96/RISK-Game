package com.risk.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

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
	String fortificationMsg ="";
	String reinforcementMsg ="";
	int tradeIn = 0;
	Map<String, String> cards;
	Map<String, String> territoryCards;
	public Double value;
	Map<String,String> playerType;
	/**
	 * This method use to get message when event performed in reinforcement phase
	 * @return reinforcementMsg
	 */
	public String getReinforcementMsg() {
		return reinforcementMsg;
	}
	/**
	 * This method use to set message when event performed in reinforcement phase
	 * @param reinforcementMsg text which is print in logs
	 */
	public void setReinforcementMsg(String reinforcementMsg) {
		this.reinforcementMsg = reinforcementMsg;
	}
	/**
	 * This method use to get message when event performed in fortification phase
	 * @return fortificationMsg
	 */
	public String getFortificationMsg() {
		return fortificationMsg;
	}
	/**
	 * This method use to set message when event performed in fortification phase
	 * @param fortificationMsg text which is print in logs
	 */
	public void setFortificationMsg(String fortificationMsg) {
		this.fortificationMsg = fortificationMsg;
	}

	/**
	 * This method is used to get Type of players
	 * @return playerType
	 */
	public Map<String, String> getPlayerType() {
		return playerType;
	}
	/**
	 * This method is used to set Type of players
	 * @param playerType Type of players
	 */
	public void setPlayerType(Map<String, String> playerType) {
		this.playerType = playerType;
	}

	/**
	 * Constructor
	 */
	public Players() {
		playerContinent = new HashMap<>();
		playerArmy = new HashMap<>();
		playerList = new ArrayList<>();
		playerPlaying = new ArrayList<>();
		cards = new HashMap<>();
		territoryCards = new HashMap<>();
		playerType = new HashMap<>();
	}
	/**
	 * This method is used to put player name and its type in playerType Map
	 * @param playerName player Name
	 * @param type player type
	 */
	public void addPlayerType(String playerName,String type) {
		playerType.put(playerName, type);
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
	/**
	 * This method is used to get number of trade in performed in game
	 * @return tradeIn
	 */
	public int getTradeIn() {
		return tradeIn;
	}
	/**
	 *  This method increment number of trade in whenever player trade in their cards
	 */
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
			playerContinent.get(toPlayer).getContinentOwnedterritory().get(toContinentPlayer).remove(toTerritory);
			playerContinent.get(fromPlayer).getContinentOwnedterritory().get(fromContinentPlayer).add(toTerritory);
			currentTerritory.updateTerritoryUser(currentTerritory.getTerritoryUser().get(fromTerritory),toTerritory);
			int rand = 0;
			if(!currentTerritory.getTerritoryCard().isEmpty())
				rand = new Random().nextInt(currentTerritory.getTerritoryCard().keySet().toArray().length)+1;
			if(currentTerritory.getTerritoryCard().size() == 1) {
				rand = 0;
			}
			if(currentTerritory.getTerritoryCard().size() == rand) {
				rand -=1;
			}
			if(rand >= 0 ) {
				Object randomTerritory = currentTerritory.getTerritoryCard().keySet().toArray()[rand];
				if (!isWonCard) {
					cards.put(randomTerritory.toString(), fromPlayer);
					territoryCards.put(randomTerritory.toString().trim(),currentTerritory.getTerritoryCard().get(randomTerritory));
					setWonCard(true);
				}
				currentTerritory.getTerritoryCard().remove(randomTerritory);
			}

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
		setReinforcementMsg("Armies Placed on "+currentTerritoryName +" : "+army);
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
		value = new Double(Math.floor(count / 3));
		updateArmy(currentPlayer, value.intValue() > 3 ? (value.intValue() + checkContinentAcquired(currentPlayer,currentContinent))   : (3 + checkContinentAcquired(currentPlayer, currentContinent)), "ADD");	    
	}
	/**
	 * This method add armies to current player if a particular continent is Acquired fully by player
	 * @param currentPlayer currentPlayer
	 * @param currentContinent currentContinent
	 * @return count if count is more than 0 else return 0.
	 */
	public int checkContinentAcquired(String currentPlayer, Continent currentContinent){
		int count = 0;
		Map<String, ArrayList<String>> tempData = getPlayerContinent(currentPlayer).getContinentOwnedterritory();
		for (Entry<String, ArrayList<String>> entry : tempData.entrySet()) {
			if (entry.getValue().size() == currentContinent.getContTerrValue().get(entry.getKey())) {
				count += currentContinent.getContinentValue().get(entry.getKey());
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
	/**
	 * This method is used to perform reinforcement phase for BOT player
	 */
	@Override
	public void doBotReinforcement(String currentPlayer, Territory currentTerritory) {
		String tempTerritory = "";
		int tempArmy = 0;
		String[] tempSplit;
		StringBuilder message = new StringBuilder();
		ArrayList<String> tempTerrList = new ArrayList<>();
		if (playerType.get(currentPlayer).equalsIgnoreCase("AGGRESSIVE")) {
			for (Entry<String, String> entry : currentTerritory.getTerritoryUser().entrySet()) {
				if (currentTerritory.getTerritoryUser().get(entry.getKey()).equalsIgnoreCase(currentPlayer)) {
					tempTerrList.add(currentTerritory.getTerritoryArmy().get(entry.getKey()) + "-" + entry.getKey());
				}
			}
			Collections.sort(tempTerrList);
			Collections.reverse(tempTerrList);
			if(!tempTerrList.isEmpty()) {
				int rand = 0;
				rand = new Random().nextInt(2)+1;
				rand = tempTerrList.size() == 1 ? 0 : (rand-1);
				tempSplit = tempTerrList.get(rand).split("-");
				doReinforcement(currentPlayer, tempSplit[1], playerArmy.get(currentPlayer), currentTerritory);
			}
		} else if (playerType.get(currentPlayer).equalsIgnoreCase("BENEVOLENT")) {
			for (Entry<String, String> entry : currentTerritory.getTerritoryUser().entrySet()) {
				if (currentTerritory.getTerritoryUser().get(entry.getKey()).equalsIgnoreCase(currentPlayer)) {
					tempTerrList.add(currentTerritory.getTerritoryArmy().get(entry.getKey()) + "-" + entry.getKey());
				}
			}
			Collections.sort(tempTerrList);
			if (!tempTerrList.isEmpty()) {
				tempSplit = tempTerrList.get(0).split("-");
				doReinforcement(currentPlayer, tempSplit[1], playerArmy.get(currentPlayer), currentTerritory);
			}
		} else if(getPlayerType().get(currentPlayer).equalsIgnoreCase("RANDOM")) {
			for (Entry<String, String> entry : currentTerritory.getTerritoryUser().entrySet()) {
				if (currentTerritory.getTerritoryUser().get(entry.getKey()).equalsIgnoreCase(currentPlayer)) {
					tempTerrList.add(entry.getKey());
				}
			}
			int rand = 0;
			do{
				rand = new Random().nextInt(tempTerrList.size())+1;
				if(tempTerrList.size() == 1 || tempTerrList.size() == rand) {
					rand = 0;
				}
				doReinforcement(currentPlayer, tempTerrList.get(rand), 1, currentTerritory);
				message.append(getReinforcementMsg()+"\n");
			} while(getPlayerArmy(currentPlayer) >= 1);
			setReinforcementMsg(message.toString());
		} else if(getPlayerType().get(currentPlayer).equalsIgnoreCase("CHEATER")) {
			for (Entry<String, String> entry : currentTerritory.getTerritoryUser().entrySet()) {
				if (currentTerritory.getTerritoryUser().get(entry.getKey()).equalsIgnoreCase(currentPlayer)) {
					tempTerrList.add(entry.getKey());
				}
			}
			if(!tempTerrList.isEmpty()) {
				updateArmy(currentPlayer, getPlayerArmy(currentPlayer), "DELETE");
			}
			for(int i = 0;i< tempTerrList.size();i++) {
				currentTerritory.updateTerritoryArmy(tempTerrList.get(i), currentTerritory.getTerritoryArmy().get(tempTerrList.get(i)), "ADD");
				message.append("Armies Placed on "+tempTerrList.get(i) +" : "+currentTerritory.getTerritoryArmy().get(tempTerrList.get(i))+"\n");
			}
			setReinforcementMsg(message.toString());
		}

	}
	/**
	 * This method is used to perform attack phase for BOT Player
	 */
	@Override
	public void doBotAttack(Territory currentTerritory, String fromTerritory, String toTerritory, int attackerDie,
			int defenderDie, String currentPlayer) {
		String type= playerType.get(currentPlayer);
		if(type.equalsIgnoreCase("AGGRESSIVE")) {
			ArrayList<String> tempTerrList = new ArrayList<>();
			for (Entry<String, String> entry : currentTerritory.getTerritoryUser().entrySet()) {
				if (currentTerritory.getTerritoryUser().get(entry.getKey()).equalsIgnoreCase(currentPlayer)) {
					tempTerrList.add(currentTerritory.getTerritoryArmy().get(entry.getKey()) + "-" + entry.getKey());
				}
			}
			if(!tempTerrList.isEmpty()) {
				Collections.sort(tempTerrList);
				Collections.reverse(tempTerrList);
				String[] tempSplit = tempTerrList.get(0).split("-");
				String tempdefenderTerr="";
				for(int i =0;i < currentTerritory.getAdjacentTerritory().get(tempSplit[1]).size();i++) {
					if(!currentTerritory.getTerritoryUser().get(currentTerritory.getAdjacentTerritory().get(tempSplit[1]).get(i)).equalsIgnoreCase(currentPlayer)) {
						tempdefenderTerr = currentTerritory.getAdjacentTerritory().get(tempSplit[1]).get(i);
						if(currentTerritory.getTerritoryArmy().get(tempdefenderTerr) >= 1 ) {
							break;
						}
					}
				}
				if(StringUtils.isNotBlank(tempdefenderTerr)) {
					int attackerDice = currentTerritory.getTerritoryArmy().get(tempSplit[1]) > 4 ? 3 : (currentTerritory.getTerritoryArmy().get(tempSplit[1]) - 1);
					int defenderDice = currentTerritory.getTerritoryArmy().get(tempdefenderTerr) > 3 ? 3 : currentTerritory.getTerritoryArmy().get(tempdefenderTerr);
					String message1 = "Before Attack \n " + tempSplit[1] +" : "+currentTerritory.getTerritoryArmy().get(tempSplit[1]) +"\n"+tempdefenderTerr+" : "+currentTerritory.getTerritoryArmy().get(tempdefenderTerr)+"\n";
					doAttack(currentTerritory,tempSplit[1] , tempdefenderTerr, attackerDice, defenderDice);
					String message2 = "After Attack \n " + tempSplit[1] +" : "+currentTerritory.getTerritoryArmy().get(tempSplit[1]) +"\n"+tempdefenderTerr+" : "+currentTerritory.getTerritoryArmy().get(tempdefenderTerr)+"\n";
					setAttackerMsg(message1 + "\n" +message2);
				} else {
					setAttackerMsg("Nothing to Attack from Strongest Territory");
				}
			}
		} else if(type.equalsIgnoreCase("RANDOM")) {
			String message1 = "Before Attack \n " + fromTerritory +" : "+currentTerritory.getTerritoryArmy().get(fromTerritory) +"\n"+toTerritory+" : "+currentTerritory.getTerritoryArmy().get(toTerritory)+"\n";
			doAttack(currentTerritory, fromTerritory, toTerritory, attackerDie, defenderDie);
			String message2 = "After Attack \n " + fromTerritory +" : "+currentTerritory.getTerritoryArmy().get(fromTerritory) +"\n"+toTerritory+" : "+currentTerritory.getTerritoryArmy().get(toTerritory)+"\n";
			if(isAttackWon) {
				currentTerritory.updateTerritoryArmy(fromTerritory, 1, "DELETE");
				currentTerritory.updateTerritoryArmy(toTerritory, 1, "ADD");    
			}
			setAttackerMsg(message1 + "\n" +message2);
		} else 	if(type.equalsIgnoreCase("CHEATER")) {
			StringBuilder message3 = new StringBuilder();
			ArrayList<String> tempArray = new ArrayList<>();
			for(Entry<String, String> entry : currentTerritory.getTerritoryUser().entrySet()) {
				if(entry.getValue().equalsIgnoreCase(fromTerritory)) {
					tempArray.add(entry.getKey());
				}
			}
			if(!tempArray.isEmpty()) {
				boolean tempFlag = false;
				for(int i = 0 ;i< tempArray.size();i++) {
					String fromPlayer = fromTerritory;
					for(int j=0;j<currentTerritory.getAdjacentTerritory().get(tempArray.get(i)).size();j++) {
						String tempTerr = currentTerritory.getAdjacentTerritory().get(tempArray.get(i)).get(j);
						String toPlayer = currentTerritory.getTerritoryUser().get(tempTerr);
						String fromContinentPlayer = "";
						String toContinentPlayer = "";
						if(!fromPlayer.equalsIgnoreCase(toPlayer)) {
							for(Entry<String, ArrayList<String>> entry : playerContinent.get(fromPlayer).getContinentOwnedterritory().entrySet()) {
								fromContinentPlayer = entry.getValue().contains(tempArray.get(i)) ? entry.getKey() : fromContinentPlayer;
							}
							for(Entry<String, ArrayList<String>> entry : playerContinent.get(toPlayer).getContinentOwnedterritory().entrySet()) {
								toContinentPlayer = entry.getValue().contains(tempTerr) ? entry.getKey() : toContinentPlayer;
							}
							playerContinent.get(toPlayer).getContinentOwnedterritory().get(toContinentPlayer).remove(tempTerr);
							playerContinent.get(fromPlayer).getContinentOwnedterritory().get(fromContinentPlayer).add(tempTerr);
							currentTerritory.updateTerritoryUser(currentTerritory.getTerritoryUser().get(tempArray.get(i)),tempTerr);
							message3.append("Cheater Player has Capture : " + tempTerr + " Territory\n");
							tempFlag = true;
						}
					}
				}
				setAttackerMsg(message3.toString());
				if(tempFlag && currentTerritory.getTerritoryCard().size() > 0) {
					int rand = 0;

					rand = new Random().nextInt(currentTerritory.getTerritoryCard().keySet().toArray().length)+1;
					if(currentTerritory.getTerritoryCard().size() == 1) {
						rand = 0;
					}
					if(currentTerritory.getTerritoryCard().size() == rand) {
						rand -=1;
					}
					Object randomTerritory = currentTerritory.getTerritoryCard().keySet().toArray()[rand];
					if (!isWonCard) {
						cards.put(randomTerritory.toString(), fromTerritory);
						territoryCards.put(randomTerritory.toString().trim(),currentTerritory.getTerritoryCard().get(randomTerritory));
						setWonCard(true);
					}
					currentTerritory.getTerritoryCard().remove(randomTerritory);
				}
			}
		}

	}
	/**
	 * THis Method is used to perform fortification for BOT Player
	 */
	@Override
	public void doBotForitification(String currentPlayer, Territory currentTerritory) {
		ArrayList<String> tempTerritory = new ArrayList<>();
		String message ="";
		if(getPlayerType().get(currentPlayer).equalsIgnoreCase("AGGRESSIVE") || getPlayerType().get(currentPlayer).equalsIgnoreCase("BENEVOLENT")) {
			for(Entry<String, String> entry : currentTerritory.getTerritoryUser().entrySet()) {
				if(entry.getValue().equalsIgnoreCase(currentPlayer)) {
					tempTerritory.add(currentTerritory.getTerritoryArmy().get(entry.getKey())+"-"+entry.getKey());
				}
			}
			Collections.sort(tempTerritory);
			if(getPlayerType().get(currentPlayer).equalsIgnoreCase("AGGRESSIVE")) {
				Collections.reverse(tempTerritory);    
			}
			if(tempTerritory.size() >= 1) {
				String[] splitTerr = tempTerritory.get(0).split("-");
				for(int i=0;i<currentTerritory.getAdjacentTerritory().get(splitTerr[1]).size();i++) {
					String temp = currentTerritory.getAdjacentTerritory().get(splitTerr[1]).get(i);
					if(currentTerritory.getTerritoryUser().get(temp).equalsIgnoreCase(currentPlayer) && currentTerritory.getTerritoryArmy().get(temp) > 1) {
						message += " Before Fortification \n"+splitTerr[1]+ " : " + currentTerritory.getTerritoryArmy().get(splitTerr[1])+"\n" + temp + " : "+currentTerritory.getTerritoryArmy().get(temp)+"\n"  ;
						doForitification(currentTerritory,temp, splitTerr[1], currentTerritory.getTerritoryArmy().get(temp)-1); 
						message += " After Fortification \n"+splitTerr[1]+ " : " + currentTerritory.getTerritoryArmy().get(splitTerr[1])+"\n" + temp + " : "+currentTerritory.getTerritoryArmy().get(temp)+"\n"  ;
						break;
					}
				}
				setFortificationMsg(message);
			} else {
				setFortificationMsg("No Armies to Move");
			}

		} else if(getPlayerType().get(currentPlayer).equalsIgnoreCase("RANDOM")) {
			for(Entry<String, String> entry : currentTerritory.getTerritoryUser().entrySet()) {
				if(entry.getValue().equalsIgnoreCase(currentPlayer)) {
					tempTerritory.add(entry.getKey());
				}
			}
			int rand = 0;
			rand = new Random().nextInt(tempTerritory.size())+1;
			if(tempTerritory.size() == 1 || rand == tempTerritory.size()) {
				rand = 0;
			}
			if(tempTerritory.size() >= 1) {
				for(int i=0;i<currentTerritory.getAdjacentTerritory().get(tempTerritory.get(rand)).size();i++) {
					String temp = currentTerritory.getAdjacentTerritory().get(tempTerritory.get(rand)).get(i);
					if(currentTerritory.getTerritoryUser().get(temp).equalsIgnoreCase(currentPlayer) && currentTerritory.getTerritoryArmy().get(temp) > 1) {
						message += " Before Fortification \n"+tempTerritory.get(rand)+ " : " + currentTerritory.getTerritoryArmy().get(tempTerritory.get(rand))+"\n" + temp + " : "+currentTerritory.getTerritoryArmy().get(temp)+"\n"  ;
						doForitification(currentTerritory,temp, tempTerritory.get(rand), currentTerritory.getTerritoryArmy().get(temp)-1); 
						message += " After Fortification \n"+tempTerritory.get(rand)+ " : " + currentTerritory.getTerritoryArmy().get(tempTerritory.get(rand))+"\n" + temp + " : "+currentTerritory.getTerritoryArmy().get(temp)+"\n"  ;
						break;
					}
				}
				setFortificationMsg(message);
			} else {
				setFortificationMsg("No Armies to Move");
			}
		} else if (getPlayerType().get(currentPlayer).equalsIgnoreCase("CHEATER")) {
			boolean tempFlag = false;
			for(Entry<String, String> entry : currentTerritory.getTerritoryUser().entrySet()) {
				if(entry.getValue().equalsIgnoreCase(currentPlayer)) {
					tempTerritory.add(entry.getKey());
				}
			}
			for(int i =0;i< tempTerritory.size();i++) {
				for(int j=0;j<currentTerritory.getAdjacentTerritory().get(tempTerritory.get(i)).size();j++) {
					if(!currentTerritory.getTerritoryUser().get(currentTerritory.getAdjacentTerritory().get(tempTerritory.get(i)).get(j)).equalsIgnoreCase(currentPlayer)) {
						tempFlag = true;
						currentTerritory.updateTerritoryArmy(tempTerritory.get(i), currentTerritory.getTerritoryArmy().get(tempTerritory.get(i)), "ADD");
						message+= currentPlayer +" has double armies in " +tempTerritory.get(i)+ ". Now Current Armies are "+currentTerritory.getTerritoryArmy().get(tempTerritory.get(i))+"\n"; 
						break;
					}
					if(tempFlag) {
						break;
					}
				}
			}
			setFortificationMsg(message);
		}
	}

}
