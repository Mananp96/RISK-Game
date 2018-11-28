package com.riskTest.models;

import java.awt.List;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.controller.BoardData;
import com.risk.models.ArmiesSelection;
import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;
import com.risk.view.GamePanels;

/**
 * This method test the Players class methods.
 */
public class PlayersTestTwo {
	
	GamePanels gamePanel;
	Players players;
	Continent continent;
	Territory territory;
	BoardData boardData;
	ArmiesSelection armiesSelection;
//	File directory = new File("tournamentMaps/");
//	File[] mapFilePath;
//	ArrayList<String> playerPlaying;
//	Map<String,String> playerType = new HashMap<>();
	
	/**
	 * Run before each test.
	 */
	@BeforeEach
	public void beforeEachTest() {
		players = new Players();
		continent = new Continent();
		territory = new Territory();
//		playerPlaying = new ArrayList<>();
//		playerPlaying.add("manan");
//		playerPlaying.add("khyati");
//		players.setPlayerList(playerPlaying);
//		
//		players.setPlayerPlaying(playerPlaying);
//		players.setPlayerType(playerType);
//		playerType.put("manan", "AGGRESSIVE");
//		playerType.put("khyati", "CHEATER");
//		players.setPlayerType(playerType);
//		 
//		gamePanel = new GamePanels();
//		mapFilePath = directory.listFiles();
//		gamePanel.setMapFilePath(mapFilePath);
		gamePanel.setTournamentModeOn(true);
		
	}
	
	/**
	 * This method test the tournament mode.
	 */
	@Test
	public void testTournamnentMode() {
//		System.out.println(mapFilePath);
//		gamePanel.setPlayers(players);
//		gamePanel.startPlayingGame();
//		gamePanel.getTournamentResult();
//		System.out.println(gamePanel.getTournamentResult());
		
	}
}
