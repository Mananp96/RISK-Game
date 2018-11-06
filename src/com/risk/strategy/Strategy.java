package com.risk.strategy;

import com.risk.models.Continent;
import com.risk.models.Territory;

public interface Strategy {
    public void doReinforcement(String currentPlayer, String currentTerritory, int army, Territory territory);

    public void doForitification(Territory territory, String fromTerritory, String toTerritory, int getArmySelect);

    public void generateReinforcementArmy(String currentPlayer, Continent currentContinent);

    public void doAttack(Territory currentTerritory, String fromTerritory, String toTerritory, int attackerDice,
	    int defenderDice);

    public void moveArmyAfterAttack(String fromTerritory, Territory currentTerritory, String toTerritory,
	    String toTerritory2, int armies);
}
