package com.risk.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.risk.validate.MapValidator;

public class InitializeData {
	
	String filePath;
	int playerCount;

	int armies;
	ArrayList<String> currentPlayers;
	HashMap<String, ArrayList<String>> contTerr; 						//for storing [Continents] and it's [Territories].
	HashMap<String, Integer> contValue; 								//for storing [Continents] and it's [Winning Value].
	HashMap<String, ArrayList<String>> adjcentTerr;						//for storing [Territories] and it's [Adjacent Territories].
	
	public InitializeData(String filePath, int playerCount, int armies, ArrayList<String> currentPlayers) {
		this.filePath = filePath;
		this.playerCount = playerCount;
		this.armies = armies;
		this.currentPlayers = currentPlayers;
		System.out.println("FilePath ------------- : "+filePath);
		System.out.println("Player Playing ------------- : "+playerCount);
		System.out.println("Armies for Each Player ------------- : "+armies);
		System.out.println("No. of Player ------------- : "+currentPlayers);
	}
	public ArrayList<String> getCurrentPlayers() {
		return currentPlayers;
	}
	public void setCurrentPlayers(ArrayList<String> currentPlayers) {
		this.currentPlayers = currentPlayers;
	}
	public int getArmies() {
		return armies;
	}
	public HashMap<String, ArrayList<String>> getContTerr() {
		return contTerr;
	}
	public void setContTerr(HashMap<String, ArrayList<String>> contTerr) {
		this.contTerr = contTerr;
	}
	public HashMap<String, Integer> getContValue() {
		return contValue;
	}
	public void setContValue(HashMap<String, Integer> contValue) {
		this.contValue = contValue;
	}
	public HashMap<String, ArrayList<String>> getAdjcentTerr() {
		return adjcentTerr;
	}
	public void setAdjcentTerr(HashMap<String, ArrayList<String>> adjcentTerr) {
		this.adjcentTerr = adjcentTerr;
	}
	public void setArmies(int armies) {
		this.armies = armies;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getPlayerCount() {
		return playerCount;
	}
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}
	public boolean generateData(){
		BoardData boardData = new BoardData(filePath);
		boardData.generateBoardData(filePath);
		contTerr = boardData.contTerr;
		contValue = boardData.contValue;
		adjcentTerr = boardData.adjcentTerr;
		Reinforcement reinforcement = new Reinforcement(currentPlayers,contTerr,contValue,adjcentTerr);
		
		return false;
	}
	
	
	

}
