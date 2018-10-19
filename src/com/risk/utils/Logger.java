package com.risk.utils;

import java.util.Observable;

public class Logger extends Observable {

    private String log;

    public void riskLogger(String log){
        this.log = log;
        setChanged();
        notifyObservers();
    }

    public String getLog(){
        return log;
    }

}
