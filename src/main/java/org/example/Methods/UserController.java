package org.example.Methods;

import org.example.App;
import org.example.View.BaseView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserController {
    public static void user(Scanner scanner,String username){

        try {

            try (Connection con= BaseView.getConnection()){

                String sql = "SELECT name, surname FROM account WHERE login = ?";
                try (PreparedStatement statement = con.prepareStatement(sql)) {
                    statement.setString(1, username);
                    try (ResultSet resultSet = statement.executeQuery()) {

                        if (!resultSet.isBeforeFirst()) {
                            System.out.println("No data found.");
                            return;
                        }

                        System.out.println("----------------------------------------");

                        while (resultSet.next()) {
                            String name = resultSet.getString("name");
                            String surname = resultSet.getString("surname");
                            System.out.println("Name: " + name);
                            System.out.println("Surname: " + surname);
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Boolean cond=true;
        while(cond) {
            System.out.println("1.Change a password");
            System.out.println("2.Go back");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    try
                    {
                        changePassword(username);

                    }
                    catch (SQLException e)
                    {

                        e.printStackTrace();
                    }

                    cond = false;
                    break;
                case 2:
                    App.menu(scanner, username);
                    cond = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    public static void changePassword(String username) throws SQLException{
        Scanner scanner = new Scanner(System.in);
        try(Connection con = BaseView.getConnection()) {
            System.out.println("---------------------");
            System.out.println("Write your old password");
            String oldpass=scanner.nextLine();
            String sqlCheck = "SELECT * FROM account WHERE login = ? AND password = ?";
            String sqlUpdate = "UPDATE account SET password = ? WHERE login = ? AND password = ?";
                try (PreparedStatement statement = con.prepareStatement(sqlCheck)) {
                    statement.setString(1, username);
                    statement.setString(2, oldpass);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        System.out.println("Write your new password");
                        String newpass = scanner.nextLine();
                        try (PreparedStatement stat = con.prepareStatement(sqlUpdate)) {
                            stat.setString(1, newpass);
                            stat.setString(2, username);
                            stat.setString(3, oldpass);
                            stat.executeUpdate();

                            System.out.println("New password is updated");
                            App.menu(scanner, username);
                        }

                    } else {
                        System.out.println("Incorrect old password");
                    }
                }
        }
    }

}
