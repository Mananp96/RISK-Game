package com.risk.controllers;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.risk.exception.InvalidMapException;
import com.risk.validate.MapValidator;

/**
 *
 * @author s_vaisha
 *
 */

public class CreateMapFile {

	public CreateMapFile() {
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * @param mapData
	 *            = continent and territories data which user entered while creating
	 *            new map
	 */

	public boolean validateMap(String continentData, String territoriesData) {
		HashMap<String, Integer> continentValue = new HashMap<>(); // For storing continent -> Winning value
		// relationship

		String[] continentsList = continentData.split("\\n");
		for (String keyValue : continentsList) {
			if (keyValue.length() > 0) {
				String[] keyValueList = keyValue.split("=");
				if (keyValueList.length == 0) {
					System.out.println("Invalid format for continent and its winning value");
					return false;
				}
				try {
					String continentName = keyValueList[0];
					int numberOfTerritories = Integer.parseInt(keyValueList[1]);
					if (numberOfTerritories <= 0) {
						System.out.println(
								String.format("Wining value must be > 0, instead received winning value '%d' for '%s'",
										numberOfTerritories, continentName));
						return false;
					}
					continentValue.put(continentName, numberOfTerritories);
				} catch (NumberFormatException e) {
					System.out.println(String.format("Expected integer for winning value, instead got '%s' for '%s'\n",
							keyValue.split("=")[0], keyValue.split("=")[1]));
					return false;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		HashMap<String, ArrayList<String>> continentTerritory = new HashMap<>(); // For storing continent -> its
		// territories
		HashMap<String, ArrayList<String>> graphAdjancenyTerritory = new HashMap<>(); // (Graph) For storing territories
		// -> its adjacent territories

		String[] territoryContinentNeighbours = territoriesData.split("\\n");

		for (String territoryInfo : territoryContinentNeighbours) {
			// Ignore blank lines
			if (territoryInfo.length() > 0) {
				String[] territoryInfoList = territoryInfo.split(",");
				String territory = territoryInfoList[0];
				if (territoryInfoList.length == 1) {
					System.out.println("No value of continent and adjacent territories found for " + territory);
					return false;
				} else if (territoryInfoList.length == 2) {
					System.out.println("No value of adjacent territories found for  " + territory);
					return false;
				}
				String continent = territoryInfoList[1];
				if (!continentValue.containsKey(continent)) {
					System.out.println(String.format("No continent of name '%s' found", continent));
					return false;
				}

				// storing continent -> its territories
				if (continentTerritory.containsKey(continent)) {
					if (!continentTerritory.get(continent).contains(territory)) {
						ArrayList<String> values = continentTerritory.get(continent);
						values.add(territory);
						continentTerritory.put(continent, values);
					}

				} else {
					ArrayList<String> values = new ArrayList<>();
					values.add(territory);
					continentTerritory.put(continent, values);
				}

				// storing territories -> its adjacent territories
				graphAdjancenyTerritory.put(territory, new ArrayList<>(
						Arrays.asList(Arrays.copyOfRange(territoryInfoList, 2, territoryInfoList.length))));
			}
		}

		MapValidator mapValidator = new MapValidator(continentTerritory, continentValue, graphAdjancenyTerritory);
		try {
			if (mapValidator.validateMap()) {
				System.out.println("Valid Map: Write into file");
				return true;
				// write finalMapString into file
			} else {
				System.out.println("Invalid Map: ");
				return false;
			}
		} catch (InvalidMapException e) {
			System.out.println("Invalid Map: " + e);
		}
		return false;

		// Uncomment to see Continent - > territories
		// for (String key : continentTerritory.keySet()) {
		// System.out.print(key + " -> ");
		// for(String x : continentTerritory.get(key)) {
		// System.out.print(x + ",");
		// }
		// System.out.println("");
		// }

		// Uncomment to see Territory and its adjacent territories
		// for (String key : graphAdjancenyTerritory.keySet()) {
		// System.out.print(key + " -> ");
		// for(String x : graphAdjancenyTerritory.get(key)) {
		// System.out.print(x + ",");
		// }
		// System.out.println("");
		// }
	}

	public void createMap(String mapData) {

		BufferedWriter bw = null;
		FileWriter fw = null;
		String fileSeparator = System.getProperty("file.separator");

		try {

			String newMapFileName = "";
			long millis = System.currentTimeMillis();
			String datetime = new Date().toString();
			datetime = datetime.replace(" ", "");
			datetime = datetime.replace(":", "");
			newMapFileName = "Map_" + datetime + "_" + millis;

			File newMapFile = new File(newMapFileName + ".map");

			boolean fvar = newMapFile.createNewFile();

			fw = new FileWriter(newMapFile);
			bw = new BufferedWriter(fw);
			bw.write(mapData);

			System.out.println("Done");

			if (fvar) {
				System.out.println("File has been created successfully");
			} else {
				System.out.println("File already present at the specified location");
			}
		} catch (Exception e1) {
			System.out.println("Exception Occurred:");
			e1.printStackTrace();
		}

		finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
