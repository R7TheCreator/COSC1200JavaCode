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
        // variables
        boolean isLoop = true;
        double angle = 0;
        // loop
        while (isLoop){
            System.out.print(playerName + " Enter an Angle: ");
            angle = input.nextDouble();
            // conditions
            if (angle >= 0 && angle <= 180){
                isLoop = false;
            }
            else System.out.println("Invalid angle");
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
            velocity = input.nextDouble();
            // conditions
            if (velocity >= 1 && velocity <= 1000){
                isLoop = false;
            }
            else System.out.println("Invalid velocity");
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
}
