package org.example.Methods;

import org.example.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserController {
    public static void user(Scanner scanner,String username) throws SQLException {

        try {

            try (Connection con= BaseController.getConnection()){

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
        try(Connection con = BaseController.getConnection()) {
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
    public static boolean insertUserIntoDatabase(String login, String password, String name, String surname) {

        String sql = "INSERT INTO account (login, password, name, surname,client) VALUES (?, ?, ?, ?,true)";

        try (Connection con= BaseController.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            ((PreparedStatement) statement).setString(1, login);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setString(4, surname);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkUser(String login, String password) throws SQLException{
        String sql = "SELECT * FROM account WHERE login = ? AND password = ?";
        try (Connection con= BaseController.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setString(1, login);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                return resultSet.next();
            }
            catch (Exception e) {
                throw new RuntimeException(e);

            }
        }
    }
    public static boolean checkAdmin(String username) throws SQLException {
        String sql = "SELECT client FROM account WHERE login = ?";
        try (Connection con = BaseController.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSetHandler handler = rs -> rs.next() && !rs.getBoolean("client");
            try (ResultSet resultSet = statement.executeQuery()) {
                return handler.handle(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException("Error processing ResultSet", e);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error checking admin status", e);
        }
    }
    public interface ResultSetHandler {
        boolean handle(ResultSet rs) throws SQLException;
    }


}
