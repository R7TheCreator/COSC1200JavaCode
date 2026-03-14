// Name: Rasul Amini
// Date: March 10th, 2026
// Description: Assignment for class COSC1200

// setup scanner
import java.util.Scanner;

// import random
import java.util.Random;

public class ProjectileMotion {
    public static void main(String[] args){

        // Variables

        // player variables
        String playerOne = " ";
        String playerTwo = " ";
        int playerOneScore = 0;
        int playerTwoScore = 0;

        // loop variable
        boolean isActive = true;

        // import scanner
        Scanner input = new Scanner(System.in);

        // loop
        while (isActive){
            // Starting menu
            System.out.println("--- Welcome to the projectile game ---");
            // Rules
            System.out.println("How to play: First select which player you are, " +
                    "and type in your desired angle and power for your shot. " +
                    "First to eliminate the other play wins!");

            System.out.println("-- Select player names --");
            // player naming
            System.out.print("Player One Name: ");
            playerOne = input.nextLine();
            System.out.print("Player Two Name: ");
            playerTwo = input.nextLine();

            if (playerOne.equalsIgnoreCase(playerTwo)){
                System.out.println("Select different names");
            }

            else {
                System.out.println("Your names are: " + playerOne + " and " + playerTwo);

                // determine winner + scores
                int winner = runGame(playerOne, playerTwo, input);

                input.nextLine();

                if (winner == 1){
                    playerOneScore++;
                } else if (winner == 2) {
                    playerTwoScore++;
                }

                // scoreboard
                System.out.println("--- Scoreboard ---");
                System.out.println(playerOne + ": " + playerOneScore);
                System.out.println(playerTwo + ": " + playerTwoScore);
                System.out.println("-------------------");

                // first to 3 wins the entire game
                if (playerOneScore == 3){
                    System.out.println(playerOne + " is the overall winner!");
                    isActive = false;
                } else if (playerTwoScore == 3) {
                    System.out.println(playerTwo + " is the overall winner!");
                    isActive = false;
                }
                else {
                    System.out.print("Press enter to continue, or type 'Q' to quit: ");
                    String choice = input.nextLine();

                    if (choice.equalsIgnoreCase("Q")){
                        isActive = false;
                        System.out.println("Game over. Final Scores - " + playerOne + ": " + playerOneScore);
                        System.out.println("Game over. Final Scores - " + playerTwo + ": " + playerTwoScore);
                    }
                }
            }

        }
    }
    public static double getAngle(String playerName, Scanner input){
        // variables
        boolean isLoop = true;
        double angle = 0;
        // loop
        while (isLoop){
            System.out.print(playerName + " Enter an Angle: ");
            // conditions
            if (input.hasNextDouble()){
                angle = input.nextDouble();
                if (angle >= 0 && angle <= 180){
                    isLoop = false;
                }
                else System.out.println("Invalid angle. Enter a value between 0 and 180");
            }
            else {
                System.out.println("Invalid angle.");
                input.next();
            }

        }
        return angle;
    }

    public static double getPower(String playerName, Scanner input){
        // variables
        boolean isLoop = true;
        double velocity = 0;
        // loop
        while (isLoop){
            System.out.print(playerName + " Enter your Power: ");

            // conditions
            if (input.hasNextDouble()){
                velocity = input.nextDouble();
                if (velocity >= 1 && velocity <= 1000){
                    isLoop = false;
                }
                else {
                    System.out.println("Invalid velocity, enter between 1 and 1000");
                }
            }
            else {
                System.out.println("Invalid velocity");
                input.next();
            }
        }
        return velocity;
    }

    public static double getShot(String playerName, Scanner input, double startingX){
        // variables
        double angle = getAngle(playerName, input);
        double velocity = getPower(playerName, input);
        double gravity = 9.8;

        // convert radians for java
        double radians = Math.toRadians(angle);

        // find time
        double t = (2 * velocity * Math.sin(radians) / gravity);

        // find the lading coordinate
        double xLanding = (velocity * Math.cos(radians)) * t + startingX;

        // output message
        System.out.println("The shot landed at: " + xLanding);

        return xLanding;
    }

    public static int runGame(String p1Name, String p2Name, Scanner input){
        // loop setup
        boolean isLoop = true;

        // input random
        Random rand = new Random();

        // set player positions
        double p1Position = rand.nextDouble() * 120;
        double p2Position = rand.nextDouble() * 120;

        // player positions
        System.out.println(p1Name + " Player one position: " + p1Position);
        System.out.println(p2Name + " Player two position: " + p2Position);

        // main game loop for turns
        while (isLoop){
            // player 1 turn
            double p1Landing = getShot(p1Name, input, p1Position);
            // check if player 1 hit player 2
            if (Math.abs(p1Landing - p2Position) < 1){
                System.out.println("Direct hit, " + p1Name + " wins!");
                return 1;
            }
            // player 2 turn
            double p2Landing = getShot(p2Name, input, p2Position);
            // check if player two hit player one
            if (Math.abs(p2Landing - p1Position) < 1){
                System.out.println("Direct hit, " + p2Name + " wins!");
                return 2;
            }
        }
        return 0;

    }
}
