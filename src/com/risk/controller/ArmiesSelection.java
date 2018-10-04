package com.risk.controller;

public class ArmiesSelection {

	private int armies;

	public ArmiesSelection(int playerPlaying) {
		super();
		armies = 50 - (playerPlaying * 5);
	}

	public int getPlayerArmies() {
		return armies;
	}

	public void setPlayerArmies(int armies) {
		this.armies = armies;
	}
}
