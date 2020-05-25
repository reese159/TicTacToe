import java.util.Scanner; 
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;

/**
 * Plays the tic-tac-toe game. eventually, the AI will never lose.
 * @author Reese Pelletier, Justin Briley
 *
 */
@SuppressWarnings("serial")
public class GamePlayer extends JFrame{

	//================================FIELDS=====================================
	/**
	 * Fields
	 */
	static final Scanner scan = new Scanner(System.in);
	static Color background = new Color(32, 160, 230);
	//TWO-PLAYER GUI FIELDS
	static JFrame twoPlayerframe;
	static JButton l1, l2, l3, l4, l5, l6, l7, l8, l9;
	static JLabel twoPlayerTitle;
	static JPanel twoPlayerWin;
	static int GUIXWin = 0;
	static int GUIOWin = 0;
	static int GUICat = 0;
	static int GUITurnCounter;
	static boolean GUIWinCondition;
	static boolean l1Clicked;
	static boolean l2Clicked;
	static boolean l3Clicked;
	static boolean l4Clicked;
	static boolean l5Clicked;
	static boolean l6Clicked;
	static boolean l7Clicked;
	static boolean l8Clicked;
	static boolean l9Clicked;
	//TWO-PLAYER GAME OVER FIELDS
	static JLabel winTalley;
	static JButton replay, noReplay;
	
	static JFrame twoPlayerVicotryFrame;
	static JLabel twoPlayerWinner;
	static JPanel twoPlayerVictoryWin;
	
	static JFrame twoPlayerCATFrame;
	static JLabel twoPlayerTie;
	static JPanel twoPlayerCatWin;
	//================================HELPER METHODS=====================================
	/**
	 * 
	 * @return Player Location
	 */
	public static int valuePrompter() {
		System.out.println("Select a location: "); //Prompt user for location selection
		int location = scan.nextInt();
		return location;
	}
	
	/**
	 * Sets up rules for the game
	 */
	public static void gamePrelim() {
		System.out.println("Do you want to hear the rules?");
		System.out.println("Y/N");
		String RuleDecision = scan.next().toLowerCase();		
		if(RuleDecision.equals("y")) {
			System.out.println("In this game, you will be playing Tic-Tac-Toe.");
			System.out.println("The game board is made up of a 3x3 grid with the following locations:");
			System.out.println("1|2|3");		
			System.out.println("4|5|6");	
			System.out.println("7|8|9");	
			System.out.println("In order to play the game, simply type a location and hit the enter key.");
		}
		else if(RuleDecision.equals("n")) {
			System.out.println("Well then, let's get on with the game...");
		}
		else {
			System.out.println("You didn't answer correctly, so I'll asssume that's a no.");
		}
	}
	
	/**
	 * Create new game board
	 * 
	 * @return Initialized Game Board
	 */
	public static String[] initGameBoard() {
		String[] initBoard = new String[9];
		for(int i = 0; i < 9; i++) {
			initBoard[i] = " ";
		}
		return initBoard;
	}
	
	/**
	 * Check if valid move
	 * 
	 * @param currBoard represents current locations
	 * @param loc represents current current location
	 * @return Valid or invalid move
	 */
	public static boolean validityCheck(String[] currBoard, int loc) {
		
		for(int i = 0; i < 9; i++) {
			if(i == loc - 1) {
				if(currBoard[i] == " ") {
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Update the game board & metadata
	 * 
	 * @param location contains the current location values containing input
	 * @return locations in terms of a char array to be used later
	 */
	public static String[] update(String[] currBoard, int loc, int counter) {
		
		for(int i = 0; i < 9; i++) {
			if(i == loc - 1) {
				if(counter % 2 == 0)
					currBoard[i] = "X";
				else
					currBoard[i] = "O";
			}
			
		}
		System.out.println(currBoard[0]+"|"+currBoard[1]+"|"+currBoard[2]);		
		System.out.println(currBoard[3]+"|"+currBoard[4]+"|"+currBoard[5]);	
		System.out.println(currBoard[6]+"|"+currBoard[7]+"|"+currBoard[8]);	
		
		return currBoard;
	}
	
	/**
	 * Check if winner has been decided
	 * @param currBoard current game board after update
	 * @param counter to check player turn
	 * @return win/no win
	 */
	public static boolean winCheck(String[] currBoard, int counter) {
		//X Win Check
		if(counter % 2 == 0) {
			//Column Checker
			for(int i = 0; i < 3; i++) {
				if(currBoard[i] == "X" && currBoard[i+3] == "X" && currBoard[i+6] == "X") {
					return true;
				}
			}
			
			//Row Checker
			for(int i = 0; i < 9; i+=3) {
				if(currBoard[i] == "X" && currBoard[i+1] == "X" && currBoard[i+2] == "X") {
					return true;
				}
			}
			//Right Diag Checker
			if(currBoard[0] == "X" && currBoard[4] == "X" && currBoard[8] == "X") {
				return true;
			}
			
			//Left Diag Checker
			if(currBoard[2] == "X" && currBoard[4] == "X" && currBoard[6] == "X") {
				return true;
			}
		}
		//O Win Check
		else {
			//Column Checker
			for(int i = 0; i < 3; i++) {
				if(currBoard[i] == "O" && currBoard[i+3] == "O" && currBoard[i+6] == "O") {
					return true;
				}
			}
			
			//Row Checker
			for(int i = 0; i < 9; i+=3) {
				if(currBoard[i] == "O" && currBoard[i+1] == "O" && currBoard[i+2] == "O") {
					return true;
				}
			}
			
			//Right Diag Checker
			if(currBoard[0] == "O" && currBoard[4] == "O" && currBoard[8] == "O") {
				return true;
			}
			
			//Left Diag Checker
			if(currBoard[2] == "O" && currBoard[4] == "O" && currBoard[6] == "O") {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Two player game
	 */
	public static void twoPlayerGame() {
		//Show user how game works
		gamePrelim();
		//Set Initial Variables
		int OWins = 0;
		int XWins = 0;
		int Cats = 0;
		boolean again = true;
		//Game Loop
		while(again == true) {
			int counter = 0;
			boolean win = false;
			int locArray[] = new int[9];
			String gameBoard[] = initGameBoard();
			//Game play
			while(win == false) {
				int loc = valuePrompter();
				locArray[0] = loc;
				while(validityCheck(gameBoard, loc) == false) {
					System.out.println("That move was invalid. Please try again.");
					loc = valuePrompter();
				}
				//Update the game board with move
				update(gameBoard, loc, counter);
				win = winCheck(gameBoard, counter);
				if(win == true) {
					break;
				}
				counter++;
				if(counter == 9) {
					Cats++;
					System.out.println("Tied");
					System.out.println("X Wins: " + XWins);
					System.out.println("O Wins: " + OWins);
					System.out.println("Ties: " + Cats);	
					break;
				}
			}
			//Declare Winner
			if(win == true) {
				if(counter % 2 == 0) {
					XWins++;
					System.out.println("XvX");
					System.out.println("X Wins: " + XWins);
					System.out.println("O Wins: " + OWins);		
					System.out.println("Ties: " + Cats);	
				}
				else {
					OWins++;
					System.out.println("OwO");
					System.out.println("X Wins: " + XWins);
					System.out.println("O Wins: " + OWins);
					System.out.println("Ties: " + Cats);	
				}
			}
			//Prompt for re match
			System.out.println("Would you like to play again?");
			System.out.println("Y/N");
			String decide = scan.next().toLowerCase();
			if(decide.equals("n")) {
				again = false;
				System.out.println("Good Game.");
				System.out.println("No Rematch.");
				System.out.println("GGNORE");
			}
			else if(decide.equals("y")) {
				again = true;
				System.out.println("Onto the next game.");
				System.out.println();
			}
			else {
				again = false;
				System.out.println("I'll take that as a no...");
		
			}
		}
	}
	
	/**
	 * Initialize Game Window
	 */
	public static void twoPlayerGUIInit() {
		//Create frame storing game
		twoPlayerframe = new JFrame("Tic-Tac-Toe");
		//Label game type
		JLabel dummy = new JLabel("");
		twoPlayerTitle = new JLabel("         Two-Player Game");
		//Create button grid
		l1 = new JButton("empty");
		l2 = new JButton("empty");
		l3 = new JButton("empty");
		l4 = new JButton("empty");
		l5 = new JButton("empty");
		l6 = new JButton("empty");
		l7 = new JButton("empty");
		l8 = new JButton("empty");
		l9 = new JButton("empty");
		//Create game window
		twoPlayerWin = new JPanel();
		twoPlayerWin.setLayout(new GridLayout(0,3));
		//Add assets & setup game window
		twoPlayerWin.add(l1);
		twoPlayerWin.add(l2);
		twoPlayerWin.add(l3);
		twoPlayerWin.add(l4);
		twoPlayerWin.add(l5);
		twoPlayerWin.add(l6);
		twoPlayerWin.add(l7);
		twoPlayerWin.add(l8);
		twoPlayerWin.add(l9);
		twoPlayerWin.add(dummy);
		twoPlayerWin.add(twoPlayerTitle);
		twoPlayerWin.setBackground(background);
		twoPlayerframe.add(twoPlayerWin);
		twoPlayerframe.setSize(500,450);
		twoPlayerframe.setVisible(true);
	}
	
	/*
	 * Check if win in GUI
	 * 
	 * @param location contains the current location values containing input
	 * @return locations in terms of a char array to be used later
	 */
	public static boolean winCheckGUI(String[] currBoard, int counter) {
		//X Win Check
		for(int i = 0; i < 3; i++) {
			if(currBoard[i] == "X" && currBoard[i+3] == "X" && currBoard[i+6] == "X") {
				return true;
			}
		}
		
		//Row Checker
		for(int i = 0; i < 9; i+=3) {
			if(currBoard[i] == "X" && currBoard[i+1] == "X" && currBoard[i+2] == "X") {
				return true;
			}
		}
		//Right Diag Checker
		if(currBoard[0] == "X" && currBoard[4] == "X" && currBoard[8] == "X") {
			return true;
		}
		
		//Left Diag Checker
		if(currBoard[2] == "X" && currBoard[4] == "X" && currBoard[6] == "X") {
			return true;
		}
	//O Win Check
		//Column Checker
		for(int i = 0; i < 3; i++) {
			if(currBoard[i] == "O" && currBoard[i+3] == "O" && currBoard[i+6] == "O") {
				return true;
			}
		}
		
		//Row Checker
		for(int i = 0; i < 9; i+=3) {
			if(currBoard[i] == "O" && currBoard[i+1] == "O" && currBoard[i+2] == "O") {
				return true;
			}
		}
		
		//Right Diag Checker
		if(currBoard[0] == "O" && currBoard[4] == "O" && currBoard[8] == "O") {
			return true;
		}
		
		//Left Diag Checker
		if(currBoard[2] == "O" && currBoard[4] == "O" && currBoard[6] == "O") {
			return true;
		}

		return false;
	}
	
	/**
	 * Updates game board based on button press
	 */
	public static void twoPlayerButtonUpdate() {
		//Set variables
		GUITurnCounter = 0;
		l1Clicked = false;
		l2Clicked = false;
		l3Clicked = false;
		l4Clicked = false;
		l5Clicked = false;
		l6Clicked = false;
		l7Clicked = false;
		l8Clicked = false;
		l9Clicked = false;
		//@SuppressWarnings("unused")
		String gameBoard[] = new String[9];
		
		//Action listeners based on button press
		//======================ACTION LISTENERS===========================
		l1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l1Clicked == false) {
					if(GUITurnCounter %2 == 0) {
						l1.setText("X");
						GUITurnCounter++;
						l1Clicked = true;
						gameBoard[0] = "X";
					}
					else {
						l1.setText("O");
						GUITurnCounter++;
						l1Clicked = true;
						gameBoard[0] = "O";
					}
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						if(GUITurnCounter %2 == 0) {
							GUIOWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("O");
						}
						else {
							GUIXWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("X");
						}
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						twoPlayerframe.dispose();
						twoPlayerCATGUI();
					}
				}
			}			
		});	
		l2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l2Clicked == false) {
					if(GUITurnCounter %2 == 0) {
						l2.setText("X");
						GUITurnCounter++;
						l2Clicked = true;
						gameBoard[1] = "X";
					}
					else {
						l2.setText("O");
						GUITurnCounter++;
						l2Clicked = true;
						gameBoard[1] = "O";
					}
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						if(GUITurnCounter %2 == 0) {
							GUIOWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("O");
						}
						else {
							GUIXWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("X");
						}
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						twoPlayerframe.dispose();
						twoPlayerCATGUI();
					}
				}
			}			
		});
		l3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l3Clicked == false) {
					if(GUITurnCounter %2 == 0) {
						l3.setText("X");
						GUITurnCounter++;
						l3Clicked = true;
						gameBoard[2] = "X";
					}
					else {
						l3.setText("O");
						GUITurnCounter++;
						l3Clicked = true;
						gameBoard[2] = "O";
					}
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						if(GUITurnCounter %2 == 0) {
							GUIOWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("O");
						}
						else {
							GUIXWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("X");
						}
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						twoPlayerframe.dispose();
						twoPlayerCATGUI();
					}
				}
			}			
		});
		l4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l4Clicked == false) {
					if(GUITurnCounter %2 == 0) {
						l4.setText("X");
						GUITurnCounter++;
						l4Clicked = true;
						gameBoard[3] = "X";
						
					}
					else {
						l4.setText("O");
						GUITurnCounter++;
						l4Clicked = true;
						gameBoard[3] = "O";
					}
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						if(GUITurnCounter %2 == 0) {
							GUIOWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("O");
						}
						else {
							GUIXWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("X");
						}
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						twoPlayerframe.dispose();
						twoPlayerCATGUI();
					}				}
			}			
		});
		l5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l5Clicked == false) {
					if(GUITurnCounter %2 == 0) {
						l5.setText("X");
						GUITurnCounter++;
						l5Clicked = true;
						gameBoard[4] = "X";
					}
					else {
						l5.setText("O");
						GUITurnCounter++;
						l5Clicked = true;
						gameBoard[4] = "O";
					}
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						if(GUITurnCounter %2 == 0) {
							GUIOWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("O");
						}
						else {
							GUIXWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("X");
						}
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						twoPlayerframe.dispose();
						twoPlayerCATGUI();
					}
				}
			}			
		});
		l6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l6Clicked == false) {
					if(GUITurnCounter %2 == 0) {
						l6.setText("X");
						GUITurnCounter++;
						l6Clicked = true;
						gameBoard[5] = "X";
					}
					else {
						l6.setText("O");
						GUITurnCounter++;
						l6Clicked = true;
						gameBoard[5] = "O";
					}
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						if(GUITurnCounter %2 == 0) {
							GUIOWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("O");
						}
						else {
							GUIXWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("X");
						}
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						twoPlayerframe.dispose();
						twoPlayerCATGUI();
					}
				}
			}			
		});
		l7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l7Clicked == false) {
					if(GUITurnCounter %2 == 0) {
						l7.setText("X");
						GUITurnCounter++;
						l7Clicked = true;
						gameBoard[6] = "X";
					}
					else {
						l7.setText("O");
						GUITurnCounter++;
						l7Clicked = true;
						gameBoard[6] = "O";
					}
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						if(GUITurnCounter %2 == 0) {
							GUIOWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("O");
						}
						else {
							GUIXWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("X");
						}
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						twoPlayerframe.dispose();
						twoPlayerCATGUI();
					}
				}
			}			
		});
		l8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l8Clicked == false) {
					if(GUITurnCounter %2 == 0) {
						l8.setText("X");
						GUITurnCounter++;
						l8Clicked = true;
						gameBoard[7] = "X";
					}
					else {
						l8.setText("O");
						GUITurnCounter++;
						l8Clicked = true;
						gameBoard[7] = "O";
					}
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						if(GUITurnCounter %2 == 0) {
							GUIOWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("O");
						}
						else {
							GUIXWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("X");
						}
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						twoPlayerframe.dispose();
						twoPlayerCATGUI();
					}
				}
			}			
		});
		l9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l9Clicked == false) {
					if(GUITurnCounter %2 == 0) {
						l9.setText("X");
						GUITurnCounter++;
						l9Clicked = true;
						gameBoard[8] = "X";
					}
					else {
						l9.setText("O");
						GUITurnCounter++;
						l9Clicked = true;
						gameBoard[8] = "O";
					}
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						if(GUITurnCounter %2 == 0) {
							GUIOWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("O");
						}
						else {
							GUIXWin++;
							twoPlayerframe.dispose();
							twoPlayerVictoryGUI("X");
						}
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						twoPlayerframe.dispose();
						twoPlayerCATGUI();
					}
				}
			}			
		});
	}
	
	/**
	 * Handles GameOver Buttons for two-player GUI game
	 */
	public static void twoPlayerGameOverButtonUpdate() {
		replay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				twoPlayerGameGUI();
			}	
		});
		noReplay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}	
		});
	}

	/**
	 * GUI in case of victory
	 * 
	 * @param winner Describes the winner of the game
	 */
	public static void twoPlayerVictoryGUI(String winner) {
		twoPlayerVicotryFrame = new JFrame("Game Over");
		twoPlayerWinner = new JLabel ("The Winner is: " + winner);
		winTalley = new JLabel("X Wins: " + GUIXWin + "   O Wins: " + GUIOWin + "   TIES: " + GUICat);
		replay = new JButton("Play Again");
		noReplay = new JButton("Quit");
		twoPlayerVictoryWin = new JPanel();
		twoPlayerVictoryWin.add(twoPlayerWinner);
		twoPlayerVictoryWin.add(winTalley);
		twoPlayerVictoryWin.add(replay);
		twoPlayerVictoryWin.add(noReplay);
		twoPlayerVictoryWin.setBackground(background);
		twoPlayerVicotryFrame.add(twoPlayerVictoryWin);
		twoPlayerVicotryFrame.setSize(200,150);
		twoPlayerVicotryFrame.setVisible(true);
		twoPlayerGameOverButtonUpdate();
	}
	
	/**
	 * GUI in case of tie
	 */
	public static void twoPlayerCATGUI() {
		twoPlayerVicotryFrame = new JFrame("Game Over");
		twoPlayerWinner = new JLabel ("CAT");
		winTalley = new JLabel("X Wins: " + GUIXWin + "   O Wins: " + GUIOWin + "   TIES: " + GUICat);
		replay = new JButton("Play Again");
		noReplay = new JButton("Quit");
		twoPlayerVictoryWin = new JPanel();
		twoPlayerVictoryWin.add(twoPlayerWinner);
		twoPlayerVictoryWin.add(winTalley);
		twoPlayerVictoryWin.add(replay);
		twoPlayerVictoryWin.add(noReplay);
		twoPlayerVictoryWin.setBackground(background);
		twoPlayerVicotryFrame.add(twoPlayerVictoryWin);
		twoPlayerVicotryFrame.setSize(200,150);
		twoPlayerVicotryFrame.setVisible(true);
		twoPlayerGameOverButtonUpdate();
	}
	
	/**
	 * Handles GUI-Based game play
	 */
	public static void twoPlayerGameGUI() {
		GUIWinCondition = false;
		twoPlayerGUIInit();
		twoPlayerButtonUpdate();
	}
	
	//================================MAIN METHOD=====================================
	/**
	 * Runs the game
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Would you like to play 1 player or 2 player?");
		System.out.println("1 or 2");
		int gameType = scan.nextInt();
		if(gameType == 1) {
			System.out.println("Not Implemented");
		}
		else if(gameType == 2) {
			System.out.println("Hacker Mode?");
			System.out.println("Y/N");
			String decide = scan.next().toLowerCase();
			if(decide.equals("y")) {
				twoPlayerGame();
			}
			else if(decide.equals("n")){
				twoPlayerGameGUI();
			}
			else {
				System.out.println("Not smart enough for Hacker Mode...");
				twoPlayerGUIInit();
			}
		}
		else {
			System.out.println("No");
		}
		scan.close();
	}
}
