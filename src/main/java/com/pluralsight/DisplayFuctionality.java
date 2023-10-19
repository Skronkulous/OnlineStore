package com.pluralsight;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static com.pluralsight.CartFunctionality.*;
import static com.pluralsight.CheckoutFunctionality.*;
import static com.pluralsight.HomeScreen.*;
import static com.pluralsight.ProductMap.productMap;
import static com.pluralsight.ProductMap.*;

public class DisplayFuctionality {
    private static Scanner userInput = new Scanner(System.in);
    public static void productMenu(){
        displayProductList();
        System.out.print("\nPlease choose an option (1, 2, or 3).\n\t1) Search or filter product list\n\t2) Add a product to your cart\n\t3)Go back to home page\nUser Input: ");
        String choice = userInput.nextLine();
        userInput.reset();
        switch(choice){
            case("1"):
                filterMenu();
                break;
            case("2"):
                addCart();
                break;
            case("3"):
                mainMenu();
                break;
            default:
                System.out.println("\nPlease enter a valid option (1, 2, or 3).\n");
                productMenu();
                break;
        }
    }

    public static void cartMenu(){
        displayCartList();
        System.out.print("\nPlease select an option (1, 2, or 3).\n\t1)Check out\n\t2)Remove product\n\t3)Go back to home screen\nUser Input: ");
        String choice = userInput.nextLine();
        userInput.reset();
        switch(choice){
            case("1"):
                checkout();
                break;
            case("2"):
                removeCart();
                break;
            case("3"):
                mainMenu();
                break;
            default:
                System.out.println("\nPlease enter a valid option (1, 2, or 3).\n");
                cartMenu();
                break;
        }
    }

    public static void filterMenu(){
        System.out.print("\nSelect which option you would like to filter by (1, 2 or 3).\n\t1)Price filtering\n\t2)Department filtering\n\t3)Go back to main menu\nUser Input: ");
        String choice = userInput.nextLine();
        userInput.reset();
        switch(choice){
            case("1"):
                priceFilter();
                break;
            case("2"):
                departmentFilter();
                break;
            case("3"):
                mainMenu();
                break;
            default:
                System.out.println("\nPlease enter a valid option (1, 2, or 3).\n");
                filterMenu();
                break;
        }
    }

    public static void priceFilter(){
        try{
            System.out.print("\nPlease enter the minimum value to filter by: $");
            double minValue = Double.parseDouble(userInput.nextLine());
            System.out.print("\nPlease enter the maximum value to filter by: $");
            double maxValue = Double.parseDouble(userInput.nextLine());
            userInput.reset();
            boolean found = true;
            System.out.println(productMap.size());
            System.out.printf("\nProducts between: $%.2f and $%.2f:", minValue, maxValue);
            System.out.println();
            for(Product p: productMap.values()){
                if(p.getPrice() >= minValue && p.getPrice() <= maxValue){
                    System.out.println("Name: " + p.getName() + " | Price: " + p.getPrice());
                    found = false;
                }
            }
            if(found){
                System.out.println("\nCould not find any products within the given price range, please try again.");
                priceFilter();
            }
            else{
                System.out.println();
                mainMenu();
            }
        }
        catch(Exception parseError){
            System.out.println("Please enter a valid value (Ex. $5.25).");
            departmentFilter();
        }
    }

    public static void departmentFilter(){
        System.out.print("Please enter which department to filter by (1, 2, 3, or 4).\n\t1)Audio Video\n\t2)Computers\n\t3)Games\n\t4)Electronics\nUser Input: ");
        String choice = userInput.nextLine();
        userInput.reset();
        switch (choice){
            case ("1"):
                displayDptProducts("Audio Video");
                mainMenu();
                break;
            case("2"):
                displayDptProducts("Computers");
                mainMenu();
                break;
            case("3"):
                displayDptProducts("Games");
                mainMenu();
                break;
            case("4"):
                displayDptProducts("Electronics");
                mainMenu();
                break;
            default:
                System.out.println("Please enter a valid option (1, 2, 3, or 4).");
                departmentFilter();
                break;

        }

    }

}
