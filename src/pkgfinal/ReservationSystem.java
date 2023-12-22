package pkgfinal;

import java.io.*;
import java.util.*;

public class ReservationSystem {

    private Scanner scanner = new Scanner(System.in);
    private ReservationReceiver receiver = new ReservationReceiver();
    private ReservationInvoker invoker = new ReservationInvoker();
    private Map<Integer, Boolean> tableAvailability;
    private TableGroup tableGroup;
    private String loggedInUser;
    private static final String RESERVATIONS_FILE = "reservations.txt";
    private static final int WIDTH = 45;

    public ReservationSystem(String loggedInUser) {
        this.loggedInUser = loggedInUser;
        initializeTables();
        loadReservationsFromFile();
    }

    private void displayUserReservations() {
        System.out.println("Your Reservations:");
        try (BufferedReader reader = new BufferedReader(new FileReader(RESERVATIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Reserved By: " + loggedInUser)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    private void cancelReservation() {
        System.out.println("Enter the table number to cancel the reservation:");
        int tableNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<String> fileContent = new ArrayList<>();
        boolean reservationFound = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(RESERVATIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Table Number: " + tableNumber) && line.contains("Reserved By: " + loggedInUser)) {
                    reservationFound = true;
                    tableAvailability.put(tableNumber, true); // Mark the table as available
                    continue; // Skip adding this line to the updated file content
                }
                fileContent.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            return;
        }

        if (!reservationFound) {
            System.out.println("No reservation found for table " + tableNumber + " under your name.");
            return;
        }
        // Rewrite the file without the canceled reservation
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESERVATIONS_FILE))) {
            for (String line : fileContent) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        System.out.println("Reservation for table " + tableNumber + " has been canceled.");
    }

    private void initializeTables() {
        tableAvailability = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            tableAvailability.put(i, true); // Initially, all tables are available
        }
    }

    private void loadReservationsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RESERVATIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Table Number: ")) {
                    int tableNumber = extractTableNumber(line);
                    tableAvailability.put(tableNumber, false); // Mark the table as reserved
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    private int extractTableNumber(String line) {
        String[] parts = line.split(", ");
        String tablePart = parts[0]; // "Table Number: [tableNumber]"
        return Integer.parseInt(tablePart.split(": ")[1]);
    }

    public void start() {
        int choice;
        do {
            printBorder();
            printCentered("");
            printCentered("1. Make a reservation");
            printCentered("2. View your reservations");
            printCentered("3. Cancel a reservation");
            printCentered("");
            printBorder();
            System.out.print("Enter your choice (1,2 or 3): ");

            while (!scanner.hasNextInt()) {
                printBorder();
                printCentered("");
                printCentered("1. Make a reservation");
                printCentered("2. View your reservations");
                printCentered("3. Cancel a reservation");
                printCentered("");
                printBorder();
                System.out.print("Enter your choice (1,2 or 3): ");
                scanner.next(); // to avoid infinite loop
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // clear the newline character

            if (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("Invalid choice. Please enter 1,2 or 3.");
            }
        } while (choice != 1 && choice != 2 && choice != 3);

        switch (choice) {
            case 1:
                System.out.println("\n\n\n\n\n\n\n\n\n\n");
                System.out.println("Welcome to the Reservation System");
                displayAvailableTables();

                System.out.println("Please enter the table number you wish to reserve:");
                int tableNumber = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                if (isTableAvailable(tableNumber)) {
                    makeReservation(tableNumber);
                    saveReservationToFile(tableNumber, loggedInUser);
                } else {
                    System.out.println("Sorry, table " + tableNumber + " is already reserved. Please choose a different table.");
                }
                break;
            case 2: // View reservations
                System.out.println("\n\n\n\n\n\n\n\n\n\n");
                displayUserReservations();
                break;
            case 3: // Cancel a reservation
                System.out.println("\n\n\n\n\n\n\n\n\n\n");
                cancelReservation();
                break;
        }
    }

    private void displayAvailableTables() {
        System.out.println("Available Tables:");
        tableAvailability.forEach((tableNumber, available) -> {
            if (available) {
                System.out.println("Table " + tableNumber);
            }
        });
    }

    private boolean isTableAvailable(int tableNumber) {
        return tableAvailability.getOrDefault(tableNumber, false);
    }

    private void makeReservation(int tableNumber) {
        tableAvailability.put(tableNumber, false); // Mark the table as reserved
        System.out.println("Table " + tableNumber + " has been reserved by " + loggedInUser);
    }

    private void saveReservationToFile(int tableNumber, String name) {
        String content = "Table Number: " + tableNumber + ", Reserved By: " + name + "\n";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservations.txt", true))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
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

}
