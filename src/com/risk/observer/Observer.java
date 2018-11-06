package com.risk.observer;

public abstract class Observer {
    protected Subject observerSubject;

    public abstract void update();

    public abstract void fortificationUpdate();

    public abstract void attackUpdate();

    public abstract void tradeInCardUpdate();

}
