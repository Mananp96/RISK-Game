package com.risk.strategy;

import com.risk.models.Continent;
import com.risk.models.Territory;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
	this.strategy = strategy;
    }

    public void executeReinforcement(String currentPlayer, String currentTerritory, int army, Territory territory){
	strategy.doReinforcement(currentPlayer, currentTerritory, army, territory);
    }
    public void executeReinforcementArmy(String currentPlayer, Continent currentContinent) {
	strategy.generateReinforcementArmy(currentPlayer,currentContinent);
    }
    public void executeAttack(Territory currentTerritory, String fromTerritory, String toTerritory, int attackerDice,
			int defenderDice) {
	strategy.doAttack(currentTerritory,fromTerritory,toTerritory,attackerDice,defenderDice);
    }
    public void executeFortification(Territory territory, String fromTerritory, String toTerritory, int getArmySelect) {
	strategy.doForitification(territory, fromTerritory, toTerritory, getArmySelect);
    }
}
