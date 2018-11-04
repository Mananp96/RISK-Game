package com.risk.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private String state;
    private boolean flag;
    private String phase;
    private String message;
    private boolean messageFlag;

    public boolean getMessageFlag() {
        return messageFlag;
    }

    public void setMessageFlag(boolean messageFlag) {
        this.messageFlag = messageFlag;
    }

    public String getState() {
       return state;
    }

    public void setState(String state, boolean flag) {
       this.state = state;
       this.flag= flag;
       notifyAllObservers();
    }
    
    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase, String message) {
        this.phase = phase;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(boolean flag,String newMessage) {
        message = /*message +*/  newMessage+ "\n" ;
        messageFlag = flag;
    }

    public boolean getFlag() {
        return flag;
    }
    public void attach(Observer observer){
       observers.add(observer);		
    }

    public void notifyAllObservers(){
       for (Observer observer : observers) {
          observer.update();
       }
    } 	
}
