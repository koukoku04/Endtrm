package org.example.View;

import org.example.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Registration {
    public static void registration(Scanner scanner, User user) throws SQLException {

        registerUser();
        Login.login(user);

    }
    public static boolean registerUser() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Registration Form");

        System.out.print("Enter login: ");
        String login = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();



        if (validateInput(login, password, name, surname)) {
            ArrayList<Object> info = new ArrayList<Object>();
            info.add(name);
            info.add(surname);
            info.add(login);
            info.add(password);
            if (UserView.insertUserIntoDatabase(login,password,name,surname)) {
                System.out.println("User registered successfully.");
                return true;
            } else {
                System.out.println("An error occurred during registration.");
            }
        } else {
            System.out.println("Invalid input. Please try again.");
        }
        return registerUser();
    }

    private static boolean validateInput(String login, String password, String name, String surname) {

        return !login.isEmpty() && !password.isEmpty() && password.length() >= 6 && !name.isEmpty() && !surname.isEmpty();
    }


}
