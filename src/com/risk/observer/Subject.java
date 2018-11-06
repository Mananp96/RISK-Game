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
    private String fortificationMsg;

    public String getTradeInMsg() {
	return tradeInMsg;
    }

    public void setTradeInMsg(String tradeInMsg) {
	this.tradeInMsg = tradeInMsg;
	notifyForTradeIn();
    }

    private String attackMsg;
    private String tradeInMsg;

    public boolean getMessageFlag() {
	return messageFlag;
    }

    public void setMessageFlag(boolean messageFlag) {
	this.messageFlag = messageFlag;
    }

    public String getAttackMsg() {
	return attackMsg;
    }

    public void setAttackMsg(String attackMsg) {
	this.attackMsg = attackMsg;
	notifyForAttack();
    }

    public String getState() {
	return state;
    }

    public void setState(String state, boolean flag) {
	this.state = state;
	this.flag = flag;
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

    public void setMessage(boolean flag, String newMessage) {
	message = /* message + */ newMessage + "\n";
	messageFlag = flag;
    }

    public boolean getFlag() {
	return flag;
    }

    public void attach(Observer observer) {
	observers.add(observer);
    }

    public void notifyAllObservers() {
	for (Observer observer : observers) {
	    observer.update();
	}
    }

    public String getFortificationMessage() {
	return fortificationMsg;
    }

    public void setFortificationMessage(String fortificationMsg) {
	this.fortificationMsg = fortificationMsg;
	notifyForFortification();
    }

    public void notifyForFortification() {
	for (Observer observer : observers) {
	    observer.fortificationUpdate();
	}
    }

    public void notifyForAttack() {
	for (Observer observer : observers) {
	    observer.attackUpdate();
	}
    }

    public void notifyForTradeIn() {
	for (Observer observer : observers) {
	    observer.tradeInCardUpdate();
	}
    }
}
