package com.risk.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.DefaultCaret;

import org.apache.commons.lang3.StringUtils;

import com.risk.controller.CreateMapFile;
import com.risk.controller.InitializeData;
import com.risk.models.ArmiesSelection;
import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;

public class GamePanels implements ActionListener, ListSelectionListener {

	private JFrame frame;
	Players players;
	Territory territory;
	Continent continent;
	int playerTurn = 0;
	private JPanel menuPanel;
	private JPanel playerPanel;
	private JPanel gamePanel;
	private JPanel mapPanel;
	private JPanel editMapPanel;
	private JPanel createMapPanel;
	private String newBtnName = "New Game";
	private String exitBtnName = "Quit";
	private String twoPlayersBtnName = "twoPlayersBtn";
	private String threePlayersBtnName = "threePlayersBtn";
	private String fourPlayersBtnName = "fourPlayersBtn";
	private String fivePlayersBtnName = "fivePlayersBtn";
	private String createNewMapBtnName = "Create New Map";
	private String editExistingMapBtnName = "Edit Existing Map";
	private String saveBtnName = "Save";
	private String backBtnName = "backBtn";
	private int  playerPlaying;
	
	private JLabel playerCountLabel;
	
	private GridLayout mainLayout;
	private GridLayout playerLayout;
	private GridLayout editMapLayout;
	private JPanel logPanel;
	private JPanel eventPanel;
	private JPanel countryPanel;
	private JButton reinforceBtn;
	private JButton attackBtn;
	private JButton fortifyBtn;
	private JButton endTurnBtn;
	private JButton menuBtn;
	private JButton turnInBtn;
	private JButton createNewMapBtn;
	private JButton editExistingMapBtn;
	private JButton saveMapBtn;
	private JButton newButton;
	private JButton exitButton;
	private JButton twoPlayersBtn;
	private JButton threePlayersBtn;
	private JButton fourPlayersBtn;
	private JButton fivePlayersBtn;
	/* private JButton sixPlayersBtn; */
	private JButton backBtn;

	private JTextArea addContinentsArea;
	private JTextArea addTerritoriesArea;
	private JLabel selectedLabel;
	private JLabel targetLabel;
	private JList<String> cardsList;
	private JList<String> territoryAList;
	private JList<String> territoryBList;
	private JList<String> continentInfoList;
	private JList<String> territoryInfoList;
	private JScrollPane continentScrollPane;
	private JScrollPane territoryScrollPane;
	private GridBagConstraints c;
	private GridBagLayout mapLayout;
	private GridBagLayout logLayout;
	private JTextArea logArea;
	private JScrollPane logScrollPane;
	private DefaultCaret caret;
	private JPanel userPanel;
	private JButton startGameBtn;
	private String editMapBtnName = "Edit Button";
	private String mapFilePath;
	private JButton editButton;
	private JRadioButton mapOptA;
	private JRadioButton mapOptB;
	
	private boolean randomMap = false;
	private boolean previousEditMap = false;
	private DefaultListModel<String> territoryAModel;
	private DefaultListModel<String> territoryBModel;
	private DefaultListModel<String> continentInfoModel;
	private DefaultListModel<String> territoryInfoModel;
	private JScrollPane continentInfoScrollPane;
	private JScrollPane territoryInfoScrollPane;
	private JTextArea playerDetails;
	private JTextArea territoryDetails;
	
	public JPanel playerMenu(){
		// Creates the panel
		playerPanel = new JPanel();
		// Sets Layout
		/* Layout for 6 Player
		 playerLayout = new GridLayout(7, 1, 5, 5);
		 */
		playerLayout = new GridLayout(6, 1, 5, 5);
		playerPanel.setLayout(playerLayout);
		
		playerCountLabel = new JLabel("Number of Players : ");
		twoPlayersBtn = new JButton("Two");
		threePlayersBtn = new JButton("Three");
		fourPlayersBtn = new JButton("Four");
		fivePlayersBtn = new JButton("Five");
		/*sixPlayersBtn = new JButton("Six");*/
		backBtn = new JButton ("Back");	
		
		playerPanel.add(playerCountLabel);
		playerPanel.add(twoPlayersBtn);
		playerPanel.add(threePlayersBtn);
		playerPanel.add(fourPlayersBtn);
		playerPanel.add(fivePlayersBtn);
		/*playerPanel.add(sixPlayersBtn);*/
		playerPanel.add(backBtn);
		
		twoPlayersBtn.addActionListener(this);
		threePlayersBtn.addActionListener(this);
		fourPlayersBtn.addActionListener(this);
		fivePlayersBtn.addActionListener(this);
/*		sixPlayersBtn.addActionListener(this);*/
		backBtn.addActionListener(this);
		
		twoPlayersBtn.setActionCommand(twoPlayersBtnName);
		threePlayersBtn.setActionCommand(threePlayersBtnName);
		fourPlayersBtn.setActionCommand(fourPlayersBtnName);
		fivePlayersBtn.setActionCommand(fivePlayersBtnName);
		/*sixPlayersBtn.setActionCommand(sixPlayersBtnName);*/
		backBtn.setActionCommand(backBtnName);
		return playerPanel;
	}
	
	protected JPanel editMapPanel() {
		
		// Creates the panel
		editMapPanel = new JPanel();
		// Sets Layout
		editMapLayout = new GridLayout(2, 1, 5, 5);
		editMapPanel.setLayout(editMapLayout);
		// Creates buttons
		createNewMapBtn = new JButton("Create new map");
		editExistingMapBtn = new JButton("Edit Existing map");
		editMapPanel.add(createNewMapBtn);
		editMapPanel.add(editExistingMapBtn);
		createNewMapBtn.addActionListener(this);
		editExistingMapBtn.addActionListener(this);
		createNewMapBtn.setActionCommand(createNewMapBtnName);
		editExistingMapBtn.setActionCommand(editExistingMapBtnName);
		return editMapPanel;
		
	}
	protected JPanel createMapPanel() {
		
		createMapPanel = new JPanel();
		frame.setPreferredSize(new Dimension(300, 600));
		frame.setVisible(true);
		frame.setResizable(true);
		JLabel l1 = new JLabel("Continents ", JLabel.CENTER);
		JLabel l2 = new JLabel("Territories ", JLabel.CENTER);
		addContinentsArea = new JTextArea(6, 20);
		addTerritoriesArea = new JTextArea(6, 20);
		addContinentsArea.setFocusable(true);
		addTerritoriesArea.setFocusable(true);
		addContinentsArea.setLineWrap(true);
		addTerritoriesArea.setLineWrap(true);
		addContinentsArea.setWrapStyleWord(true);
		addTerritoriesArea.setWrapStyleWord(true);
		createMapPanel.add(l1);
		createMapPanel.add(addContinentsArea);
		createMapPanel.add(l2);
		createMapPanel.add(addTerritoriesArea);
		saveMapBtn = new JButton("Save");
		createMapPanel.add(saveMapBtn);
		saveMapBtn.addActionListener(this);
		saveMapBtn.setActionCommand(saveBtnName);
		return createMapPanel;
		
	}
	protected JPanel gameView() {
		
		frame.setPreferredSize(new Dimension(900,800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(true);
		gamePanel = new JPanel();
		frame.setLayout(mainLayout);
		
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		gamePanel.add(displayMap(),c);
				
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		gamePanel.add(eventScreen(),c);
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		gamePanel.add(countryScreen(),c);
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.SOUTHWEST;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		gamePanel.add(logScreen(),c);
	
		return gamePanel;
	}
	
	protected JPanel displayMap(){
		mapPanel = new JPanel();
		mapPanel.setSize(new Dimension(400, 600));
		mapLayout = new GridBagLayout();
		mapPanel.setLayout(mapLayout);
		ImageIcon mapImageIcon = new ImageIcon("D:\\eclipse-workspace\\RiskGame\\src\\riskpackage\\Map.jpg");
		JScrollPane mapScrollPane = new JScrollPane(new JLabel(mapImageIcon));
		mapScrollPane.setPreferredSize(new Dimension(300, 600));
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 14;
		c.gridx = 0;
		c.gridy = 0;
		
		mapPanel.add(mapScrollPane,c);
		return mapPanel;
	}
	
	protected JPanel eventScreen(){
	
		eventPanel = new JPanel();
		eventPanel.setPreferredSize(new Dimension(300, 600));
		GridBagLayout eventLayout = new GridBagLayout();
		eventPanel.setLayout(eventLayout);
		
		selectedLabel = new JLabel("Selected Territory:");
		targetLabel = new JLabel("Adjacent Territory:");
		
		menuBtn = new JButton("Menu");
		turnInBtn = new JButton("Turn In Cards");
		reinforceBtn = new JButton("Place Reinforcements");
		attackBtn = new JButton("Attack!");
		fortifyBtn = new JButton("Fortify");
		endTurnBtn = new JButton("End Turn");
		
		menuBtn.setActionCommand(backBtnName);
		reinforceBtn.setActionCommand("placeReinforcement");
		
		menuBtn.addActionListener(this);
		reinforceBtn.addActionListener(this);

		cardsList = new JList<>();
		cardsList.setLayoutOrientation(JList.VERTICAL_WRAP);
		cardsList.setVisibleRowCount(6);
		territoryAModel = new DefaultListModel<>();
		territoryAList = new JList<>(territoryAModel);
		for (Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
			if(entry.getValue().equalsIgnoreCase(players.getPlayers(playerTurn))) {
				territoryAModel.addElement(entry.getKey() +"---" +territory.getTerritoryArmy().get(entry.getKey()));
			}
		}
		
		territoryAList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		territoryAList.setLayoutOrientation(JList.VERTICAL);
		territoryAList.setVisibleRowCount(42);
		territoryBModel = new DefaultListModel<>();
		territoryBList = new JList<>(territoryBModel);
		territoryBList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		territoryBList.setLayoutOrientation(JList.VERTICAL);
		territoryBList.setVisibleRowCount(6);
		
		continentScrollPane = new JScrollPane(territoryAList);
		territoryScrollPane = new JScrollPane(territoryBList);
		territoryAList.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				territoryBModel.removeAllElements();
				if(StringUtils.isNotEmpty(territoryAList.getSelectedValue())){
				    String[] territorySelected = territoryAList.getSelectedValue().split("---");
				    ArrayList<String> tempAdjacentTerritory = territory.getAdjacentTerritory().get(territorySelected[0]);
				    for(int i=0;i<tempAdjacentTerritory.size();i++) {
					territoryBModel.addElement(tempAdjacentTerritory.get(i)+ "---" +territory.getTerritoryArmy().get(tempAdjacentTerritory.get(i)));
				    }
				}	
			}
		});
		territoryBList.addListSelectionListener(this);
		
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		eventPanel.add(menuBtn, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 5;
		c.gridx = 0;
		c.gridy = 2;
		eventPanel.add(cardsList, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		eventPanel.add(turnInBtn, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 4;
		eventPanel.add(selectedLabel, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 10;
		c.gridx = 0;
		c.gridy = 5;
		eventPanel.add(continentScrollPane, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 6;
		eventPanel.add(reinforceBtn, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 7;
		eventPanel.add(targetLabel, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 10;
		c.gridx = 0;
		c.gridy = 8;
		eventPanel.add(territoryScrollPane, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 9;
		eventPanel.add(attackBtn, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 10;
		eventPanel.add(fortifyBtn, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 11;
		eventPanel.add(endTurnBtn, c);
		
		return eventPanel;
	}
	
	protected JPanel countryScreen(){

		countryPanel = new JPanel();		
		countryPanel.setPreferredSize(new Dimension(600, 600));
		GridBagLayout countryLayout = new GridBagLayout();
		countryPanel.setLayout(countryLayout);
		JLabel countryLabel = new JLabel("CONTINENTS");	
		countryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		JLabel territoryLabel = new JLabel("TERRITORYS---PLAYER---ARMY");	
		countryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		continentInfoModel = new DefaultListModel<>();
		continentInfoList = new JList<>(continentInfoModel);
		for (Entry<String, Integer> entry : continent.getContinentValue().entrySet()) {
			    continentInfoModel.addElement(entry.getKey());
		}
		continentInfoList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		continentInfoList.setLayoutOrientation(JList.VERTICAL);
		continentInfoList.setVisibleRowCount(42);
		continentInfoList.setPreferredSize(new Dimension(150, 300));
		continentInfoList.addListSelectionListener(new ListSelectionListener() {
		    
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
			if(StringUtils.isNotEmpty(continentInfoList.getSelectedValue())) {
			    territoryInfoModel.removeAllElements();
			    String continentSelected = continentInfoList.getSelectedValue().trim();
			    ArrayList<String> tempContinentTerritory = continent.getContinentTerritory().get(continentSelected);
			    for(int i=0;i<tempContinentTerritory.size();i++) {
				String territoryName = tempContinentTerritory.get(i).trim();
				territoryInfoModel.addElement(territoryName.trim()+ "---" +territory.getTerritoryUser().get(territoryName));
			    }   
			}
		    }
		});
		
		territoryInfoModel = new DefaultListModel<>();
		territoryInfoList = new JList<>(territoryInfoModel);
		territoryInfoList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		territoryInfoList.setLayoutOrientation(JList.VERTICAL);
		territoryInfoList.setVisibleRowCount(10);
		territoryInfoList.setPreferredSize(new Dimension(150, 300));
		territoryInfoList.addListSelectionListener(new ListSelectionListener() {
		    
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			displayTerritoryDetails();
		    }
		});
		
		continentInfoScrollPane = new JScrollPane(continentInfoList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		territoryInfoScrollPane = new JScrollPane(territoryInfoList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		territoryDetails = new JTextArea(4,2);
		playerDetails = new JTextArea(5, 2);
		
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,2, 2, 5);
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		countryPanel.add(countryLabel, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 0;
		countryPanel.add(territoryLabel, c);
		
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 3;
		c.gridx = 0;
		c.gridy = 1;
		countryPanel.add(continentInfoScrollPane, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 3;
		c.gridx = 1;
		c.gridy = 1;
		countryPanel.add(territoryInfoScrollPane, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 3;
		c.gridx = 0;
		c.gridy = 2;
		countryPanel.add(territoryDetails, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 3;
		c.gridx = 0;
		c.gridy = 2;
		countryPanel.add(playerDetails, c);
		
		
		/*c = new GridBagConstraints();
		int index = 0;
		for (Entry<String, Integer> entry : continent.getContinentValue().entrySet()) {
			JPanel tempPanel = new JPanel();
			ArrayList<String> tempTerritory = continent.getContinentTerritory().get(entry.getKey());
			tempPanel.setPreferredSize(new Dimension(550, 100));
			tempPanel.setLayout(new GridLayout(tempTerritory.size()/2, 2, 2, 2));
			JLabel countryLabel = new JLabel(entry.getKey());	
			countryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(2, 2, 2, 2);
			c.weightx = 0.5;
			c.weighty = 0;
			c.gridx = index;
			c.gridy = 0;
			tempPanel.add(countryLabel, c);
			for(int i =0;i< tempTerritory.size(); i++) {
				JLabel territoryLabel = new JLabel(tempTerritory.get(i) + " : " + territory.getTerritoryUser().get(tempTerritory.get(i)));
				territoryLabel.setFont(new Font("Arial", Font.PLAIN, 12));
				tempPanel.add(territoryLabel);
			}
			index++;
			countryPanel.setLayout(new GridLayout(index, 2));
			countryPanel.add(tempPanel);
		}*/
		/*ImageIcon mapImageIcon = new ImageIcon("D:\\eclipse-workspace\\RiskGame\\src\\riskpackage\\Map.jpg");
		JScrollPane countryScrollPane = new JScrollPane(new JLabel(mapImageIcon));
		countryScrollPane.setPreferredSize(new Dimension(450, 600));
		countryPanel.add(countryScrollPane);*/
		return countryPanel;
	}
	protected JPanel logScreen(){
		
		logPanel = new JPanel();
		logLayout = new GridBagLayout();
		logPanel.setLayout(logLayout);
		logPanel.setSize(new Dimension(400,250));
		c = new GridBagConstraints();
		
		logArea = new JTextArea(4,40);
	//	System.setOut(new PrintStream(new TextAreaOutputStream(printTextArea)));
		logArea.setFocusable(false);
		logArea.setLineWrap(true);
		logArea.setWrapStyleWord(true);
		caret = (DefaultCaret)logArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		logScrollPane = new JScrollPane(logArea);
		
		c.fill = GridBagConstraints.WEST;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 14;
		c.gridx = 0;
		c.gridy = 0;
		logPanel.add(logScrollPane, c);
		/*		logPanel.setLayout(new GridBagLayout());
		logPanel.setSize(new Dimension(400,200));
		
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		
		logPanel.add(new JLabel("Log Screen"),c);
	*/		
		return logPanel;
	}

	protected JPanel mainMenu(JFrame frame, Players players){
		this.players = players;
		this.frame = frame;
		// Creates the panel
		menuPanel = new JPanel();
		// Sets Layout
		mainLayout = new GridLayout(3, 1, 5, 5);
		menuPanel.setLayout(mainLayout);
		// Creates buttons
		newButton = new JButton("Play Game");
		editButton = new JButton("Edit map");
		exitButton = new JButton("Quit");
				
		menuPanel.add(newButton);
		menuPanel.add(editButton);
		menuPanel.add(exitButton);
		
		newButton.addActionListener(this);
		editButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		newButton.setActionCommand(newBtnName);
		editButton.setActionCommand(editMapBtnName);
		exitButton.setActionCommand(exitBtnName);
		return menuPanel;
	}

	public JPanel userInfoPanel(int count){
		int playerCount = count;
		userPanel = new JPanel();
		userPanel.setLayout(new GridLayout(6 + count, 1, 5, 5));
		System.out.println("No. of Players : " + playerCount);
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
				if(mapOptA.isSelected()) {
					int retVal = chooseMap.showOpenDialog(frame);
					System.out.println("File Path : " + chooseMap.getSelectedFile().getPath());
					mapFilePath = chooseMap.getSelectedFile().getPath();
					randomMap = true;
				}
			}
		});
		
		mapOptB = new JRadioButton("Choose Previously Edited Map");
		mapOptB.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {

				if(mapOptB.isSelected()) {
					
				}
			}
		});
		ButtonGroup buttonGrp = new ButtonGroup();
		buttonGrp.add(mapOptA);
		buttonGrp.add(mapOptB);
		userPanel.add(mapOptA);
		userPanel.add(mapOptB);
		userPanel.add(new JLabel("Player Names are"));
		for (int i = 0; i < count; i++) {
			userPanel.add(new JLabel("Player " + (i + 1) + " : " + players.getPlayers(i)));
		}
		playerPlaying = count;
		startGameBtn = new JButton("Start Game");
		backBtn = new JButton ("Back");	
		startGameBtn.setActionCommand("Start Game");
		backBtn.setActionCommand(backBtnName);
		startGameBtn.addActionListener(this);
		backBtn.addActionListener(this);
		userPanel.add(startGameBtn);
		userPanel.add(backBtn);
		return userPanel;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {

		players = new Players();
		players.addPlayers("Manan");
		players.addPlayers("Shalin");

		String actionName = arg0.getActionCommand();
		
		if(actionName.equalsIgnoreCase(newBtnName)){
			System.out.println("Play Game");
			frame.setContentPane(playerMenu());
			frame.invalidate();
			frame.validate();
		}
		else if(actionName.equals(editMapBtnName)){
					
			frame.setContentPane(editMapPanel());
			frame.invalidate();
			frame.validate();
			
		} else if (actionName.equals(editExistingMapBtnName)) {
			
			System.out.println("Editing Existing Map");
			frame.setContentPane(editMapPanel());
			frame.invalidate();
			frame.validate();
		
		} else if (actionName.equals(createNewMapBtnName)) {
			
			System.out.println("Creating New Map");
			frame.setContentPane(createMapPanel());
			frame.invalidate();
			frame.validate();
		
		} else if (actionName.equals(saveBtnName)) {
			
			System.out.println("Saving New Map");
			String defaultMapTag = "[Map]\n"+
					"author=Sean O'Connor\n"+
					"warn=yes\n"+
					"image=Africa.bmp\n"+
					"wrap=no\n";
			String finalMapData = String.format("%s\n[Continents]\n%s\n\n[Territories]\n%s", defaultMapTag, addContinentsArea.getText(),
					addTerritoriesArea.getText());
			CreateMapFile createMapFile = new CreateMapFile(finalMapData);
			createMapFile.createMap();
		}else if(actionName.equals(exitBtnName)){
			System.out.println("Quit Game");
			System.exit(0);
		}
		else if(actionName.equals("Start Game")){
		    if(randomMap) {
				ArmiesSelection armies = new ArmiesSelection(playerPlaying); 
				InitializeData initializeData = new InitializeData(mapFilePath , playerPlaying , armies.getPlayerArmies(), players);
				boolean isMapValid = initializeData.generateData();
				continent = initializeData.getContinent();
				players = initializeData.getPlayers();
				territory = initializeData.getTerritory();
				frame.setContentPane(gameView());
				frame.invalidate();
				frame.validate();
		    } else {
				// previously Edited Map
		    }
			
		}
		else if(actionName.equals(twoPlayersBtnName)){
			System.out.println("Two Player Game");
			players.addPlayers("Neutral Player");
			frame.setContentPane(userInfoPanel(3));
			frame.invalidate();
			frame.validate();
		}
		else if(actionName.equals(threePlayersBtnName)){
			System.out.println("Three Player Game");
			players.addPlayers("Khyati");
			frame.setContentPane(userInfoPanel(3));
			frame.invalidate();
			frame.validate();
		}
		else if(actionName.equals(fourPlayersBtnName)){
			System.out.println("Four Player Game");
			players.addPlayers("Vaishakhi");
			frame.setContentPane(userInfoPanel(4));
			frame.invalidate();
			frame.validate();
		}
		else if(actionName.equals(fivePlayersBtnName)){
			System.out.println("Five Player Game");
			players.addPlayers("Himen");
			frame.setContentPane(userInfoPanel(5));
			frame.invalidate();
			frame.validate();
		}
		/*else if(actionName.equals(sixPlayersBtnName)){
			System.out.println("Six Player Game");
		}*/
		else if(actionName.equals("placeReinforcement")) {
		openInputDialog(true);
		}		
		else if(actionName.equals(backBtnName)){
			frame.setContentPane(mainMenu(frame,players));
			frame.invalidate();
			frame.validate();
			
		}
	}

	public void openInputDialog(boolean flag) {
	    if(StringUtils.isNotEmpty(territoryAList.getSelectedValue())){	    
		String[] terrName = territoryAList.getSelectedValue().split("---");
	    	String message = flag ? "Add Armies in " + terrName[0] : "Add Armies Again in " + terrName[0];
	    	String name = players.getPlayers(playerTurn);
	    	int army = players.getPlayerArmy(name);
	    	String title = "Add Amrmies upto " + army;
	    	System.out.println("Player Name " + players.getPlayers(playerTurn));
	    	System.out.println("Player Army " + players.getPlayerArmy(players.getPlayers(playerTurn)));
	    	String output = JOptionPane.showInputDialog(frame, message, title, JOptionPane.OK_CANCEL_OPTION).trim();
	    	if (StringUtils.isNumeric(output)) {
	    	    if(Integer.parseInt(output) > 0 && Integer.parseInt(output) <= army) {
	    		players.updateArmy(name,Integer.parseInt(output) , "DELETE");
	    		territory.updateTerritoryArmy(terrName[0], Integer.parseInt(output), "ADD");
	    		System.out.println("Armies Updates " + players.getPlayerArmy(name));
	    		territoryAModel.removeAllElements();
	    		territoryBModel.removeAllElements();
	    		territoryInfoModel.removeAllElements();
	    		continentInfoModel.removeAllElements();
	    		updateTerritoryAList();
	    		updateContinentInfoList();    
	    		enableReinforcementBtn();
	    	    } else {
    	    	    	System.out.println("Input armies are out pf range");
    	    	    	openInputDialog(false);	
	    	    }
	    	} else {
	    	    System.out.println("Input armies are not properly entered");
	    	    openInputDialog(false);
	    	}
	    }
	}
	public  void displayTerritoryDetails() {
	// TODO Auto-generated method stub
	    try {
		territoryDetails.setText("");
		System.out.println("territoryInfoList.getSelectedValue() " +territoryInfoList.getSelectedValue());
    		if(territoryInfoList.getSelectedValue() != null) {
    		    String[] territoryName = territoryInfoList.getSelectedValue().split("---");
    		    territoryDetails.append("Continent  : " + continentInfoList.getSelectedValue() + "\n");
    		    territoryDetails.append("Territory  : " + territoryName[0]+"\n");
    		    territoryDetails.append("Player     : " + territory.getTerritoryUser().get(territoryName[0].trim())+"\n");
    		    territoryDetails.append("Army       : " + territory.getTerritoryArmy().get(territoryName[0].trim()));
    		}
	    }
	    catch(Exception ex) {
		System.out.println("Handles Null Values");
	    }			
	}
	
	public void updateTerritoryAList() {
	    territoryAModel.removeAllElements();
	    
	    for (Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
		if(entry.getValue().equals(players.getPlayers(playerTurn)))	
		    territoryAModel.addElement(entry.getKey());
	    }
	}
	
	public void updateContinentInfoList() {
	    continentInfoModel.removeAllElements();
	    for (Entry<String, Integer> entry : continent.getContinentValue().entrySet()) {
		    continentInfoModel.addElement(entry.getKey());
	    }
	}
	
	public void enableReinforcementBtn() {
	    String name = players.getPlayers(playerTurn);
	    if(StringUtils.isNotEmpty(name)) {
		if(players.getPlayerArmy(name) == 0) 
		    reinforceBtn.setEnabled(false);
		else
		    reinforceBtn.setEnabled(true);
	    }
	}

	    
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}




}
