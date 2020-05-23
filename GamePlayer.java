import java.util.Scanner; 

/**
 * Plays the tic-tac-toe game. eventually, the AI will never lose.
 * @author Reese's PBC
 *
 */
public class GamePlayer {
	
	/**
	 * 
	 * @return Player Location
	 */
	public static int valuePrompter() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Select a location for your X: "); //Prompt user for location selection
		int location = scan.nextInt();
		scan.close();
		return location;
	}
	
	/**
	 * Sets up rules for the game
	 */
	public static void gamePrelim() {
		Scanner rules = new Scanner(System.in);
		System.out.println("Do you want to hear the rules?");
		System.out.println("Y/N");
		String RuleDecision = rules.nextLine();		
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
		rules.close();
	}
	
	/**
	 * Update the game board & metadata
	 * 
	 * @param location contains the current location values containing input
	 * @return locations in terms of a char array to be used later
	 */
	public static String[] updater(int[] location, int loc) {
		//if()
		
		return null;
	}
	
	/**
	 * Runs the game
	 * @param args
	 */
	public static void main(String[] args) {
		//Set variables
		int locArray[] = new int[9];
		String playArray[] = new String[9];
		
		//Show user how game works
		gamePrelim();
		
		//Prompt User For Input & Set Input Values
		int loc = valuePrompter();
		locArray[0] = loc;
		System.out.println("Value entered: " + locArray[0]);
		
		//Update the game board with move
	}
	

}
