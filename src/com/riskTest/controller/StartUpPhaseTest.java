package com.riskTest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.risk.controller.StartUpPhase;
import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;

public class StartUpPhaseTest {

	Continent continent;
	Players players;
	Territory territory;
	StartUpPhase startUpPhase;
	
	ArrayList<String> playerList;
	ArrayList<String> territoryList;
	Map<String, Integer> territoryArmy;
	String NorthernAfrica = "Northern Africa";
	String Morroco = "Morroco";
	String Algeria = "Algeria";
	String Tunisia = "Tunisia";
	
	String SouthernAfrica = "Southern Africa";
	String WesternSahara = "Western Sahara";
	String Mauritania = "Mauritania";
	String Senegal = "Senegal";
	
	String WesternAfrica = "Western Africa";
	String Niger = "Niger";
	String Nigeria = "Nigeria";
	String chad = "chad";
			
	
	@BeforeEach
	public void beforeEachTest() {
		continent = new Continent();
		territory = new Territory();
		players = new Players();
		playerList = new ArrayList<>();
		territoryList = new ArrayList<>();
		territoryArmy = new HashMap<String, Integer>();
		
		playerList.add("Manan");
		playerList.add("Prince");
		playerList.add("Shalin");
		playerList.add("Khyati");
		players.setPlayerList(playerList);
		territory.addTerritory(Algeria);
		territoryList.add(Algeria);
		territory.addTerritoryCont(Algeria, NorthernAfrica);
		territory.addTerritory(Mauritania);
		territoryList.add(Mauritania);
		territory.addTerritoryCont(Mauritania, NorthernAfrica);
		territory.addTerritory(Morroco);
		territoryList.add(Morroco);
		territory.addTerritoryCont(Morroco, NorthernAfrica);
		territory.addTerritory(Niger);
		territoryList.add(Niger);
		territory.addTerritoryCont(Niger, SouthernAfrica);
		territory.addTerritory(Nigeria);
		territoryList.add(Nigeria);
		territory.addTerritoryCont(Nigeria, SouthernAfrica);
		territory.addTerritory(Senegal);
		territoryList.add(Senegal);
		territory.addTerritoryCont(Senegal, SouthernAfrica);
		territory.addTerritory(Tunisia);
		territoryList.add(Tunisia);
		territory.addTerritoryCont(Tunisia, WesternAfrica);
		territory.addTerritory(WesternSahara);
		territoryList.add(WesternSahara);
		territory.addTerritoryCont(WesternSahara, WesternAfrica);
		startUpPhase = new StartUpPhase(players, continent, territory);
		
		for(int i=0;i<territoryList.size();i++) {
			territoryArmy.put(territoryList.get(i), 15);
		}
		
	}
	
	@Test
	public void testInitialStartUpPhase() {
		startUpPhase.initialStartUpPhase();
		assertEquals(territoryArmy, territory.getTerritoryArmy());
	}
}
