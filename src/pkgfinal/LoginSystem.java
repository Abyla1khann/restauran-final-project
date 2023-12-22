/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

import java.io.*;
import java.util.*;

public class LoginSystem {
    private static final String USER_DATA_FILE = "Users.txt";

    public boolean login(String username, String password) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] userData = line.split(",");
            if (userData.length >= 4) {
                if (userData[1].trim().equals(username) && userData[2].trim().equals(password)) {
                    reader.close();
                    return true;
                }
            }
        }
        reader.close();
        return false;
    }
}

