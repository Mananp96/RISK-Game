package com.risk.observer;

/**
 * This is observer class
 */
public abstract class Observer {
    protected Subject observerSubject;

    /**
     * this method update new changes
     */
    public abstract void update();

    /**
     * This method update to print events taken during fortification phase
     */
    public abstract void fortificationUpdate();

    /**
     * This method update to print events taken during attack phase
     */
    public abstract void attackUpdate();

    /**
     * This method update card changes
     */
    public abstract void tradeInCardUpdate();

    /**
     * This method update card changes for bot player
     */
    public abstract void botTradeInCardUpdate();

    /**
     * This method update to print events taken during reinforcement phase
     */
    public abstract void reinforcementUpdate();

    /**
     * This method is used to print player details
     */
    public abstract void playerLogUpdate();
}
