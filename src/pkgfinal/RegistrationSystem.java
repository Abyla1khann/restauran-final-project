/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrationSystem {
    private static final String DATABASE_PATH = "C:\\Users\\user\\Desktop\\Final\\Users.txt";

    public void registerUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_PATH, true))) {
            writer.write(user.getUserID() + ", " + user.getUsername() + ", " + user.getPassword() + ", " + user.getEmail() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

