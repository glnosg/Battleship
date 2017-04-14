import java.util.*;

public class GameLauncher {

		private GameHelper helper = new GameHelper();
		private ArrayList<Ship> shipsList = new ArrayList<Ship>();
		private int numOfGuesses = 0;
		
		private void setUpGame() {
		
			Ship one = new Ship();
			Ship two = new Ship();
			Ship three = new Ship();
			
			one.setName("Tiger");
			two.setName("Eagle");
			three.setName("Wolverine");
			
			shipsList.add(one);
			shipsList.add(two);
			shipsList.add(three);
			
			System.out.println("Your goal is to sink three battleships.");
			System.out.println("Tiger, Eagle and Wolverine");
			System.out.println("Try to sink them all in the fewest number of guesses.");
			
			for (Ship shipToSet : shipsList) {
				ArrayList<String> newLocation = helper.placeShip(3);
				shipToSet.setLocationCells(newLocation);
			}
		}
		
		private void startPlaying() {
			while(!shipsList.isEmpty()) {
				String userGuess = helper.getUserInput("Enter a guess:");
				checkUserGuess(userGuess);
			}
			finishGame();
		}
		
		private void checkUserGuess(String userGuess) {
			
			numOfGuesses++;
			String result = "miss";
			
			for (Ship shipToTest : shipsList) {
				result = shipToTest.checkYourself(userGuess);
				
				if (result.equals("hit"))
					break;
				
				if (result.equals("kill")) {
					shipsList.remove(shipToTest);
					break;
				}
			}
			System.out.println(result);
		}
		
		private void finishGame() {
			System.out.println("All battleships are destroyed! Your stock is now worthless.");
			
			if (numOfGuesses <= 18) {
				System.out.println("It only took you" + numOfGuesses + "guesses.");
				System.out.println("You got out before your options sank");
			} else {
				System.out.println("It only took long enough." + numOfGuesses + "guesses.");
				System.out.println("Fishes are dancing with your options.");
			}
		}
		
		public static void main (String[] args) {
			GameLauncher game = new GameLauncher();
			game.setUpGame();
			game.startPlaying();
		}
}