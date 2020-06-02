import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToeGameMenu {
	//Start menu fields
	static JFrame startFrame;
	static JButton onePlayer, twoPlayer, quit;
	static JLabel GamePlay;
	static JCheckBox hackerButton;
	static JPanel startWin;
	static int gameType = 0;
	static boolean hackerMode = false;
	static Color background = new Color(32, 160, 230);
	static TicTacToeGame twoPlayerGame;
	static TicTacToeAIGame onePlayerGame;
	
	public TicTacToeGameMenu() {
		TicTacToeGame twoPlayerGame = new TicTacToeGame();
		TicTacToeAIGame onePlayerGame = new TicTacToeAIGame();
		startMenuInit();
	}
	
	/*
	 * Set up initial window for game selection
	 */
	public static void startMenu() {
		startFrame = new JFrame("Start Menu");
		GamePlay = new JLabel ("Tic-Tac-Toe Game");
		onePlayer = new JButton("Single-Player");
		twoPlayer = new JButton("Two-Player");
		quit = new JButton("Quit");
		hackerButton = new JCheckBox("Hacker Mode");
		startWin = new JPanel();
		startWin.add(GamePlay);
		startWin.add(onePlayer);
		startWin.add(twoPlayer);
		startWin.add(quit);
		startWin.add(hackerButton);
		startWin.setBackground(background);
		startFrame.add(startWin);
		startFrame.setSize(150,200);
		startFrame.setVisible(true);
		startMenuUpdate();
	}
	
	/**
	 * Updates start menu to initialize game
	 */
	public static void startMenuUpdate() {
		twoPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(hackerButton.isSelected() == false) {
					twoPlayerGame.twoPlayerGameGUI();
					startFrame.dispose();
				}
				else {
					startFrame.dispose();
					twoPlayerGame.twoPlayerGame();
				}
			}	
		});
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}	
		});
		
		onePlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(hackerButton.isSelected() == false) {
					onePlayerGame.onePlayerGameGUI();
					startFrame.dispose();
				}
				else {
					startFrame.dispose();
					onePlayerGame.onePlayerGame();
				}
			}
		});
	}
	
	/**
	 * Initializes the start menu
	 */
	public static void startMenuInit() {
		startMenu();
		startMenuUpdate();
	}
	
}
