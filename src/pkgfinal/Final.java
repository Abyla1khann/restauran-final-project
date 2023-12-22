package pkgfinal;

import java.util.*;
import java.io.*;

public class Final {

    private static final int WIDTH = 45;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int choise;
        int action;

        do {
            System.out.println("+================Welcome================+");
            System.out.println("|                                       |");
            System.out.println("| 1. Log in                             |");
            System.out.println("| 2. Register                           |");
            System.out.println("|                                       |");
            System.out.println("+=======================================+");
            System.out.print("Enter your choice (1 or 2): ");

            while (!scanner.hasNextInt()) {
                System.out.println("+================Welcome================+");
                System.out.println("|                                       |");
                System.out.println("| 1. Log in                             |");
                System.out.println("| 2. Register                           |");
                System.out.println("|                                       |");
                System.out.println("+=======================================+");
                System.out.println("Invalid input. Please enter 1 or 2.");
                scanner.next(); // to avoid infinite loop
            }

            choise = scanner.nextInt();
            scanner.nextLine(); // clear the newline character

            if (choise != 1 && choise != 2) {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } while (choise != 1 && choise != 2);
        switch (choise) {
            case 1:
                LoginSystem loginSystem = new LoginSystem();
                System.out.println("\n\n\n\n\n\n\n\n");
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();

                boolean success = loginSystem.login(username, password);
                if (success) {
                    System.out.println("\n\n\n\n\n\n\n\n");
                    printBorder();
                    printCentered("Log in");
                    printCentered("Enter username: " + username);
                    printCentered("Enter password: " + password);
                    printCentered("");
                    printCentered("Log in successful!");
                    printBorder();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n");

                    do {
                        printBorder();
                        printCentered("1.Table reservation");
                        printCentered("2.Order menu");

                        printBorder();
                        System.out.print("Enter your choice (1,2): ");

                        while (!scanner.hasNextInt()) {
                            printBorder();
                            printCentered("1.Table reservation");
                            printCentered("2.Order menu");

                            printBorder();
                            System.out.print("Enter your choice (1,2): ");
                            scanner.next(); // to avoid infinite loop
                        }
                        action = scanner.nextInt();
                        scanner.nextLine(); // clear the newline character

                        if (action != 1 && action != 2) {
                            printBorder();
                            printCentered("1.Table reservation");
                            printCentered("2.Order menu");

                            printBorder();
                            System.out.print("Enter your choice (1,2): ");
                        }

                    } while (action != 1 && action != 2);
                    switch (action) {
                        case 1:
                            new ReservationSystem(username).start();
                            break;
                        case 2:
                            runShoppingCartSystem(username);
                            break;


                    }

                } else {
                    System.out.println("Login failed. Invalid username or password.");
                }
                break;

            case 2:

                System.out.println("Welcome to the Registration System");
                System.out.print("Enter User ID: ");
                String userID = scanner.nextLine();

                System.out.print("Enter Username: ");
                String userName = scanner.nextLine();

                System.out.print("Enter Password: ");
                String Password = scanner.nextLine();

                String email = "";
                boolean validEmail = false;
                while (!validEmail) {
                    System.out.print("Enter Email: ");
                    email = scanner.nextLine();
                    try {
                        validateEmail(email);
                        validEmail = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                User user = new User.UserBuilder()
                        .userID(userID)
                        .username(userName)
                        .password(Password)
                        .email(email)
                        .build();

                RegistrationSystem system = new RegistrationSystem();
                system.registerUser(user);

                System.out.println("Registration Complete!");
                scanner.close();
        }
    }

    public static void runShoppingCartSystem(String username) {
        List<Product> availableProducts = new ArrayList<>();
        Cart cart = new Cart();
        Scanner scanner = new Scanner(System.in);

        // Sample products
        availableProducts.add(new Product("Apple", 0.99));
        availableProducts.add(new Product("Bread", 2.49));
        availableProducts.add(new Product("Milk", 3.99));
        availableProducts.add(new Product("Burger", 7.99));

        int choice = -1;
        while (choice != 0) {
            System.out.println("\nAvailable Products:");
            for (int i = 0; i < availableProducts.size(); i++) {
                System.out.println((i + 1) + ". " + availableProducts.get(i));
            }
            System.out.println("0. Exit");

            System.out.print("Choose a product to add to cart (1-" + availableProducts.size() + "): ");
            choice = scanner.nextInt();

            if (choice > 0 && choice <= availableProducts.size()) {
                cart.addProduct(availableProducts.get(choice - 1));
                System.out.println("Product added to cart.");
            } else if (choice != 0) {
                System.out.println("Invalid choice. Please try again.");
            }

            cart.displayCart();
        }

        cart.saveCartSummaryToFile("cart_summary.txt", username);
        System.out.println("Cart summary saved to 'cart_summary.txt'.");
    }

    private static void printCentered(String message) {
        if (message.length() > WIDTH) {
            System.out.println(message); // Print as-is if too long
        } else {
            int spaces = (WIDTH - message.length()) / 2;
            String formatted = "|%" + spaces + "s%s%" + spaces + "s|\n";
            System.out.printf(formatted, "", message, "");
        }
    }

    private static void printBorder() {
        System.out.println("+" + "=".repeat(WIDTH - 1) + "+");
    }

    private static void validateEmail(String email) {
        // Basic validation: checks if email contains '@' and '.'
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        // Additional checks can be added here (like regex pattern matching)
    }
}
