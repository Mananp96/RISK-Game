package com.risk.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InitializeData {
	
	String filePath;
	int playerCount;
	int armies;
	ArrayList<String> currentPlayers;
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
	public void generateData() throws IOException
	{
		try {
			BufferedReader br= new BufferedReader(new FileReader(new File(filePath)));
			while(br.readLine() != null)
			{
				System.out.println(br.readLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
