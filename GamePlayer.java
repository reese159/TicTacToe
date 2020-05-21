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
		System.out.println("Select a location for your x (1-9): "); //Prompt user for location selection
		int location = scan.nextInt();
		scan.close();
		return location;
	}
	
	/**
	 * Prints newly created grid
	 */
	public static void gridHandler() {
		System.out.println(" | | ");		
		System.out.println(" | | ");	
		System.out.println(" | | ");	
	}
	
	/**
	 * Runs the game
	 * @param args
	 */
	public static void main(String[] args) {
		int loc = valuePrompter();
		System.out.println("Value entered: " + loc);
		gridHandler();
		//TODO: Dictionary or Array-Based implementation?
		//Dictionary makes sense, but unsure if AI could be implemented
	}
	

}
