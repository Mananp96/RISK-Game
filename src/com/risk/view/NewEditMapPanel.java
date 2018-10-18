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
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.lang3.StringUtils;

import com.risk.controller.EditMapFile;
import com.risk.models.Continent;
import com.risk.models.Territory;

public class NewEditMapPanel implements ActionListener {
    
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
        private JComboBox<String> addAdjContinentList;
        private JComboBox<String> addAdjContTerritoryList;
        private JComboBox<String> addAdjTerritoryList;
        
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

        public JPanel createMapPanel(){
            
        	JPanel existingMapPanel = new JPanel();
        	GridBagLayout exisitngMapLayout = new GridBagLayout();
        	existingMapPanel.setLayout(exisitngMapLayout);
        	existingMapPanel.setSize(new Dimension(400,250));
        	mapOptA = new JRadioButton("Choose Your Own Map");
        	mapOptA.setActionCommand("Own Map");
        	JFileChooser chooseMap = new JFileChooser("D:");
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
        			// TODO Auto-generated method stub
        			if(mapOptA.isSelected() && chooseMap.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        				existingMapFilePath = chooseMap.getSelectedFile().getPath();
        				generateEditMapData(existingMapFilePath);
        				randomMap = false;
        			}
        		}
        	});
        	fetchFileDataError = new JLabel("There is problem with file");
        	
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
        	
        	addAdjContinentList = new JComboBox<>();
        	addAdjContinentList.setActionCommand("addAdjContinentList");
        	addAdjContinentList.addActionListener(this);
        	addAdjContTerritoryList = new JComboBox<>();
        	addAdjContTerritoryList.setActionCommand("addAdjContTerritoryList");
        	addAdjContTerritoryList.addActionListener(this);
        	addAdjTerritoryList = new JComboBox<>();
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
        	editTerritoryField = new JTextField();
           	editTerritoryBtn = new JButton("Update Territory");
           	editTerritoryBtn.setActionCommand("Update Territory");
           	editTerritoryBtn.addActionListener(this);
        	
        	
           	deleteContinentList = new JComboBox<>();
           	deleteTerritoryList = new JComboBox<>();
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
        	
        	saveMapBtn = new JButton("Update Map Data");
        	saveMapBtn.addActionListener(this);
        	saveMapBtn.setActionCommand("Update Map Data");
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
        	existingMapPanel.add(new JLabel("Continent"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, 0.5, 0.5, 0, 8));
        	existingMapPanel.add(addAdjContinentList, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 1, 8));
        	existingMapPanel.add(new JLabel("Continent-->Territory"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, 0.5, 0.5, 2, 8));
        	existingMapPanel.add(addAdjContTerritoryList, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 3, 8));
        	existingMapPanel.add(new JLabel("All Territory"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, 0.5, 0.5, 4, 8));
        	existingMapPanel.add(addAdjTerritoryList, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 5, 8));
        	existingMapPanel.add(addAdjTerritoryBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 6, 8));
        
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
         *  method is used to generate map data which is selected for editing
         * @param filePath path of input file use for editing
         */
        protected void generateEditMapData(String filePath) {
            if(StringUtils.isNotEmpty(filePath)) {
        	EditMapFile editMapFile = new EditMapFile(filePath); 
        	if(editMapFile.generateData()) {
        	  	//continentArea.setText(editMapFile.getContinentData().toString());
        	    //territoryArea.setText(editMapFile.getTerritoryData().toString());
        	    fetchFileDataError.setText("File Content Data is validated and Ready to Edit");
        	    //territory = editMapFile.getTerritory();
        	    //continent = editMapFile.getContinent();
        	    updateAllContinentList();
        	    updateAddTerrContinentList();
        	    updateAddAdjContinentList();
        	    updateEditContinentList();
        	    updateDeleteContinentList();
        	}
            }
        }	

	private void updateEditContinentList() {
	    editContinentList.removeAllItems();
	    for(Entry<String, Integer> entry : continent.getContinentValue().entrySet()) {
		editContinentList.addItem(entry.getKey());
	    }	    
	}
	private void updateDeleteContinentList() {
	    deleteContinentList.removeAllItems();
	    for(Entry<String, Integer> entry : continent.getContinentValue().entrySet()) {
		deleteContinentList.addItem(entry.getKey());
	    }
	}
	private void updateAddAdjContinentList() {
	    addAdjContinentList.removeAllItems();
	    for(Entry<String, Integer> entry : continent.getContinentValue().entrySet()) {
		addAdjContinentList.addItem(entry.getKey());
	    }
	}
	private void updateAddTerrContinentList() {
	    addTerrContinentList.removeAllItems();
	    for(Entry<String, Integer> entry : continent.getContinentValue().entrySet()) {
		addTerrContinentList.addItem(entry.getKey());
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
	 * @param insets The initial insets value
	 * @param fill The initial fill value
	 * @param anchor The initial anchor value.
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
	    // TODO Auto-generated method stub
	    String actionName = arg0.getActionCommand();
		System.out.println("Action Name " + actionName);
		if(actionName.equalsIgnoreCase(backBtnName)){
		    displayErrorDialog();	    
		} else if(actionName.equalsIgnoreCase("Add Continent")){
		    addContinent(addContinentField.getText(),addContinentValField.getText());
		} else if(actionName.equalsIgnoreCase("Add Territory")){
		    System.out.println("------->");
		    addTerritory(addTerrContinentList.getItemAt(addTerrContinentList.getSelectedIndex()),addTerritoryField.getText());
		} else if(actionName.equalsIgnoreCase("Add Adjacent")){
		    addAdjacentTerritory(addAdjContinentList.getItemAt(addAdjContinentList.getSelectedIndex()),addAdjContTerritoryList.getItemAt(addAdjContTerritoryList.getSelectedIndex()),addAdjTerritoryList.getItemAt(addAdjTerritoryList.getSelectedIndex()));
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
		    
		} else if(actionName.equalsIgnoreCase("allContinentList")) {
		   updateAllTerritoryList();
		} else if(actionName.equalsIgnoreCase("allTerritoryList")) {
		    updateAllAdjTerritoryList();
		} else if(actionName.equalsIgnoreCase("addAdjContinentList")) {
		    updateAddAdjContTerritoryList();
		} else if(actionName.equalsIgnoreCase("addAdjContTerritoryList")) {
		    updateAddAdjTerritoryList();
		} else if(actionName.equalsIgnoreCase("editContinentList") && editContinentList.getSelectedIndex() != -1) {
			editContinentField.setText(editContinentList.getItemAt(editContinentList.getSelectedIndex()));
			editContinentValue.setText(Integer.toString(continent.getContinentValue().get(editContinentList.getItemAt(editContinentList.getSelectedIndex()))));
    		}
	    
	}
	
	private void updateAddAdjTerritoryList() {
	    // TODO Auto-generated method stub
	    for(Entry<String, String> entry : territory.getTerritoryCont().entrySet()) {
		addAdjTerritoryList.addItem(entry.getKey());
		   
	    }	    
	}
	private void updateAddAdjContTerritoryList() {
	    // TODO Auto-generated method stub
	    try {
		addAdjContTerritoryList.removeAllItems();
		for(Entry<String, String> entry : territory.getTerritoryCont().entrySet()) {
		    if(entry.getValue().equalsIgnoreCase(addAdjContinentList.getItemAt(addAdjContinentList.getSelectedIndex()))) {
			addAdjContTerritoryList.addItem(entry.getKey());
		    }
		}
	    }  catch (Exception e) {
		System.out.println("Exception in updateAddAdjContTerritoryList");
	    }
	}
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
	private void deleteAdjacentTerritory(String adjacent) {
	    System.out.println("Enter adjacent Territory  for delete is "+ adjacent);	    
	}
	private void deleteTerritory(String territoryStr) {
	    System.out.println("Enter  Territory  for delete is "+ territoryStr);
	}
	private void deleteContinent(String continentStr) {
	    System.out.println("Enter  continent  for delete is "+ continentStr);
	}
	private void updateTerritory(String oldTerritory, String newTerritory) {
	    System.out.println("Territory Enter is " + oldTerritory + " and New Territory is : "+ newTerritory);	
	}
	private void updateContinent(String oldContinent, String newContinent, String value) {
	    System.out.println("Continent Selected is + " + oldContinent + " new one is " + newContinent + " value "+ value);
	    if(StringUtils.isNotEmpty(oldContinent) && StringUtils.isNotEmpty(newContinent) && StringUtils.isNotEmpty(value) && StringUtils.isNumeric(value) && Integer.parseInt(value) > 0) {
		//continent.updateContinentValue(oldContinent, newContinent, Integer.parseInt(value));
		//territory.updateTerritoryContinent(oldContinent, newContinent);
		displaySuccessDialog();
		updateAllContinentList();
		updateEditContinentList();
		updateDeleteContinentList();
		updateAddTerrContinentList();
		updateAddAdjContinentList();
	    } else {
		displayErrorDialog();
	    }
	    
	}
	private void addAdjacentTerritory(String continentStr, String territoryStr, String adjacent) {
	    System.out.println("Continent selected is " + continentStr + " with Territory " + territoryStr + " and Adjacent of Terriory is " + adjacent);
	    if(StringUtils.isNotEmpty(continentStr) && StringUtils.isNotEmpty(territoryStr) && StringUtils.isNotEmpty(adjacent) && !territoryStr.equalsIgnoreCase(adjacent) && !territory.getAdjacentTerritory().get(territoryStr).contains(adjacent)) {
		territory.addAdjacentTerritory(territoryStr, adjacent);
		displaySuccessDialog();
		updateAllContinentList();
		updateEditContinentList();
		updateDeleteContinentList();
		updateAddTerrContinentList();
		updateAddAdjContinentList();
	    } else {
		duplicateErrorDialog();
	    }
	    
	}
	private void addTerritory(String continentStr, String territoryStr) {
	    System.out.println("Continent selected is " + continentStr + " with Territory " + territoryStr);
	    if(StringUtils.isNotEmpty(continentStr) && StringUtils.isNotEmpty(territoryStr) && !territory.getTerritoryCont().containsKey(territoryStr)) {
		territory.addTerritoryCont(territoryStr, continentStr);
		displaySuccessDialog();
		addContinentField.setText("");
		addContinentValField.setText("");
		addTerritoryField.setText("");
		updateAllTerritoryList();
	    } else {
		displayErrorDialog();
	    }
	}
	public void addContinent(String continentStr, String value) {
	    System.out.println("Continent Enter is " + continentStr + " with value " + value);
	    if(StringUtils.isNotEmpty(continentStr) && StringUtils.isNotEmpty(value) && StringUtils.isNumeric(value) && Integer.parseInt(value) > 1) {
		    if(!continent.getContinentValue().containsKey(continentStr)) {
			continent.setContinentValue(continentStr, Integer.parseInt(value));
			displaySuccessDialog();
			addContinentField.setText("");
			addContinentValField.setText("");
			addTerritoryField.setText("");
			updateAllContinentList();
			updateEditContinentList();
			updateDeleteContinentList();
			updateAddTerrContinentList();
			updateAddAdjContinentList(); 
		    } else {
			duplicateErrorDialog();
		    }
		
	    } else {
		displayErrorDialog();
	    }
	}
	private void updateAllTerritoryList() {
	    allTerritoryList.removeAllItems();
	    for(Entry<String, String> entry : territory.getTerritoryCont().entrySet()) {
		if(entry.getValue().equalsIgnoreCase(allContinentList.getItemAt(allContinentList.getSelectedIndex()))) {
		    allTerritoryList.addItem(entry.getKey());
		}
	    }
	}
	private void updateAllContinentList() {
	    allContinentList.removeAllItems();
	    for(Entry<String, Integer> entry : continent.getContinentValue().entrySet()) {
		allContinentList.addItem(entry.getKey());
	    }
	}
	public void displaySuccessDialog() {
	    JOptionPane.showMessageDialog(null, "Content Added Successfully", "Content Validated", JOptionPane.INFORMATION_MESSAGE);   
	}
	public void displayErrorDialog(){
	    JOptionPane.showMessageDialog(null, "Please Check data Again.", "Content Invalid", JOptionPane.ERROR_MESSAGE);   
	}
	public void duplicateErrorDialog(){
	    JOptionPane.showMessageDialog(null, "Please Enter New Value. Values Already exist", "Content Same", JOptionPane.ERROR_MESSAGE);   
	}

}
