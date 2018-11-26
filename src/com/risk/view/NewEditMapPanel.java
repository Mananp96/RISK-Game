package com.risk.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.lang3.StringUtils;

import com.risk.controller.EditMapFile;
import com.risk.exception.InvalidMapException;
import com.risk.models.Continent;
import com.risk.models.Territory;


/**
 * This Class used to display Panel of Edit Map
 * This class is also used to edit .Map file selected by user
 * @author Himen Sidhpura
 *
 */
public class NewEditMapPanel implements ActionListener{

    private JRadioButton mapOptA;
    private JLabel fetchFileDataError;
    private GridBagConstraints c;
    private String backBtnName = "backBtn";
    String existingMapFilePath;
    boolean randomMap;

    private JComboBox<String> editContinentList;
    private JComboBox<String> editTerritoryList;
    private JComboBox<String> allContinentList;
    private JComboBox<String> allTerritoryList;
    private JComboBox<String> allAdjTerritoryList;
    private JComboBox<String> addTerrContinentList;
    private JComboBox<String> deleteContinentList;
    private JComboBox<String> deleteTerritoryList;
    private JComboBox<String> deleteAdjTerrtList;
    private JComboBox<String> addAdjTerritoryListA;
    private JComboBox<String> addAdjTerritoryListB;

    private JTextField addContinentField;
    private JTextField addContinentValField;
    private JTextField addTerritoryField;
    private JTextField editContinentField;
    private JTextField editContinentValue;
    private JTextField editTerritoryField;

    private JButton backBtn;
    private JButton saveMapBtn;
    private JButton addContinentBtn;
    private JButton addTerritoryBtn;
    private JButton addAdjTerritoryBtn;
    private JButton editContinentBtn;
    private JButton editTerritoryBtn;
    private JButton deleteContinentBtn;
    private JButton deleteTerritoryBtn;
    private JButton deleteAdjTerrBtn;
    private Territory territory;
    private Continent continent;
    private boolean validFlag;
    JFrame frame;
    boolean editMapFileFlag;
    boolean isMapSelected = false;

    /**
     * This method UI of New and  Edit Existing Map
     * @param frame Current Frame
     * @param editMapFileFlag Used too identify whether player want to create new map or edit existing map
     * @return JPanel which is added in current frame
     */
    public JPanel createMapPanel(JFrame frame,boolean editMapFileFlag){
	this.frame = frame;
	this.editMapFileFlag = editMapFileFlag;
	if(!editMapFileFlag) {
	    territory = new Territory();
	    continent = new Continent();
	}
	JPanel existingMapPanel = new JPanel();
	GridBagLayout exisitngMapLayout = new GridBagLayout();
	existingMapPanel.setLayout(exisitngMapLayout);
	existingMapPanel.setSize(new Dimension(400,250));
	mapOptA = new JRadioButton("Choose Your Own Map");
	mapOptA.setActionCommand("Own Map");
	JFileChooser chooseMap = new JFileChooser("D:");
	FileNameExtensionFilter filter = new FileNameExtensionFilter("MAP FILES", "map", "map");
	chooseMap.setFileFilter(filter);
	chooseMap.addChoosableFileFilter(new FileFilter() {
	    public String getDescription() {
		return "MAP Documents (*.map)";
	    }
	    public boolean accept(File f) {
		if (f.isDirectory()) {
		    return true;
		} else {
		    return f.getName().toLowerCase().endsWith(".map");
		}
	    }
	});
	mapOptA.addItemListener(new ItemListener() {

	    @Override
	    public void itemStateChanged(ItemEvent e) {
		if(mapOptA.isSelected() && chooseMap.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		    existingMapFilePath = chooseMap.getSelectedFile().getPath();
		    generateEditMapData(existingMapFilePath);
		    randomMap = false;
		    isMapSelected = true;
		}
	    }
	});
	fetchFileDataError = new JLabel("There is problem with file");
	if(!editMapFileFlag) {
	    mapOptA.setVisible(false);
	    fetchFileDataError.setVisible(false);
	    chooseMap.setVisible(false);
	}
	allContinentList = new JComboBox<>();
	allContinentList.setActionCommand("allContinentList");
	allContinentList.addActionListener(this);
	allTerritoryList = new JComboBox<>();
	allTerritoryList.setActionCommand("allTerritoryList");
	allTerritoryList.addActionListener(this);
	allAdjTerritoryList = new JComboBox<>();

	addContinentField = new JTextField();
	addContinentValField = new JTextField();
	addContinentBtn = new JButton("Add Continent");
	addContinentBtn.setActionCommand("Add Continent");
	addContinentBtn.addActionListener(this);

	addTerrContinentList = new JComboBox<>();
	addTerritoryField = new JTextField();
	addTerritoryBtn = new JButton("Add Terrritory");
	addTerritoryBtn.setActionCommand("Add Territory");
	addTerritoryBtn.addActionListener(this);

	addAdjTerritoryListA = new JComboBox<>();
	addAdjTerritoryListB = new JComboBox<>();
	addAdjTerritoryBtn = new JButton("Add Adjacent");
	addAdjTerritoryBtn.setActionCommand("Add Adjacent");
	addAdjTerritoryBtn.addActionListener(this);

	editContinentList = new JComboBox<>();
	editContinentList.setActionCommand("editContinentList");
	editContinentList.addActionListener(this);
	editContinentField = new JTextField();
	editContinentValue = new JTextField();
	editContinentBtn = new JButton("Update Continent");
	editContinentBtn.setActionCommand("Update Continent");
	editContinentBtn.addActionListener(this);
	editTerritoryList = new JComboBox<>();
	editTerritoryList.setActionCommand("editTerritoryList");
	editTerritoryList.addActionListener(this);
	editTerritoryField = new JTextField();
	editTerritoryBtn = new JButton("Update Territory");
	editTerritoryBtn.setActionCommand("Update Territory");
	editTerritoryBtn.addActionListener(this);


	deleteContinentList = new JComboBox<>();
	deleteContinentList.setActionCommand("deleteContinentList");
	deleteContinentList.addActionListener(this);
	deleteTerritoryList = new JComboBox<>();
	deleteTerritoryList.setActionCommand("deleteTerritoryList");
	deleteTerritoryList.addActionListener(this);
	deleteAdjTerrtList = new JComboBox<>();
	deleteContinentBtn = new JButton("Delete Continent");
	deleteTerritoryBtn = new JButton("Delete Territory");
	deleteAdjTerrBtn = new JButton("Delete Adjacent");


	deleteContinentBtn.setActionCommand("Delete Continent");
	deleteContinentBtn.addActionListener(this);
	deleteTerritoryBtn.setActionCommand("Delete Territory");
	deleteTerritoryBtn.addActionListener(this);
	deleteAdjTerrBtn.setActionCommand("Delete Adjacent");
	deleteAdjTerrBtn.addActionListener(this);
	if(editMapFileFlag) {
	    saveMapBtn = new JButton("Update Map Data");
	    saveMapBtn.addActionListener(this);
	    saveMapBtn.setActionCommand("Update Map Data");    
	} else {
	    saveMapBtn = new JButton("Create Map");
	    saveMapBtn.addActionListener(this);
	    saveMapBtn.setActionCommand("Create Map Data");    
	}

	backBtn = new JButton("Exit");
	backBtn.addActionListener(this);
	backBtn.setActionCommand(backBtnName);

	existingMapPanel.add(mapOptA, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 0, 0));
	existingMapPanel.add(fetchFileDataError, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 1, 0));
	existingMapPanel.add(new JLabel("List of Continent"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 0, 1));
	existingMapPanel.add(new JLabel("All territory"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 1, 1));
	existingMapPanel.add(new JLabel("Adjacent Territory"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 2, 1));
	existingMapPanel.add(allContinentList, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 0, 2));		        	
	existingMapPanel.add(allTerritoryList, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 1, 2));		
	existingMapPanel.add(allAdjTerritoryList, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 2, 2));		

	existingMapPanel.add(new JLabel("Add Continent"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 0, 3));
	existingMapPanel.add(new JLabel("Continent Name"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, 0.5, 0.5, 0, 4));
	existingMapPanel.add(addContinentField, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 1, 4));
	existingMapPanel.add(new JLabel("Value"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, 0.5, 0.5, 2, 4));
	existingMapPanel.add(addContinentValField, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 3, 4));
	existingMapPanel.add(addContinentBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 4, 4));

	existingMapPanel.add(new JLabel("Add Territory"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 0, 5));
	existingMapPanel.add(new JLabel("Continent List"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, 0.5, 0.5, 0, 6));
	existingMapPanel.add(addTerrContinentList, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 1, 6));
	existingMapPanel.add(new JLabel("Territory Name"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, 0.5, 0.5, 2, 6));
	existingMapPanel.add(addTerritoryField, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 3, 6));
	existingMapPanel.add(addTerritoryBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 4, 6));

	existingMapPanel.add(new JLabel("Add Adjacent"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 0, 7));
	existingMapPanel.add(new JLabel("Territory"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, 0.5, 0.5, 0, 8));
	existingMapPanel.add(addAdjTerritoryListA, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 1, 8));
	existingMapPanel.add(new JLabel("and It's Adjacent Territory"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, 0.5, 0.5, 2, 8));
	existingMapPanel.add(addAdjTerritoryListB, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 3, 8));
	existingMapPanel.add(addAdjTerritoryBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 4, 8));

	existingMapPanel.add(new JLabel("Edit Continent"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 9));
	existingMapPanel.add(editContinentList, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 0, 10));
	existingMapPanel.add(editContinentField, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 1, 10));
	existingMapPanel.add(editContinentValue, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 2, 10));
	existingMapPanel.add(editContinentBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 3, 10));

	existingMapPanel.add(new JLabel("Edit Territory"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 4, 9));
	existingMapPanel.add(editTerritoryList, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 4, 10));
	existingMapPanel.add(editTerritoryField, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 5, 10));        	
	existingMapPanel.add(editTerritoryBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 6, 10));

	existingMapPanel.add(new JLabel("List of Continent"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, 0.5, 0.5, 0, 11));
	existingMapPanel.add(new JLabel("All territory"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 2, 11));
	existingMapPanel.add(new JLabel("Adjacent Territory"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 4, 11));
	existingMapPanel.add(deleteContinentList, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 0, 12));
	existingMapPanel.add(deleteContinentBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, 0.5, 0.5, 1, 12));
	existingMapPanel.add(deleteTerritoryList, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 2, 12));
	existingMapPanel.add(deleteTerritoryBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, 0.5, 0.5, 3, 12));		
	existingMapPanel.add(deleteAdjTerrtList, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 4, 12));
	existingMapPanel.add(deleteAdjTerrBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, 0.5, 0.5, 5, 12));
	existingMapPanel.add(saveMapBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, 0.5, 0.5, 2, 13));
	existingMapPanel.add(backBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, 0.5, 0.5, 3, 13));

	return existingMapPanel;
    }
    /**
     * This method is used to generate map data which is selected for editing
     * @param filePath path of input file use for editing
     */
    protected void generateEditMapData(String filePath) {
	if(StringUtils.isNotEmpty(filePath)) {
	    EditMapFile editMapFile = new EditMapFile(filePath); 
	    if(editMapFile.generateData()) {
		fetchFileDataError.setText("File Content Data is validated and Ready to Edit");
		territory = editMapFile.getTerritory();
		continent = editMapFile.getContinent();

		updateAllContinentList();
		updateAddTerrContinentList();
		updateEditContinentList();
		updateAddAdjTerritoryList();
		updateEditTerritoryList();
		updateDeleteContinentList();
	    }
	}
    }	

    /**
     * The GridBagConstraints is used specifies constraints for components that are laid out using the GridBagLayout class.
     * Initialize GridBagConstraint object with all of its fields set to their default value.
     * @param insets The initial insets value
     * @param fill The initial fill value
     * @param anchor The initial anchor value.
     * @param wx The initial weightx value.
     * @param wy The initial weighty value.
     * @param x The initial gridx value.
     * @param y The initial gridy value.
     * @return GridBagConstraints object with all of its fields set to the passed-in arguments
     */
    public GridBagConstraints setGridBagConstraints(Insets insets,int fill,int anchor, double wx, double wy, int x, int y) {
	c = new GridBagConstraints();
	c.fill = fill;
	c.anchor = anchor;
	c.insets = insets;
	c.weightx = wx;
	c.weighty = wy;
	c.gridx = x;
	c.gridy = y;
	return c;
    }
    /**
     * The GridBagConstraints is used specifies constraints for components that are laid out using the GridBagLayout class.
     * Initialize GridBagConstraint object with all of its fields set to their default value.
     * @param insets The initial insets value.
     * @param fill The initial fill value.
     * @param wx The initial weightx value.
     * @param wy The initial weighty value.
     * @param x The initial gridx value.
     * @param y The initial gridy value.
     * @return GridBagConstraints object with all of its fields set to the passed-in arguments
     */
    public GridBagConstraints setGridBagConstraints(Insets insets,int fill, double wx, double wy, int x, int y) {
	c = new GridBagConstraints();
	c.fill = fill;
	c.insets = insets;
	c.weightx = wx;
	c.weighty = wy;
	c.gridx = x;
	c.gridy = y;
	return c;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
	String actionName = arg0.getActionCommand();
	if(actionName.equalsIgnoreCase(backBtnName)){
	    GamePanels gamePanels = new GamePanels();
	    frame.setContentPane(gamePanels.mainMenu(frame));
	    frame.invalidate();
	    frame.validate();
	} else if(actionName.equalsIgnoreCase("Add Continent")){
	    addContinent(addContinentField.getText(),addContinentValField.getText());
	} else if(actionName.equalsIgnoreCase("Add Territory")){
	    addTerritory(addTerrContinentList.getItemAt(addTerrContinentList.getSelectedIndex()),addTerritoryField.getText());
	} else if(actionName.equalsIgnoreCase("Add Adjacent")){
	    addAdjacentTerritory(addAdjTerritoryListA.getItemAt(addAdjTerritoryListA.getSelectedIndex()),addAdjTerritoryListB.getItemAt(addAdjTerritoryListB.getSelectedIndex()));
	} else if(actionName.equalsIgnoreCase("Update Continent")){
	    updateContinent(editContinentList.getItemAt(editContinentList.getSelectedIndex()),editContinentField.getText(),editContinentValue.getText());		 		    
	} else if(actionName.equalsIgnoreCase("Update Territory")){
	    updateTerritory(editTerritoryList.getItemAt(editTerritoryList.getSelectedIndex()),editTerritoryField.getText());
	} else if(actionName.equalsIgnoreCase("Delete Continent")){
	    deleteContinent(deleteContinentList.getItemAt(deleteContinentList.getSelectedIndex()));
	} else if(actionName.equalsIgnoreCase("Delete Territory")){
	    deleteTerritory(deleteTerritoryList.getItemAt(deleteAdjTerrtList.getSelectedIndex()));
	} else if(actionName.equalsIgnoreCase("Delete Adjacent")){
	    deleteAdjacentTerritory(deleteAdjTerrtList.getItemAt(deleteAdjTerrtList.getSelectedIndex()));
	} else if(actionName.equalsIgnoreCase("Update Map Data")){
	    EditMapFile editMapFile = new EditMapFile("previous.map");
	    try {
		if(isMapSelected) {
		    validFlag = editMapFile.saveEditMap(continent, territory);
		    if(validFlag) {
			displaySuccessDialog();
		    }
		} else {
		    JOptionPane.showMessageDialog(null, "Please Select for Editing");
		}

	    } catch (InvalidMapException e) {
		e.printStackTrace();
	    }
	}  else if(actionName.equalsIgnoreCase("Create Map Data")) {
	    Random random = new Random();
	    String tempFileName = "mapFile"+ random.nextInt(1000)+".map";
	    EditMapFile editMapFile = new EditMapFile(tempFileName);
	    try {
		validFlag = editMapFile.saveEditMap(continent, territory);
		if(validFlag) {
		    JOptionPane.showMessageDialog(null, "File Create with name : "+tempFileName,"Content Validated",JOptionPane.INFORMATION_MESSAGE);			    
		} else {
		    displayErrorDialog();
		}


	    } catch (InvalidMapException e) {
		e.printStackTrace();
	    } 

	} else if(actionName.equalsIgnoreCase("allContinentList")) {
	    updateAllTerritoryList();
	} else if(actionName.equalsIgnoreCase("allTerritoryList")) {
	    updateAllAdjTerritoryList();
	} else if(actionName.equalsIgnoreCase("editContinentList") && editContinentList.getSelectedIndex() != -1) {
	    editContinentField.setText(editContinentList.getItemAt(editContinentList.getSelectedIndex()));
	    editContinentValue.setText(Integer.toString(continent.getContinentValue().get(editContinentList.getItemAt(editContinentList.getSelectedIndex()))));
	} else if(actionName.equalsIgnoreCase("editTerritoryList") && editTerritoryList.getSelectedIndex() != -1) {
	    editTerritoryField.setText(editTerritoryList.getItemAt(editTerritoryList.getSelectedIndex()));
	} else if(actionName.equalsIgnoreCase("deleteContinentList")) {
	    generateDeleteTerritoryList(deleteContinentList.getItemAt(deleteContinentList.getSelectedIndex()));
	} else if(actionName.equalsIgnoreCase("deleteTerritoryList")) {
	    generateDeleteAdjTerritoryList(deleteTerritoryList.getItemAt(deleteTerritoryList.getSelectedIndex()));
	}

    }

    /**
     *  This method is used to generate adjacent territory drop down in Delete Section
     * @param itemAt Current Territory whose adjacent territory need to populate
     */
    private void generateDeleteAdjTerritoryList(String itemAt) {
	deleteAdjTerrtList.removeAllItems();
	for(Entry<String, ArrayList<String>> entry : territory.getAdjacentTerritory().entrySet()) {
	    if(entry.getKey().equalsIgnoreCase(itemAt)) {
		for(int i = 0;i< entry.getValue().size();i++) {
		    deleteAdjTerrtList.addItem(entry.getValue().get(i));
		}
	    }
	}
    }
    /**
     *  This method is used to generate territory drop down of particular continent in Delete Section
     * @param itemAt Current Continent whose territory need to populate
     */
    private void generateDeleteTerritoryList(String itemAt) {
	deleteTerritoryList.removeAllItems();
	for(Entry<String, String> entry : territory.getTerritoryCont().entrySet()) {
	    if(entry.getValue().equalsIgnoreCase(itemAt)) {
		deleteTerritoryList.addItem(entry.getKey());
	    }
	}
    }
    /**
     *  This method is used to generate territory drop down  in Add Adjacent Section
     */
    private void updateAddAdjTerritoryList() {
	addAdjTerritoryListA.removeAllItems();
	addAdjTerritoryListB.removeAllItems();
	for(Entry<String, String> entry : territory.getTerritoryCont().entrySet()) {
	    addAdjTerritoryListA.addItem(entry.getKey());
	    addAdjTerritoryListB.addItem(entry.getKey());

	}	    
    }
    /**
     *  This method is used to generate territory drop down of particular continent in Display All Detail Section
     */
    private void updateAllAdjTerritoryList() {
	allAdjTerritoryList.removeAllItems();
	try {
	    if(allTerritoryList.getSelectedIndex() != -1 ) {
		for(int i=0;i< territory.getAdjacentTerritory().get(allTerritoryList.getItemAt(allTerritoryList.getSelectedIndex())).size();i++){
		    if(StringUtils.isNotEmpty(territory.getAdjacentTerritory().get(allTerritoryList.getItemAt(allTerritoryList.getSelectedIndex())).get(i)))
			allAdjTerritoryList.addItem(territory.getAdjacentTerritory().get(allTerritoryList.getItemAt(allTerritoryList.getSelectedIndex())).get(i));
		}	
	    }
	}
	catch(Exception e) {
	    System.out.println("No Territories ");
	}


    }
    /**
     *  This method is used to delete adjacent territory  of particular territory in Delete Section
     * @param adjacent Current adjacent territory which need to delete
     */
    private void deleteAdjacentTerritory(String adjacent) {
	if(deleteAdjTerrtList.getSelectedIndex() != -1) {
	    territory.getAdjacentTerritory().get(deleteTerritoryList.getItemAt(deleteTerritoryList.getSelectedIndex())).remove(adjacent);
	    displaySuccessDeleteDialog();
	    updateAllContinentList();
	    updateAddTerrContinentList();
	    updateEditContinentList();
	    updateAddAdjTerritoryList();
	    updateEditTerritoryList();
	    updateDeleteContinentList();
	}
    }

    /*
     * This method is used to delete territory  and it's all adjacent territory of particular continent in Delete Section
     * @param territoryStr Territory which need to delete
     */
    private void deleteTerritory(String territoryStr) {
	if(deleteTerritoryList.getSelectedIndex() != -1) {
	    territory.getTerritoryCont().remove(territoryStr);
	    territory.getTerritoryList().remove(territoryStr);
	    territory.getAdjacentTerritory().remove(territoryStr);
	    for(Entry<String, ArrayList<String>> entry : continent.getContinentTerritory().entrySet()) {
		if(entry.getValue().contains(territoryStr))
		    entry.getValue().remove(territoryStr);
	    }
	    for(Entry<String, ArrayList<String>> entry : territory.getAdjacentTerritory().entrySet()) {
		ArrayList<String> tempArray = new ArrayList<>();
		for(int i=0;i<entry.getValue().size();i++) {
		    if(!entry.getValue().get(i).equalsIgnoreCase(territoryStr)) {
			tempArray.add(entry.getValue().get(i));
		    }   
		}
		entry.setValue(tempArray);
	    }
	    displaySuccessDeleteDialog();
	    updateAllContinentList();
	    updateAddTerrContinentList();
	    updateEditContinentList();
	    updateAddAdjTerritoryList();
	    updateEditTerritoryList();
	    updateDeleteContinentList();
	}
    }
    /**
     * This method is used to delete continent and all it's territory in Delete Section
     * @param continentStr Continent which need to delete
     */
    private void deleteContinent(String continentStr) {
	try {
	    if(deleteContinentList.getSelectedIndex() != -1) {
		continent.getContinentValue().remove(continentStr);
		continent.getContinentTerritory().remove(continentStr);
		Map<String, String> tempMap =territory.getTerritoryCont();
		Set<String> set = new HashSet<> ();
		for(Entry<String, String> entry : tempMap.entrySet()) {
		    if(entry.getValue().equalsIgnoreCase(continentStr)) {
			territory.getAdjacentTerritory().remove(entry.getKey());
			set.add(entry.getKey());
		    }
		}
		territory.getTerritoryCont().keySet().removeAll(set);
		displaySuccessDeleteDialog();
		updateAllContinentList();
		updateAddTerrContinentList();
		updateEditContinentList();
		updateAddAdjTerritoryList();
		updateEditTerritoryList();
		updateDeleteContinentList();

	    }
	}
	catch(Exception e) {
	    System.out.println("Exception ");
	}

    }
    /**
     * This method is used to update  territory name with new name  Edit Territory Section Section
     * @param oldTerritory
     * @param newTerritory
     */
    private void updateTerritory(String oldTerritory, String newTerritory) {
	if(StringUtils.isNotEmpty(oldTerritory) && StringUtils.isNotEmpty(newTerritory)) {
	    territory.getTerritoryCont().put(newTerritory,territory.getTerritoryCont().remove(oldTerritory));
	    territory.getAdjacentTerritory().put(newTerritory, territory.getAdjacentTerritory().remove(oldTerritory));
	    ArrayList<String> temp =new ArrayList<>();
	    for(int i = 0;i < territory.getTerritoryList().size();i++ ) {
		if(territory.getTerritoryList().get(i).equalsIgnoreCase(oldTerritory)) {
		    temp.add(newTerritory);
		} else {
		    temp.add(territory.getTerritoryList().get(i));    
		}
	    }
	    territory.setTerritoryList(temp);
	    for(Entry<String, ArrayList<String>> entry : continent.getContinentTerritory().entrySet()) {
		if(entry.getValue().contains(oldTerritory)) {
		    entry.getValue().remove(oldTerritory);
		    entry.getValue().add(newTerritory);
		}
	    }
	    for(Entry<String, ArrayList<String>> entry : territory.getAdjacentTerritory().entrySet()) {
		ArrayList<String> tempArray = new ArrayList<>();
		for(int i=0;i<entry.getValue().size();i++) {
		    if(entry.getValue().get(i).equalsIgnoreCase(oldTerritory)) {
			tempArray.add(newTerritory);
		    } else {
			tempArray.add(entry.getValue().get(i));
		    }   
		}
		entry.setValue(tempArray);
	    }
	    displaySuccessDialog();
	    updateAllContinentList();
	    updateAddTerrContinentList();
	    updateEditContinentList();
	    updateAddAdjTerritoryList();
	    updateEditTerritoryList();
	    updateDeleteContinentList();	    }
    }
    /**
     * This method is used to update Continent Name and it's value in Edit Continent Section
     * @param oldContinent Old Continent
     * @param newContinent New Continent 
     * @param value Value of Continent
     */
    private void updateContinent(String oldContinent, String newContinent, String value) {
	if(StringUtils.isNotEmpty(oldContinent) && StringUtils.isNotEmpty(newContinent) && StringUtils.isNotEmpty(value) && StringUtils.isNumeric(value) && Integer.parseInt(value) > 0) {
	    continent.updateContinentValue(oldContinent, newContinent, Integer.parseInt(value));
	    territory.updateTerritoryContinent(oldContinent, newContinent);
	    ArrayList<String> tempArray = continent.getContinentTerritory().get(oldContinent);
	    continent.getContinentTerritory().remove(oldContinent);
	    continent.getContinentTerritory().put(newContinent,tempArray);
	    displaySuccessDialog();
	    updateAllContinentList();
	    updateAddTerrContinentList();
	    updateEditContinentList();
	    updateAddAdjTerritoryList();
	    updateEditTerritoryList();
	    updateDeleteContinentList();
	} else {
	    displayErrorDialog();
	}

    }
    /**
     * This method is used to add adjacent territory  to  particular territory in Add Adjacent Section
     * @param territoryStr Territory whose adjacent territory you need to Add
     * @param adjacent Adjacent Territory
     */
    private void addAdjacentTerritory( String territoryStr, String adjacent) {
	if( StringUtils.isNotEmpty(territoryStr) && StringUtils.isNotEmpty(adjacent) && !territoryStr.equalsIgnoreCase(adjacent)) {
	    if(territory.getAdjacentTerritory().containsKey(territoryStr) && !territory.getAdjacentTerritory().get(territoryStr).contains(adjacent) && territory.getAdjacentTerritory().get(territoryStr).size() < 10 ) {
		territory.addAdjacentTerritory(territoryStr, adjacent);
		territory.addAdjacentTerritory(adjacent, territoryStr);
		displaySuccessDialog();
		updateAllContinentList();
		updateEditContinentList();
		updateAddTerrContinentList();
		updateAddAdjTerritoryList();
		updateEditTerritoryList();
		updateDeleteContinentList(); 
	    } else if(!territory.getAdjacentTerritory().containsKey(territoryStr)){
		territory.addAdjacentTerritory(territoryStr, adjacent);
		territory.addAdjacentTerritory(adjacent, territoryStr);
		displaySuccessDialog();
		updateAllContinentList();
		updateEditContinentList();
		updateAddTerrContinentList();
		updateAddAdjTerritoryList();
		updateEditTerritoryList();
		updateDeleteContinentList();    
	    } else {
		displayErrorDialog();
	    }
	} else {
	    duplicateErrorDialog();
	}

    }
    /**
     * This method is used to add territory  to  particular continent in Add Territory Section Section
     * @param continentStr Continent Name
     * @param territoryStr Territory Name
     */
    private void addTerritory(String continentStr, String territoryStr) {
	if(StringUtils.isNotEmpty(continentStr) && StringUtils.isNotEmpty(territoryStr) && !territory.getTerritoryCont().containsKey(territoryStr)) {
	    territory.addTerritoryCont(territoryStr, continentStr);
	    territory.addTerritory(territoryStr);
	    continent.addContinentTerritory(continentStr, territoryStr);
	    displaySuccessDialog();
	    addContinentField.setText("");
	    addContinentValField.setText("");
	    addTerritoryField.setText("");
	    updateAllContinentList();
	    updateAddTerrContinentList();
	    updateEditContinentList();
	    updateAddAdjTerritoryList();
	    updateEditTerritoryList();
	    updateDeleteContinentList();
	} else {
	    displayErrorDialog();
	}
    }
    /**
     * This method is used to add Continent with particular value in Add Continent Section
     * @param continentStr Continent Name
     * @param value Value of Continent
     */
    public void addContinent(String continentStr, String value) {
	if(StringUtils.isNotEmpty(continentStr) && StringUtils.isNotEmpty(value) && StringUtils.isNumeric(value) && Integer.parseInt(value) > 0) {
	    if(!continent.getContinentValue().containsKey(continentStr)) {
		continent.setContinentValue(continentStr, Integer.parseInt(value));
		displaySuccessDialog();
		addContinentField.setText("");
		addContinentValField.setText("");
		addTerritoryField.setText("");
		updateAllContinentList();
		updateAddTerrContinentList();
		updateEditContinentList();
		updateAddAdjTerritoryList();
		updateEditTerritoryList();
		updateDeleteContinentList();
	    } else {
		duplicateErrorDialog();
	    }

	} else {
	    displayErrorDialog();
	}
    }
    /**
     * provide list of territory of particular continent 
     */
    private void updateAllTerritoryList() {
	allTerritoryList.removeAllItems();
	for(Entry<String, String> entry : territory.getTerritoryCont().entrySet()) {
	    if(entry.getValue().equalsIgnoreCase(allContinentList.getItemAt(allContinentList.getSelectedIndex()))) {
		allTerritoryList.addItem(entry.getKey());
	    }
	}
    }
    /**
     * Update all Continent List drop down
     */
    private void updateAllContinentList() {
	allContinentList.removeAllItems();
	for(Entry<String, Integer> entry : continent.getContinentValue().entrySet()) {
	    allContinentList.addItem(entry.getKey());
	}
    }
    /**
     * Update territory List drop down of Edit Territory Section
     */
    private void updateEditTerritoryList() {
	editTerritoryList.removeAllItems();
	for(Entry<String,String> entry : territory.getTerritoryCont().entrySet()) {
	    editTerritoryList.addItem(entry.getKey());
	}
    }
    /**
     * Update Continent List drop down of Edit Continent Section
     */
    private void updateEditContinentList() {
	editContinentList.removeAllItems();
	for(Entry<String, Integer> entry : continent.getContinentValue().entrySet()) {
	    editContinentList.addItem(entry.getKey());
	}	    
    }
    /**
     * Update Continent List drop down of Delete Continent Section
     */
    private void updateDeleteContinentList() {
	deleteContinentList.removeAllItems();
	for(Entry<String, Integer> entry : continent.getContinentValue().entrySet()) {
	    deleteContinentList.addItem(entry.getKey());
	}
    }
    /**
     * Update Continent List drop down of Add Adjacent Section
     */
    private void updateAddTerrContinentList() {
	addTerrContinentList.removeAllItems();
	for(Entry<String, Integer> entry : continent.getContinentValue().entrySet()) {
	    addTerrContinentList.addItem(entry.getKey());
	}	    
    }
    /**
     * Show Pop Up when Content is added or created
     */
    public void displaySuccessDialog() {
	JOptionPane.showMessageDialog(null, "Content Created Successfully", "Content Validated", JOptionPane.INFORMATION_MESSAGE);   
    }
    /**
     * Show Pop Up when Content is deleted
     */
    public void displaySuccessDeleteDialog() {
	JOptionPane.showMessageDialog(null, "Content Deleted Successfully", "Content Validated", JOptionPane.INFORMATION_MESSAGE);   
    }
    /**
     * Show Pop Up when content is invalid
     */
    public void displayErrorDialog(){
	JOptionPane.showMessageDialog(null, "Please Check data Again.", "Content Invalid", JOptionPane.ERROR_MESSAGE);   
    }
    /**
     * Show Pop Up when duplicate Content is found
     */
    public void duplicateErrorDialog(){
	JOptionPane.showMessageDialog(null, "Please Enter New Value. Values Already exist", "Content Same", JOptionPane.ERROR_MESSAGE);   
    }

}
