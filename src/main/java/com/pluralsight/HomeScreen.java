package com.pluralsight;
import java.util.Scanner;

import static com.pluralsight.DisplayFuctionality.*;
import static com.pluralsight.FileManipulators.*;
public class HomeScreen {
    public static ProductMap productMap;
    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Hello! Welcome to RadioShack (Post Bankruptcy):");
        productMap = loadInventory();
        mainMenu();
    }

    public static void mainMenu(){
        System.out.print("\nHow can we help you today? (Please choose an option 1, 2, or 3)\n\t1)Display Products\n\t2)Display Cart\n\t3)Exit (Closes Application)\nUser Input: ");
        String choice = userInput.nextLine();
        switch(choice){
            case("1"):
                productMenu();
                break;
            case("2"):
                cartMenu();
                break;
            case("3"):
                System.out.println("Thank you, & see you again soon!");
                break;
            default:
                System.out.println("\nPlease enter a valid option (1, 2, or 3).\n");
                mainMenu();
        }
    }
}
