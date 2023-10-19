package com.pluralsight;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import static com.pluralsight.HomeScreen.*;
import static com.pluralsight.ProductMap.productMap;

public class CartFunctionality {
    private static Scanner userInput = new Scanner(System.in);
    public static HashMap<String, CartItem> cartMap = new HashMap<String, CartItem>();
    public static void addCart(){
        try{
            System.out.print("Please enter the SKU of the item you would like to add to your cart (\"x\" to leave): ");
            String skuChoice = userInput.nextLine().toUpperCase();
            if(skuChoice.equals("X")){
                System.out.println("\nReturning to main menu...\n");
                mainMenu();
            }
            else{
                System.out.print("Please enter the quantity of your chosen item: ");
                int quantityChoice = Integer.parseInt(userInput.nextLine());
                cartMap.put(skuChoice, new CartItem (quantityChoice,productMap.get(skuChoice)));
                System.out.println("Item successfully added to cart! Now returning to main menu. ");
                mainMenu();
            }
        }
        catch(Exception inputError){
            System.out.println("Please enter a valid SKU/Quantity.");
            addCart();
        }
    }

    public static void removeCart(){
        try{
            System.out.print("Please enter the SKU of the item you would like to remove from your cart (\"x\" to leave): ");
            String skuChoice = userInput.nextLine().toUpperCase();
            if(skuChoice.equals("X")){
                System.out.println("\nReturning to main menu...\n");
                mainMenu();
            }
            else{
                System.out.print("Please enter the new quantity: ");
                int newQuantityChoice = Integer.parseInt(userInput.nextLine());
                if(newQuantityChoice == 0){
                    cartMap.remove(skuChoice);
                }
                else{
                    cartMap.get(skuChoice).setQuantity(newQuantityChoice);
                }
                System.out.println("\nCart has been successfully updated! Now returning to main menu. ");
                mainMenu();
            }
        }
        catch(Exception inputError){
            System.out.println("Please enter a valid SKU/Quantity.");
            removeCart();
        }
    }
    public static void displayCartList(){
        System.out.println("\nCurrent Cart List:");
        for(CartItem p: cartMap.values()){
            Product temp = p.getProduct();
            System.out.println("SKU: " + temp.getSku() + " | Name: " + temp.getName() + " | Price: " + temp.getPrice() + " | Quantity: " + p.getQuantity());
        }
    }

    public static double cartListTotal(){
        double total = 0.00;
        for(CartItem p : cartMap.values()){
            total += (p.getProduct().getPrice())*p.getQuantity();
        }
        return total;
    }
}
