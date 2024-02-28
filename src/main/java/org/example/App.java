package org.example;


import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.example.Methods.CartController;
import org.example.Methods.UserController;
import org.example.Models.Car;
import org.example.Models.User;
import org.example.View.*;
import org.example.Methods.CarController;

public class App
{


    public static void main( String[] args ) throws Exception {

        CarView carRepository;
        try {
            carRepository = new CarView();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        Boolean cond = true;
        while (cond) {
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Login.login(user);
                    cond = false;
                    break;
                case 2:
                    boolean isRegistered = Registration.registerUser();
                    if (!isRegistered) {
                        System.out.println("Registration failed. Returning to main menu...");
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }
    public static void menu(Scanner scanner, String username) throws SQLException {
        Boolean cond=true;
        while (cond) {
            System.out.println("------Menu------");
            System.out.println("1.Account");
            System.out.println("2.Shop");
            System.out.println("3.Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    UserController.user(scanner,username);
                    cond=false;
                    break;
                case 2:
                    start(username);
                    cond=false;
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        }
    }
    public static void start(String username) throws SQLException {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    exit=true;
                    menu(scanner,username);
                    break;
                case 3:
                    CarController.displayAllCars();
                    break;
                case 5:
                    CarController.CarService();
                    break;
                case 6:
                    List<Car> cartContents = CartController.getCartContents(username);
                    cartContents.forEach(System.out::println);
                    break;
                case 7:

                    boolean success = CartController.addToCart(username, displayAddCart());
                    if (success) {
                        System.out.println("Car added to cart successfully.");
                    } else {
                        System.out.println("Failed to add car to cart.");
                    }
                    break;
                case 8:
                    try {
                        if(UserView.checkAdmin(username)){
                            CarController.updateExistingCar();}
                        else{
                            System.out.println("You are not ADMIN");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 9:
                    try {
                        if(UserView.checkAdmin(username)){
                            CarController.deleteCarById();;}
                        else{
                            System.out.println("You are not ADMIN");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                case 4:
                    CarController.searchCarByModel();
                    break;
                case 2:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
//        scanner.close();
    }
    private static void displayMenu() {
        System.out.println("+-------------------------------------------------------------------------------+");
        System.out.println("|    SHINA.com                            1.Back to menu           2.Exit       |");
        System.out.println("+-------------------------------------------------------------------------------|");
        System.out.println("|  3.all available cars      4.Search car by model      5.Sell your car         |");
        System.out.println("|  6.My Cart                 7.Add to cart                                      |");
        System.out.println("|                           ADMIN PANEL                                         |");
        System.out.println("|                                                                               |");
        System.out.println("|  8.Update an existing car                             9.Delete a car by id    |");
        System.out.println("|                                                                               |");
        System.out.println("+-------------------------------------------------------------------------------+");
        System.out.println("Enter your choice:");
    }
    private static int displayAddCart(){
        System.out.println("Write a ID of car");
        Scanner scanner = new Scanner(System.in);
        int carId = scanner.nextInt();
        return carId;
    }
}