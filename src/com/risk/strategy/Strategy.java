package com.risk.strategy;

import com.risk.models.Continent;
import com.risk.models.Territory;

public interface Strategy {
    public void doReinforcement(String currentPlayer, String currentTerritory, int army, Territory territory);
    public void doAttack();
    public void doForitification(Territory territory, String fromTerritory, String toTerritory, int getArmySelect);
    public void generateReinforcementArmy(String currentPlayer, Continent currentContinent);
}
