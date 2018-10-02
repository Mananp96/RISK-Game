package com.risk.controller;

public class ArmiesSelection {

	private int armies;

	public ArmiesSelection(int playerPlaying) {
		super();
		if(playerPlaying == 2) {
			armies = 40;
		} else if(playerPlaying == 3) {
			armies = 35;
		} else if(playerPlaying == 4) {
			armies = 30;
		} else if(playerPlaying == 5) {
			armies = 25;
		} else {
			armies = -1;
		}
	}

	public int getPlayerArmies() {
		return armies;
	}

	public void setPlayerArmies(int armies) {
		this.armies = armies;
	}
}
