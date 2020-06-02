package GameCollection;
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
	//General use fields
	static final Scanner scan = new Scanner(System.in);
	static Color background = new Color(32, 160, 230);
	//one-player GUI fields
	static JFrame onePlayerframe;
	static JButton l1, l2, l3, l4, l5, l6, l7, l8, l9;
	static JLabel onePlayerTitle;
	static JPanel onePlayerWin;
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
	//one-player game over fields
	static JLabel winTalley;
	static JButton replay, noReplay;
	static JFrame onePlayerVicotryFrame;
	static JLabel onePlayerWinner;
	static JPanel onePlayerVictoryWin;
	static JFrame onePlayerCATFrame;
	static JLabel onePlayerTie;
	static JPanel onePlayerCatWin;

	//===================================ONE-PLAYER METHODS=====================================
	
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
	 * Implementation of AI strategy
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
	 * one player game
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
	 * Update Game Window As Game Progresses
	 */
	public static void onePlayerGameButtonUpdate() {
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
		
		//0 = player
		//1 = AI
		Random rand = new Random();
		int player = rand.nextInt(2);
		
		//@SuppressWarnings("unused")
		String gameBoard[] = initGameBoard();
		
		//AI first move
		if(player == 0) {
			onePlayerGUIAI(gameBoard);
		}
		
		l1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l1Clicked == false) {
					//Player Turn
					l1.setText("X");
					GUITurnCounter++;
					l1Clicked = true;
					gameBoard[0] = "X";
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIXWin++;
						
						onePlayerVictoryGUI("X");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
					//AI Turn
					onePlayerGUIAI(gameBoard);
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIOWin++;
						
						onePlayerVictoryGUI("O");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
				}
			}			
		});	
		l2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l2Clicked == false) {
					//Player Turn
					l2.setText("X");
					GUITurnCounter++;
					l2Clicked = true;
					gameBoard[1] = "X";
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIXWin++;
						
						onePlayerVictoryGUI("X");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
					//AI Turn
					onePlayerGUIAI(gameBoard);
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIOWin++;
						
						onePlayerVictoryGUI("O");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
				}
			}			
		});
		l3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l3Clicked == false) {
					//Player Turn
					l3.setText("X");
					GUITurnCounter++;
					l3Clicked = true;
					gameBoard[2] = "X";
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIXWin++;
						
						onePlayerVictoryGUI("X");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
					//AI Turn
					onePlayerGUIAI(gameBoard);
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIOWin++;
						
						onePlayerVictoryGUI("O");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
				}
			}			
		});
		l4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l4Clicked == false) {
					//Player Turn
					l4.setText("X");
					GUITurnCounter++;
					l4Clicked = true;
					gameBoard[3] = "X";
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIXWin++;
						
						onePlayerVictoryGUI("X");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
					//AI Turn
					onePlayerGUIAI(gameBoard);
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIOWin++;
						
						onePlayerVictoryGUI("O");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}			
				}
			}			
		});
		l5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l5Clicked == false) {
					//Player Turn
					l5.setText("X");
					GUITurnCounter++;
					l5Clicked = true;
					gameBoard[4] = "X";
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIXWin++;
						
						onePlayerVictoryGUI("X");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
					//AI Turn
					onePlayerGUIAI(gameBoard);
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIOWin++;
						
						onePlayerVictoryGUI("O");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
				}
			}			
		});
		l6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l6Clicked == false) {
					//Player Turn
					l6.setText("X");
					GUITurnCounter++;
					l6Clicked = true;
					gameBoard[5] = "X";
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIXWin++;
						
						onePlayerVictoryGUI("X");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
					//AI Turn
					onePlayerGUIAI(gameBoard);
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIOWin++;
						
						onePlayerVictoryGUI("O");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
				}
			}			
		});
		l7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l7Clicked == false) {
					//Player Turn
					l7.setText("X");
					GUITurnCounter++;
					l7Clicked = true;
					gameBoard[6] = "X";
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIXWin++;
						
						onePlayerVictoryGUI("X");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
					//AI Turn
					onePlayerGUIAI(gameBoard);
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIOWin++;
						
						onePlayerVictoryGUI("O");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
				}
			}			
		});
		l8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l8Clicked == false) {
					//Player Turn
					l8.setText("X");
					GUITurnCounter++;
					l8Clicked = true;
					gameBoard[7] = "X";
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIXWin++;
						
						onePlayerVictoryGUI("X");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
					//AI Turn
					onePlayerGUIAI(gameBoard);
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIOWin++;
						
						onePlayerVictoryGUI("O");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
				}
			}			
		});
		l9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l9Clicked == false) {
					//Player Turn
					l9.setText("X");
					GUITurnCounter++;
					l9Clicked = true;
					gameBoard[8] = "X";
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIXWin++;
						
						onePlayerVictoryGUI("X");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
					//AI Turn
					onePlayerGUIAI(gameBoard);
					GUIWinCondition = winCheckGUI(gameBoard, GUITurnCounter);
					if(GUIWinCondition == true) {
						GUIOWin++;
						
						onePlayerVictoryGUI("O");
					}
					else if(GUITurnCounter == 9) {
						GUICat++;
						
						onePlayerCATGUI();
					}
				}
			}			
		});
	}
	
	/**
	 * Update game board as AI makes move
	 * 
	 * @param currBoard Current game state
	 * @return New game state
	 */
	public static String[] onePlayerGUIAI(String[] currBoard){
		int decision = aiStrat(currBoard);
		if(decision == 1) {
			currBoard[0] = "O";
			l1.setText("O");
			l1Clicked = true;
			GUITurnCounter++;
		}
		else if(decision == 2) {
			currBoard[1] = "O";
			l2.setText("O");
			l2Clicked = true;
			GUITurnCounter++;
		}
		else if(decision == 3) {
			currBoard[2] = "O";
			l3.setText("O");
			l3Clicked = true;
			GUITurnCounter++;
		}
		else if(decision == 4) {
			currBoard[3] = "O";
			l4.setText("O");
			l4Clicked = true;
			GUITurnCounter++;
		}
		else if(decision == 5) {
			currBoard[4] = "O";
			l5.setText("O");
			l5Clicked = true;
			GUITurnCounter++;
		}
				
		else if(decision == 6) {
			currBoard[5] = "O";
			l6.setText("O");
			l6Clicked = true;
			GUITurnCounter++;
		}
		else if(decision == 7) {
			currBoard[6] = "O";
			l7.setText("O");
			l7Clicked = true;
			GUITurnCounter++;
		}
		else if(decision == 8) {
			currBoard[7] = "O";
			l8.setText("O");
			l8Clicked = true;
			GUITurnCounter++;
		}
		else if(decision == 9) {
			currBoard[8] = "O";
			l9.setText("O");
			l9Clicked = true;
			GUITurnCounter++;
		}
		return currBoard;
	}
	
	/*
	 * Handles GameOver Buttons for one-player GUI game
	 */
	public static void onePlayerGameOverButtonUpdate() {
		replay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onePlayerframe.dispose();
				onePlayerVicotryFrame.dispose();
				onePlayerGameGUI();
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
	public static void onePlayerVictoryGUI(String winner) {
		onePlayerVicotryFrame = new JFrame("Game Over");
		onePlayerWinner = new JLabel ("The Winner is: " + winner);
		winTalley = new JLabel("X Wins: " + GUIXWin + "   O Wins: " + GUIOWin + "   TIES: " + GUICat);
		replay = new JButton("Play Again");
		noReplay = new JButton("Quit");
		onePlayerVictoryWin = new JPanel();
		onePlayerVictoryWin.add(onePlayerWinner);
		onePlayerVictoryWin.add(winTalley);
		onePlayerVictoryWin.add(replay);
		onePlayerVictoryWin.add(noReplay);
		onePlayerVictoryWin.setBackground(background);
		onePlayerVicotryFrame.add(onePlayerVictoryWin);
		onePlayerVicotryFrame.setSize(200,150);
		onePlayerVicotryFrame.setVisible(true);
		onePlayerGameOverButtonUpdate();
	}
	
	/**
	 * GUI in case of tie
	 */
	public static void onePlayerCATGUI() {
		onePlayerVicotryFrame = new JFrame("Game Over");
		onePlayerWinner = new JLabel ("CAT");
		winTalley = new JLabel("X Wins: " + GUIXWin + "   O Wins: " + GUIOWin + "   TIES: " + GUICat);
		replay = new JButton("Play Again");
		noReplay = new JButton("Quit");
		onePlayerVictoryWin = new JPanel();
		onePlayerVictoryWin.add(onePlayerWinner);
		onePlayerVictoryWin.add(winTalley);
		onePlayerVictoryWin.add(replay);
		onePlayerVictoryWin.add(noReplay);
		onePlayerVictoryWin.setBackground(background);
		onePlayerVicotryFrame.add(onePlayerVictoryWin);
		onePlayerVicotryFrame.setSize(200,150);
		onePlayerVicotryFrame.setVisible(true);
		onePlayerGameOverButtonUpdate();
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
	
	/*
	 * Handles GUI-Based game play
	 */
	public static void onePlayerGameGUI() {
		GUIWinCondition = false;
		onePlayerGUIInit();
		onePlayerGameButtonUpdate();
	}
	
}
