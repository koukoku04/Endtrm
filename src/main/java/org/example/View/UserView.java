package org.example.View;

import org.example.App;
import org.example.Methods.UserController;
import org.example.Models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.regex.*;

public class UserView {
    public static void login() throws SQLException {
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
            if (UserController.checkUser(username, userPassword)) {
                System.out.println("Login successful!");
                loginSuccess = true;
                System.out.println("------Welcome to CarShop, " + username+"---------");
                App.menu(scanner, username);
            } else
            {
                System.out.println("Invalid username or password. Please try again.");
            }
        }
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
            if (UserController.insertUserIntoDatabase(login,password,name,surname)) {
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

        return !login.isEmpty() && isValidPassword(password) && !name.isEmpty() && !surname.isEmpty();
    }
    public static boolean
    isValidPassword(String password)
    {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }
}