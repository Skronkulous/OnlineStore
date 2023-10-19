package com.pluralsight;
import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.time.*;

import static com.pluralsight.CartFunctionality.*;
import static com.pluralsight.HomeScreen.*;

public class FileManipulators {
    public static ProductMap loadInventory(){
        HashMap<String, Product> productMap = new HashMap<String, Product>();
        try{
            FileReader fr = new FileReader("src/main/resources/products.csv");
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String tempInfo = "";
            while ((tempInfo = br.readLine()) != null) {
                String[] splitLine = tempInfo.split("\\|");
                String sku = (splitLine[0]);
                String name = (splitLine[1]);
                double price = Double.parseDouble(splitLine[2]);
                String department = (splitLine[3]);
                Product newProduct = new Product(sku, name, price, department);
                productMap.put(sku, newProduct);
            }
            fr.close();
            br.close();
        }
        catch(Exception fileError){
            System.out.println("There seems to be an error with the current file path, please update and try again.");
            fileError.printStackTrace();
        }
        ProductMap rtn = new ProductMap(productMap);
        return rtn;
    }
    public static void writeReceipt(double payment, double changeGiven, double cartTotal){
        try{
            LocalDateTime today = LocalDateTime.now();
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            FileWriter fw = new FileWriter("src/main/resources/receipts/" + Integer.toString(today.getYear()) + today.getMonthValue() + Integer.toString(today.getDayOfMonth()) + Integer.toString(today.getHour())+ Integer.toString(today.getMinute()) + ".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            System.out.println("Radio Shack (Post Bankruptcy) Receipt:\nDate: " + today.format(fmt));
            bw.write("Radio Shack (Post Bankruptcy) Receipt:\nDate: " + today.format(fmt));
            bw.newLine();
            for(CartItem p: cartMap.values()){
                Product tempProduct = p.getProduct();
                String itemString = ("SKU: " + tempProduct.getSku() + " | Name: " + tempProduct.getName() + " | Price: " + tempProduct.getPrice() + " | Quantity: " + p.getQuantity());
                System.out.println(itemString);
                bw.write(itemString);
                bw.newLine();
            }
            String formatted = String.format("Total Price: $%.2f \nPayment Given: $%.2f \nChange Received: $%.2f\n", cartTotal, payment, changeGiven);
            System.out.println(formatted);
            bw.write(formatted);
            bw.close();
        }
        catch(Exception fileError){
            System.out.println("There seems to be an error with the current file path, please update and try again.\nReturning to the main menu.\n");
            mainMenu();
        }
    }
}
