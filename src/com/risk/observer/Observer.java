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
     * this method update fortification changes 
     */
    public abstract void fortificationUpdate();

    /**
     * this method update attack changes 
     */
    public abstract void attackUpdate();

    /**
     * this method update card changes 
     */
    public abstract void tradeInCardUpdate();
    public abstract void botTradeInCardUpdate();
    public abstract void reinforcementUpdate();
    public abstract void playerLogUpdate();
}
