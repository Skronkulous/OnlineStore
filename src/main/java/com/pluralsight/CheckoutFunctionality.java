package com.pluralsight;
import java.util.Scanner;

import static com.pluralsight.CartFunctionality.*;
import static com.pluralsight.FileManipulators.*;
import static com.pluralsight.HomeScreen.*;

public class CheckoutFunctionality {
    public static Scanner userInput = new Scanner(System.in);
    public static void checkout(){
        try{
            double cartTotal = cartListTotal();
            System.out.printf("Your total is: $%.2f", cartTotal);
            System.out.print("\nPlease enter your payment amount: $");
            double payment = userInput.nextDouble();
            userInput.reset();
            if(payment >= cartTotal){
                double changeGiven = (payment-cartTotal);
                System.out.printf("\nYour change is: $%.2f \n\nThank you for shopping with us!\n", changeGiven);
                writeReceipt(payment, changeGiven, cartTotal);
                System.out.println("Returning to main menu...");
                cartMap.clear();
                mainMenu();
            }
            else{
                System.out.println("\nYour payment is not enough, please get your money up, not your funny up.\n\nReturning to main menu...");
                mainMenu();
            }
        }
        catch(Exception parseError){
            System.out.println("Please enter a valid payment amount (Ex. $10.34).");
            checkout();
        }
    }
}
