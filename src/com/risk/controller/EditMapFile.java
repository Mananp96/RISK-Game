package com.risk.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map.Entry;
import com.risk.exception.InvalidMapException;
import com.risk.models.Continent;
import com.risk.models.Territory;
import com.risk.validate.MapValidator;

/**
 * This Class is used to edit map which are loaded by user
 * @author Himen Sidhpura
 *
 */
public class EditMapFile {
    /**
     * @param filePath Store path of file loaded by user
     * @param continentData Store Continent Data read from file
     * @param territoryData Store Territory Data read from file
     */
    String filePath;
    Territory territory;
    Continent continent;
    /**
     * Default Constructor which one parameter, filePath
     * @param filePath path of file loaded by user
     */
    public EditMapFile(String filePath) {
	super();
	this.filePath = filePath;
    }
    
    /**
     * This method used to generate Map Data and Valid File content
     * @return true if file data is validated corrected Otherwise return false
     */
    public boolean generateData() {
	BoardData boardData = new BoardData(filePath);
	boolean isMapValid = boardData.generateBoardData();
	if(isMapValid) {
	    continent = boardData.continentObject;
	    territory = boardData.territoryObject;
	}
	return isMapValid;
    }
    
    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    /**
     * 
     * @param continent Continent Model Object
     * @param territory Territory Model Object
     * @return true if content edit is validated according to Risk Map Otherwise return false;
     * @throws InvalidMapException throws exception when map is invalid
     */
    public boolean saveEditMap(Continent continent,Territory territory) throws InvalidMapException {
	this.continent = continent;
	this.territory = territory;
	for(int i=0;i<territory.getTerritoryList().size();i++ ) {
	    territory.addNumberOfTerritory(territory.getTerritoryList().get(i),i);
	}
	MapValidator mapValidator = new MapValidator(continent, territory);
	boolean flag = mapValidator.validateMap();
	if(flag) {
	    createFile();
	}
	return mapValidator.validateMap();
    }
    
    public void createFile() {
	try {
	    System.out.println("File Creation Started");
	    String defaultMapTag = "[Map]\n"+
			"author=Unknown\n"+
			"warn=yes\n"+
			"image=previous.bmp\n"+
			"wrap=no\n";
	    StringBuilder continentStr= new StringBuilder();
	    StringBuilder territoryStr= new StringBuilder();
	    continentStr.append("[Continents]\n");
	    territoryStr.append("[Territories]\n");
	    File file = new File("previous.map"); 
	    if(file.exists()) {
		System.out.println("Exist file deletd "+ file.delete());
	    }
	    boolean isFileCreated = file.createNewFile();
	    if(isFileCreated) {
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		for(Entry<String, Integer> entry : continent.getContinentValue().entrySet() ) {
		    continentStr.append(entry.getKey()+"="+entry.getValue()+"\n");
		}
		for(Entry<String,ArrayList<String>> entry : territory.getAdjacentTerritory().entrySet()) {
		    territoryStr.append(entry.getKey()+",0,0,"+territory.getTerritoryCont().get(entry.getKey()));
		    for(int i=0; i< entry.getValue().size();i++) {
			territoryStr.append(","+entry.getValue().get(i));
		    }
		    territoryStr.append("\n");
		}
		bufferedWriter.write(defaultMapTag+""+continentStr+""+territoryStr);
		bufferedWriter.flush();
		bufferedWriter.close();
		System.out.println("File Created");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    System.out.println("Exception is thrown while creating file : "+e);
	}
	

    }
    /**
     * 
     * @return filePath path of file 
     */
    public String getFilePath() {
        return filePath;
    }
    /**
     * 
     * @param filePath path of file 
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
