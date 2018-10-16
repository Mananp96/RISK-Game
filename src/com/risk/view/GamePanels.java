package com.risk.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
/**
 * 
 * @author Himen Sidhpura
 */
public class GamePanels implements ActionListener, ListSelectionListener {
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
	private JButton saveMapBtn;
	private JButton newButton;
	private JButton exitButton;
	private JButton twoPlayersBtn;
	private JButton threePlayersBtn;
	private JButton fourPlayersBtn;
	private JButton fivePlayersBtn;
	private JButton backBtn;
	private JButton editButton;
	private JButton startGameBtn;
	
	private JTextArea territoryDetails;
	private JTextArea addContinentsArea;
	private JTextArea addTerritoriesArea;
	private JTextArea logArea;
	private JList<String> cardsList;
	private JList<String> territoryAList;
	private JList<String> territoryBList;
	private JList<String> continentInfoList;
	private JList<String> territoryInfoList;
	private GridBagConstraints c;
	private DefaultCaret caret;
	
	private JRadioButton mapOptA;
	private JRadioButton mapOptB;
	
	private boolean randomMap = false;
	private DefaultListModel<String> territoryAModel;
	private DefaultListModel<String> territoryBModel;
	private DefaultListModel<String> continentInfoModel;
	private DefaultListModel<String> territoryInfoModel;
	private JComboBox<String> territoryADropDown;
	private JComboBox<String> territoryBDropDown;
	private SpinnerNumberModel selectArmyModel;
	private JLabel fortErrorMsg;
	
	
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
	 * Panel for editing existing map 
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
	 * Panel for Creating new Map from scratch
	 * @return createMapPanel
	 */
	protected JPanel createMapPanel() {
		
		JPanel createMapPanel = new JPanel();
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
	
	/**
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
	
	/**
	 * 
	 * @return GamePanel object which consist portion of Game Play
	 */
	protected JPanel gameView() {
		
		frame.setPreferredSize(new Dimension(900,800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(true);
		JPanel gamePanel = new JPanel();
		frame.setLayout(mainLayout);
		
		gamePanel.add(displayMap(),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.LINE_START, 0.5, 0.5, 0, 0));
		gamePanel.add(eventScreen(),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.CENTER, 0.5, 0.5, 1, 0));
		gamePanel.add(countryScreen(),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.LINE_END, 0.5, 0.5, 2, 0));
		gamePanel.add(logScreen(),setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,GridBagConstraints.SOUTHWEST, 0.5, 0.5, 0, 1));

		return gamePanel;
	}
	/**
	 * 
	 * @return
	 */
	protected JPanel displayMap(){
		JPanel mapPanel = new JPanel();
		mapPanel.setSize(new Dimension(400, 600));
		GridBagLayout mapLayout = new GridBagLayout();
		mapPanel.setLayout(mapLayout);
		ImageIcon mapImageIcon = new ImageIcon("D:\\eclipse-workspace\\RiskGame\\src\\riskpackage\\Map.jpg");
		JScrollPane mapScrollPane = new JScrollPane(new JLabel(mapImageIcon));
		mapScrollPane.setPreferredSize(new Dimension(300, 600));
		c = new GridBagConstraints();
		
		mapPanel.add(mapScrollPane,setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 14, 0, 0));
		return mapPanel;
	}
	/**
	 * Panel consist various sections such as Reinforcement Button, Foritify Button, Attack Button, List of Territory and Adjacent Territory
	 * @return EventPanel consist of various game play events
	 */
	protected JPanel eventScreen(){
	
		JPanel eventPanel = new JPanel();
		eventPanel.setPreferredSize(new Dimension(300, 600));
		GridBagLayout eventLayout = new GridBagLayout();
		eventPanel.setLayout(eventLayout);
		
		JLabel selectedLabel = new JLabel("Selected Territory:");
		JLabel targetLabel = new JLabel("Adjacent Territory:");
		
		menuBtn = new JButton("Menu");
		turnInBtn = new JButton("Turn In Cards");
		reinforceBtn = new JButton("Place Reinforcements");
		attackBtn = new JButton("Attack!");
		fortifyBtn = new JButton("Fortify");
		endTurnBtn = new JButton("End Turn");
		
		if(reinforceBtn.isEnabled())
		    fortifyBtn.setEnabled(false);
		menuBtn.setActionCommand(backBtnName);
		reinforceBtn.setActionCommand("placeReinforcement");
		fortifyBtn.setActionCommand("startFortification");
		endTurnBtn.setActionCommand("endTurn");
		menuBtn.addActionListener(this);
		reinforceBtn.addActionListener(this);
		fortifyBtn.addActionListener(this);
		endTurnBtn.addActionListener(this);
		
		cardsList = new JList<>();
		cardsList.setLayoutOrientation(JList.VERTICAL_WRAP);
		cardsList.setVisibleRowCount(6);
		territoryAModel = new DefaultListModel<>();
		territoryAList = new JList<>(territoryAModel);
		for (Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
			if(entry.getValue().equalsIgnoreCase(players.getPlayerPlaying().get(playerTurn))) {
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
		
		JScrollPane continentScrollPane = new JScrollPane(territoryAList);
		JScrollPane territoryScrollPane = new JScrollPane(territoryBList);
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
		
		eventPanel.add(menuBtn, setGridBagConstraints(new Insets(0, 5, 5, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 0, 0));
		eventPanel.add(cardsList, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 5, 0, 2));
		eventPanel.add(turnInBtn, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 3));
		eventPanel.add(selectedLabel, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 4));
		eventPanel.add(continentScrollPane, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 10, 0, 5));
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
		JLabel territoryLabel = new JLabel("TERRITORYS---PLAYER---ARMY");	
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
				territoryInfoModel.addElement(territoryName.trim()+ "---" +territory.getTerritoryUser().get(territoryName));
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
		territoryInfoList.addListSelectionListener(new ListSelectionListener() {
		    
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			displayTerritoryDetails();
		    }
		});
		
		JScrollPane continentInfoScrollPane = new JScrollPane(continentInfoList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane territoryInfoScrollPane = new JScrollPane(territoryInfoList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		territoryDetails = new JTextArea(4,2);		

		countryPanel.add(countryLabel, setGridBagConstraints(new Insets(5,2, 2, 5), GridBagConstraints.BOTH, 0.5,1, 0, 0));
		countryPanel.add(territoryLabel, setGridBagConstraints(new Insets(5,5, 5, 5), GridBagConstraints.BOTH, 0.5,1, 1, 0));
		countryPanel.add(continentInfoScrollPane, setGridBagConstraints(new Insets(5,5, 5, 5), GridBagConstraints.BOTH, 0.5,3, 0, 1));
		countryPanel.add(territoryInfoScrollPane, setGridBagConstraints(new Insets(5,5, 5, 5), GridBagConstraints.BOTH, 0.5,3, 1, 1));
		countryPanel.add(fortificationPanel(), setGridBagConstraints(new Insets(5,5, 5, 5), GridBagConstraints.BOTH, 0.5,3, 0, 2));
		countryPanel.add(territoryDetails, setGridBagConstraints(new Insets(5,5, 5, 5), GridBagConstraints.BOTH, 0.5,3, 1, 2));
		return countryPanel;
	}
	
	/**
	 * Display list of territory and it's adjacent territory of current Player 
	 * @return fortificationPanel for movement of army
	 */
	private JPanel fortificationPanel() {
	    // TODO Auto-generated method stub
	    JPanel fortificationPanel = new JPanel();
	    fortificationPanel.setLayout(new GridLayout(6, 1));
	    JLabel territoryALabel = new JLabel("Territory List");
	    territoryADropDown = new JComboBox<>();
	    JLabel territoryBLabel = new JLabel("Adjacent Territory List");
	    territoryBDropDown = new JComboBox<>();
	    fortErrorMsg = new JLabel("Select Army : ");
	    selectArmyModel = new SpinnerNumberModel();
	    JSpinner selectArmy = new JSpinner(selectArmyModel);	
	    fortificationPanel.add(territoryALabel);
	    fortificationPanel.add(territoryADropDown);
	    fortificationPanel.add(territoryBLabel);
	    fortificationPanel.add(territoryBDropDown);
	    fortificationPanel.add(fortErrorMsg);
	    fortificationPanel.add(selectArmy);
	    territoryADropDown.addItemListener(new ItemListener() {
	        
	        @Override
	        public void itemStateChanged(ItemEvent e) {
	    	// TODO Auto-generated method stub
	        addTerritoryBDropDown();
	        }       
	    });
	    territoryBDropDown.addItemListener(new ItemListener() {
	        
	        @Override
	        public void itemStateChanged(ItemEvent e) {
	    	// TODO Auto-generated method stub
	            enterArmyToMove();
	    	
	        }
	    });
	    return fortificationPanel;
	}

	/**
	 * Display who is current with his total number of army
	 * @return logPanel
	 */
	protected JPanel logScreen(){
		
		JPanel logPanel = new JPanel();
		GridBagLayout logLayout = new GridBagLayout();
		logPanel.setLayout(logLayout);
		logPanel.setSize(new Dimension(400,250));
		logArea = new JTextArea(4,40);
		logArea.setFocusable(false);
		logArea.setLineWrap(true);
		logArea.setWrapStyleWord(true);
		caret = (DefaultCaret)logArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane logScrollPane = new JScrollPane(logArea);
		logPanel.add(logScrollPane, setGridBagConstraints(new Insets(5,5, 5, 5), GridBagConstraints.WEST, 0.5,14, 0, 0));
		updateLogArea();

		return logPanel;
	}
	/**
	 * Frame consist of Start button, Edit Button and Quit Button
	 * @param frame current frame
	 * @param players player object
	 * @return menuPanel 
	 */ 
	protected JPanel mainMenu(JFrame frame, Players players){
		this.players = players;
		this.frame = frame;
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
		int playerCount = count;
		JPanel userPanel = new JPanel();
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

		String actionName = arg0.getActionCommand();
		
		if(actionName.equalsIgnoreCase(newBtnName)){
			System.out.println("Play Game");
			players = new Players();
			players.addPlayers("Manan");
			players.addPlayers("Shalin");
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
			players.addPlayers("Khyati");
			players.addPlayers("Vaishakhi");
			frame.setContentPane(userInfoPanel(4));
			frame.invalidate();
			frame.validate();
		}
		else if(actionName.equals(fivePlayersBtnName)){
			System.out.println("Five Player Game");
			players.addPlayers("Khyati");
			players.addPlayers("Vaishakhi");
			players.addPlayers("Himen");
			frame.setContentPane(userInfoPanel(5));
			frame.invalidate();
			frame.validate();
		}
		else if(actionName.equals("placeReinforcement")) {
		    goForReinforcement(true);
		}	
		else if(actionName.equals("startFortification")) {
		    goForFortification();
		}
		else if(actionName.equals("endTurn")) {
		    changePlayerTurn();
		}
		else if(actionName.equals(backBtnName)){
			frame.setContentPane(mainMenu(frame,players));
			frame.invalidate();
			frame.validate();
			
		}
	}
	
	/**
	 * Method is used to change the Turn of player when End Turn Button is Clicked.
	 */
	public void changePlayerTurn() {
	    playerTurn++;
	    if(playerTurn < players.getPlayerList().size()) {
		territoryAModel.removeAllElements();
    		territoryBModel.removeAllElements();
    		territoryInfoModel.removeAllElements();
    		continentInfoModel.removeAllElements();
    		updateTerritoryAList();
    		updateContinentInfoList();    
    		enableReinforcementBtn();
		updateLogArea();
	    } else {
		playerTurn = 0;
		territoryAModel.removeAllElements();
    		territoryBModel.removeAllElements();
    		territoryInfoModel.removeAllElements();
    		continentInfoModel.removeAllElements();
    		updateTerritoryAList();
    		updateContinentInfoList();    
    		enableReinforcementBtn();
		updateLogArea();
	    }
	}
	
	/**
	 * Used to Display Detail of Current Player.
	 */
	public void updateLogArea() {
	    logArea.setText("");
	    logArea.append("Current Player : " + players.getPlayerList().get(playerTurn)+"\n");
	    logArea.append("Current Armies : " + players.getPlayerArmy(players.getPlayerList().get(playerTurn)));
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
		    territory.updateTerritoryArmy(fromTerritory, getArmySelect, "DELETE");
		    territory.updateTerritoryArmy(toTerritory, getArmySelect, "ADD");
		    fortErrorMsg.setText("You can Move upto " + (territory.getTerritoryArmy().get(fromTerritory)-1) + " Army");
		} else {
		    JOptionPane.showMessageDialog(frame, "Armies unable to move from " + fromTerritory + " to " + toTerritory +". Please enter no. of  Armies again", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		checkFortificationStatus();
	    }
	}
	/**
	 * method used to check whether current player can go for fortification phase more or not.
	 */
	public void checkFortificationStatus() {
	    boolean flag = false;
	    for(Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
		if(entry.getValue().equalsIgnoreCase(players.getPlayerPlaying().get(playerTurn))) {
		    if(territory.getTerritoryArmy().get(entry.getKey()) > 1) {
			flag = true;
			break;
		    }
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
	 * method used to do reinforcement on territory.
	 * @param flag used to identify whether player can do reinforcement or not.
	 */
	public void goForReinforcement(boolean flag) {
	    if(StringUtils.isNotEmpty(territoryAList.getSelectedValue())){	    
		String[] terrName = territoryAList.getSelectedValue().split("---");
	    	String message = flag ? "Add Armies in " + terrName[0] : "Add Armies Again in " + terrName[0];
	    	String name = players.getPlayerPlaying().get(playerTurn);
	    	int army = players.getPlayerArmy(name);
	    	String title = "Add Amrmies upto " + army;
	    	System.out.println("Player Name " + players.getPlayerPlaying().get(playerTurn));
	    	System.out.println("Player Army " + players.getPlayerArmy(players.getPlayerPlaying().get(playerTurn)));
	    	String output = JOptionPane.showInputDialog(frame, message, title, JOptionPane.OK_CANCEL_OPTION);
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
	    		updateLogArea();
	    	    } else {
    	    	    	System.out.println("Input armies are out pf range");
    	    	goForReinforcement(false);	
	    	    }
	    	} else {
	    	    System.out.println("Input armies are not properly entered");
	    	goForReinforcement(false);
	    	}
	    }
	}
	/**
	 * Method used to display complete details of territory such as which continent it belongs to, which player has occupied it with how many armies.
	 */
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
	
	/**
	 *  method used to update list of territory of current player
	 */
	public void updateTerritoryAList() {
	    territoryAModel.removeAllElements();
	    
	    for (Entry<String, String> entry : territory.getTerritoryUser().entrySet()) {
		if(entry.getValue().equals(players.getPlayerList().get(playerTurn)))	
		    territoryAModel.addElement(entry.getKey());
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
	 * method used to enable reinforcement button and disable fortify button if player has 1 or more army
	 * It also used to disable reinforcement button and enable fortify button if player has no army  
	 */
	public void enableReinforcementBtn() {
	    String name = players.getPlayerPlaying().get(playerTurn);
	    System.out.println("enableReinforcementBtn :  Name " +name);
	    System.out.println("enableReinforcementBtn :  playerTurn " +playerTurn);
	    System.out.println("enableReinforcementBtn :  player " +players.getPlayerList());
	    System.out.println("enableReinforcementBtn :  player aRMY " +players.getPlayerArmy(name));
	    if(StringUtils.isNotEmpty(name)) {
		if(players.getPlayerArmy(name) == 0) {
		    reinforceBtn.setEnabled(false);
		    fortifyBtn.setEnabled(true);
		    startFortificationPhase();
		}
		    
		else {
		    fortifyBtn.setEnabled(false);
		    reinforceBtn.setEnabled(true);
		}
		    
	    }
	}
	/**
	 * method use to enable list of current territory owned by current player to move army from one  territory to another.   
	 */
	public void startFortificationPhase() {
	   addTerritoryADropDown();
	   
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
		System.out.println("Current Army in  " + fromTerritory + " is " + fromArmy);
		if(fromArmy > 1) {
		    fortErrorMsg.setText("You can Move upto " + fromArmy + " Army");
		}
		else
		    fortErrorMsg.setText("You can't move your Army");
	    }
	}
	

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub	
	}




}
