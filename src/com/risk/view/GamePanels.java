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
    private JButton fortifyBtn;
    private JButton endTurnBtn;
    private JButton menuBtn;
    private JButton turnInBtn;
    private JButton createNewMapBtn;
    private JButton editExistingMapBtn;
    private JButton newButton;
    private JButton exitButton;
    private JButton twoPlayersBtn;
    private JButton threePlayersBtn;
    private JButton fourPlayersBtn;
    private JButton fivePlayersBtn;
    private JButton backBtn;
    private JButton editButton;
    private JButton startGameBtn;

    private JTextArea territoryDetails = new JTextArea(10,4);
    private JTextArea logArea = new JTextArea(4,20);
;
    private JList<String> cardsList;
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
    private SpinnerNumberModel selectArmyModel;
    private JLabel fortErrorMsg;
    private Context context;
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
	// Creates the panel
	JPanel playerPanel = new JPanel();
	// Sets Layout
	LayoutManager playerLayout = new GridLayout(6, 1, 5, 5);
	playerPanel.setLayout(playerLayout);

	JLabel playerCountLabel = new JLabel("Number of Players : ");
	twoPlayersBtn = new JButton("Two");
	threePlayersBtn = new JButton("Three");
	fourPlayersBtn = new JButton("Four");
	fivePlayersBtn = new JButton("Five");
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

	// Creates the panel
	JPanel editMapPanel = new JPanel();
	// Sets Layout
	GridLayout editMapLayout = new GridLayout(2, 1, 5, 5);
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
	JPanel gamePanel = new JPanel();
	frame.setLayout(mainLayout);

	gamePanel.add(displayLog(),setGridBagConstraints(new Insets(25, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.LINE_START, 0.5, 0.5, 0, 0));
	gamePanel.add(eventScreen(),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.CENTER, 0.5, 0.5, 1, 0));
	gamePanel.add(countryScreen(),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.LINE_END, 0.5, 0.5, 2, 0));

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
	//updateLogArea();
//	observerSubject.setState(players.getPlayers(0), true);

	JScrollPane jScrollPane = new JScrollPane(log);
	jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

	logPanel.add(menuBtn, setGridBagConstraints(new Insets(5, 0, 20, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 0, 0));
	logPanel.add(logScrollPane, setGridBagConstraints(new Insets(0,5, 5, 5), GridBagConstraints.BOTH, 0.5,5, 0, 1));
	logPanel.add(jScrollPane, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 5, 0, 2));

	return logPanel;
    }

    public static void riskLogger(String logString){
	/*log.append(logString + "\n");*/
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
	attackBtn = new JButton("Attack!");
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

	cardsList = new JList<>();
	cardsList.setLayoutOrientation(JList.VERTICAL_WRAP);
	cardsList.setVisibleRowCount(6);
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

	//eventPanel.add(cardsList, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 5, 0, 2));
	//eventPanel.add(turnInBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 3));
	eventPanel.add(selectedLabel, setGridBagConstraints(new Insets(25, 5, 21, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 4));
	eventPanel.add(continentScrollPane, setGridBagConstraints(new Insets(5, 5, 25, 5), GridBagConstraints.BOTH, 0.5, 10, 0, 5));
	eventPanel.add(reinforceBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 6));
	eventPanel.add(targetLabel, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 7));
	eventPanel.add(territoryScrollPane, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 10, 0, 8));
	eventPanel.add(attackBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 9));
	eventPanel.add(fortifyBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 10));
	eventPanel.add(endTurnBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 11));

	return eventPanel;
    }

    /**
     * Display various portion of game play such as list of continent and territory. And also details about army in particular territory and also which player occupied it.
     * Section for movement of Army for Fortification Phase.
     * @return countryPanel which consist detail view continent and territory
     */
    protected JPanel countryScreen(){

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
/*	territoryInfoList.addListSelectionListener(new ListSelectionListener() {

	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		displayTerritoryDetails();
	    }
	});*/

	JScrollPane continentInfoScrollPane = new JScrollPane(continentInfoList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JScrollPane territoryInfoScrollPane = new JScrollPane(territoryInfoList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	//territoryDetails = new JTextArea(4,1);
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
	countryPanel.add(fortificationPanel(), setGridBagConstraints(new Insets(5,5, 5, 5), GridBagConstraints.BOTH, 0.5,3, 0, 2));
	countryPanel.add(logScrollPane, setGridBagConstraints(new Insets(5,5, 5, 5), GridBagConstraints.BOTH, 0.5,3, 1, 2));
	return countryPanel;
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
		addTerritoryBDropDown();
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
     * @param players player object
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
	// Creates the panel
	JPanel menuPanel = new JPanel();
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

	if(actionName.equalsIgnoreCase(newBtnName)){
	    riskLogger("Play Game");
	    players = new Players();
	    players.addPlayers("Manan");
	    players.addPlayers("Shalin");
	    frame.setContentPane(playerMenu());
	    frame.invalidate();
	    frame.validate();
	} else if(actionName.equals(editMapBtnName)){					
	    frame.setContentPane(editMapPanel());
	    frame.invalidate();
	    frame.validate();

	} else if (actionName.equals(editExistingMapBtnName)) {
	    riskLogger("Editing Existing Map");
	    frame.setContentPane(editExistingMapPanel());
	    frame.invalidate();
	    frame.validate();

	} else if (actionName.equals(createNewMapBtnName)) {
	    riskLogger("Creating New Map");
	    frame.setContentPane(createMapPanel());
	    frame.invalidate();
	    frame.validate();

	} else if (actionName.equals(exitBtnName)){
	    riskLogger("Quit Game");
	    System.exit(0);
	    riskLogger("0");
	} else if (actionName.equals("Start Game")){
	    if(randomMap) {
		if(StringUtils.isNotEmpty(mapFilePath)) {
		    ArmiesSelection armies = new ArmiesSelection(playerPlaying);
		    InitializeData initializeData = new InitializeData(mapFilePath , playerPlaying , armies.getPlayerArmies(), players);
		    boolean isMapValid = initializeData.generateData();
		    if(isMapValid) {
			continent = initializeData.getContinent();
			players = initializeData.getPlayers();
			territory = initializeData.getTerritory();
			observerSubject.setState(players.getPlayers(0),true);
			observerSubject.setState("Reinforcement Force Started \n",false);
			frame.setContentPane(gameView());
			frame.invalidate();
			frame.validate();
		    } else {
			JOptionPane.showMessageDialog(frame, "Please Check data Again.", "Content Invalid", JOptionPane.ERROR_MESSAGE);   
		    }
		} else {
		    JOptionPane.showMessageDialog(frame, "No Map Selected.", "Content Invalid", JOptionPane.ERROR_MESSAGE);    
		}

	    } else {
		if(StringUtils.isNotEmpty(existingMapFilePath)) {
		    ArmiesSelection armies = new ArmiesSelection(playerPlaying); 
		    InitializeData initializeData = new InitializeData( existingMapFilePath, playerPlaying , armies.getPlayerArmies(), players);
		    boolean isEditMapValid = initializeData.generateData();
		    if(isEditMapValid) {
			continent = initializeData.getContinent();
			players = initializeData.getPlayers();
			territory = initializeData.getTerritory();
			observerSubject.setState(players.getPlayers(0),true);
			observerSubject.setState("Reinforcement Force Started \n",false);
			//updateLogArea();
			frame.setContentPane(gameView());
			frame.invalidate();
			frame.validate();
		    } else {
			JOptionPane.showMessageDialog(frame, "Please Check data Again.", "Content Invalid", JOptionPane.ERROR_MESSAGE);   
		    }
		} else {
		    JOptionPane.showMessageDialog(frame, "No Map Edited Previously.", "Content Invalid", JOptionPane.ERROR_MESSAGE);   
		}

	    }

	} else if(actionName.equals(twoPlayersBtnName)){
	    riskLogger("Two Player Game");
	    players.addPlayers("Neutral Player");
	    frame.setContentPane(userInfoPanel(3));
	    frame.invalidate();
	    frame.validate();
	} else if(actionName.equals(threePlayersBtnName)){
	    riskLogger("Three Player Game");
	    players.addPlayers("Khyati");
	    frame.setContentPane(userInfoPanel(3));
	    frame.invalidate();
	    frame.validate();
	} else if(actionName.equals(fourPlayersBtnName)){
	    riskLogger("Four Player Game");
	    players.addPlayers("Khyati");
	    players.addPlayers("Vaishakhi");
	    frame.setContentPane(userInfoPanel(4));
	    frame.invalidate();
	    frame.validate();
	} else if(actionName.equals(fivePlayersBtnName)){
	    riskLogger("Five Player Game");
	    players.addPlayers("Khyati");
	    players.addPlayers("Vaishakhi");
	    players.addPlayers("Himen");
	    frame.setContentPane(userInfoPanel(5));
	    frame.invalidate();
	    frame.validate();
	} else if(actionName.equals("placeReinforcement")) {
	    goForReinforcement(true);
	} else if(actionName.equals("attackBtn")) {
	    goForAttack();
	} else if(actionName.equals("startFortification")) {
	    goForFortification();
	} else if(actionName.equals("endTurn")) {
	    riskLogger("Player Turn ended.");
	    riskLogger("");
	    changePlayerTurn();
	} else if(actionName.equals(backBtnName)){
	    frame.setContentPane(mainMenu(frame));
	    frame.invalidate();
	    frame.validate();
	}
    }

    /**
     * Attack phase.
     * Invoke the fortification phase.
     */
    private void goForAttack() {
	players.setCurrentPhase("Attack");
	//updateLogArea();
	JOptionPane.showMessageDialog(frame,"Attack Phase is in Progress");
	fortifyBtn.setEnabled(true);
	endTurnBtn.setEnabled(true);
	//riskLogger("Fortification Phase Started");
	//updateLogArea();
	observerSubject.setState("Fortification Phase Started \n", false);
	startFortificationPhase();

    }

    /**
     * method used to do reinforcement on territory.
     * @param flag used to identify whether player can do reinforcement or not.
     */
    public void goForReinforcement(boolean flag) {
	String name = players.getPlayerPlaying().get(playerTurn);
	//Reinforcement reinforcement = new Reinforcement(name,players,territory,continent);
	//players.setCurrentPhase("Reinforcement");		
	//updateLogArea();
	if(StringUtils.isNotEmpty(territoryAList.getSelectedValue())){	    
	    String[] terrName = territoryAList.getSelectedValue().split("--");
	    String message = flag ? "Add Armies in " + terrName[0] : "Add Armies Again in " + terrName[0];
	    int army = players.getPlayerArmy(name);
	    String title = "Add Armies upto " + army;
	    riskLogger("Player Name : " + players.getPlayerPlaying().get(playerTurn));
	    String output = JOptionPane.showInputDialog(frame, message, title, JOptionPane.OK_CANCEL_OPTION);
	    if (StringUtils.isNotEmpty(output) && StringUtils.isNumeric(output)) {
		if(Integer.parseInt(output) > 0 && Integer.parseInt(output) <= army) {
		    context = new Context(players);
		    context.executeReinforcement(name,terrName[0].trim(),Integer.parseInt(output),territory);
		  /*  System.out.println("-----> phase  " + players.getCurrentPhase());
		    System.out.println("------> territory Army " + territory.getTerritoryArmy().get(terrName[0].trim()));
		    System.out.println("----> player army " + players.getPlayerArmy(name));
		     reinforcement.startReinforcement();
		    setPlayers(reinforcement.getPlayers());
		    setTerritory(reinforcement.getTerritory());*/
		    observerSubject.setMessage(true,"Army Placed on   " + terrName[0]  + " is "+ output);
		    //  observerSubject.setPhase("Reinforcement","Remaining Armies :  " + players.getPlayerArmy(name));
		    /*riskLogger("Army on  " + terrName[0]  + " "+ territory.getTerritoryArmy().get(terrName[0].trim()));
		    riskLogger("Remain Armies " + players.getPlayerArmy(name));*/
		    territoryAModel.removeAllElements();
		    territoryBModel.removeAllElements();
		    territoryInfoModel.removeAllElements();
		    continentInfoModel.removeAllElements();
		    updateTerritoryAList();
		    updateContinentInfoList();
		    enableReinforcementBtn();
		    observerSubject.setState(name, true);
		    //updateLogArea();	
		} else {
		    observerSubject.setMessage(true,"Input armies are out of range ot not properly enter");
		    //riskLogger("Input armies are out of range ot not properly enter");
		    goForReinforcement(false);
		}
	    } else {
		 observerSubject.setMessage(true,"Input armies  entered is null or cancel button is clicked");
		//riskLogger("Input armies  entered is null or cancel button is clicked");
		JOptionPane.showMessageDialog(null, "Input armies  entered is null or cancel button is clicked","Invalid Content",JOptionPane.ERROR_MESSAGE);
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
	    if(players.getPlayerArmy(name) == 0) {
		reinforceBtn.setEnabled(false);
		players.setCurrentPhase("Attack");
		observerSubject.setState(name, true);
		observerSubject.setState("Attack Phase Started \n", false);
		//riskLogger(players.getCurrentPhase()+ " Phase Started");
		attackBtn.setEnabled(true);
		endTurnBtn.setEnabled(true);
		fortifyBtn.setEnabled(false);
	    } else {
		attackBtn.setEnabled(false);
		fortifyBtn.setEnabled(false);
		endTurnBtn.setEnabled(false);
		reinforceBtn.setEnabled(true);
	    }
	}
    }
    /**
     * method use to enable list of current territory owned by current player to move army from one  territory to another.   
     */
    public void startFortificationPhase() {
	players.setCurrentPhase("Fortification");
	
	observerSubject.setState(players.getPlayerPlaying().get(playerTurn), true);
	//updateLogArea();
	attackBtn.setEnabled(false);
	addTerritoryADropDown();

    }

    /**
     * Method is used to change the Turn of player when End Turn Button is Clicked.
     */
    public void changePlayerTurn() {
	playerTurn++;
	playerTurn = playerTurn < players.getPlayerList().size() ? playerTurn : 0;
	/*Reinforcement reinforcement = new Reinforcement(players.getPlayers(playerTurn),players, territory, continent);
	players.updateArmy(players.getPlayers(playerTurn), reinforcement.generateArmy(),"ADD");*/
	context = new Context(players);
	context.executeReinforcementArmy(players.getPlayers(playerTurn),continent);
	removeElements();
	updateTerritoryAList();
	updateContinentInfoList();    
	enableReinforcementBtn();
	observerSubject.setState(players.getPlayers(playerTurn), true);
	log.setText("");
	observerSubject.setState("Reinforcement Phase Started \n", false);
	//updateLogArea();
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
     * Used to Display Detail of Current Player.
     */
    public void updateLogArea() {
	/*logArea.setText("");
	logArea.append("Current Player : " + players.getPlayerList().get(playerTurn)+"\n");
	logArea.append("Current Armies : " + players.getPlayerArmy(players.getPlayerList().get(playerTurn))+"\n");
	logArea.append("Current Phase : " + players.getCurrentPhase());*/
    }
    /**
     * Method Allow Player to do Fortification Phase.
     */
    public void goForFortification() {
	/*Fortification fortification = new Fortification(territory);*/
	String fromTerritory = territoryADropDown.getItemAt(territoryADropDown.getSelectedIndex());
	String toTerritory = territoryBDropDown.getItemAt(territoryBDropDown.getSelectedIndex());
	if(StringUtils.isNotEmpty(fromTerritory) && StringUtils.isNotEmpty(toTerritory)) {
	    int fromArmy = territory.getTerritoryArmy().get(fromTerritory);
	    int getArmySelect = (int) selectArmyModel.getValue();
	    if(getArmySelect < fromArmy && getArmySelect >= 1) {
		context = new Context(players);
		context.executeFortification(territory,fromTerritory, toTerritory, getArmySelect);
		/*fortification.startFortification(fromTerritory, toTerritory, getArmySelect);
		setTerritory(fortification.getTerritory());*/
		/*riskLogger("Armies moved from "+fromTerritory+" to "+toTerritory);
		riskLogger("Armies at :"+fromTerritory+" : "+territory.getTerritoryArmy().get(fromTerritory));
		riskLogger("Armies at :"+toTerritory+" : "+territory.getTerritoryArmy().get(toTerritory));
		riskLogger("");*/
		String tempMsg = "Armies moved from "+fromTerritory+" to "+toTerritory + "\n"+"Armies at :"+fromTerritory+" : "+territory.getTerritoryArmy().get(fromTerritory)+"\nArmies at :"+toTerritory+" : "+territory.getTerritoryArmy().get(toTerritory)+"\n";
		observerSubject.setMessage(true,tempMsg);
		fortErrorMsg.setText("You can Move upto " + (territory.getTerritoryArmy().get(fromTerritory)-1) + " Army");
	    } else {
		JOptionPane.showMessageDialog(frame, "Armies unable to move from " + fromTerritory + " to " + toTerritory +". Please enter no. of  Armies again", "Error Message", JOptionPane.ERROR_MESSAGE);
	    }
	    checkFortificationStatus();
	} else {
	    JOptionPane.showMessageDialog(null, "Content Invalid");
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
	/*try {
	    territoryDetails.setText("");
	    if(territoryInfoList.getSelectedValue() != null) {
		String[] territoryName = territoryInfoList.getSelectedValue().split("--");
		territoryDetails.append("Continent  : " + continentInfoList.getSelectedValue() + "\n");
		territoryDetails.append("Territory  : " + territoryName[0]+"\n");
		territoryDetails.append("Player     : " + territory.getTerritoryUser().get(territoryName[0].trim())+"\n");
		territoryDetails.append("Army       : " + territory.getTerritoryArmy().get(territoryName[0].trim()));
	    }
	} catch(Exception ex) {
	    riskLogger("Handles Null Values");
	}*/
	territoryDetails.setText("");
	SimpleAttributeSet green = new SimpleAttributeSet();
	StyleConstants.setFontFamily(green, "Courier New Italic");
	StyleConstants.setForeground(green, Color.BLACK);
	StyleConstants.setFontSize(green, 15);
	try {
	    territoryDetails.getDocument().insertString(0, "Player Details", green);
	    frame.revalidate();
	} catch (BadLocationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
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
     */
    public void addTerritoryBDropDown() {
	territoryBDropDown.removeAllItems();
	String dropDownAValue =  territoryADropDown.getItemAt(territoryADropDown.getSelectedIndex());
	if(StringUtils.isNotEmpty(dropDownAValue)) {
	    for(int i = 0 ; i < territory.getAdjacentTerritory().get(dropDownAValue).size() ; i++) {
		String terrName = territory.getAdjacentTerritory().get(dropDownAValue).get(i);
		if(players.getPlayerPlaying().get(playerTurn).equals(territory.getTerritoryUser().get(terrName))) {
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
	    }
	    else {
		fortErrorMsg.setText("You can't move your Army");
	    }
	}
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
	// TODO Auto-generated method stub	
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

    @Override
    public void update() {
	if(observerSubject.getFlag()) {
	    logArea.setText("");
		logArea.append("Current Player : " + observerSubject.getState()+"\n");
		logArea.append("Current Armies : " + players.getPlayerArmy(observerSubject.getState())+"\n");
		logArea.append("Current Phase : " + players.getCurrentPhase());    
	} else if (!observerSubject.getFlag()){
	    log.append("------------------------------------------\n");
	    log.append(observerSubject.getState() +"\n");
	    log.append("------------------------------------------\n");
	    displayTerritoryDetails();
	    observerSubject.setMessageFlag(false);
	}
	if(observerSubject.getMessageFlag()) {
	    log.append(observerSubject.getMessage());
	}
	    
    }
}
