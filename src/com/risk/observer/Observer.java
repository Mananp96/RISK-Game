package com.risk.observer;

public abstract class Observer {
    protected Subject observerSubject;
    public abstract void update();
 }
