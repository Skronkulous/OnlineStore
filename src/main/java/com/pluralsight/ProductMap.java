package com.pluralsight;

import java.util.HashMap;

public class ProductMap {
    public static HashMap<String, Product> productMap;
    ProductMap(HashMap<String, Product> productList){
        this.productMap = productList;
    }

    public static void displayProductList(){
        System.out.println("\nAll Available Products:");
        for(Product p: productMap.values()){
            System.out.println("SKU: " + p.getSku() + " | Name: " + p.getName() + " | Price: " + p.getPrice());
        }
        System.out.println();
    }

    public static void displayDptProducts(String dptChoice){
        System.out.println("Products in the " + dptChoice + " department:");
        productMap.forEach((key, values)->{
            if(values.getDepartment().equalsIgnoreCase(dptChoice)){
                System.out.println("Name: " + values.getName() + " | Price: " + values.getPrice());
            }
        });
    }
}
