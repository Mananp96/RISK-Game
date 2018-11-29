package com.risk.models;

/**
 * This Model class is used to select the Number of initial
 * Armies depending on Number of Players playing.
 *
 */
public class ArmiesSelection {
	private int armies;

	/**
	 * This constructor set the number of armies according to playerPlaying.
	 * @param playerPlaying Number of Players playing the game.
	 */
	public ArmiesSelection(int playerPlaying) {
		super();
		armies = 50 - (playerPlaying * 5);
	}

	/**
	 * Returns the Number of initial armies per Player.
	 * @return number of initial armies
	 */
	public int getPlayerArmies() {
		return armies;
	}

	/**
	 * Set the Number of initial Armies assigned per Player.
	 * @param armies number of initial armies
	 */
	public void setPlayerArmies(int armies) {
		this.armies = armies;
	}
}
