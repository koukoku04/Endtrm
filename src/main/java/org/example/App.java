package org.example;


import java.sql.Connection;

import java.util.Scanner;

import org.example.Controller.UserController;
import org.example.Models.User;
import org.example.View.CarView;
import org.example.View.Registration;
import org.example.View.BaseView;
import org.example.View.Login;
import org.example.Methods.MainHelper;
import org.example.Methods.CarService;
public class App 
{
//    private static Object MainHelper;
//    private static CarService carService;
//    private static Object MainHelper;
//    private static MainHelper mainHelper;

    public static void main( String[] args ) throws Exception {

        CarView carRepository;
        try {
            carRepository = new CarView();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        carService = new CarService(carRepository);
//        Scanner scanner = null;
//        mainHelper = new MainHelper(scanner, carService);

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
    public static void menu(Scanner scanner, String username) {
        Boolean cond=true;
        while (cond) {
            System.out.println("------Menu------");
            System.out.println("1.Account");
            System.out.println("2.My Cart");
            System.out.println("3.Shop");
            System.out.println("4.Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    UserController.user(scanner,username);
                    cond=false;
                    break;
                case 2:
                    cond=false;

                    break;
                case 3:
                    start();
                    cond=false;

                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        }
    }
    public static void start() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    MainHelper.displayCarById();
                    break;
                case 2:
                    MainHelper.displayAllCars();
                    break;
                case 3:
                    CarService.CarService();
                    break;
                case 4:
                    MainHelper.updateExistingCar();
                    break;
                case 5:
                    MainHelper.deleteCarById();
                    break;
                case 6:
                    MainHelper.searchCarByModel();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        MainHelper.closeScanner();
    }
    private static void displayMenu() {
        System.out.println("+-------------------------------------------------------------------------------+");
        System.out.println("|    SHINA.com                                                     7.Exit       |");
        System.out.println("+-------------------------------------------------------------------------------|");
        System.out.println("|  2.all available cars      6.Search car by model      3.Sell your car         |");
        System.out.println("|                                                                               |");
        System.out.println("|                                                                               |");
        System.out.println("|  4.Update an existing car                             5.Delete a car by id    |");
        System.out.println("|                                                                               |");
        System.out.println("+-------------------------------------------------------------------------------+");
        System.out.println("Enter your choice:");
    }
}
