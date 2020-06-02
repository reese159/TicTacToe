import java.util.Scanner;
import java.util.Random;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;

/**
 * Single player tic-tac-toe competent AI
 * ONLY WAY TO WIN IS CREATE A FORKING PATH
 * @author Reese Pelletier, Justin Briley
 *
 */
public class TicTacToeAIGame{
	
	//============================FIELDS==============================================
	//Start menu fields
	static JFrame startFrame;
	static JButton onePlayer, twoPlayer, quit;
	static JLabel GamePlay;
	static JCheckBox hackerButton;
	static JPanel startWin;
	static int gameType = 0;
	static boolean hackerMode = false;
	//General Fields
	static final Scanner scan = new Scanner(System.in);
	//
	static JFrame onePlayerframe;
	static JButton l1, l2, l3, l4, l5, l6, l7, l8, l9;
	static JLabel onePlayerTitle;
	static JPanel onePlayerWin;
	static Color background = new Color(32, 160, 230);
	
	
	//================================MAIN METHOD=====================================	
	/**
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
					//twoPlayerGameGUI();
					startFrame.dispose();
				}
				else {
					startFrame.dispose();
					//twoPlayerGame();
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
					onePlayerGUIInit();
					startFrame.dispose();
				}
				else {
					startFrame.dispose();
					onePlayerGame();
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
	
	//===================================ONE-PLAYER METHODS=====================================
	/**
	 * Runs the game
	 * @param args
	 */
	public static void main(String[] args) {
		startMenu();
	}
	
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
	public static String[] update(String[] currBoard, int loc, int counter, int playerTurn) {
		
		for(int i = 0; i < 9; i++) {
			if(i == loc - 1) {
				if(playerTurn == 0)
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
	 * Implementation of AI strategy to always win
	 * Uses basic order-of-operations strategy
	 * 
	 * @param currBoard current game board
	 * @return AI decision
	 */
	@SuppressWarnings("null")
	public static int aiStrat(String[] currBoard) {
		//check 1
		if(check1(currBoard) != 0) {
			return check1(currBoard);
		}
		//check 2
		if(check2(currBoard) != 0) {
			return check2(currBoard);
		}
		//check 3
		//FORK NOT IMPLEMENTED
		//check 4
		//FORK BLOCK NOT IMPLEMENTED
		//check 5
		if(check3(currBoard) != 0) {
			return check3(currBoard);
		}
		//check 6
		if(check4(currBoard) != 0) {
			return check4(currBoard);
		}
		//check 7
		if(check5(currBoard) != 0) {
			return check5(currBoard);
		}
		//check 8
		if(check6(currBoard) != 0) {
			return check6(currBoard);
		}
		
		return (Integer) null;
	}
	
	/**
	 * Check if winning is currently possible
	 * 
	 * @param currBoard current game board
	 * @return Use/Don't Use
	 */
	public static int check1(String[] currBoard) {
		 //Column Checker
        for(int i = 0; i < 3; i++) {
            if(currBoard[i] == "O" && currBoard[i+3] == "O" && currBoard[i+6] == " ") {
                return i + 7;
            }
            else if(currBoard[i+3] == "O" && currBoard[i+6] == "O" && currBoard[i] == " ") {
                return i + 1;
            }
            else if (currBoard[i] == "O" && currBoard[i+6] == "O" && currBoard[i + 3] == " ") {
                return i + 4;
            }
        }

    //Row Checker
        for(int i = 0; i < 9; i+=3) {
            if(currBoard[i] == "O" && currBoard[i+1] == "O" && currBoard[i+2] == " ") {
                return i + 3;
            }
            else if(currBoard[i+1] == "O" && currBoard[i+2] == "O" && currBoard[i] == " ") {
                return i + 1;
            }
            else if(currBoard[i] == "O" && currBoard[i+2] == "O" && currBoard[i + 1] == " ") {
                return i + 2;
            }
        }

        //Right Diag Checker
        if(currBoard[0] == "O" && currBoard[4] == "O" && currBoard[8] == " ") {
            return 9;
        }

        if(currBoard[4] == "O" && currBoard[8] == "O" && currBoard[0] == " ") {
            return 1;
        }

        if (currBoard[0] == "O" && currBoard[8] == "O" && currBoard[4] == " ") {
            return 5;
        }

        //Left Diag Checker
        if(currBoard[2] == "O" && currBoard[4] == "O" && currBoard[6] == " ") {
            return 7;
        }

        if (currBoard[4] == "O" && currBoard[6] == "O" && currBoard[2] == " ") {
            return 3;
        }

        if (currBoard[2] == "O" && currBoard[6] == "O" && currBoard[4] == " ") {
            return 5;
        }
        return 0;
	}
	
	/**
	 * Check if losing is currently possible
	 * 
	 * @param currBoard current game board
	 * @return Use/Don't Use
	 */
	public static int check2(String[] currBoard) {
        //Column Checker
        for(int i = 0; i < 3; i++) {
            if(currBoard[i] == "X" && currBoard[i+3] == "X" && currBoard[i+6] == " ") {
                return i + 7;
            }
            else if(currBoard[i+3] == "X" && currBoard[i+6] == "X" && currBoard[i] == " ") {
                return i + 1;
            }
            else if (currBoard[i] == "X" && currBoard[i+6] == "X" && currBoard[i + 3] == " ") {
                return i + 4;
            }
        }

    //Row Checker
        for(int i = 0; i < 9; i+=3) {
            if(currBoard[i] == "X" && currBoard[i+1] == "X" && currBoard[i+2] == " ") {
                return i + 3;
            }
            else if(currBoard[i+1] == "X" && currBoard[i+2] == "X" && currBoard[i] == " ") {
                return i + 1;
            }
            else if(currBoard[i] == "X" && currBoard[i+2] == "X" && currBoard[i + 1] == " ") {
                return i + 2;
            }
        }

        //Right Diag Checker
        if(currBoard[0] == "X" && currBoard[4] == "X" && currBoard[8] == " ") {
            return 9;
        }

        if(currBoard[4] == "X" && currBoard[8] == "X" && currBoard[0] == " ") {
            return 1;
        }

        if (currBoard[0] == "X" && currBoard[8] == "X" && currBoard[4] == " ") {
            return 5;
        }

        //Left Diag Checker
        if(currBoard[2] == "X" && currBoard[4] == "X" && currBoard[6] == " ") {
            return 7;
        }

        if (currBoard[4] == "X" && currBoard[6] == "X" && currBoard[2] == " ") {
            return 3;
        }

        if (currBoard[2] == "X" && currBoard[6] == "X" && currBoard[4] == " ") {
            return 5;
        }
        return 0;
    }
	
	/**
	 * Check if center is played
	 * 
	 * @param currBoard current game board
	 * @return Use/Don't Use
	 */
	public static int check3(String[] currBoard) {
		if(currBoard[4].equals(" ")) {
			return 5;
		}
		return 0;
	}
	
	/**
	 * Check if opposite corner has been played
	 * Block Corners
	 * 
	 * @param currBoard current game board
	 * @return Use/Don't Use
	 */
	public static int check4(String[] currBoard) {
		if(currBoard[0].equals("X") && currBoard[8].equals(" ")) {
			return 9;
		}
		else if(currBoard[8].equals("X") && currBoard[0].equals(" ")) {
			return 1;
		}
		else if(currBoard[2].equals("X") && currBoard[6].equals(" ")) {
			return 7;
		}
		else if(currBoard[6].equals("X") && currBoard[2].equals(" ")) {
			return 3;
		}
		return 0;
	}
	
	/**
	 * Check if corner has been played
	 * Play corners
	 * 
	 * @param currBoard current game board
	 * @return Use/Don't Use
	 */
	public static int check5(String[] currBoard) {
		Random rand = new Random();
		int loc = rand.nextInt(4);
		if(loc == 0) {
			if(currBoard[0].equals(" ")) {
				return 1;
			}
		}
		if(loc == 1) {
			if(currBoard[2].equals(" ")) {
				return 3;
			}
		}
		if(loc == 2) {
			if(currBoard[6].equals(" ")) {
				return 7;
			}
		}
		if(loc == 3) {
			if(currBoard[8].equals(" ")) {
				return 9;
			}
		}
		return 0;
	}
	
	/**
	 * Check if side has been played
	 * Play sides
	 * 
	 * @param currBoard current game board
	 * @return Use/Don't Use
	 */
	public static int check6(String[] currBoard) {
		while(true) {
			Random rand = new Random();
			int loc = rand.nextInt(4);
			if(loc == 0) {
				if(currBoard[1].equals(" ")) {
					return 2;
				}
			}
			if(loc == 1) {
				if(currBoard[3].equals(" ")) {
					return 4;
				}
			}
			if(loc == 2) {
				if(currBoard[5].equals(" ")) {
					return 6;
				}
			}
			if(loc == 3) {
				if(currBoard[7].equals(" ")) {
					return 8;
				}
			}
		}
		//return 0;
	}
	
 	/**
	 * Check if winner has been decided
	 * @param currBoard current game board after update
	 * @param counter to check player turn
	 * @return win/no win
	 */
	public static boolean winCheck(String[] currBoard, int counter, int player) {
		//X Win Check
		if(player == 0) {
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
	public static void onePlayerGame() {
		//Show user how game works
		//Set Initial Variables
		Random rand = new Random();
		int player = rand.nextInt(2);
		int winner = 0;
		int OWins = 0;
		int XWins = 0;
		int Cats = 0;
		boolean again = true;
		//Game Loop
		while(again == true) {
			gamePrelim();
			int counter = 0;
			boolean win = false;
			int locArray[] = new int[9];
			String gameBoard[] = initGameBoard();
			//Game play
			while(win == false) {
				//====================PLAYER 1 GOES FIRST====================
				if(player == 0) {
					int loc = valuePrompter();
					locArray[0] = loc;
					while(validityCheck(gameBoard, loc) == false) {
						System.out.println("That move was invalid. Please try again.");
						loc = valuePrompter();
					}
					//Update the game board with move
					gameBoard = update(gameBoard, loc, counter, 0);
					win = winCheck(gameBoard, counter, 0);
					if(win == true) {
						winner = 1;
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
					System.out.println("");
					gameBoard = update(gameBoard, aiStrat(gameBoard), counter, 1);
					win = winCheck(gameBoard, counter, 1);
					if(win == true) {
						winner = 2;
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
					System.out.println("");
				}
				//====================AI GOES FIRST====================
				if(player == 1) {
					gameBoard = update(gameBoard, aiStrat(gameBoard), counter, 1);
					win = winCheck(gameBoard, counter, 1);
					if(win == true) {
						winner = 2;
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
					System.out.println("");
					int loc = valuePrompter();
					locArray[0] = loc;
					while(validityCheck(gameBoard, loc) == false) {
						System.out.println("That move was invalid. Please try again.");
						loc = valuePrompter();
					}
					
					//Update the game board with move
					gameBoard = update(gameBoard, loc, counter, 0);
					win = winCheck(gameBoard, counter, 0);
					if(win == true) {
						winner = 1;
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
					System.out.println("");
				}
			}
			//Declare Winner
			if(win == true) {
				if(winner == 1) {
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
				scan.close();
				System.exit(0);
			}
			else if(decide.equals("y")) {
				player = rand.nextInt(2);
				again = true;
				System.out.println("Onto the next game.");
				System.out.println();
			}
			else {
				again = false;
				System.out.println("I'll take that as a no...");
				scan.close();
				System.exit(0);
			}
		}
	}
	
	/**
	 * Initiate GUI for single player game
	 */
	public static void onePlayerGUIInit() {
		//Create frame storing game
		onePlayerframe = new JFrame("Tic-Tac-Toe");
		//Label game type
		JLabel dummy = new JLabel("");
		onePlayerTitle = new JLabel("         One-Player Game");
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
		onePlayerWin = new JPanel();
		onePlayerWin.setLayout(new GridLayout(0,3));
		//Add assets & setup game window
		onePlayerWin.add(l1);
		onePlayerWin.add(l2);
		onePlayerWin.add(l3);
		onePlayerWin.add(l4);
		onePlayerWin.add(l5);
		onePlayerWin.add(l6);
		onePlayerWin.add(l7);
		onePlayerWin.add(l8);
		onePlayerWin.add(l9);
		onePlayerWin.add(dummy);
		onePlayerWin.add(onePlayerTitle);
		onePlayerWin.setBackground(background);
		onePlayerframe.add(onePlayerWin);
		onePlayerframe.setSize(500,450);
		onePlayerframe.setVisible(true);
	}
	
}
