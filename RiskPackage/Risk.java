package RiskPackage;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Risk implements ActionListener {

	private JFrame frame;
	private JPanel menuPanel;
	private JPanel playerPanel;
	private JPanel gamePanel;
	
	private String newBtnName = "New Game";
	private String loadBtnName = "Load Game";
	private String exitBtnName = "Quit";
	private String twoPlayersBtnName = "twoPlayersBtn";
	private String threePlayersBtnName = "threePlayersBtn";
	private String fourPlayersBtnName = "fourPlayersBtn";
	private String fivePlayersBtnName = "fivePlayersBtn";
/*	private String sixPlayersBtnName = "sixPlayersBtn";*/
	private String backBtnName = "backBtn";
	
	private JLabel playerCountLabel;
	
	private JButton newButton;
	private JButton loadButton;
	private JButton exitButton;
	private JButton twoPlayersBtn;
	private JButton threePlayersBtn;
	private JButton fourPlayersBtn;
	private JButton fivePlayersBtn;
/*	private JButton sixPlayersBtn;*/
	private JButton backBtn;
	
	private GridLayout mainLayout;
	private GridLayout playerLayout;
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
		newButton = new JButton("New Game");
		loadButton = new JButton("Load Game");
		exitButton = new JButton("Quit");
				
		menuPanel.add(newButton);
		menuPanel.add(loadButton);
		menuPanel.add(exitButton);
		
		newButton.addActionListener(this);
		loadButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		newButton.setActionCommand(newBtnName);
		loadButton.setActionCommand(loadBtnName);
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
	
	protected JPanel gameView() {
		return gamePanel;
	}
	
	public void actionPerformed(ActionEvent ae) {
		 
		String actionName = ae.getActionCommand();
		BoardView boardView = new BoardView();
		if(actionName.equalsIgnoreCase(newBtnName)){
			System.out.println("New Game");
			frame.setContentPane(playerMenu());
			frame.invalidate();
			frame.validate();
		}
		else if(actionName.equals(loadBtnName)){
			System.out.println("Load Game");
		}
		else if(actionName.equals(exitBtnName)){
			System.out.println("Quit Game");
			System.exit(0);
		}
		else if(actionName.equals(twoPlayersBtnName)){
			System.out.println("Two Player Game");
			boardView.initialize(frame);
		}
		else if(actionName.equals(threePlayersBtnName)){
			System.out.println("Three Player Game");
		}
		else if(actionName.equals(fourPlayersBtnName)){
			System.out.println("Four Player Game");
		}
		else if(actionName.equals(fivePlayersBtnName)){
			System.out.println("Five Player Game");
		}
		/*else if(actionName.equals(sixPlayersBtnName)){
			System.out.println("Six Player Game");
		}*/
		else if(actionName.equals(backBtnName)){
			frame.setContentPane(mainMenu());
			frame.invalidate();
			frame.validate();
		}

}
}
