package com.risk.controller;

import com.risk.models.Territory;

public class Fortification {

    Territory territory;

    public Fortification(Territory territory) {
	super();
	this.territory = territory;
    }
    /**
     * This method allow player to move army from one territory to other territory(adjacent)
     * @param fromTerritory Territory Name from which army want to move
     * @param toTerritory  Territory Name to which armies is moved.
     * @param army No. of Armies Player want to move
     */
    public void startFortification(String fromTerritory,String toTerritory, int army) {
	territory.updateTerritoryArmy(fromTerritory, army, "DELETE");
	territory.updateTerritoryArmy(toTerritory, army, "ADD");
    }
    /**
     * 
     * @return Current Territory Object
     */
    public Territory getTerritory() {
        return territory;
    }
    /**
     *  This method set Current Territory Object
     * @param territory Current Territory Object
     */
    public void setTerritory(Territory territory) {
        this.territory = territory;
    }
}
