package com.risk.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InitializeData {
	
	String filePath;
	int playerCount;
	public InitializeData(String filePath, int playerCount) {
		
		this.filePath = filePath;
		this.playerCount =playerCount;
		System.out.println("FilePath ------------- : "+filePath);
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
