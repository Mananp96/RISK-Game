package com.risk.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.DefaultCaret;

import com.risk.controller.InitializeData;

public class Risk implements ActionListener {

	private JFrame frame;
	private JPanel menuPanel;
	private JPanel playerPanel;
	private JPanel gamePanel;
	private JPanel mapPanel;
	
	private String newBtnName = "New Game";
	private String exitBtnName = "Quit";
	private String twoPlayersBtnName = "twoPlayersBtn";
	private String threePlayersBtnName = "threePlayersBtn";
	private String fourPlayersBtnName = "fourPlayersBtn";
	private String fivePlayersBtnName = "fivePlayersBtn";
/*	private String sixPlayersBtnName = "sixPlayersBtn";*/
	private String backBtnName = "backBtn";
	private int  playerPlaying; 
	
	private JLabel playerCountLabel;
	
	private JButton newButton;
	private JButton exitButton;
	private JButton twoPlayersBtn;
	private JButton threePlayersBtn;
	private JButton fourPlayersBtn;
	private JButton fivePlayersBtn;
/*	private JButton sixPlayersBtn;*/
	private JButton backBtn;
	
	private GridLayout mainLayout;
	private GridLayout playerLayout;
	private JPanel logPanel;
	private JPanel eventPanel;
	private JPanel countryPanel;
	private JButton reinforceBtn;
	private JButton attackBtn;
	private JButton fortifyBtn;
	private JButton endTurnBtn;
	private JButton menuBtn;
	private JButton turnInBtn;
	private JLabel selectedLabel;
	private JLabel targetLabel;
	private JList cardsList;
	private JList countryAList;
	private JList countryBList;
	private JScrollPane countryAScrollPane;
	private JScrollPane countryBScrollPane;
	private GridBagConstraints c;
	private GridBagLayout mapLayout;
	private GridBagLayout logLayout;
	private JTextArea logArea;
	private JScrollPane logScrollPane;
	private DefaultCaret caret;
	private JPanel userPanel;
	private JTextField[] playerNames;
	private JButton startGameBtn;
	private int playerCount = 0;
	private ArrayList<String> playerNameList;
	private String editMapBtnName = "Edit Button";
	private String mapFilePath;
	private JButton editButton;
	private JRadioButton mapOptA;
	private JRadioButton mapOptB;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Risk window = new Risk();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Risk() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		playerNameList = new ArrayList<>();
		playerNameList.add("Manan");
		playerNameList.add("Shalin");
		playerNameList.add("Khyati");
		playerNameList.add("Vaishakhi");
		playerNameList.add("Himen");
		frame = new JFrame();
		frame.setTitle("Risk");
		frame.setPreferredSize(new Dimension(300, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.add(mainMenu());
		frame.pack();
		
	}
	
	
	protected JPanel mainMenu(){
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

	protected JPanel playerMenu(){
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
	

	protected JPanel userInfoPanel(int count){
		playerCount = count;
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
				}
			}
		});
		mapOptB = new JRadioButton("Choose Previously Edited Map");
		ButtonGroup buttonGrp = new ButtonGroup();
		buttonGrp.add(mapOptA);
		buttonGrp.add(mapOptB);
		userPanel.add(mapOptA);
		userPanel.add(mapOptB);
		userPanel.add(new JLabel("Player Names are"));
		for (int i = 0; i < count; i++) {
			userPanel.add(new JLabel("Player " + (i+1) + " : " + playerNameList.get(i))); 
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
	
	protected JPanel gameView() {
		
		frame.setPreferredSize(new Dimension(900,800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(true);
		gamePanel = new JPanel();
		//gamePanel.setSize(new Dimension(300, 600));
		//	gamePanel.setLayout(new GridLayout(2, 3));
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
		mapScrollPane.setPreferredSize(new Dimension(450, 600));
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
		
		/*
		cardsListModel = new RiskListModel(model, "cards");
		countryAListModel = new RiskListModel(model, "countryA");
		countryBListModel = new RiskListModel(model, "countryB");
		*/
		/*model.addObserver((RiskListModel)cardsListModel);
		model.addObserver((RiskListModel)countryAListModel);
		model.addObserver((RiskListModel)countryBListModel);
		*/
		cardsList = new JList();
		cardsList.setLayoutOrientation(JList.VERTICAL_WRAP);
		cardsList.setVisibleRowCount(6);
		
		countryAList = new JList();
		countryAList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		countryAList.setLayoutOrientation(JList.VERTICAL_WRAP);
		countryAList.setVisibleRowCount(42);
		
		countryBList = new JList();
		countryBList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		countryBList.setLayoutOrientation(JList.VERTICAL_WRAP);
		countryBList.setVisibleRowCount(6);
		
		countryAScrollPane = new JScrollPane(countryAList);
		countryBScrollPane = new JScrollPane(countryBList);
		
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
		eventPanel.add(countryAScrollPane, c);
		
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
		eventPanel.add(countryBScrollPane, c);
		
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
		countryPanel.setPreferredSize(new Dimension(550, 600));
		countryPanel.setLayout(new GridBagLayout());
		ImageIcon mapImageIcon = new ImageIcon("D:\\eclipse-workspace\\RiskGame\\src\\riskpackage\\Map.jpg");
		JScrollPane countryScrollPane = new JScrollPane(new JLabel(mapImageIcon));
		countryScrollPane.setPreferredSize(new Dimension(450, 600));
		countryPanel.add(countryScrollPane);
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
*/		return logPanel;
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		 
		String actionName = ae.getActionCommand();
		if(actionName.equalsIgnoreCase(newBtnName)){
			System.out.println("Play Game");
			frame.setContentPane(playerMenu());
			frame.invalidate();
			frame.validate();
		}
		else if(actionName.equals(editMapBtnName)){
			System.out.println("Edit Map Game");
		}
		else if(actionName.equals(exitBtnName)){
			System.out.println("Quit Game");
			System.exit(0);
		}
		else if(actionName.equals("Start Game")){
			InitializeData initializeData = new InitializeData(mapFilePath , playerPlaying);
			try {
				initializeData.generateData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.setContentPane(gameView());
			frame.invalidate();
			frame.validate();
		}
		else if(actionName.equals(twoPlayersBtnName)){
			System.out.println("Two Player Game");
			frame.setContentPane(userInfoPanel(2));	
			frame.invalidate();
			frame.validate();
		}
		else if(actionName.equals(threePlayersBtnName)){
			System.out.println("Three Player Game");
			frame.setContentPane(userInfoPanel(3));	
			frame.invalidate();
			frame.validate();
		}
		else if(actionName.equals(fourPlayersBtnName)){
			System.out.println("Four Player Game");
			frame.setContentPane(userInfoPanel(4));	
			frame.invalidate();
			frame.validate();
		}
		else if(actionName.equals(fivePlayersBtnName)){
			System.out.println("Five Player Game");
			frame.setContentPane(userInfoPanel(5));	
			frame.invalidate();
			frame.validate();
		}
		/*else if(actionName.equals(sixPlayersBtnName)){
			System.out.println("Six Player Game");
		}*/
		else if(actionName.equals(backBtnName)){
			frame.setContentPane(mainMenu());
			frame.invalidate();
			frame.validate();
		}
		
		frame.pack();

	}
	

}
