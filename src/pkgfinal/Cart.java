/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

// Cart.java
import java.io.*;
import java.util.*;

public class Cart {
    private Map<Product, Integer> productQuantities;

    public Cart() {
        productQuantities = new HashMap<>();
    }

    public void addProduct(Product product) {
        productQuantities.put(product, productQuantities.getOrDefault(product, 0) + 1);
    }

    public void displayCart() {
        if (productQuantities.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Cart Contents:");
            for (Map.Entry<Product, Integer> entry : productQuantities.entrySet()) {
                System.out.println(entry.getKey() + " - Quantity: " + entry.getValue());
            }
        }
    }

    public void saveCartSummaryToFile(String fileName, String username) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            double totalCost = 0;

            for (Map.Entry<Product, Integer> entry : productQuantities.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                double cost = product.getPrice() * quantity;
                totalCost += cost;

                writer.write(product.getName() + " - Quantity: " + quantity + ",");
            }

            writer.write("Total Cost $" + totalCost +","+username+ "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
}
