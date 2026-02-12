//------------------------------------------------
// Name: Rasul.A
// Date: Feb, 11, 2026
// Description: Assignment 1: Pizza Cutting
//------------------------------------------------
// import scanner for inputs
import java.util.Scanner;

public class pizzaCutting {
    public static void main(String[] args){
        // set up the scanner
        Scanner input = new Scanner(System.in);

        // setting up slices
        int numOfSlices = 0;

        // set up the loops with a boolean
        boolean cuttingThePizza = true;
        boolean validate = false;

        // Loop
        while (cuttingThePizza){

            // Ask for the diameter
            System.out.print("Enter the Diameter of your pizza: ");
            float userDiameter = input.nextFloat();

            // if statements for slices
            if (userDiameter >= 6 && userDiameter < 8) {
                numOfSlices = 4;
                validate = true;
            } else if (userDiameter >= 8 && userDiameter < 12) {
                numOfSlices = 6;
                validate = true;
            }
            else if (userDiameter >= 12 && userDiameter < 14) {
                numOfSlices = 8;
                validate = true;
            }
            else if (userDiameter >= 14 && userDiameter < 16) {
                numOfSlices = 10;
                validate = true;
            }
            else if (userDiameter >= 16 && userDiameter < 20) {
                numOfSlices = 12;
                validate = true;
            }
            else if (userDiameter >= 20 && userDiameter < 25) {
                numOfSlices = 16;
                validate = true;
            }
            else {
                System.out.println("Error: Input a number between 6 & 24.");
            }
            // a validation loop to ensure it wouldn't calculate 25+
            while (validate) {
                // calculations
                // Get the radius from user
                float userRadius = userDiameter / 2;

                // Calculate the arena of the pizza
                float totalArea = (float) (Math.PI * Math.pow(userRadius, 2));

                // Calculate the arena of a slice
                float sliceArea = totalArea / numOfSlices;

                // print the outcome
                System.out.printf("The area of a slice is %.2f square inches %n", sliceArea);
                validate = false;

            }
        }
    }
}
