import java.util.Scanner; 

/**
 * Plays the tic-tac-toe game. eventually, the AI will never lose.
 * @author Reese Pelletier, Justin Briley
 *
 */
public class GamePlayer {
	
	//================================FIELDS=====================================
	static final Scanner scan = new Scanner(System.in);

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

	
	public static void twoPlayerGame() {
		//Show user how game works
				gamePrelim();
				int OWins = 0;
				int XWins = 0;
				int Cats = 0;
				boolean again = true;
				//Prompt User For Input & Set Input Values
				while(again == true) {
					int counter = 0;
					boolean win = false;
					int locArray[] = new int[9];
					String gameBoard[] = initGameBoard();
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
	//================================MAIN METHOD=====================================
	/**
	 * Runs the game
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Would you like to play 1 player or 2 player?");
		System.out.println("1/2");
		int gameType = scan.nextInt();
		if(gameType == 1) {
			System.out.println("Not Implemented");
		}
		else if(gameType == 2) {
			twoPlayerGame();
		}
		else {
			System.out.println("No");
		}
		scan.close();
	}
}
