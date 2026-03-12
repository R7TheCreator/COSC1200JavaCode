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

            else System.out.println("Your names are: " + playerOne + " and " + playerTwo);

        }
    }
    public static double getAngle(String playerName, Scanner input){
        boolean isLoop = true;
        double angle = 0;
        while (isLoop){
            System.out.print(playerName + "Enter an Angle: ");
            angle = input.nextDouble();

            if (angle >= 0 && angle <= 180){
                isLoop = false;
            }
            else System.out.println("Invalid angle");
        }
        return angle;
    }
}
