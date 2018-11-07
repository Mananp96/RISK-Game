package com.risk.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.apache.commons.lang3.StringUtils;

import com.risk.controller.InitializeData;
import com.risk.models.ArmiesSelection;
import com.risk.models.Continent;
import com.risk.models.Players;
import com.risk.models.Territory;
import com.risk.observer.Observer;
import com.risk.observer.Subject;
import com.risk.strategy.Context;
/**
 * 
 * User Interface for Game Play
 */
public class GamePanels extends Observer implements ActionListener, ListSelectionListener {
    /**
     * @param frame Frame object
     * @param players Player model object
     * @param territory Territory model object
     * @param continent Continent model object
     */
    public static final String INFANTRY_CARD = "Infantry";
    public static final String CAVALRY_CARD = "Cavalry";
    public static final String ARTILLERY_CARD = "Artillery";
    public static final String WILD_CARD = "Wild Card";
    public static final String CONTENT_INVALID = "Invalid Content";
    public static final String MANAN_PLAYER = "Manan";
    public static final String SHALIN_PLAYER = "Shalin";
    public static final String KHYATI_PLAYER = "Khyati";
    public static final String VAISHAKHI_PLAYER = "Vaishakhi";
    public static final String HIMEN_PLAYER = "Himen";
    JFrame frame;
    Players players;
    Territory territory;
    Continent continent;
    int playerTurn = 0;
    String newBtnName = "New Game";
    String exitBtnName = "Quit";
    String twoPlayersBtnName = "twoPlayersBtn";
    String threePlayersBtnName = "threePlayersBtn";
    String fourPlayersBtnName = "fourPlayersBtn";
    String fivePlayersBtnName = "fivePlayersBtn";
    String createNewMapBtnName = "Create New Map";
    String editExistingMapBtnName = "Edit Existing Map";
    String saveBtnName = "Save";
    String backBtnName = "backBtn";
    String existingMapFilePath;

    private String editMapBtnName = "Edit Button";
    private String mapFilePath;
    int  playerPlaying;
    private GridLayout mainLayout;
    private JButton reinforceBtn;
    private JButton attackBtn;
    private JButton attackSkipBtn;
    private JButton fortifySkipBtn;
    private JButton fortifyBtn;
    private JButton endTurnBtn;
    private JButton menuBtn;
    private JButton turnInBtn;
    private JButton tradeCardBtn;
    private JButton createNewMapBtn;
    private JButton editExistingMapBtn;
    private JButton backBtn;
    private JButton editButton;
    private JButton startGameBtn;

    private JTextArea territoryDetails = new JTextArea(10,4);
    private JTextArea logArea = new JTextArea(4,20);
    private JList<String> territoryAList;
    private JList<String> territoryBList;
    private JList<String> continentInfoList;
    private JList<String> territoryInfoList;	
    private GridBagConstraints c;
    private DefaultCaret caret;

    private JRadioButton mapOptA;
    private JRadioButton mapOptB;

    private boolean randomMap = true;
    private DefaultListModel<String> territoryAModel;
    private DefaultListModel<String> territoryBModel;
    private DefaultListModel<String> continentInfoModel;
    private DefaultListModel<String> territoryInfoModel;

    private JComboBox<String> territoryADropDown;
    private JComboBox<String> territoryBDropDown;
    private JComboBox<Integer> attackerDiceDropDown;
    private JComboBox<Integer> defenderDiceDropDown;
    private SpinnerNumberModel selectArmyModel;
    private JLabel fortErrorMsg;
    private Context context;
    private JPanel gamePanel;
    private JComboBox<String> territoryCDropDown;
    public static JTextArea log = new JTextArea(25,20);

    public GamePanels() {

    }
    public GamePanels(Subject observerSubject) {
	this.observerSubject = observerSubject;
	this.observerSubject.attach(this);

    }
    /**
     * Allow user to select number of player he/she want to play in game.
     * @return playerPanel
     */

    public JPanel playerMenu(){
	JPanel playerPanel = new JPanel();
	LayoutManager playerLayout = new GridLayout(6, 1, 5, 5);
	playerPanel.setLayout(playerLayout);

	JLabel playerCountLabel = new JLabel("Number of Players : ");
	JButton twoPlayersBtn = new JButton("Two");
	JButton threePlayersBtn = new JButton("Three");
	JButton fourPlayersBtn = new JButton("Four");
	JButton fivePlayersBtn = new JButton("Five");
	backBtn = new JButton ("Back");	

	playerPanel.add(playerCountLabel);
	playerPanel.add(twoPlayersBtn);
	playerPanel.add(threePlayersBtn);
	playerPanel.add(fourPlayersBtn);
	playerPanel.add(fivePlayersBtn);
	playerPanel.add(backBtn);

	twoPlayersBtn.addActionListener(this);
	threePlayersBtn.addActionListener(this);
	fourPlayersBtn.addActionListener(this);
	fivePlayersBtn.addActionListener(this);
	backBtn.addActionListener(this);

	twoPlayersBtn.setActionCommand(twoPlayersBtnName);
	threePlayersBtn.setActionCommand(threePlayersBtnName);
	fourPlayersBtn.setActionCommand(fourPlayersBtnName);
	fivePlayersBtn.setActionCommand(fivePlayersBtnName);
	backBtn.setActionCommand(backBtnName);
	return playerPanel;
    }

    /**
     * Create New File or Update Existing one Navigation Panel
     * @return editMapPanel
     */
    protected JPanel editMapPanel() {

	JPanel editMapPanel = new JPanel();
	GridLayout editMapLayout = new GridLayout(2, 1, 5, 5);
	editMapPanel.setLayout(editMapLayout);
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
    /**
     * method used for Creating new Map from scratch
     * @return createMapPanel
     */
    protected JPanel createMapPanel() {
	frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
	frame.setResizable(true);
	frame.setVisible(true);
	NewEditMapPanel newEditMapPanel=new NewEditMapPanel();
	return newEditMapPanel.createMapPanel(frame,false);
    }
    /**
     * method used for Editing Existing Map
     * @return existingMapPanel
     */
    protected JPanel editExistingMapPanel() {
	frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
	frame.setResizable(true);
	frame.setVisible(true);
	NewEditMapPanel newEditMapPanel=new NewEditMapPanel();
	return newEditMapPanel.createMapPanel(frame,true);
    }

    /**
     * 
     * @return GamePanel object which consist portion of Game Play
     */
    protected JPanel gameView() {

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
	frame.setResizable(true);
	gamePanel = new JPanel();
	frame.setLayout(mainLayout);
	gamePanel.add(displayLog(),setGridBagConstraints(new Insets(25, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.LINE_START, 0.5, 0.5, 0, 0));
	gamePanel.add(eventScreen(),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.CENTER, 0.5, 0.5, 1, 0));
	gamePanel.add(countryScreen("No Phase"),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.LINE_END, 0.5, 0.5, 2, 0));
	return gamePanel;
    }
    /**
     * 
     * @return returns JPanel of logs
     */
    protected JPanel displayLog(){
	JPanel logPanel = new JPanel();
	logPanel.setSize(new Dimension(300, 600));
	GridBagLayout eventLayout = new GridBagLayout();
	logPanel.setLayout(eventLayout);

	menuBtn = new JButton("Menu");
	menuBtn.setActionCommand(backBtnName);
	menuBtn.addActionListener(this);

	logArea.setFocusable(false);
	logArea.setLineWrap(true);
	logArea.setWrapStyleWord(true);
	caret = (DefaultCaret)logArea.getCaret();
	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	JScrollPane logScrollPane = new JScrollPane(logArea);
	logScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	JScrollPane jScrollPane = new JScrollPane(log);
	jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	logPanel.add(menuBtn, setGridBagConstraints(new Insets(5, 0, 20, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 0, 0));
	logPanel.add(logScrollPane, setGridBagConstraints(new Insets(0,5, 5, 5), GridBagConstraints.BOTH, 0.5,5, 0, 1));
	logPanel.add(jScrollPane, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 5, 0, 2));
	return logPanel;
    }
    /**
     * Panel consist various sections such as Reinforcement Button, Fortify Button, Attack Button, List of Territory and Adjacent Territory
     * @return EventPanel consist of various game play events
     */
    protected JPanel eventScreen(){

	JPanel eventPanel = new JPanel();
	eventPanel.setPreferredSize(new Dimension(300, 600));
	GridBagLayout eventLayout = new GridBagLayout();
	eventPanel.setLayout(eventLayout);

	JLabel selectedLabel = new JLabel("SELECTED TERRITORY");
	JLabel targetLabel = new JLabel("ADJACENT TERRITORY");

	turnInBtn = new JButton("Turn In Cards");
	turnInBtn.setEnabled(false);
	reinforceBtn = new JButton("Place Reinforcements");
	attackBtn = new JButton("Attack");
	attackSkipBtn = new JButton("Attack Skip");
	fortifySkipBtn = new JButton("Fortify Skip");
	fortifyBtn = new JButton("Fortify");
	endTurnBtn = new JButton("End Turn");
	if(reinforceBtn.isEnabled()) {
	    attackBtn.setEnabled(false);
	    fortifyBtn.setEnabled(false);
	    endTurnBtn.setEnabled(false);
	}

	reinforceBtn.setActionCommand("placeReinforcement");
	attackBtn.setActionCommand("attackBtn");
	fortifyBtn.setActionCommand("startFortification");
	endTurnBtn.setActionCommand("endTurn");
	reinforceBtn.addActionListener(this);
	fortifyBtn.addActionListener(this);
	attackBtn.addActionListener(this);
	endTurnBtn.addActionListener(this);
	territoryAModel = new DefaultListModel<>();
	territoryAList = new JList<>(territoryAModel);
	for (Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
	    if(entry.getValue().equalsIgnoreCase(players.getPlayerPlaying().get(playerTurn))) {
		territoryAModel.addElement(entry.getKey() +" -- " +territory.getTerritoryArmy().get(entry.getKey()));
	    }
	}		

	territoryAList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	territoryAList.setLayoutOrientation(JList.VERTICAL);
	territoryAList.setVisibleRowCount(40);
	territoryBModel = new DefaultListModel<>();
	territoryBList = new JList<>(territoryBModel);
	territoryBList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	territoryBList.setLayoutOrientation(JList.VERTICAL);
	territoryBList.setVisibleRowCount(6);

	JScrollPane continentScrollPane = new JScrollPane(territoryAList);
	JScrollPane territoryScrollPane = new JScrollPane(territoryBList);
	territoryAList.addListSelectionListener(new ListSelectionListener() {			
	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		territoryBModel.removeAllElements();
		if(StringUtils.isNotEmpty(territoryAList.getSelectedValue())){
		    String[] territorySelected = territoryAList.getSelectedValue().split(" -- ");
		    ArrayList<String> tempAdjacentTerritory = territory.getAdjacentTerritory().get(territorySelected[0]);
		    for(int i=0;i<tempAdjacentTerritory.size();i++) {
			territoryBModel.addElement(tempAdjacentTerritory.get(i)+ " -- " +territory.getTerritoryArmy().get(tempAdjacentTerritory.get(i)));
		    }
		}	
	    }
	});
	territoryBList.addListSelectionListener(this);

	JPanel attackButtonPanel = new JPanel();
	JPanel attackContainerPanel = new JPanel();
	attackButtonPanel.add(attackBtn);
	attackButtonPanel.add(attackSkipBtn);
	attackButtonPanel.setPreferredSize(new Dimension(300,600));
	attackContainerPanel.add(attackButtonPanel);
	attackSkipBtn.setEnabled(true);
	attackSkipBtn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		attackSkipBtn.setEnabled(false);
		attackBtn.setEnabled(false);
		fortifyBtn.setEnabled(true);
		fortifySkipBtn.setEnabled(true);
		observerSubject.setState("Attack Phase Skipped \n", false);
		gamePanel.remove(2);
		gamePanel.invalidate();
		gamePanel.validate();
		gamePanel.add(countryScreen("fortification"),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.LINE_END, 0.5, 0.5, 2, 0));
		gamePanel.invalidate();
		gamePanel.validate();
		startFortificationPhase();

	    }
	});
	JPanel fortifyButtonPanel = new JPanel();
	JPanel fortifyContainerPanel = new JPanel();
	fortifyButtonPanel.add(fortifyBtn);
	fortifyButtonPanel.add(fortifySkipBtn);
	fortifyButtonPanel.setPreferredSize(new Dimension(300,600));
	fortifyContainerPanel.add(fortifyButtonPanel);
	fortifySkipBtn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		fortifySkipBtn.setEnabled(false);
		fortifyBtn.setEnabled(false);
		endTurnBtn.setEnabled(true);
		observerSubject.setState("Fortification Phase Skipped \n", false);
		gamePanel.remove(2);
		gamePanel.invalidate();
		gamePanel.validate();
		gamePanel.add(countryScreen("No Phase"),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.LINE_END, 0.5, 0.5, 2, 0));
		gamePanel.invalidate();
		gamePanel.validate();			
	    }
	});
	eventPanel.add(selectedLabel, setGridBagConstraints(new Insets(25, 5, 21, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 4));
	eventPanel.add(continentScrollPane, setGridBagConstraints(new Insets(5, 5, 25, 5), GridBagConstraints.BOTH, 0.5, 10, 0, 5));
	eventPanel.add(reinforceBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 6));
	eventPanel.add(turnInBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 7));
	eventPanel.add(targetLabel, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 8));
	eventPanel.add(territoryScrollPane, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 10, 0, 9));
	eventPanel.add(attackContainerPanel, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 10));
	eventPanel.add(fortifyContainerPanel, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 11));
	eventPanel.add(endTurnBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 12));
	return eventPanel;
    }

    /**
     * Display various portion of game play such as list of continent and territory. And also details about army in particular territory and also which player occupied it.
     * Section for movement of Army for Fortification Phase.
     * @param phase 
     * @return countryPanel which consist detail view continent and territory
     */
    protected JPanel countryScreen(String phase){

	JPanel countryPanel = new JPanel();		
	countryPanel.setPreferredSize(new Dimension(600, 600));
	GridBagLayout countryLayout = new GridBagLayout();
	countryPanel.setLayout(countryLayout);
	JLabel countryLabel = new JLabel("CONTINENTS");
	countryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
	JLabel territoryLabel = new JLabel("TERRITORIES -- PLAYER -- ARMY");
	countryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
	continentInfoModel = new DefaultListModel<>();
	continentInfoList = new JList<>(continentInfoModel);
	for (Entry<String, Integer> entry : continent.getContinentValue().entrySet()) {
	    continentInfoModel.addElement(entry.getKey());
	}
	continentInfoList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	continentInfoList.setLayoutOrientation(JList.VERTICAL);
	continentInfoList.setVisibleRowCount(100);
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
			territoryInfoModel.addElement(territoryName.trim()+ " -- " +territory.getTerritoryUser().get(territoryName) + " -- "+territory.getTerritoryArmy().get(territoryName.trim()));
		    }   
		}
	    }
	});		
	territoryInfoModel = new DefaultListModel<>();
	territoryInfoList = new JList<>(territoryInfoModel);
	territoryInfoList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	territoryInfoList.setLayoutOrientation(JList.VERTICAL);
	territoryInfoList.setVisibleRowCount(100);
	territoryInfoList.setPreferredSize(new Dimension(150, 300));
	JScrollPane continentInfoScrollPane = new JScrollPane(continentInfoList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JScrollPane territoryInfoScrollPane = new JScrollPane(territoryInfoList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	Border border = BorderFactory.createLineBorder(Color.GRAY);
	territoryDetails.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	territoryDetails.setEditable(false);
	territoryDetails.setFocusable(false);
	territoryDetails.setLineWrap(true);
	territoryDetails.setWrapStyleWord(true);
	caret = (DefaultCaret)logArea.getCaret();
	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	JScrollPane logScrollPane = new JScrollPane(territoryDetails);
	logScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	countryPanel.add(countryLabel, setGridBagConstraints(new Insets(5,2, 2, 5), GridBagConstraints.BOTH, 0.5,1, 0, 0));
	countryPanel.add(territoryLabel, setGridBagConstraints(new Insets(5,5, 5, 5), GridBagConstraints.BOTH, 0.5,1, 1, 0));
	countryPanel.add(continentInfoScrollPane, setGridBagConstraints(new Insets(5,5, 5, 5), GridBagConstraints.BOTH, 0.5,3, 0, 1));
	countryPanel.add(territoryInfoScrollPane, setGridBagConstraints(new Insets(5,5, 5, 5), GridBagConstraints.BOTH, 0.5,3, 1, 1));
	if(phase.equalsIgnoreCase("fortification")) {
	    countryPanel.add(fortificationPanel(), setGridBagConstraints(new Insets(5,5, 5, 5), GridBagConstraints.BOTH, 0.5,3, 0, 2));  
	} else if(phase.equalsIgnoreCase("attack")) {
	    countryPanel.add(attackPanel(), setGridBagConstraints(new Insets(5,5, 5, 5), GridBagConstraints.BOTH, 0.5,3, 0, 2));  
	} else if(phase.equalsIgnoreCase("tradein")) {
	    countryPanel.add(cardPanel(), setGridBagConstraints(new Insets(5,5, 5, 5), GridBagConstraints.BOTH, 0.5,3, 0, 2));  
	}
	countryPanel.add(logScrollPane, setGridBagConstraints(new Insets(5,5, 5, 5), GridBagConstraints.BOTH, 0.5,3, 1, 2));
	return countryPanel;
    }
    /**
     * This Method return Panel Consisting 3 drop down for for Trading Cards
     * @return JPanel
     */
    private JPanel cardPanel() {
	JPanel cardPanel = new JPanel();
	cardPanel.setLayout(new GridLayout(10, 1));
	JLabel attackerLabel = new JLabel("Card 1");
	territoryADropDown = new JComboBox<>();
	JLabel defenderLabel = new JLabel("Card 2");
	territoryBDropDown = new JComboBox<>();
	JLabel attackerDiceLable = new JLabel("Card 3");
	territoryCDropDown = new JComboBox<>();
	tradeCardBtn = new JButton("Trade Cards");
	cardPanel.add(attackerLabel);
	cardPanel.add(territoryADropDown);
	territoryADropDown.addItem("");
	for(Entry<String, String> entry : players.getCards().entrySet()) {
	    if(players.getPlayerPlaying().get(playerTurn).equalsIgnoreCase(entry.getValue())) {
		territoryADropDown.addItem(players.getTerritoryCards().get(entry.getKey())+" -- "+entry.getKey());
	    }
	}
	cardPanel.add(new JLabel(""));
	cardPanel.add(defenderLabel);
	cardPanel.add(territoryBDropDown);
	cardPanel.add(new JLabel(""));
	cardPanel.add(attackerDiceLable);
	cardPanel.add(territoryCDropDown);
	cardPanel.add(new JLabel(""));
	cardPanel.add(tradeCardBtn);
	tradeCardBtn.setActionCommand("tradeCardBtn");
	tradeCardBtn.addActionListener(this);
	territoryADropDown.addItemListener(new ItemListener() {

	    @Override
	    public void itemStateChanged(ItemEvent e) {
		territoryBDropDown.removeAllItems();
		territoryBDropDown.addItem("");
		if(territoryADropDown.getSelectedIndex() > 0) {
		    String[] tempVar = territoryADropDown.getItemAt(territoryADropDown.getSelectedIndex()).split("--");
		    for(Entry<String, String> entry : players.getCards().entrySet()) {
			if(!tempVar[1].trim().equalsIgnoreCase(entry.getKey()) && players.getPlayerPlaying().get(playerTurn).equalsIgnoreCase(entry.getValue()))
			    territoryBDropDown.addItem(players.getTerritoryCards().get(entry.getKey())+" -- "+entry.getKey());
		    }		
		}
	    }
	});
	territoryBDropDown.addItemListener(new ItemListener() {

	    @Override
	    public void itemStateChanged(ItemEvent e) {
		territoryCDropDown.removeAllItems();
		territoryCDropDown.addItem("");
		if(territoryBDropDown.getSelectedIndex() > 0) {
		    String[] tempVar1 = territoryADropDown.getItemAt(territoryADropDown.getSelectedIndex()).split("--");
		    String[] tempVar2 = territoryBDropDown.getItemAt(territoryBDropDown.getSelectedIndex()).split("--");
		    for(Entry<String, String> entry : players.getCards().entrySet()) {
			if(!tempVar1[1].trim().equalsIgnoreCase(entry.getKey()) && players.getPlayerPlaying().get(playerTurn).equalsIgnoreCase(entry.getValue())) {
			    if( !tempVar2[1].trim().equalsIgnoreCase(entry.getKey())) 
				territoryCDropDown.addItem(players.getTerritoryCards().get(entry.getKey())+" -- "+entry.getKey());
			}

		    }
		}
	    }
	});
	return cardPanel;
    }
    /** 
     * This Method return  Panel consisting Attack Functionalities for Player.
     * @return JPanel
     */
    private JPanel attackPanel() {
	JPanel attackPanel = new JPanel();
	attackPanel.setLayout(new GridLayout(8, 1));
	JLabel attackerLabel = new JLabel("Attacker Territory");
	territoryADropDown = new JComboBox<>();
	JLabel defenderLabel = new JLabel("Defender Territory");
	territoryBDropDown = new JComboBox<>();
	JLabel attackerDiceLable = new JLabel("Attacker Dice");
	attackerDiceDropDown = new JComboBox<>();
	JLabel defenderDiceLable = new JLabel("Defender dice");
	defenderDiceDropDown = new JComboBox<>();
	attackPanel.add(attackerLabel);
	attackPanel.add(territoryADropDown);
	territoryADropDown.addItem("");
	for(Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
	    if(entry.getValue().equalsIgnoreCase(players.getPlayers(playerTurn)) && territory.getTerritoryArmy().get(entry.getKey()) > 1) {
		territoryADropDown.addItem(entry.getKey());
	    }
	}
	territoryADropDown.addItemListener(new ItemListener() {

	    @Override
	    public void itemStateChanged(ItemEvent e) {
		addTerritoryBDropDown("attack");
		attackerDiceDropDown.removeAllItems();
		String terrName = territoryADropDown.getItemAt(territoryADropDown.getSelectedIndex());
		if(StringUtils.isNotEmpty(terrName)) {
		    int numberOfDie = territory.getTerritoryArmy().get(terrName) > 3 ? 3 : territory.getTerritoryArmy().get(terrName)-1;
		    for(int i=1;i<=numberOfDie;i++) {
			attackerDiceDropDown.addItem(i);
		    }
		}
	    }
	});
	attackPanel.add(defenderLabel);
	attackPanel.add(territoryBDropDown);
	attackPanel.add(attackerDiceLable);
	attackPanel.add(attackerDiceDropDown);
	attackPanel.add(defenderDiceLable);
	attackPanel.add(defenderDiceDropDown);
	territoryBDropDown.addItemListener(new ItemListener() {

	    @Override
	    public void itemStateChanged(ItemEvent e) {
		defenderDiceDropDown.removeAllItems();
		String terrName = territoryBDropDown.getItemAt(territoryBDropDown.getSelectedIndex());
		if(StringUtils.isNotEmpty(terrName)) {
		    int numberOfDie = territory.getTerritoryArmy().get(terrName) >= 3 ? 3 : territory.getTerritoryArmy().get(terrName);
		    for(int i=1;i<=numberOfDie;i++) {
			defenderDiceDropDown.addItem(i);
		    }
		}

	    }
	});
	return attackPanel;
    }

    /**
     * Display list of territory and it's adjacent territory of current Player 
     * @return fortificationPanel for movement of army
     */
    private JPanel fortificationPanel() {
	JPanel fortificationPanel = new JPanel();
	fortificationPanel.setLayout(new GridLayout(9, 1));
	JLabel territoryALabel = new JLabel("Territory List");
	territoryADropDown = new JComboBox<>();
	JLabel territoryBLabel = new JLabel("Adjacent Territory List");
	territoryBDropDown = new JComboBox<>();
	fortErrorMsg = new JLabel("Select Army : ");
	selectArmyModel = new SpinnerNumberModel();
	JSpinner selectArmy = new JSpinner(selectArmyModel);
	fortificationPanel.add(new JLabel(""));
	fortificationPanel.add(territoryALabel);
	fortificationPanel.add(territoryADropDown);
	fortificationPanel.add(new JLabel(""));
	fortificationPanel.add(territoryBLabel);
	fortificationPanel.add(territoryBDropDown);
	fortificationPanel.add(new JLabel(""));
	fortificationPanel.add(fortErrorMsg);
	fortificationPanel.add(selectArmy);
	territoryADropDown.addItemListener(new ItemListener() {

	    @Override
	    public void itemStateChanged(ItemEvent e) {
		addTerritoryBDropDown("fortification");
	    }
	});
	territoryBDropDown.addItemListener(new ItemListener() {

	    @Override
	    public void itemStateChanged(ItemEvent e) {
		enterArmyToMove();
	    }
	});
	return fortificationPanel;
    }

    /**
     * Frame consist of Start button, Edit Button and Quit Button
     * @param frame current frame
     * @return menuPanel 
     */ 
    protected JPanel mainMenu(JFrame frame){
	playerTurn = 0;
	log.setText("");
	players = new Players();
	continent = new Continent();
	territory = new Territory();
	this.frame = frame;
	frame.setBounds(0, 0, 300, 300);
	JPanel menuPanel = new JPanel();
	mainLayout = new GridLayout(3, 1, 5, 5);
	menuPanel.setLayout(mainLayout);
	JButton newButton = new JButton("Play Game");
	editButton = new JButton("Edit map");
	JButton exitButton = new JButton("Quit");

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

    /**
     * Display number of players playing in Game.
     * Enable user to select random maps.
     * Enable user to select previously Edited Map
     * @param count number of player
     * @return userPanel
     */
    public JPanel userInfoPanel(int count){
	JPanel userPanel = new JPanel();
	userPanel.setLayout(new GridLayout(6 + count, 1, 5, 5));
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
		    existingMapFilePath = "previous.map";
		    randomMap = false;
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

	if (actionName.equalsIgnoreCase(newBtnName)) {
	    players = new Players();
	    players.addPlayers(MANAN_PLAYER);
	    players.addPlayers(SHALIN_PLAYER);
	    setFrameValidate(playerMenu());
	} else if (actionName.equals(editMapBtnName)) {
	    setFrameValidate(editMapPanel());
	} else if (actionName.equals(editExistingMapBtnName)) {
	    setFrameValidate(editExistingMapPanel());
	} else if (actionName.equals(createNewMapBtnName)) {
	    setFrameValidate(createMapPanel());
	} else if (actionName.equals(exitBtnName)) {
	    System.exit(0);
	} else if (actionName.equals("Start Game")) {
	    if (randomMap) {
		if (StringUtils.isNotEmpty(mapFilePath)) {
		    ArmiesSelection armies = new ArmiesSelection(playerPlaying);
		    InitializeData initializeData = new InitializeData(mapFilePath , playerPlaying , armies.getPlayerArmies(), players);
		    boolean isMapValid = initializeData.generateData();
		    if(isMapValid) {
			continent = initializeData.getContinent();
			players = initializeData.getPlayers();
			territory = initializeData.getTerritory();
			observerSubject.setState(MANAN_PLAYER,true);
			observerSubject.setState("Reinforcement Force Started \n",false);
			setFrameValidate(gameView());
			fortifySkipBtn.setEnabled(false);
			attackSkipBtn.setEnabled(false);
		    } else {
			JOptionPane.showMessageDialog(frame, "Please Check data Again.", CONTENT_INVALID, JOptionPane.ERROR_MESSAGE);   
		    }
		} else {
		    JOptionPane.showMessageDialog(frame, "No Map Selected.", CONTENT_INVALID, JOptionPane.ERROR_MESSAGE);    
		}
	    } else {
		if (StringUtils.isNotEmpty(existingMapFilePath)) {
		    ArmiesSelection armies = new ArmiesSelection(playerPlaying);
		    InitializeData initializeData = new InitializeData( existingMapFilePath, playerPlaying , armies.getPlayerArmies(), players);
		    boolean isEditMapValid = initializeData.generateData();
		    if (isEditMapValid) {
			continent = initializeData.getContinent();
			players = initializeData.getPlayers();
			territory = initializeData.getTerritory();
			observerSubject.setState(MANAN_PLAYER, true);
			observerSubject.setState("Reinforcement Force Started \n", false);
			setFrameValidate(gameView());
			fortifySkipBtn.setEnabled(false);
			attackSkipBtn.setEnabled(false);
		    } else {
			JOptionPane.showMessageDialog(frame, "Please Check data Again.", CONTENT_INVALID, JOptionPane.ERROR_MESSAGE);   
		    }
		} else {
		    JOptionPane.showMessageDialog(frame, "No Map Edited Previously.", CONTENT_INVALID, JOptionPane.ERROR_MESSAGE);   
		}
	    }
	} else if (actionName.equals(twoPlayersBtnName)) {
	    players.addPlayers("Neutral Player");
	    setFrameValidate(userInfoPanel(2));
	} else if (actionName.equals(threePlayersBtnName)) {
	    players.addPlayers(KHYATI_PLAYER);
	    setFrameValidate(userInfoPanel(3));
	} else if (actionName.equals(fourPlayersBtnName)) {
	    players.addPlayers(KHYATI_PLAYER);
	    players.addPlayers(VAISHAKHI_PLAYER);
	    setFrameValidate(userInfoPanel(4));
	} else if (actionName.equals(fivePlayersBtnName)) {
	    players.addPlayers(KHYATI_PLAYER);
	    players.addPlayers(VAISHAKHI_PLAYER);
	    players.addPlayers(HIMEN_PLAYER);
	    setFrameValidate(userInfoPanel(5));
	} else if (actionName.equals("placeReinforcement")) {
	    goForReinforcement(true);
	} else if (actionName.equals("attackBtn")) {
	    goForAttack();
	} else if (actionName.equals("startFortification")) {
	    goForFortification();
	} else if (actionName.equals("endTurn")) {
	    changePlayerTurn();
	} else if (actionName.equals(backBtnName)) {
	    setFrameValidate(mainMenu(frame));
	} else if (actionName.equals("tradeCardBtn")) {
	    observerSubject.setTradeInMsg("Started Trading A Card");
	}
    }
    /**
     * This Method is used to validate frame with new content
     * @param jPanel new content which  need to be added in frame
     */
    public void setFrameValidate(JPanel jPanel) {
	frame.setContentPane(jPanel);
	frame.invalidate();
	frame.validate();
    }
    /**
     * Attack phase.
     * Invoke the fortification phase.
     */
    private void goForAttack() {
	players.setCurrentPhase("Attack");
	String fromTerritory = territoryADropDown.getItemAt(territoryADropDown.getSelectedIndex());
	String toTerritory = territoryBDropDown.getItemAt(territoryBDropDown.getSelectedIndex());
	int attackerDie = attackerDiceDropDown.getSelectedIndex() != -1 ? attackerDiceDropDown.getItemAt(attackerDiceDropDown.getSelectedIndex()) : 0;
	int defenderDie= defenderDiceDropDown.getSelectedIndex() != -1 ? defenderDiceDropDown.getItemAt(defenderDiceDropDown.getSelectedIndex()) : 0;
	String message1 = "Before Attack \n "+fromTerritory+" : "+territory.getTerritoryArmy().get(fromTerritory) +"\n"+toTerritory+" : "+territory.getTerritoryArmy().get(toTerritory)+"\n";
	if(StringUtils.isNotEmpty(fromTerritory) && StringUtils.isNotEmpty(toTerritory) && attackerDie > 0 && defenderDie >0) {
	    context = new Context(players);
	    context.executeAttack(territory, fromTerritory , toTerritory, attackerDie, defenderDie);
	    String message2 = "Attacker Die \n" + players.getAttackerMsg()+"\n Defender Die \n" +players.getDefenderMsg();
	    String message3 =message1+message2+ "After Attack \n "+fromTerritory+" : "+territory.getTerritoryArmy().get(fromTerritory) +"\n"+toTerritory+" : "+territory.getTerritoryArmy().get(toTerritory)+"\n";
	    observerSubject.setAttackMsg(message3);
	    if(players.isAttackWon()) {
		moveArmyAfterAttack();
		if(checkPlayerWonGame()) {
		    JOptionPane.showMessageDialog(frame, "You Won Game", "Game Won",JOptionPane.INFORMATION_MESSAGE);
		    setFrameValidate(mainMenu(frame));
		}
	    }
	    attackPanelReset();
	    log.append(message3);
	} else {
	    JOptionPane.showMessageDialog(frame, "Please Select Value Properly");
	    observerSubject.setAttackMsg("Please Select Value Properly");
	}
    }
    /**
     * This Method check whether player has won game or not
     * @return
     */
    private boolean checkPlayerWonGame() {
	String name = players.getPlayerList().get(playerTurn);
	int totalTerritory = 0;
	for(Entry<String, ArrayList<String>> entry : players.getPlayerContinent().get(name).getContinentOwnedterritory().entrySet()) {
	    totalTerritory += entry.getValue().size();
	}
	return (totalTerritory*100)/territory.getTerritoryList().size() == 100 ? true : false;
    }
    /**
     * This Method is used to move armies when player has successfully attack the adjacent territory and acquired it.
     */
    private void moveArmyAfterAttack() {
	String fromTerritory = territoryADropDown.getItemAt(territoryADropDown.getSelectedIndex());
	String toTerritory = territoryBDropDown.getItemAt(territoryBDropDown.getSelectedIndex());
	String output = JOptionPane.showInputDialog(frame,"Move Armies from "+fromTerritory+" to "+toTerritory, "Move Armies upto "+(territory.getTerritoryArmy().get(fromTerritory)-1), JOptionPane.OK_CANCEL_OPTION);
	if (StringUtils.isNotEmpty(output) && StringUtils.isNumeric(output) && Integer.parseInt(output) > 0 && Integer.parseInt(output) < territory.getTerritoryArmy().get(fromTerritory)) {
	    context = new Context(players);
	    context.executeArmyAfterAttack(players.getPlayers(playerTurn),territory,fromTerritory,toTerritory,Integer.parseInt(output));
	    observerSubject.setAttackMsg("Army on  " + fromTerritory  + " is "+ territory.getTerritoryArmy().get(fromTerritory));
	    observerSubject.setAttackMsg("Army on  " + toTerritory  + " is "+ territory.getTerritoryArmy().get(toTerritory));
	} else {
	    observerSubject.setAttackMsg("Please Enter Armies Again");
	    moveArmyAfterAttack();
	}
    }
    /**
     * This Method reset DropDown used for selected territories and number of dies
     */
    private void attackPanelReset() {
	territoryAModel.removeAllElements();
	territoryBModel.removeAllElements();
	territoryInfoModel.removeAllElements();
	continentInfoModel.removeAllElements();
	updateTerritoryAList();
	updateContinentInfoList();
	territoryADropDown.removeAllItems();
	territoryADropDown.addItem("");
	for(Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
	    if(entry.getValue().equalsIgnoreCase(players.getPlayers(playerTurn)) && territory.getTerritoryArmy().get(entry.getKey()) > 1) {
		System.out.println("->" +entry.getKey());
		territoryADropDown.addItem(entry.getKey());
	    }
	}
	territoryBDropDown.removeAllItems();
	attackerDiceDropDown.removeAllItems();
	defenderDiceDropDown.removeAllItems();
    }
    /**
     * method used to do reinforcement on territory.
     * @param flag used to identify whether player can do reinforcement or not.
     */
    public void goForReinforcement(boolean flag) {
	String name = players.getPlayerPlaying().get(playerTurn);
	if(StringUtils.isNotEmpty(territoryAList.getSelectedValue())){	    
	    String[] terrName = territoryAList.getSelectedValue().split("--");
	    String message = flag ? "Add Armies in " + terrName[0] : "Add Armies Again in " + terrName[0];
	    int army = players.getPlayerArmy(name);
	    String title = "Add Armies upto " + army;
	    String output = JOptionPane.showInputDialog(frame, message, title, JOptionPane.OK_CANCEL_OPTION);
	    if (StringUtils.isNotEmpty(output) && StringUtils.isNumeric(output)) {
		if(Integer.parseInt(output) > 0 && Integer.parseInt(output) <= army) {
		    context = new Context(players);
		    context.executeReinforcement(name,terrName[0].trim(),Integer.parseInt(output),territory);
		    observerSubject.setMessage(true,"Army Placed on   " + terrName[0]  + " is "+ output);
		    territoryAModel.removeAllElements();
		    territoryBModel.removeAllElements();
		    territoryInfoModel.removeAllElements();
		    continentInfoModel.removeAllElements();
		    updateTerritoryAList();
		    updateContinentInfoList();
		    enableReinforcementBtn();
		    observerSubject.setState(name, true);
		} else {
		    observerSubject.setMessage(true,"Input armies are out of range ot not properly enter");
		    goForReinforcement(false);
		}
	    } else {
		observerSubject.setMessage(true,"Input armies  entered is null or cancel button is clicked");
		JOptionPane.showMessageDialog(null, "Input armies  entered is null or cancel button is clicked",CONTENT_INVALID,JOptionPane.ERROR_MESSAGE);
	    }
	}
    }
    /**
     * method used to enable reinforcement button and disable fortify button if player has 1 or more army
     * It also used to disable reinforcement button and enable fortify button if player has no army  
     */
    public void enableReinforcementBtn() {
	String name = players.getPlayerPlaying().get(playerTurn);
	if(StringUtils.isNotEmpty(name)) {
	    if(players.getPlayerArmy(name) == 0 && !hasPlayerOwnedMoreCards()) {
		reinforceBtn.setEnabled(false);
		players.setCurrentPhase("Attack");
		observerSubject.setState(name, true);
		observerSubject.setState("Attack Phase Started \n", false);
		attackBtn.setEnabled(true);
		attackSkipBtn.setEnabled(true);
		fortifySkipBtn.setEnabled(false);
		gamePanel.remove(2);
		gamePanel.invalidate();
		gamePanel.validate();
		gamePanel.add(countryScreen("attack"),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.LINE_END, 0.5, 0.5, 2, 0));
		gamePanel.invalidate();
		gamePanel.validate();
	    } else {
		reinforceBtn.setEnabled(true);
	    }
	}
    }
    /**
     * This Method check whether player has more than 5 cards
     */
    public boolean hasPlayerOwnedMoreCards() {
	int cardCount = 0;
	for(Entry< String, String> entry : players.getCards().entrySet()) {
	    if(entry.getValue().equalsIgnoreCase(players.getPlayerList().get(playerTurn))) {
		cardCount++;
	    }
	}
	return cardCount > 5 ? true :  false;
    }
    /**
     * method use to enable list of current territory owned by current player to move army from one  territory to another.   
     */
    public void startFortificationPhase() {
	players.setCurrentPhase("Fortification");
	observerSubject.setState(players.getPlayerPlaying().get(playerTurn), true);
	attackBtn.setEnabled(false);
	addTerritoryADropDown();

    }

    /**
     * Method is used to change the Turn of player when End Turn Button is Clicked.
     */
    public void changePlayerTurn() {
	endTurnBtn.setEnabled(false);
	playerTurn++;
	playerTurn = playerTurn < players.getPlayerList().size() ? playerTurn : 0;
	if(!playerHasTerritory()) {
	    players.getPlayerList().remove(playerTurn);
	    players.getPlayerPlaying().remove(playerTurn);
	}
	playerTurn = players.getPlayerList().get(playerTurn).equalsIgnoreCase("Neutral Player") ? 0 : playerTurn;
	if(players.isWonCard()) {
	    JOptionPane.showMessageDialog(frame, "You Have Won 1 Card", "Card", JOptionPane.INFORMATION_MESSAGE);
	}
	players.setWonCard(false);
	context = new Context(players);
	context.executeReinforcementArmy(players.getPlayers(playerTurn),continent);
	removeElements();
	updateTerritoryAList();
	updateContinentInfoList();    
	enableReinforcementBtn();
	observerSubject.setState(players.getPlayers(playerTurn), true);
	log.setText("");
	observerSubject.setState("Reinforcement Phase Started \n", false);
	gamePanel.remove(2);
	gamePanel.invalidate();
	gamePanel.validate();
	if(players.getCards().containsValue(players.getPlayerPlaying().get(playerTurn))) {
	    gamePanel.add(countryScreen("tradeIn"),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.LINE_END, 0.5, 0.5, 2, 0));
	} else {
	    gamePanel.add(countryScreen("No Phase"),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.LINE_END, 0.5, 0.5, 2, 0));
	}
	gamePanel.invalidate();
	gamePanel.validate();
	playerOwnedCards();
    }
    /**
     * This Method Check Whether Player has Territories or not.
     * @return true if player has territories Otherwise false
     */
    public boolean playerHasTerritory() {
	return territory.getTerritoryUser().containsValue(players.getPlayerList().get(playerTurn)) ? true : false;
    }
    /**
     * This Method check whether player has more than 5 cards
     */
    public void playerOwnedCards() {
	int cardCount = 0;
	for(Entry< String, String> entry : players.getCards().entrySet()) {
	    if(entry.getValue().equalsIgnoreCase(players.getPlayerList().get(playerTurn))) {
		cardCount++;
	    }
	}
	if(cardCount > 5) {
	    JOptionPane.showMessageDialog(frame, players.getPlayerList().get(playerTurn) +" you need to Trade in Some Cards", "Need Card Trade In", JOptionPane.ERROR_MESSAGE);
	}
    }

    /**
     * This method remove elements from  territoryAModel, territoryBModel, territoryInfoModel, continentInfoModel, territoryADropDown and territoryBDropDown
     */
    public void removeElements() {
	territoryAModel.removeAllElements();
	territoryBModel.removeAllElements();
	territoryInfoModel.removeAllElements();
	continentInfoModel.removeAllElements();
	territoryADropDown.removeAllItems();
	territoryBDropDown.removeAllItems();
    }
    /**
     * Method Allow Player to do Fortification Phase.
     */
    public void goForFortification() {
	String fromTerritory = territoryADropDown.getItemAt(territoryADropDown.getSelectedIndex());
	String toTerritory = territoryBDropDown.getItemAt(territoryBDropDown.getSelectedIndex());
	if(StringUtils.isNotEmpty(fromTerritory) && StringUtils.isNotEmpty(toTerritory)) {
	    int fromArmy = territory.getTerritoryArmy().get(fromTerritory);
	    int getArmySelect = (int) selectArmyModel.getValue();
	    if(getArmySelect < fromArmy && getArmySelect >= 1) {
		context = new Context(players);
		context.executeFortification(territory,fromTerritory, toTerritory, getArmySelect);
		String tempMsg = "Armies moved from "+fromTerritory+" to "+toTerritory + "\n"+"Armies at :"+fromTerritory+" : "+territory.getTerritoryArmy().get(fromTerritory)+"\nArmies at :"+toTerritory+" : "+territory.getTerritoryArmy().get(toTerritory)+"\n";
		observerSubject.setFortificationMessage(tempMsg);
		fortErrorMsg.setText("You can Move upto " + (territory.getTerritoryArmy().get(fromTerritory)-1) + " Army");
	    } else {
		JOptionPane.showMessageDialog(frame, "Armies unable to move from " + fromTerritory + " to " + toTerritory +". Please enter no. of  Armies again", "Error Message", JOptionPane.ERROR_MESSAGE);
	    }
	    checkFortificationStatus();
	} else {
	    JOptionPane.showMessageDialog(null, CONTENT_INVALID);
	}
    }
    /**
     * method used to check whether current player can go for fortification phase more or not.
     */
    public void checkFortificationStatus() {
	boolean flag = false;
	for(Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
	    if(entry.getValue().equalsIgnoreCase(players.getPlayerPlaying().get(playerTurn)) && territory.getTerritoryArmy().get(entry.getKey()) > 1) {
		flag = true;
		break;
	    }
	}
	if(!flag) {
	    fortifyBtn.setEnabled(false);
	}
	else {
	    territoryAModel.removeAllElements();
	    territoryBModel.removeAllElements();
	    territoryInfoModel.removeAllElements();
	    continentInfoModel.removeAllElements();
	    updateTerritoryAList();
	    updateContinentInfoList(); 
	    startFortificationPhase();
	}
    }
    /**
     * Method used to display complete details of territory such as which continent it belongs to,
     * which player has occupied it with how many armies.
     */
    public  void displayTerritoryDetails() {
	territoryDetails.setText("");
	SimpleAttributeSet green = new SimpleAttributeSet();
	StyleConstants.setFontFamily(green, "Courier New Italic");
	StyleConstants.setForeground(green, Color.BLACK);
	StyleConstants.setFontSize(green, 15);
	try {
	    territoryDetails.getDocument().insertString(0, "Player Details", green);
	    frame.revalidate();
	} catch (BadLocationException e) {
	    System.out.println("Bad Location Exception " +e);
	}
	territoryDetails.append("\n");

	for(int i = 0; i < players.getPlayerList().size(); i++) {
	    String name = players.getPlayerList().get(i);
	    int totalArmyOwned = 0;
	    territoryDetails.append("Player Name : " +  name+ "\n");
	    int totalTerritory = 0;
	    for(Entry<String, ArrayList<String>> entry : players.getPlayerContinent().get(name).getContinentOwnedterritory().entrySet()) {
		totalTerritory += entry.getValue().size();
		for(int j =0;j <entry.getValue().size() ; j++ )
		    totalArmyOwned += territory.getTerritoryArmy().get(entry.getValue().get(j));
	    }
	    territoryDetails.append("Territory Owned : " +  totalTerritory+ "\n");
	    territoryDetails.append("Total Number of Armies : " + totalArmyOwned+"\n");
	    territoryDetails.append("Map Occupied : " +  Math.floor((totalTerritory*100/territory.getTerritoryList().size()))+ " %\n");
	    territoryDetails.append("-----------------------------------------\n");
	}
    }

    /**
     *  method used to update list of territory of current player
     */
    public void updateTerritoryAList() {
	territoryAModel.removeAllElements();

	for (Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
	    if(entry.getValue().equals(players.getPlayerList().get(playerTurn))) {
		territoryAModel.addElement(entry.getKey()+ " -- "+territory.getTerritoryArmy().get(entry.getKey()));
	    }
	}
    }
    /**
     * method used to update list of continent in CountryPanel
     */
    public void updateContinentInfoList() {
	continentInfoModel.removeAllElements();
	for (Entry<String, Integer> entry : continent.getContinentValue().entrySet()) {
	    continentInfoModel.addElement(entry.getKey());
	}
    }
    /**
     * method use to display list of current territory owned by current player to move army from one  territory to another.   
     */
    public void addTerritoryADropDown() {
	for(Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
	    if(entry.getValue().equals(players.getPlayerPlaying().get(playerTurn))){
		territoryADropDown.addItem(entry.getKey());
	    }
	}
    }
    /**
     * method use to display list of adjacent territory owned by current player to move army from selected  territory to adjacent one.   
     * @param phase 
     */
    public void addTerritoryBDropDown(String phase) {
	territoryBDropDown.removeAllItems();
	String dropDownAValue =  territoryADropDown.getItemAt(territoryADropDown.getSelectedIndex());
	if(StringUtils.isNotEmpty(dropDownAValue)) {
	    for(int i = 0 ; i < territory.getAdjacentTerritory().get(dropDownAValue).size() ; i++) {
		String terrName = territory.getAdjacentTerritory().get(dropDownAValue).get(i);
		if(players.getPlayerPlaying().get(playerTurn).equals(territory.getTerritoryUser().get(terrName)) && phase.equalsIgnoreCase("fortification")) {
		    territoryBDropDown.addItem(terrName);
		} else if(!players.getPlayerPlaying().get(playerTurn).equals(territory.getTerritoryUser().get(terrName)) && phase.equalsIgnoreCase("attack")) {
		    territoryBDropDown.addItem(terrName);
		}
	    }
	}
    }
    /**
     * method used to give input as no. of armies player want to move in fortification phase
     */
    public void enterArmyToMove() {
	String fromTerritory = territoryADropDown.getItemAt(territoryADropDown.getSelectedIndex());
	String toTerritory = territoryBDropDown.getItemAt(territoryBDropDown.getSelectedIndex());
	if(StringUtils.isNotEmpty(fromTerritory) && StringUtils.isNotEmpty(toTerritory)) {
	    int fromArmy = territory.getTerritoryArmy().get(fromTerritory) - 1;
	    if(fromArmy > 1) {
		fortErrorMsg.setText("You can Move upto " + fromArmy + " Army");
	    } else {
		fortErrorMsg.setText("You can't move your Army");
		observerSubject.setFortificationMessage("You can't move your Army");
	    }
	}
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
    /**
     * This Method Get Current Player Object
     * @return Current Player Object
     */
    public Players getPlayers() {
	return players;
    }
    /**
     * This Method Set Current Player Object
     * @param players Current Player Object
     */
    public void setPlayers(Players players) {
	this.players = players;
    }
    /**
     * This Method Get Current Territory Object
     * @return  Current Territory Object
     */
    public Territory getTerritory() {
	return territory;
    }
    /**
     * This Method Get Current Territory Object
     * @param territory Current Territory Object
     */
    public void setTerritory(Territory territory) {
	this.territory = territory;
    }
    /**
     * This Method Get Current Player Object
     * @return Current Continent Object
     */
    public Continent getContinent() {
	return continent;
    }
    /**
     * This Method Set Current Continent Object
     * @param continent Get Current Continent Object
     */
    public void setContinent(Continent continent) {
	this.continent = continent;
    }
    /**
     * This Method Print Logs of Events done by Player
     */
    @Override
    public void update() {
	if (observerSubject.getFlag()) {
	    logArea.setText("");
	    logArea.append("Current Player : " + observerSubject.getState() + "\n");
	    logArea.append("Current Armies : " + players.getPlayerArmy(observerSubject.getState()) + "\n");
	    logArea.append("Current Phase : " + players.getCurrentPhase());
	} else if (!observerSubject.getFlag()) {
	    log.append("------------------------------------------\n");
	    log.append(observerSubject.getState() + "\n");
	    log.append("------------------------------------------\n");
	    displayTerritoryDetails();
	    observerSubject.setMessageFlag(false);
	}
	if (observerSubject.getMessageFlag()) {
	    log.append(observerSubject.getMessage());
	    observerSubject.setMessageFlag(false);
	}

    }
    /**
     * This Method print action done by player during fortification phase
     */
    @Override
    public void fortificationUpdate() {
	log.append(observerSubject.getFortificationMessage() + "\n");
	displayTerritoryDetails();
    }
    /**
     * This Method print action done by player during Attack phase
     */
    @Override
    public void attackUpdate() {
	log.append(observerSubject.getAttackMsg() + "\n");
	displayTerritoryDetails();

    }
    /**
     * This Method perform trade in card operation using observer pattern
     */
    @Override
    public void tradeInCardUpdate() {
	log.append(observerSubject.getTradeInMsg() + "\n");
	int index1 = territoryADropDown.getSelectedIndex();
	int index2 = territoryBDropDown.getSelectedIndex();
	int index3 = territoryCDropDown.getSelectedIndex();
	if(index1  > 0 && index2  > 0 && index3  > 0 ) {
	    String[] card1 = territoryADropDown.getItemAt(index1).split("--");
	    String[] card2 = territoryBDropDown.getItemAt(index2).split("--");
	    String[] card3 = territoryCDropDown.getItemAt(index3).split("--");
	    ArrayList<String> cardList= new ArrayList<>();
	    cardList.add(card1[0].trim());
	    cardList.add(card2[0].trim());
	    cardList.add(card3[0].trim());
	    if(checkTradeInCard(cardList)) {
		int army = tradeInArmy();
		players.setTradeInArmies(army);
		players.setTradeIn();
		log.append("\nTraded Cards are " +card1[0].trim()+  " " +card1[1].trim()+"\n");
		log.append("Traded Cards are " +card2[0].trim()+  " " +card2[1].trim()+"\n");
		log.append("Traded Cards are " +card3[0].trim()+  " " +card3[1].trim()+"\n");
		log.append("Number of Trade In : "+players.getTradeIn()+"\n");
		log.append("You Got " + army+" Amies\n");
		players.updateArmy(players.getPlayerList().get(playerTurn), army, "ADD");
		if(territory.getTerritoryUser().get(card1[1].trim()).equalsIgnoreCase(players.getPlayerList().get(playerTurn)) || territory.getTerritoryUser().get(card2[1].trim()).equalsIgnoreCase(players.getPlayerList().get(playerTurn)) || territory.getTerritoryUser().get(card3[1].trim()).equalsIgnoreCase(players.getPlayerList().get(playerTurn))) {
		    players.updateArmy(players.getPlayerList().get(playerTurn), 2, "ADD");
		    log.append("You Got Additional 2 Amies\n");
		}		    
		players.getTerritoryCards().remove(card1[1].trim());
		players.getTerritoryCards().remove(card2[1].trim());
		players.getTerritoryCards().remove(card3[1].trim());
		players.getCards().remove(card1[1].trim());
		players.getCards().remove(card2[1].trim());
		players.getCards().remove(card3[1].trim());
		territory.getTerritoryCard().put(card1[1].trim(), card1[0].trim());
		territory.getTerritoryCard().put(card2[1].trim(), card2[0].trim());
		territory.getTerritoryCard().put(card3[1].trim(), card3[0].trim());
		territoryADropDown.removeAllItems();
		territoryBDropDown.removeAllItems();
		territoryCDropDown.removeAllItems();
		territoryADropDown.addItem("");
		for(Entry<String, String> entry : players.getCards().entrySet()) {
		    if(players.getPlayerPlaying().get(playerTurn).equalsIgnoreCase(entry.getValue())) {
			territoryADropDown.addItem(players.getTerritoryCards().get(entry.getKey())+" -- "+entry.getKey());
		    }
		}
		JOptionPane.showMessageDialog(frame, "Trade In Completed Successfully. You Got "+army+" armies", "Armies Generated",JOptionPane.INFORMATION_MESSAGE);
		logArea.setText("");
		logArea.append("Current Player : " + players.getPlayerList().get(playerTurn) + "\n");
		logArea.append("Current Armies : " + players.getPlayerArmy(players.getPlayerList().get(playerTurn)) + "\n");
		logArea.append("Current Phase : " + players.getCurrentPhase());
		displayTerritoryDetails();
		log.append("Trade In Done\n");
	    } else {
		JOptionPane.showMessageDialog(frame, "Select proper Combination of Cards", "Card Combination Error",JOptionPane.ERROR_MESSAGE);
		log.append("Select proper Combination of Cards\n");
	    }

	} else {
	    JOptionPane.showMessageDialog(frame, "Select proper Combination of Cards", "Card Combination Error",JOptionPane.ERROR_MESSAGE);
	    log.append("Select proper Combination of Cards\n");
	}
    }
    /**
     * This Method generate armies when players trade in cards
     * @return 
     */

    public int tradeInArmy() {
	int tradeInNum = players.getTradeIn();
	if(tradeInNum == 0) {
	    return players.getTradeInArmies();
	} else if(tradeInNum >= 1 && tradeInNum < 3) {
	    return players.getTradeInArmies() + 2;
	} else {
	    return players.getTradeInArmies() + 5;
	}
    }
    /**
     * This Method Check whether combination of trade in card is correct or not.
     * @param cardList list of card select for trade
     * @return true any combination matches otherwise false
     */
    public boolean checkTradeInCard(ArrayList<String> cardList) {
	boolean list1 = checkCardCombination(cardList, INFANTRY_CARD, INFANTRY_CARD, INFANTRY_CARD);
	boolean list2 = checkCardCombination(cardList, CAVALRY_CARD, CAVALRY_CARD, CAVALRY_CARD);
	boolean list3 = checkCardCombination(cardList, ARTILLERY_CARD, ARTILLERY_CARD, ARTILLERY_CARD);
	boolean list4 = checkCardCombination(cardList, INFANTRY_CARD, CAVALRY_CARD, ARTILLERY_CARD);
	boolean list5 = checkCardCombination(cardList, INFANTRY_CARD, INFANTRY_CARD, WILD_CARD);
	boolean list6 = checkCardCombination(cardList, CAVALRY_CARD, CAVALRY_CARD, WILD_CARD);
	boolean list7 = checkCardCombination(cardList, ARTILLERY_CARD, ARTILLERY_CARD, WILD_CARD);
	return list1 || list2 || list3 || list4 || list5 || list6 || list7 ? true : false;	      
    }
    /**
     * This Method used to generate Combination of cards
     * @param card1 card type
     * @param card2 card type
     * @param card3 card type
     * @return true when any of the combination matches Otherwise false
     */
    public boolean checkCardCombination(ArrayList<String> cardList, String card1, String card2, String card3){
	ArrayList<String> list = new ArrayList<>();
	list.add(card1);
	list.add(card2);
	list.add(card3);
	for (int i = 0; i < cardList.size(); i++) {
	    if (list.contains(cardList.get(i))) {
		list.remove(cardList.get(i));
	    }
	}
	return list.isEmpty() ? true : false;
    }
}
