import java.util.Scanner; 

/**
 * Plays the tic-tac-toe game. eventually, the AI will never lose.
 * @author Reese's PBC
 *
 */
public class GamePlayer {
	
	static final Scanner scan = new Scanner(System.in);
	
	/**
	 * 
	 * @return Player Location
	 */
	public static int valuePrompter() {
		System.out.println("Select a location for your X: "); //Prompt user for location selection
		int location = scan.nextInt();
		return location;
	}
	
	/**
	 * Sets up rules for the game
	 */
	public static void gamePrelim() {
		System.out.println("Do you want to hear the rules?");
		System.out.println("Y/N");
		String RuleDecision = scan.nextLine();		
		if(RuleDecision.equals("Y") || RuleDecision.equals("y")) {
			System.out.println("In this game, you will be playing Tic-Tac-Toe.");
			System.out.println("The game board is made up of a 3x3 grid with the following locations:");
			System.out.println("1|2|3");		
			System.out.println("4|5|6");	
			System.out.println("7|8|9");	
			System.out.println("In order to play the game, simply type a location and hit the enter key.");
		}
		else if(RuleDecision.equals("N") || RuleDecision.equals("n")) {
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
	public static String[] update(String[] currBoard, int loc) {
		for(int i = 0; i < 9; i++) {
			if(i == loc - 1) {
				currBoard[i] = "X";
			}
		}
		
		System.out.println(currBoard[0]+"|"+currBoard[1]+"|"+currBoard[2]);		
		System.out.println(currBoard[3]+"|"+currBoard[4]+"|"+currBoard[5]);	
		System.out.println(currBoard[6]+"|"+currBoard[7]+"|"+currBoard[8]);	
		
		return currBoard;
	}
	
	/**
	 * Runs the game
	 * @param args
	 */
	public static void main(String[] args) {
		//Set variables
		int locArray[] = new int[9];
		String gameBoard[] = initGameBoard();
		
		//Show user how game works
		gamePrelim();
		
		//Prompt User For Input & Set Input Values
		int loc = valuePrompter();
		locArray[0] = loc;
		//System.out.println("Value entered: " + locArray[0]);
		
		
		//==========================NEEDS TO BE IN WHILE LOOP: WHILE GAME IS CURRENTLY GOING==================================
		//Check if move is valid. if valid, add move
		while(validityCheck(gameBoard, loc) == false) {
			System.out.println("That move was invalid. Please try again.");
			loc = valuePrompter();
		}
		//Update the game board with move
		update(gameBoard, loc);
		//==========================NEEDS TO BE IN WHILE LOOP: WHILE GAME IS CURRENTLY GOING==================================
		
		//Close stream
		scan.close();
	}
	

}
