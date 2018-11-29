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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
import com.risk.controller.SaveAndLoadGame;
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
	public static final String HUMAN_TYPE = "Human";
	public static final String AGGRESSIVE_TYPE = "Aggressive";
	public static final String BENEVOLENT_TYPE = "Benevolent";
	public static final String RANDOM_TYPE = "Random";
	public static final String CHEATER_TYPE = "Cheater";
	public static final String REINFORCEMENT_MSG = "Reinforcement Phase Started";

	JFrame frame;
	Players players;
	Territory territory;
	Continent continent;
	int playerTurn = 0;
	int fileTurn =  0; 
	int gameTurn =  0; 
	int turnNumber =  0; 

	String newBtnName = "New Game";
	String tournamentModeName = "tournamentMode";
	String exitBtnName = "Quit";
	String twoPlayersBtnName = "twoPlayersBtn";
	String threePlayersBtnName = "threePlayersBtn";
	String fourPlayersBtnName = "fourPlayersBtn";
	String fivePlayersBtnName = "fivePlayersBtn";
	String loadSavedGameName = "loadSavedGameBtn";
	String createNewMapBtnName = "Create New Map";
	String editExistingMapBtnName = "Edit Existing Map";
	String saveBtnName = "Save";
	String backBtnName = "backBtn";
	String existingMapFilePath;

	boolean tournamentModeOn = false;

	private String editMapBtnName = "Edit Button";
	private File[] mapFilePath;
	int  playerPlaying;
	private GridLayout mainLayout;
	private JButton reinforceBtn;
	private JButton attackBtn;
	private JButton attackSkipBtn;
	private JButton fortifySkipBtn;
	private JButton fortifyBtn;
	private JButton endTurnBtn;
	private JButton menuBtn;
	private JButton saveBtn;
	private JButton turnInBtn;
	private JButton tradeCardBtn;
	private JButton createNewMapBtn;
	private JButton editExistingMapBtn;
	private JButton backBtn;
	private JButton editButton;
	private JButton startGameBtn;
	ArrayList<String> tempCards = new ArrayList<>();
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
	ArrayList<JComboBox<String>> playersListDropDown; 
	private JComboBox<String> territoryADropDown;
	private JComboBox<String> territoryBDropDown;
	private JComboBox<String> player1DropDown = new JComboBox<>(new String[] {HUMAN_TYPE,AGGRESSIVE_TYPE, BENEVOLENT_TYPE, RANDOM_TYPE, CHEATER_TYPE});
	private JComboBox<String> player2DropDown = new JComboBox<>(new String[] {HUMAN_TYPE,AGGRESSIVE_TYPE, BENEVOLENT_TYPE, RANDOM_TYPE, CHEATER_TYPE});
	private JComboBox<String> player3DropDown = new JComboBox<>(new String[] {HUMAN_TYPE,AGGRESSIVE_TYPE, BENEVOLENT_TYPE, RANDOM_TYPE, CHEATER_TYPE});
	private JComboBox<String> player4DropDown = new JComboBox<>(new String[] {HUMAN_TYPE,AGGRESSIVE_TYPE, BENEVOLENT_TYPE, RANDOM_TYPE, CHEATER_TYPE});
	private JComboBox<String> player5DropDown = new JComboBox<>(new String[] {HUMAN_TYPE,AGGRESSIVE_TYPE, BENEVOLENT_TYPE, RANDOM_TYPE, CHEATER_TYPE});
	private JComboBox<Integer> noOfGamesDropDown;
	private JComboBox<Integer> noOfTurnsDropDown;
	private JComboBox<Integer> attackerDiceDropDown;
	private JComboBox<Integer> defenderDiceDropDown;
	private SpinnerNumberModel selectArmyModel;
	private JLabel fortErrorMsg;
	private JLabel noOfGames;
	private JLabel noOfTurns;
	private Context context;
	private JPanel gamePanel;
	private JComboBox<String> territoryCDropDown;
	private JComboBox<String> allOutDropDown;
	public static JTextArea log = new JTextArea(25,20);
	private static final Logger LOGGER = Logger.getLogger(GamePanels.class.getName());
	Handler fileHandler = null;
	Formatter simpleFormatter = null;
	boolean gameWon = false;
	private ArrayList<String> tempPlayerList;
	private Map<Integer,String> mapResult = new HashMap<>();
	private Map<Integer,Map<Integer,String>> tournamentResult = new HashMap<>();

	/**
	 * Default Constructor
	 */
	public GamePanels() {

	}

	/**
	 * Constructor
	 * @param observerSubject ObserverSubject
	 */
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
		LayoutManager playerLayout = new GridLayout(7, 1, 5, 5);
		playerPanel.setLayout(playerLayout);

		JLabel playerCountLabel = new JLabel("Number of Players : ");
		JButton twoPlayersBtn = new JButton("Two");
		JButton threePlayersBtn = new JButton("Three");
		JButton fourPlayersBtn = new JButton("Four");

		playerPanel.add(playerCountLabel);
		playerPanel.add(twoPlayersBtn);
		playerPanel.add(threePlayersBtn);
		playerPanel.add(fourPlayersBtn);

		twoPlayersBtn.addActionListener(this);
		threePlayersBtn.addActionListener(this);
		fourPlayersBtn.addActionListener(this);

		twoPlayersBtn.setActionCommand(twoPlayersBtnName);
		threePlayersBtn.setActionCommand(threePlayersBtnName);
		fourPlayersBtn.setActionCommand(fourPlayersBtnName);

		if (!tournamentModeOn) {
			JButton fivePlayersBtn = new JButton("Five");
			JButton loadSavedGameBtn = new JButton("Load Saved Game");

			playerPanel.add(fivePlayersBtn);
			playerPanel.add(loadSavedGameBtn);

			fivePlayersBtn.addActionListener(this);
			loadSavedGameBtn.addActionListener(this);

			fivePlayersBtn.setActionCommand(fivePlayersBtnName);
			loadSavedGameBtn.setActionCommand(loadSavedGameName);
		}

		backBtn = new JButton ("Back");
		playerPanel.add(backBtn);
		backBtn.addActionListener(this);
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
		if(!players.getPlayerList().isEmpty() && !players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(HUMAN_TYPE)) {
			setBotReinforcement();
		}
		return gamePanel;
	}
	/**
	 * This method is used for Reinforcement for Bot Players. Bot Players are Aggressive, Benevolent, Random and Cheater
	 */
	public void setBotReinforcement() {
		players.setCurrentPhase("Reinforcement");
		observerSubject.setPlayerLog();
		observerSubject.setReinforcementMsg(REINFORCEMENT_MSG);
		if (!players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(HUMAN_TYPE)) {
			context = new Context(players);
			context.executeBotReinforcement(players.getPlayerList().get(playerTurn), territory);
			observerSubject.setReinforcementMsg(players.getReinforcementMsg());
		}
		observerSubject.setReinforcementMsg("Reinforcement Phase End");
		removeModelElement();
		updateTerritoryAList();
		updateContinentInfoList();
		enableReinforcementBtn();
		setBotAttackPanel();
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

		saveBtn = new JButton("Save Game");
		saveBtn.setActionCommand(saveBtnName);
		saveBtn.addActionListener(this);

		logArea.setFocusable(false);
		logArea.setLineWrap(true);
		logArea.setWrapStyleWord(true);
		caret = (DefaultCaret)logArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane logScrollPane = new JScrollPane(logArea);
		logScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JScrollPane jScrollPane = new JScrollPane(log);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		logPanel.add(menuBtn, setGridBagConstraints(new Insets(5, 0, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 0, 0));
		logPanel.add(saveBtn, setGridBagConstraints(new Insets(5, 0, 20, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 0, 1));
		logPanel.add(logScrollPane, setGridBagConstraints(new Insets(0,5, 5, 5), GridBagConstraints.BOTH, 0.5,5, 0, 2));
		logPanel.add(jScrollPane, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 5, 0, 3));
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
			attackSkipBtn.setEnabled(false);
			fortifyBtn.setEnabled(false);
			fortifySkipBtn.setEnabled(false);
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
			if(entry.getValue().equalsIgnoreCase(players.getPlayerList().get(playerTurn))) {
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
		attackSkipBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				attackSkipBtn.setEnabled(false);
				attackBtn.setEnabled(false);
				fortifyBtn.setEnabled(true);
				fortifySkipBtn.setEnabled(true);
				observerSubject.setAttackMsg("Attack Phase Skipped");
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
				observerSubject.setFortificationMessage("Fortification Phase Skipped");
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
			if(players.getPlayerList().get(playerTurn).equalsIgnoreCase(entry.getValue())) {
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
						if(!tempVar[1].trim().equalsIgnoreCase(entry.getKey()) && players.getPlayerList().get(playerTurn).equalsIgnoreCase(entry.getValue()))
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
						if(!tempVar1[1].trim().equalsIgnoreCase(entry.getKey()) && players.getPlayerList().get(playerTurn).equalsIgnoreCase(entry.getValue())) {
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
		JLabel allOutLabel = new JLabel("Set All Out Mode");
		allOutDropDown = new JComboBox<>();
		attackPanel.add(attackerLabel);
		attackPanel.add(territoryADropDown);
		allOutDropDown.addItem("Yes");
		allOutDropDown.addItem("No");
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
		attackPanel.add(allOutLabel);
		attackPanel.add(allOutDropDown);
		territoryBDropDown.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				defenderDiceDropDown.setEnabled(true);
				allOutDropDown.setEnabled(true);

				defenderDiceDropDown.removeAllItems();
				String terrName = territoryBDropDown.getItemAt(territoryBDropDown.getSelectedIndex());
				if(StringUtils.isNotEmpty(terrName)) {
					int numberOfDie = territory.getTerritoryArmy().get(terrName) >= 3 ? 3 : territory.getTerritoryArmy().get(terrName);
					if(players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(AGGRESSIVE_TYPE)) {
						defenderDiceDropDown.addItem(numberOfDie);
						defenderDiceDropDown.setEnabled(false);
						allOutDropDown.setEnabled(false);
					} else {
						if(!players.getPlayerType().get(territory.getTerritoryUser().get(terrName)).equalsIgnoreCase(HUMAN_TYPE)) {
							defenderDiceDropDown.addItem(numberOfDie);
						} else {
							for(int i=1;i<=numberOfDie;i++) {
								defenderDiceDropDown.addItem(i);
							}
						}

					}
				}

			}
		});
		return attackPanel;
	}
	/**
	 * This method is used for Attack for Bot Players. Bot Players are Aggressive, Benevolent, Random and Cheater
	 */
	public void setBotAttackPanel() {
		boolean tempFlag = true;
		players.setCurrentPhase("Attack");
		observerSubject.setPlayerLog();
		if(players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(RANDOM_TYPE)){
			int rand = new Random().nextInt(6)+1;
			tempFlag = rand  <= 3 ? true : false;
		}
		if((players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(AGGRESSIVE_TYPE)||players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(RANDOM_TYPE)) && tempFlag){
			String tempAttackTerr="";
			String tempdefenderTerr="";
			for(Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
				if(entry.getValue().equalsIgnoreCase(players.getPlayers(playerTurn)) && territory.getTerritoryArmy().get(entry.getKey()) > 1) {
					tempAttackTerr= entry.getKey();
					for(int i =0;i < territory.getAdjacentTerritory().get(entry.getKey()).size();i++) {
						if(!players.getPlayerList().get(playerTurn).equals(territory.getTerritoryUser().get(territory.getAdjacentTerritory().get(entry.getKey()).get(i)))) {
							tempdefenderTerr = territory.getAdjacentTerritory().get(entry.getKey()).get(i);
							break;
						}
					}	    
				}
			}
			territoryADropDown.removeAllItems();
			territoryADropDown.addItem(tempAttackTerr);
			territoryADropDown.setEnabled(false);
			if(StringUtils.isNotBlank(tempdefenderTerr)) {
				territoryBDropDown.removeAllItems();
				territoryBDropDown.addItem(tempdefenderTerr);
				territoryBDropDown.setEnabled(false);
				int attackerDie = territory.getTerritoryArmy().get(tempAttackTerr) > 4 ? 3 : (territory.getTerritoryArmy().get(tempAttackTerr) - 1);
				int defenderDie = territory.getTerritoryArmy().get(tempdefenderTerr) > 3 ? 3 : territory.getTerritoryArmy().get(tempdefenderTerr);
				attackerDiceDropDown.removeAllItems();
				attackerDiceDropDown.addItem(attackerDie);
				attackerDiceDropDown.setEnabled(false);
				defenderDiceDropDown.removeAllItems();
				defenderDiceDropDown.addItem(defenderDie);
				defenderDiceDropDown.setEnabled(false);
				context = new Context(players);
				if(attackerDie > 0 && defenderDie >0 && !players.getPlayerList().isEmpty())
					context.executeBotAttack(territory,tempAttackTerr,tempdefenderTerr,attackerDie,defenderDie, players.getPlayerList().get(playerTurn));	
				observerSubject.setAttackMsg(players.getAttackerMsg());
				if(players.isAttackWon()) {
					if(checkPlayerWonGame()) {
						changeGame();
					}
				}if(!players.getPlayerList().isEmpty() && players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(RANDOM_TYPE)) {
					attackPanelReset();
				} else {
					setBotAttackPanelReset();    
				}
			} else {
				if(checkPlayerWonGame()) {
					changeGame();
				} else {
					setBotAttackPanelReset();    
				}
			}
		} else if(players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(BENEVOLENT_TYPE)) {
			if(checkPlayerWonGame()) {
				changeGame();
			} else {
				setBotAttackPanelReset();    
			}
		} else if(players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(RANDOM_TYPE) && !tempFlag) {
			if(checkPlayerWonGame()) {
				changeGame();
			} else {
				setBotAttackPanelReset();    
			}
		} else if(players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(CHEATER_TYPE)) {
			context = new Context(players);
			context.executeBotAttack(territory,players.getPlayerList().get(playerTurn),"",0,0,  players.getPlayerList().get(playerTurn));	
			observerSubject.setAttackMsg(players.getAttackerMsg());
			if(checkPlayerWonGame()) {
				changeGame();
			} else {
				setBotAttackPanelReset();    
			}

		}
	}
	/**
	 * This method is used to play next game number if player won game 
	 */
	private void changeGame() {
		LOGGER.info("******************* "+players.getPlayerList().get(playerTurn)+" Won Game  **********");
		if(tournamentModeOn) {
			mapResult.put(gameTurn,players.getPlayerList().get(playerTurn));
			gameTurn++;
			tournamentEnd();
		} else {
			logArea.setText(players.getPlayerList().get(playerTurn)+" Won Game");
			setButtonEnable(false, false, false, false, false);
			endTurnBtn.setEnabled(false);
			saveBtn.setEnabled(false);
		}
		setFrameValidate(mainMenu(frame));
	}


	/**
	 * This method is used to get Game Result
	 * @return mapResult
	 */
	public Map<Integer, String> getMapResult() {
		return mapResult;
	}
	/**
	 * This method is used to set game result
	 * @param mapResult store game result for particular map
	 */
	public void setMapResult(Map<Integer, String> mapResult) {
		this.mapResult = mapResult;
	}
	/**
	 * This method is used to get Tournament Result
	 * @return mapResult
	 */
	public Map<Integer, Map<Integer, String>> getTournamentResult() {
		return tournamentResult;
	}
	/**
	 * This method is used to set Tournament Result
	 * @param tournamentResult tournament result
	 */
	public void setTournamentResult(Map<Integer, Map<Integer, String>> tournamentResult) {
		this.tournamentResult = tournamentResult;
	}
	/**
	 * This Method is used to reset attack panel for Bot Player.
	 */
	public void setBotAttackPanelReset() {
		attackBtn.setEnabled(false);
		attackSkipBtn.setEnabled(false);
		fortifyBtn.setEnabled(false);
		fortifySkipBtn.setEnabled(false);

		if(!checkPlayerWonGame()) {
			goForFortification();
		}
		if (gamePanel.getComponents().length > 2) {

		}
		endTurnBtn.setEnabled(false);
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
	public JPanel mainMenu(JFrame frame){
		try {
			fileHandler = new FileHandler("./gamelogs.log");
			simpleFormatter = new SimpleFormatter();
			LOGGER.addHandler(fileHandler);
			fileHandler.setFormatter(simpleFormatter);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		playerTurn = 0;
		log.setText("");
		players = new Players();
		continent = new Continent();
		territory = new Territory();
		this.frame = frame;
		frame.setBounds(0, 0, 300, 300);
		JPanel menuPanel = new JPanel();
		mainLayout = new GridLayout(4, 1, 5, 5);
		menuPanel.setLayout(mainLayout);
		JButton newButton = new JButton("Play Game");
		JButton tournamentMode = new JButton("Tournament Game");
		editButton = new JButton("Edit map");
		JButton exitButton = new JButton("Quit");

		menuPanel.add(newButton);
		menuPanel.add(tournamentMode);
		menuPanel.add(editButton);
		menuPanel.add(exitButton);

		newButton.addActionListener(this);
		tournamentMode.addActionListener(this);
		editButton.addActionListener(this);
		exitButton.addActionListener(this);

		newButton.setActionCommand(newBtnName);
		tournamentMode.setActionCommand(tournamentModeName);
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
		frame.setBounds(0, 0, 300, 500);
		playersListDropDown = new ArrayList<>();
		if(tournamentModeOn) {
			player1DropDown = new JComboBox<>(new String[] {AGGRESSIVE_TYPE, BENEVOLENT_TYPE, RANDOM_TYPE, CHEATER_TYPE});
			player2DropDown = new JComboBox<>(new String[] {AGGRESSIVE_TYPE, BENEVOLENT_TYPE, RANDOM_TYPE, CHEATER_TYPE});
			player3DropDown = new JComboBox<>(new String[] {AGGRESSIVE_TYPE, BENEVOLENT_TYPE, RANDOM_TYPE, CHEATER_TYPE});
			player4DropDown = new JComboBox<>(new String[] {AGGRESSIVE_TYPE, BENEVOLENT_TYPE, RANDOM_TYPE, CHEATER_TYPE});
			player5DropDown = new JComboBox<>(new String[] {AGGRESSIVE_TYPE, BENEVOLENT_TYPE, RANDOM_TYPE, CHEATER_TYPE}); 
		} else {
			player1DropDown = new JComboBox<>(new String[] {HUMAN_TYPE,AGGRESSIVE_TYPE, BENEVOLENT_TYPE, RANDOM_TYPE, CHEATER_TYPE});
			player2DropDown = new JComboBox<>(new String[] {HUMAN_TYPE,AGGRESSIVE_TYPE, BENEVOLENT_TYPE, RANDOM_TYPE, CHEATER_TYPE});
			player3DropDown = new JComboBox<>(new String[] {HUMAN_TYPE,AGGRESSIVE_TYPE, BENEVOLENT_TYPE, RANDOM_TYPE, CHEATER_TYPE});
			player4DropDown = new JComboBox<>(new String[] {HUMAN_TYPE,AGGRESSIVE_TYPE, BENEVOLENT_TYPE, RANDOM_TYPE, CHEATER_TYPE});
			player5DropDown = new JComboBox<>(new String[] {HUMAN_TYPE,AGGRESSIVE_TYPE, BENEVOLENT_TYPE, RANDOM_TYPE, CHEATER_TYPE});
		}
		playersListDropDown.add(player1DropDown);
		playersListDropDown.add(player2DropDown);
		playersListDropDown.add(player3DropDown);
		playersListDropDown.add(player4DropDown);
		playersListDropDown.add(player5DropDown);

		JPanel userPanel = new JPanel();
		userPanel.setLayout(new GridLayout(8 + count, 2, 5, 5));
		mapOptA = new JRadioButton("Choose Your Own Map");
		mapOptA.setActionCommand("Own Map");
		JFileChooser chooseMap = new JFileChooser("D:");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("MAP FILES", "map", "map");
		chooseMap.setFileFilter(filter);
		chooseMap.setMultiSelectionEnabled(true);
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

		mapOptA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mapOptA.isSelected() && chooseMap.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					if (chooseMap.getSelectedFiles().length <= 5) {
						mapFilePath = chooseMap.getSelectedFiles();
						randomMap = true;
					}else{
						JOptionPane.showMessageDialog(frame, "Please select maps between 1 - 5");

					}
				}
			}
		});

		if (!tournamentModeOn) {
			mapOptB = new JRadioButton("Choose Previously Edited Map");
			mapOptB.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if (mapOptB.isSelected()) {
						existingMapFilePath = "previous.map";
						randomMap = false;
					}
				}
			});
		} else{
			noOfGames = new JLabel("No of Games");
			noOfGamesDropDown = new JComboBox<>();
			for(int i=1;i<=4;i++) {
				noOfGamesDropDown.addItem(i);
			}

			noOfTurns = new JLabel("No of Turns");
			noOfTurnsDropDown = new JComboBox<>();
			for(int i=10;i<=50;i++) {
				noOfTurnsDropDown.addItem(i);
			}
		}
		ButtonGroup buttonGrp = new ButtonGroup();
		buttonGrp.add(mapOptA);
		if (!tournamentModeOn){
			buttonGrp.add(mapOptB);
		}
		userPanel.add(mapOptA);
		if (!tournamentModeOn) {
			userPanel.add(mapOptB);
		} else{
			userPanel.add(noOfGames);
			userPanel.add(noOfGamesDropDown);
			userPanel.add(noOfTurns);
			userPanel.add(noOfTurnsDropDown);
		}
		userPanel.add(new JLabel("Player Names are"));
		for (int i = 0; i < count; i++) {
			Box  horizontalPlayerListBox = Box.createHorizontalBox();
			horizontalPlayerListBox.add(new JLabel("Player " + (i + 1) + " : " + players.getPlayers(i)));
			horizontalPlayerListBox.add(playersListDropDown.get(i));
			userPanel.add(horizontalPlayerListBox);
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


	/**
	 * Invokes a particular method when button is clicked.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String actionName = arg0.getActionCommand();
		if (actionName.equalsIgnoreCase(newBtnName)) {
			tournamentModeOn = false;
			players = new Players();
			players.addPlayers(MANAN_PLAYER);
			players.addPlayers(SHALIN_PLAYER);
			setFrameValidate(playerMenu());
		} else if (actionName.equalsIgnoreCase(tournamentModeName)) {
			tournamentModeOn = true;
			mapResult = new HashMap<>();
			tournamentResult = new HashMap<>();
			players = new Players();
			players.addPlayers(MANAN_PLAYER);
			players.addPlayers(SHALIN_PLAYER);
			setFrameValidate(playerMenu());
		}else if (actionName.equals(editMapBtnName)) {
			setFrameValidate(editMapPanel());
		} else if (actionName.equals(editExistingMapBtnName)) {
			setFrameValidate(editExistingMapPanel());
		} else if (actionName.equals(createNewMapBtnName)) {
			setFrameValidate(createMapPanel());
		} else if (actionName.equals(exitBtnName)) {
			System.exit(0);
		} else if (actionName.equals("Start Game")) {
			if (randomMap && !tournamentModeOn) {
				startPlayingGame();
			} else if(tournamentModeOn){
				boolean tempFlag = true;
				for(int i= 0;i< mapFilePath.length;i++) {
					ArmiesSelection armies = new ArmiesSelection(playerPlaying);
					InitializeData initializeData = new InitializeData( mapFilePath[i].getPath(), playerPlaying , armies.getPlayerArmies(), players);
					boolean isEditMapValid = initializeData.generateData();
					if (!isEditMapValid) {
						tempFlag = false;
						break;
					} 
				}
				if(tempFlag) {
					startPlayingGame();   
				}
			} else {
				if (StringUtils.isNotEmpty(existingMapFilePath)) {
					for(int i = 0;i<players.getPlayerList().size();i++) {
						players.getPlayerType().put(players.getPlayerList().get(i),playersListDropDown.get(i).getItemAt(playersListDropDown.get(i).getSelectedIndex()));
					}
					ArmiesSelection armies = new ArmiesSelection(playerPlaying);
					InitializeData initializeData = new InitializeData( existingMapFilePath, playerPlaying , armies.getPlayerArmies(), players);
					boolean isEditMapValid = initializeData.generateData();
					if (isEditMapValid) {
						continent = initializeData.getContinent();
						players = initializeData.getPlayers();
						territory = initializeData.getTerritory();
						observerSubject.setPlayerLog();
						if(players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(HUMAN_TYPE)) {
							observerSubject.setReinforcementMsg(REINFORCEMENT_MSG);
						}
						setFrameValidate(gameView());
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
		} else if (actionName.equalsIgnoreCase(saveBtnName)) {
			SaveAndLoadGame saveAndLoadGame = new SaveAndLoadGame(players, continent, territory, playerTurn);
			if(saveAndLoadGame.saveGame()) {
				JOptionPane.showMessageDialog(frame, "Game Saved", "Game Saved", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame, "Unable to Save Game", "Game Save", JOptionPane.ERROR_MESSAGE);
			}
		} else if (actionName.equals(loadSavedGameName)) {
			SaveAndLoadGame saveAndLoadGame = new SaveAndLoadGame();
			if(saveAndLoadGame.loadGame()) {
				players = saveAndLoadGame.getPlayers();
				continent = saveAndLoadGame.getContinent();
				territory = saveAndLoadGame.getTerritory();
				playerTurn = saveAndLoadGame.getPlayerTurn();
				setFrameValidate(gameView());
				observerSubject.setPlayerLog();
				displayTerritoryDetails();
				if (players.getCurrentPhase().equalsIgnoreCase("reinforcement")) {
					setButtonEnable(true, false, false, false, false);
					observerSubject.setReinforcementMsg("Reinforcement Force Started");
					gamePanel.remove(2);
					gamePanel.invalidate();
					gamePanel.validate();
					if(players.getPlayerList().size() >= 1 && players.getCards().containsValue(players.getPlayerList().get(playerTurn))) {
						gamePanel.add(countryScreen("tradeIn"),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.LINE_END, 0.5, 0.5, 2, 0));
					} else {
						gamePanel.add(countryScreen("No Phase"),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.LINE_END, 0.5, 0.5, 2, 0));
					}
					gamePanel.invalidate();
					gamePanel.validate();
				} else if (players.getCurrentPhase().equalsIgnoreCase("attack")) {
					setButtonEnable(false, true, true, false, false);
					observerSubject.setAttackMsg("Attack Phase Started");
					gamePanel.remove(2);
					gamePanel.invalidate();
					gamePanel.validate();
					gamePanel.add(countryScreen("attack"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, GridBagConstraints.LINE_END, 0.5, 0.5, 2, 0));
					gamePanel.invalidate();
					gamePanel.validate();
				} else if (players.getCurrentPhase().equalsIgnoreCase("fortification")) {
					setButtonEnable(false, false, false, true, true);
					observerSubject.setFortificationMessage("Fortification Phase Started ");
					gamePanel.remove(2);
					gamePanel.invalidate();
					gamePanel.validate();
					gamePanel.add(countryScreen("fortification"), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, GridBagConstraints.LINE_END, 0.5, 0.5, 2, 0));
					gamePanel.invalidate();
					gamePanel.validate();
					startFortificationPhase();
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Unable to Load Save Game", "Load Save Game", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	/**
	 * This Method is used to start  playing game with map in Single mode or Tournament Mode. 
	 */
	public void startPlayingGame() {
		String currentFileName = "";
		if(tournamentModeOn) {
			currentFileName = mapFilePath[fileTurn].getPath();
		} else {
			currentFileName = mapFilePath[0].getPath();	    
		}
		continent = new Continent();
		territory = new Territory();
		if(fileTurn == 0 && gameTurn == 0) {
			for(int i = 0;i<players.getPlayerList().size();i++) {
				players.getPlayerType().put(players.getPlayerList().get(i),playersListDropDown.get(i).getItemAt(playersListDropDown.get(i).getSelectedIndex()));
			}
			tempPlayerList = players.getPlayerList();
		} else {
			players = new Players();
			players.setPlayerList(tempPlayerList);
			for(int i = 0;i<players.getPlayerList().size();i++) {
				players.getPlayerType().put(players.getPlayerList().get(i),playersListDropDown.get(i).getItemAt(playersListDropDown.get(i).getSelectedIndex()));
			}
			playerTurn = 0;
		}
		if (mapFilePath != null  && StringUtils.isNotEmpty(mapFilePath[fileTurn].getName())) {	  
			ArmiesSelection armies = new ArmiesSelection(players.getPlayerList().size());
			InitializeData initializeData = new InitializeData(currentFileName , players.getPlayerList().size() , armies.getPlayerArmies(), players);
			boolean isMapValid = initializeData.generateData();
			if(isMapValid) {
				continent = initializeData.getContinent();
				players = initializeData.getPlayers();
				territory = initializeData.getTerritory();
				observerSubject.setPlayerLog();
				if(players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(HUMAN_TYPE)) {
					observerSubject.setReinforcementMsg(REINFORCEMENT_MSG);
				}
				setFrameValidate(gameView());
			} else {
				JOptionPane.showMessageDialog(frame, "Please Check data Again.", CONTENT_INVALID, JOptionPane.ERROR_MESSAGE);   
			}
		} else {
			JOptionPane.showMessageDialog(frame, "No Map Selected.", CONTENT_INVALID, JOptionPane.ERROR_MESSAGE);    
		}
	}
	/**
	 * This method checks whether tournament ends or not. If tournament not end, it start playing game again with map.
	 */
	public void tournamentEnd() {
		if(noOfGamesDropDown != null) {
			int gameNo = noOfGamesDropDown.getItemAt(noOfGamesDropDown.getSelectedIndex());
			if(gameTurn >= gameNo) {
				tournamentResult.put(fileTurn, mapResult);
				mapResult = new HashMap<>();
				fileTurn++;
				gameTurn=0;
			} 
			if(fileTurn >= mapFilePath.length) {
				setButtonEnable(false, false, false, false, false);
				endTurnBtn.setEnabled(false);
				saveBtn.setEnabled(false);
				logArea.setText("");
				fileTurn = 0;
				territoryDetails.setText("");
				for(int i=0;i<mapFilePath.length;i++) {
					territoryDetails.append("Map  "+(i+1)+" : " + mapFilePath[i].getPath()+" \n");
					LOGGER.info("Map  "+(i+1)+" : " + mapFilePath[i].getPath());
				}
				for(Entry<String, String> entry : players.getPlayerType().entrySet()) {
					territoryDetails.append(entry.getKey()+" : "+ entry.getValue()+"\n");
					LOGGER.info(entry.getKey()+" : "+ entry.getValue());
				}
				territoryDetails.append("Game : " + noOfGamesDropDown.getItemAt(noOfGamesDropDown.getSelectedIndex())+" \n");
				territoryDetails.append("Turns : " + noOfTurnsDropDown.getItemAt(noOfTurnsDropDown.getSelectedIndex())+" \n");
				LOGGER.info("Game : " + noOfGamesDropDown.getItemAt(noOfGamesDropDown.getSelectedIndex()));
				LOGGER.info("Turns : " + noOfTurnsDropDown.getItemAt(noOfTurnsDropDown.getSelectedIndex()));
				for(Entry<Integer, Map<Integer, String>> entry : tournamentResult.entrySet()) {
					territoryDetails.append("*************************\n");
					territoryDetails.append("Map " + (entry.getKey()+1));
					territoryDetails.append("\n*************************\n");

					for(Entry<Integer, String> newEntry : entry.getValue().entrySet()) {
						String temp = players.getPlayerType().get(newEntry.getValue()) != null ? players.getPlayerType().get(newEntry.getValue()) : "Draw"; 
						territoryDetails.append(" : Game " + (newEntry.getKey()+1) + " : "+ temp+"\n");
						LOGGER.info("Map " + (entry.getKey()+1) + " : Game " + (newEntry.getKey()+1) + " : "+ temp);
					}
				}
				setFrameValidate(mainMenu(frame));
			} else {		    
				startPlayingGame();
			}
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
		int defenderDie = defenderDiceDropDown.getSelectedIndex() != -1 ? defenderDiceDropDown.getItemAt(defenderDiceDropDown.getSelectedIndex()) : 0;
		String allOutValue = allOutDropDown.getItemAt(allOutDropDown.getSelectedIndex());
		String message1 = "Before Attack \n "+fromTerritory+" : "+territory.getTerritoryArmy().get(fromTerritory) +"\n"+toTerritory+" : "+territory.getTerritoryArmy().get(toTerritory)+"\n";
		if(StringUtils.isNotEmpty(allOutValue) && StringUtils.isNotEmpty(fromTerritory) && StringUtils.isNotEmpty(toTerritory) && attackerDie > 0 && defenderDie >0) {
			context = new Context(players);
			if(allOutValue.equalsIgnoreCase("No")) {
				context.executeAttack(territory, fromTerritory , toTerritory, attackerDie, defenderDie);
				String message2 = "Attacker Die \n" + players.getAttackerMsg()+"\n Defender Die \n" +players.getDefenderMsg();
				String message3 = message1 + message2 + "After Attack \n " + fromTerritory +" : "+territory.getTerritoryArmy().get(fromTerritory) +"\n"+toTerritory+" : "+territory.getTerritoryArmy().get(toTerritory)+"\n";
				observerSubject.setAttackMsg(message3);
			} else {
				observerSubject.setAttackMsg("All Out Mode is Selected");
				observerSubject.setAttackMsg("Before Attack \n " + fromTerritory +" : "+territory.getTerritoryArmy().get(fromTerritory) +"\n"+toTerritory+" : "+territory.getTerritoryArmy().get(toTerritory)+"\n");
				do {
					attackerDie = territory.getTerritoryArmy().get(fromTerritory) > 4 ? 3 : (territory.getTerritoryArmy().get(fromTerritory) - 1);
					defenderDie = territory.getTerritoryArmy().get(toTerritory) > 3 ? 3 : territory.getTerritoryArmy().get(toTerritory);
					context.executeAttack(territory, fromTerritory, toTerritory, attackerDie, defenderDie);		    
				} while(territory.getTerritoryArmy().get(fromTerritory) > 1 && territory.getTerritoryArmy().get(toTerritory) > 0 );
				observerSubject.setAttackMsg("After Attack \n " + fromTerritory +" : "+territory.getTerritoryArmy().get(fromTerritory) +"\n"+toTerritory+" : "+territory.getTerritoryArmy().get(toTerritory)+"\n");
			}
			if(players.isAttackWon()) {
				moveArmyAfterAttack();
				if(checkPlayerWonGame()) {
					JOptionPane.showMessageDialog(frame, players.getPlayerList().get(playerTurn)+ "  Won Game", "Game Won",JOptionPane.INFORMATION_MESSAGE);
					setFrameValidate(mainMenu(frame));
				}
			}
			attackPanelReset();
		} else {
			JOptionPane.showMessageDialog(frame, "Please Select Value Properly");
			observerSubject.setAttackMsg("Please Select Value Properly");
		}
	}
	/**
	 * This Method check whether player has won game or not
	 * @return true if player won game.
	 */
	public boolean checkPlayerWonGame() {
		String name = players.getPlayerList().get(playerTurn);
		int totalTerritory = 0;
		for(Entry<String, ArrayList<String>> entry : players.getPlayerContinent().get(name).getContinentOwnedterritory().entrySet()) {
			totalTerritory += entry.getValue().size();
		}
		if((totalTerritory*100)/territory.getTerritoryList().size() == 100) {
			gameWon = true;
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
		territoryADropDown.setEnabled(true);
		territoryBDropDown.setEnabled(true);
		attackerDiceDropDown.setEnabled(true);
		defenderDiceDropDown.setEnabled(true);
		removeModelElement();
		updateTerritoryAList();
		updateContinentInfoList();
		territoryADropDown.removeAllItems();
		territoryADropDown.addItem("");
		for(Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
			if(entry.getValue().equalsIgnoreCase(players.getPlayers(playerTurn)) && territory.getTerritoryArmy().get(entry.getKey()) > 1) {
				territoryADropDown.addItem(entry.getKey());
			}
		}
		territoryBDropDown.removeAllItems();
		attackerDiceDropDown.removeAllItems();
		defenderDiceDropDown.removeAllItems();
		displayTerritoryDetails();
		setBotAttackPanel();

	}
	private void removeModelElement() {
		territoryAModel.removeAllElements();
		territoryBModel.removeAllElements();
		territoryInfoModel.removeAllElements();
		continentInfoModel.removeAllElements();	
	}
	/**
	 * method used to do reinforcement on territory.
	 * @param flag used to identify whether player can do reinforcement or not.
	 */
	public void goForReinforcement(boolean flag) {
		String name = players.getPlayerList().get(playerTurn);
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
					observerSubject.setReinforcementMsg("Army Placed on   " + terrName[0]  + " is "+ output);
					removeModelElement();
					updateTerritoryAList();
					updateContinentInfoList();
					enableReinforcementBtn();
					observerSubject.setPlayerLog();
				} else {
					observerSubject.setReinforcementMsg("Input armies are out of range ot not properly enter");
					goForReinforcement(false);
				}
			} else {
				observerSubject.setReinforcementMsg("Input armies  entered is null or cancel button is clicked");
				JOptionPane.showMessageDialog(null, "Input armies  entered is null or cancel button is clicked",CONTENT_INVALID,JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	/**
	 * method used to enable reinforcement button and disable fortify button if player has 1 or more army
	 * It also used to disable reinforcement button and enable fortify button if player has no army  
	 */
	public void enableReinforcementBtn() {
		if(players.getPlayerList().size() >= 1) {
			String name = players.getPlayerList().get(playerTurn);
			if(StringUtils.isNotEmpty(name)) {
				if(players.getPlayerArmy(name) == 0 && !hasPlayerOwnedMoreCards()) {
					players.setCurrentPhase("Attack");
					observerSubject.setPlayerLog();
					observerSubject.setAttackMsg("Attack Phase Started");
					setButtonEnable(false, true, true, false, false);
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
	}
	/**
	 * This Method check whether player has more than 5 cards
	 * @return true if player owned card.
	 */
	public boolean hasPlayerOwnedMoreCards() {
		int cardCount = 0;
		for(Entry< String, String> entry : players.getCards().entrySet()) {
			if(entry.getValue().equalsIgnoreCase(players.getPlayerList().get(playerTurn))) {
				cardCount++;
			}
		}
		return cardCount >= 5 ? true :  false;
	}
	/**
	 * method use to enable list of current territory owned by current player to move army from one  territory to another.   
	 */
	public void startFortificationPhase() {
		players.setCurrentPhase("Fortification");
		observerSubject.setPlayerLog();
		attackBtn.setEnabled(false);
		attackSkipBtn.setEnabled(false);
		addTerritoryADropDown();
	}
	/**
	 * This Method is used for fortification for Bot Player.
	 */
	public void setBotFortification() {
		players.setCurrentPhase("Fortification");
		observerSubject.setPlayerLog();
		fortifyBtn.setEnabled(false);
		fortifySkipBtn.setEnabled(false);
		boolean tempFlag = true;
		if(players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(RANDOM_TYPE)){
			int rand = new Random().nextInt(5)+1;
			tempFlag = rand  >= 2 ? true : false;
		}
		if(!tempFlag) {
			LOGGER.info("No Fortification");
		}
		if (!players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(HUMAN_TYPE) && tempFlag) {
			context = new Context(players);
			context.executeBotFortification(players.getPlayerList().get(playerTurn), territory);
			observerSubject.setFortificationMessage(players.getFortificationMsg());
			players.setFortificationMsg("");
			if(!checkPlayerWonGame()) {
				observerSubject.setFortificationMessage("Fortification Phase End");
			}
			changePlayerTurn();
		} 
		if(!tempFlag) {
			changePlayerTurn();
		}
		/*if(players.getPlayerList().size() >= 1 && players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(RANDOM_TYPE) && tempFlag) {
	    setBotFortification();
	}*/
		removeModelElement();
		updateTerritoryAList();
		updateContinentInfoList();
	}

	/**
	 * Method is used to change the Turn of player when End Turn Button is Clicked.
	 */
	public void changePlayerTurn() {
		endTurnBtn.setEnabled(false);
		playerTurn++;
		playerTurn = playerTurn < players.getPlayerList().size() ? playerTurn : 0;
		playerHasTerritory();
		if(!players.getPlayerList().isEmpty() && players.getPlayerList().contains("Neutral Player")) {
			playerTurn = players.getPlayerList().get(playerTurn).equalsIgnoreCase("Neutral Player") ? 0 : playerTurn;
		}
		if(players.isWonCard() ) {
			LOGGER.info("You Have Won 1 Card");
		} 
		players.setWonCard(false);
		context = new Context(players);
		if(players.getPlayerList().size() >= 1) {
			context.executeReinforcementArmy(players.getPlayerList().get(playerTurn),continent);
		}
		removeElements();
		updateTerritoryAList();
		updateContinentInfoList();    
		enableReinforcementBtn();
		observerSubject.setPlayerLog();
		try {
			gamePanel.remove(2);
			gamePanel.invalidate();
			gamePanel.validate();
		} catch (Exception e) {
		}

		if(players.getPlayerList().size() >= 1 && players.getCards().containsValue(players.getPlayerList().get(playerTurn))) {
			gamePanel.add(countryScreen("tradeIn"),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.LINE_END, 0.5, 0.5, 2, 0));
		} else {
			gamePanel.add(countryScreen("No Phase"),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.LINE_END, 0.5, 0.5, 2, 0));
		}
		gamePanel.invalidate();
		gamePanel.validate();
		playerOwnedCards();
		if(players.getPlayerList().size() > 0 && !players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(HUMAN_TYPE)) {
			checkTurnNumber();
		} else if(players.getPlayerList().size() > 0 && players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(HUMAN_TYPE)){
			observerSubject.setReinforcementMsg(REINFORCEMENT_MSG);
		}
	}
	/**
	 * This method is used to check whether game is draw or not. If it's draw, it will go for playing game with map otherwise it will continue with current game only.
	 */
	public void checkTurnNumber() {
		if(tournamentModeOn) {
			turnNumber++;
			if(turnNumber >= noOfTurnsDropDown.getItemAt(noOfTurnsDropDown.getSelectedIndex())) {
				mapResult.put(gameTurn, "Draw");
				gameTurn++;
				LOGGER.info("********** DRAW ***************");
				turnNumber = 0;
				tournamentEnd();
			} else {
				setBotReinforcement();
			}
		} else {
			setBotReinforcement();
		}
	}
	public int getGameTurn() {
		return gameTurn;
	}
	public void setGameTurn(int gameTurn) {
		this.gameTurn = gameTurn;
	}
	public int getTurnNumber() {
		return turnNumber;
	}
	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}
	public JComboBox<Integer> getNoOfGamesDropDown() {
		return noOfGamesDropDown;
	}
	public void setNoOfGamesDropDown(JComboBox<Integer> noOfGamesDropDown) {
		this.noOfGamesDropDown = noOfGamesDropDown;
	}
	public JComboBox<Integer> getNoOfTurnsDropDown() {
		return noOfTurnsDropDown;
	}
	public void setNoOfTurnsDropDown(JComboBox<Integer> noOfTurnsDropDown) {
		this.noOfTurnsDropDown = noOfTurnsDropDown;
	}
	public JLabel getNoOfGames() {
		return noOfGames;
	}
	public void setNoOfGames(JLabel noOfGames) {
		this.noOfGames = noOfGames;
	}
	public JLabel getNoOfTurns() {
		return noOfTurns;
	}
	public void setNoOfTurns(JLabel noOfTurns) {
		this.noOfTurns = noOfTurns;
	}
	/**
	 * This Method Check Whether Player has Territories or not.
	 */
	public void playerHasTerritory() {
		if(!players.getPlayerList().isEmpty() && !territory.getTerritoryUser().containsValue(players.getPlayerList().get(playerTurn))) {
			changePlayerTurn();
		} 
	}
	/**
	 * This Method Check Whether Player has Territories or not for Junit.
	 */
	public boolean testPlayerHasTerritory() {
		return players.getPlayerList().isEmpty() && !territory.getTerritoryUser().containsValue(players.getPlayerList().get(playerTurn)) ? false : true;
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
		if(cardCount >= 5 && players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(HUMAN_TYPE)) {
			JOptionPane.showMessageDialog(frame, players.getPlayerList().get(playerTurn) +" you need to Trade in Some Cards", "Need Card Trade In", JOptionPane.ERROR_MESSAGE);
		} else if (players.getPlayerList().size() > 0 && players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(AGGRESSIVE_TYPE) && cardCount >=3) {
			observerSubject.setBotTradeInMsg("Started Trading A Card");
		}
	}

	/**
	 * This method remove elements from  territoryAModel, territoryBModel, territoryInfoModel, continentInfoModel, territoryADropDown and territoryBDropDown
	 */
	public void removeElements() {
		removeModelElement();
		territoryADropDown.removeAllItems();
		territoryBDropDown.removeAllItems();
	}
	/**
	 * Method Allow Player to do Fortification Phase.
	 */
	public void goForFortification() {
		observerSubject.setAttackMsg("Attack Phase End ");
		observerSubject.setFortificationMessage("Fortification Phase Started");

		if(!players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(HUMAN_TYPE)) {
			setBotFortification();
		}
		else {
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
					fortifyBtn.setEnabled(false);
					fortErrorMsg.setText("You can Move upto " + (territory.getTerritoryArmy().get(fromTerritory)-1) + " Army");
				} else {
					JOptionPane.showMessageDialog(frame, "Armies unable to move from " + fromTerritory + " to " + toTerritory +". Please enter no. of  Armies again", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				checkFortificationStatus();
			} else {
				JOptionPane.showMessageDialog(null, CONTENT_INVALID);
			}    
		}

	}
	/**
	 * method used to check whether current player can go for fortification phase more or not.
	 */
	public void checkFortificationStatus() {
		boolean flag = false;
		for(Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
			if(entry.getValue().equalsIgnoreCase(players.getPlayerList().get(playerTurn)) && territory.getTerritoryArmy().get(entry.getKey()) > 1) {
				flag = true;
				break;
			}
		}
		if(!flag) {
			fortifyBtn.setEnabled(false);
		}
		else {
			removeModelElement();
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
			int count = 0;
			for(Entry<String, ArrayList<String>> entry : players.getPlayerContinent().get(name).getContinentOwnedterritory().entrySet()) {
				if(entry.getValue().size() == continent.getContTerrValue().get(entry.getKey())) {
					count ++;
				}
				totalTerritory += entry.getValue().size();
				for(int j =0;j <entry.getValue().size() ; j++ )
					totalArmyOwned += territory.getTerritoryArmy().get(entry.getValue().get(j));
			}
			territoryDetails.append("Territory Owned : " +  totalTerritory+ "\n");
			territoryDetails.append("Continent Acquired : " + count+"\n");
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
			if(entry.getValue().equals(players.getPlayerList().get(playerTurn))){
				territoryADropDown.addItem(entry.getKey());
			}
		}
	}
	/**
	 * method use to display list of adjacent territory owned by current player to move army from selected  territory to adjacent one.   
	 * @param phase current phase.
	 */
	public void addTerritoryBDropDown(String phase) {
		territoryBDropDown.removeAllItems();
		String dropDownAValue =  territoryADropDown.getItemAt(territoryADropDown.getSelectedIndex());
		if(StringUtils.isNotEmpty(dropDownAValue)) {
			for(int i = 0 ; i < territory.getAdjacentTerritory().get(dropDownAValue).size() ; i++) {
				String terrName = territory.getAdjacentTerritory().get(dropDownAValue).get(i);
				if(players.getPlayerList().get(playerTurn).equals(territory.getTerritoryUser().get(terrName)) && phase.equalsIgnoreCase("fortification")) {
					territoryBDropDown.addItem(terrName);
				} else if(!players.getPlayerList().get(playerTurn).equals(territory.getTerritoryUser().get(terrName)) && phase.equalsIgnoreCase("attack")) {
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

	/**
	 * event Listener
	 */
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
		if (observerSubject.getMessageFlag()) {
			log.append(observerSubject.getMessage());
			observerSubject.setMessageFlag(false);
		}
	}
	/**
	 * This method is used to print current player detail in Log Area
	 */
	@Override
	public void playerLogUpdate() {
		if(players.getPlayerList().size() >= 1) {
			logArea.setText("");
			logArea.append("Current Player : " + players.getPlayerList().get(playerTurn) + "\n");
			logArea.append("Current Armies : " + players.getPlayerArmy(players.getPlayerList().get(playerTurn)) + "\n");
			logArea.append("Current Phase : " + players.getCurrentPhase()+"\n");
			logArea.append("Type : " + players.getPlayerType().get(players.getPlayerList().get(playerTurn)));
			LOGGER.info("Current Player : " + players.getPlayerList().get(playerTurn));
		}
	}
	/**
	 * This Method print action done by player during reinforcement phase
	 */
	@Override
	public void reinforcementUpdate() {
		setLogMessage(observerSubject.getReinforcementMsg());
	}
	/**
	 * This Method print action done by player during fortification phase
	 */
	@Override
	public void fortificationUpdate() {
		setLogMessage(observerSubject.getFortificationMessage());
		displayTerritoryDetails();
	}
	/**
	 * This Method print action done by player during Attack phase
	 */
	@Override
	public void attackUpdate() {
		log.append(observerSubject.getAttackMsg() + "\n");
		LOGGER.info(observerSubject.getAttackMsg());
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
			ArrayList<String> cardTerrList= new ArrayList<>();
			cardTerrList.add(card1[1].trim());
			cardTerrList.add(card2[1].trim());
			cardTerrList.add(card3[1].trim());
			if(checkTradeInCard(cardList)) {
				int army = tradeInArmy();
				players.setTradeInArmies(army);
				players.setTradeIn();
				setLogMessage("\nTraded Cards are " +card1[0].trim()+  " " +card1[1].trim());
				setLogMessage("Traded Cards are " +card2[0].trim()+  " " +card2[1].trim());
				setLogMessage("Traded Cards are " +card3[0].trim()+  " " +card3[1].trim());
				setLogMessage("Number of Trade In : "+players.getTradeIn());
				setLogMessage("You Got " + army+" Amies");
				players.updateArmy(players.getPlayerList().get(playerTurn), army, "ADD");
				for (int i = 0; i < cardTerrList.size(); i++) {
					if(!cardList.get(i).equalsIgnoreCase(WILD_CARD) && territory.getTerritoryUser().get(cardTerrList.get(i)).equalsIgnoreCase(players.getPlayerList().get(playerTurn))) {
						players.updateArmy(players.getPlayerList().get(playerTurn), 2, "ADD");
						setLogMessage("You Got Additional 2 Amies");
						break;
					}
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
					if(players.getPlayerList().get(playerTurn).equalsIgnoreCase(entry.getValue())) {
						territoryADropDown.addItem(players.getTerritoryCards().get(entry.getKey())+" -- "+entry.getKey());
					}
				}
				if(!players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(HUMAN_TYPE)) {
					JOptionPane.showMessageDialog(frame, "Trade In Completed Successfully. You Got "+army+" armies", "Armies Generated",JOptionPane.INFORMATION_MESSAGE);    
				}
				LOGGER.info("Trade In Completed Successfully. You Got "+army+" armies");
				observerSubject.setPlayerLog();
				displayTerritoryDetails();
				setLogMessage("Trade In Done");
			} else {
				setLogMessage("Select proper Combination of Cards");
			}

		} else {
			setLogMessage("Select proper Combination of Cards");
		}
	}
	/**
	 * This Method generate armies when players trade in cards
	 * @return tradeInarmies.
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
	 * This Method used to generate Combination of cards for Human as well as for BOT
	 * @param cardList list of cards.
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
		int count=0;
		for (int i = 0; i < cardList.size(); i++) {
			String[] tempSplit = cardList.get(i).split("-");    
			if (list.contains(tempSplit[0])) {
				list.remove(tempSplit[0]);
				count++;
			}
		}
		if(count == 3) {
			for(int i=0;i<cardList.size();i++) {
				String[] tempSplit = cardList.get(i).split("-");
				if(cardList.size() == 3) {
					break;
				}
				if(tempSplit[0].equalsIgnoreCase(card1) || tempSplit[0].equalsIgnoreCase(card2) || tempSplit[0].equalsIgnoreCase(card3)) {
					tempCards.add(cardList.get(i));
				}
			}
		}
		if(players.getPlayerType().get(players.getPlayerList().get(playerTurn)).equalsIgnoreCase(HUMAN_TYPE)){
			return list.isEmpty() ? true : false;
		}  else {
			return count == 3 ? true : false;
		}

	}
	/**
	 * This method is used to Trade a card for BOT Player
	 */
	@Override
	public void botTradeInCardUpdate() {
		log.append(observerSubject.getTradeInMsg() + "\n");
		ArrayList<String> cardList= new ArrayList<>();
		for(Entry<String, String> entry : players.getTerritoryCards().entrySet()) {
			if(territory.getTerritoryUser().get(entry.getKey().trim()).equalsIgnoreCase(players.getPlayerList().get(playerTurn))) {
				cardList.add(entry.getValue()+"-"+entry.getKey());   
			}
		}
		if(checkTradeInCard(cardList)) {
			String[] card1 = cardList.get(0).split("-");
			String[] card2 = cardList.get(1).split("-");
			String[] card3 = cardList.get(2).split("-");
			cardList= new ArrayList<>();
			cardList.add(card1[0].trim());
			cardList.add(card2[0].trim());
			cardList.add(card3[0].trim());
			ArrayList<String> cardTerrList= new ArrayList<>();
			cardTerrList.add(card1[1].trim());
			cardTerrList.add(card2[1].trim());
			cardTerrList.add(card3[1].trim());

			int army = tradeInArmy();
			players.setTradeInArmies(army);
			players.setTradeIn();
			setLogMessage("\nTraded Cards are " +card1[0].trim()+  " " +card1[1].trim());
			setLogMessage("Traded Cards are " +card2[0].trim()+  " " +card2[1].trim());
			setLogMessage("Traded Cards are " +card3[0].trim()+  " " +card3[1].trim());
			setLogMessage("Number of Trade In : "+players.getTradeIn());
			setLogMessage("You Got " + army+" Amies");
			players.updateArmy(players.getPlayerList().get(playerTurn), army, "ADD");
			for (int i = 0; i < cardTerrList.size(); i++) {
				if(!cardList.get(i).equalsIgnoreCase(WILD_CARD) && territory.getTerritoryUser().get(cardTerrList.get(i)).equalsIgnoreCase(players.getPlayerList().get(playerTurn))) {
					players.updateArmy(players.getPlayerList().get(playerTurn), 2, "ADD");
					setLogMessage("You Got Additional 2 Amies");
					break;
				}
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
			observerSubject.setPlayerLog();
			displayTerritoryDetails();
			setLogMessage("Trade In Done");
		} else {
			setLogMessage("Select proper Combination of Cards");
		}
	}
	/**
	 * This method is used to print message in log area as well as in LOGGER
	 * @param message message which need to print in log and LOGGER
	 */
	public void setLogMessage(String message) {
		log.append(message+"\n");
		LOGGER.info(message);
	}
	/**
	 *  This method is used to disable or enable reinforce, attack, skip attack, fortify and skip fortify button
	 * @param reinforce enable reinforce button
	 * @param attack enable attack button
	 * @param attackSkip enable skip attack button
	 * @param fortify enable fortify button
	 * @param fortifySkip enable fortify skip button
	 */
	public void setButtonEnable(boolean reinforce, boolean attack, boolean attackSkip, boolean fortify, boolean fortifySkip) {
		reinforceBtn.setEnabled(reinforce);
		attackBtn.setEnabled(attack);
		attackSkipBtn.setEnabled(attackSkip);
		fortifyBtn.setEnabled(fortify);
		fortifySkipBtn.setEnabled(fortifySkip);
	}

	/**
	 * return file path
	 * @return mapFilePath
	 */
	public File[] getMapFilePath() {
		return mapFilePath;
	}

	/**
	 * set file path
	 * @param mapFilePath
	 */
	public void setMapFilePath(File[] mapFilePath) {
		this.mapFilePath = mapFilePath;
	}

	/**
	 * return true if tournament mode is on
	 * @return true if tournament mode is on
	 */
	public boolean isTournamentModeOn() {
		return tournamentModeOn;
	}

	/**
	 * set tournament mode 
	 * @param tournamentModeOn true/false
	 */
	public void setTournamentModeOn(boolean tournamentModeOn) {
		this.tournamentModeOn = tournamentModeOn;
	}

	/**
	 * this method is used to getPlayersListDropDown
	 * @return PlayersListDropDown
	 */
	public ArrayList<JComboBox<String>> getPlayersListDropDown() {
		return playersListDropDown;
	}

	/**
	 * This method sets the PlayersListDropDown
	 * @param playersListDropDown PlayersListDropDown
	 */
	public void setPlayersListDropDown(ArrayList<JComboBox<String>> playersListDropDown) {
		this.playersListDropDown = playersListDropDown;
	}

}
