package org.example;


import java.sql.SQLException;
import java.util.Scanner;

import org.example.Methods.UserController;
import org.example.Models.User;
import org.example.View.CarView;
import org.example.View.Registration;
import org.example.View.Login;
import org.example.Methods.MainHelper;
import org.example.Methods.CarService;
import org.example.View.UserView;

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
    public static void start(String username) {
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
                    MainHelper.displayAllCars();
                    break;
                case 5:
                    CarService.CarService();
                    break;
                case 6:
                    try {
                        if(UserView.checkAdmin(username)){
                            MainHelper.updateExistingCar();}
                        else{
                            System.out.println("You are not ADMIN");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 7:
                    try {
                        if(UserView.checkAdmin(username)){
                            MainHelper.deleteCarById();;}
                        else{
                            System.out.println("You are not ADMIN");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                case 4:
                    MainHelper.searchCarByModel();
                    break;
                case 2:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        MainHelper.closeScanner();
    }
    private static void displayMenu() {
        System.out.println("+-------------------------------------------------------------------------------+");
        System.out.println("|    SHINA.com                            1.Back to menu           2.Exit       |");
        System.out.println("+-------------------------------------------------------------------------------|");
        System.out.println("|  3.all available cars      4.Search car by model      5.Sell your car         |");
        System.out.println("|                                                                               |");
        System.out.println("|                           ADMIN PANEL                                         |");
        System.out.println("|                                                                               |");
        System.out.println("|  6.Update an existing car                             7.Delete a car by id    |");
        System.out.println("|                                                                               |");
        System.out.println("+-------------------------------------------------------------------------------+");
        System.out.println("Enter your choice:");
    }
}
