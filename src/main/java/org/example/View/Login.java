package org.example.View;


import java.sql.SQLException;

import java.util.Scanner;
import org.example.App;
import org.example.Models.User;

public class Login {
    public static void login(User user) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean loginSuccess = false;

            while (!loginSuccess) {
                String userPassword=null;
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                if(username!=null){
                    System.out.print("Enter password: ");
                    userPassword = scanner.nextLine();
                }
                    if (UserView.checkUser(username, userPassword)) {
                        System.out.println("Login successful!");
                        loginSuccess = true;
                        System.out.println("------Welcome to CarShop, " + username+"---------");
                        App.menu(scanner, username);

                    } else {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                }



    }
}

