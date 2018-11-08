package com.risk.strategy;

import com.risk.models.Continent;
import com.risk.models.Territory;

/**
 * This class is for strategy pattern
 *
 */
public interface Strategy {

	/**
	 * method implement strategy pattern for reinforcement
	 * @param currentPlayer current player name
	 * @param currentTerritory current territory name
	 * @param army no of armies
	 * @param territory name of territory
	 */
	public void doReinforcement(String currentPlayer, String currentTerritory, int army, Territory territory);

	/**
	 * method implement strategy pattern for fortification
	 * @param territory name of territory
	 * @param fromTerritory name of territory
	 * @param toTerritory  name of territory
	 * @param getArmySelect no of selected army
	 */
	public void doForitification(Territory territory, String fromTerritory, String toTerritory, int getArmySelect);

	/**
	 * method implement strategy pattern for reinforcement army generation
	 * @param currentPlayer current player name
	 * @param currentContinent current continent name
	 */
	public void generateReinforcementArmy(String currentPlayer, Continent currentContinent);

	/**
	 * method implement strategy pattern for attack
	 * @param currentTerritory territory object
	 * @param fromTerritory from territory
	 * @param toTerritory to territory
	 * @param attackerDice amount of Dice of Attacker
	 * @param defenderDice amount of Dice of defender
	 */
	public void doAttack(Territory currentTerritory, String fromTerritory, String toTerritory, int attackerDice,
			int defenderDice);

	/**
	 * method implement strategy pattern for army move after attack phase
	 * @param fromTerritory from territory
	 * @param currentTerritory territory object
	 * @param toTerritory to territory
	 * @param toTerritory2   to territory2
	 * @param armies number of armies to move from
	 */
	public void moveArmyAfterAttack(String fromTerritory, Territory currentTerritory, String toTerritory,
			String toTerritory2, int armies);
}
