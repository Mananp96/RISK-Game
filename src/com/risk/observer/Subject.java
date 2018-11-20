package com.risk.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class used to implement observer pattern
 */
public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private String state;
    private boolean flag;
    private String phase;
    private String message;
    private boolean messageFlag;
    private String fortificationMsg;
    private String reinforcementMsg;

    public String getReinforcementMsg() {
	return reinforcementMsg;
    }

    public void setReinforcementMsg(String reinforcementMsg) {
	this.reinforcementMsg = reinforcementMsg;
	notifyForReinforcment();
    }

    /**
     * get method for trade message
     * @return tradeInMsg trade message 
     */
    public String getTradeInMsg() {
	return tradeInMsg;
    }

    /**
     * set method for trade message
     * @param tradeInMsg trade message
     */
    public void setTradeInMsg(String tradeInMsg) {
	this.tradeInMsg = tradeInMsg;
	notifyForTradeIn();
    }
    public void setBotTradeInMsg(String tradeInMsg) {
	this.tradeInMsg = tradeInMsg;
	notifyForBotTradeIn();
    }
    private String attackMsg;
    private String tradeInMsg;

    /**
     * get method for messageFlag
     * @return messageFlag message flag boolean
     */
    public boolean getMessageFlag() {
	return messageFlag;
    }

    /**
     * set method for message flag
     * @param messageFlag message flag boolean
     */
    public void setMessageFlag(boolean messageFlag) {
	this.messageFlag = messageFlag;
    }

    /**
     * get method for attack message
     * @return attackmsg attack message
     */
    public String getAttackMsg() {
	return attackMsg;
    }

    /**
     * set method for attack message 
     * @param attackMsg attack message
     */
    public void setAttackMsg(String attackMsg) {
	this.attackMsg = attackMsg;
	notifyForAttack();
    }

    /**
     *  get method for state
     * @return state state
     */
    public String getState() {
	return state;
    }

    /**
     *  set method for state
     * @param state state
     * @param flag flag boolean
     */
    public void setState(String state, boolean flag) {
	this.state = state;
	this.flag = flag;
	notifyAllObservers();
    }
    public void setPlayerLog() {
	notifyForLogUpdate();
    }

    /**
     *  get method for phase
     * @return phase phase
     */
    public String getPhase() {
	return phase;
    }

    /**
     *  set method for phase
     * @param phase phase
     * @param message message
     */
    public void setPhase(String phase, String message) {
	this.phase = phase;
	this.message = message;
    }

    /**
     *  get method for message
     * @return message message
     */
    public String getMessage() {
	return message;
    }

    /**
     *  get method for message
     * @param flag flag
     * @param newMessage message
     */
    public void setMessage(boolean flag, String newMessage) {
	message = /* message + */ newMessage + "\n";
	messageFlag = flag;
    }

    /**
     *  get method for flag
     * @return flag boolean value
     */
    public boolean getFlag() {
	return flag;
    }

    /**
     * observer registration
     * @param observer observer name
     */
    public void attach(Observer observer) {
	observers.add(observer);
    }

    /**
     * notify all registered observers
     * 
     */
    public void notifyAllObservers() {
	for (Observer observer : observers) {
	    observer.update();
	}
    }

    /**
     *  get method for fortification message
     * @return fortificationmsg message 
     */
    public String getFortificationMessage() {
	return fortificationMsg;
    }

    /**
     * set method for fortification message
     * @param fortificationMsg message
     */
    public void setFortificationMessage(String fortificationMsg) {
	this.fortificationMsg = fortificationMsg;
	notifyForFortification();
    }

    /**
     * notify observers about fortification changes
     */
    public void notifyForFortification() {
	for (Observer observer : observers) {
	    observer.fortificationUpdate();
	}
    }

    /**
     * notify observers about attack changes
     */
    public void notifyForAttack() {
	for (Observer observer : observers) {
	    observer.attackUpdate();
	}
    }

    /**
     * notify observers about card trade changes
     */
    public void notifyForTradeIn() {
	for (Observer observer : observers) {
	    observer.tradeInCardUpdate();
	}
    }
    public void notifyForBotTradeIn() {
	for (Observer observer : observers) {
	    observer.botTradeInCardUpdate();
	}
    }
    public void notifyForReinforcment() {
	for (Observer observer : observers) {
	    observer.reinforcementUpdate();
	}
    }
    public void notifyForLogUpdate() {
	for (Observer observer : observers) {
	    observer.playerLogUpdate();
	}
    }
}
