package org.example.Methods;

import org.example.Models.Car;
import org.example.View.BaseView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartController {
    public static List<Car> getCartContents(String login) throws SQLException {
        List<Car> cart = new ArrayList<>();
        String query = "SELECT c.model, c.brand, c.condition, c.year, c.price " +
                "FROM orders o " +
                "JOIN cars c ON o.car_id = c.id " +
                "WHERE o.login = ?";

        try (Connection conn = BaseView.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Car car = new Car.Builder()
                        .model(rs.getString("model"))
                        .brand(rs.getString("brand"))
                        .condition(rs.getString("condition"))
                        .year(rs.getInt("year"))
                        .price(rs.getInt("price"))
                        .build();
                cart.add(car);
            }

        }
        return cart;
    }
    public static boolean addToCart(String login, int carId) {
        String query = "INSERT INTO orders (login, car_id) VALUES (?, ?)";

        try (Connection conn = BaseView.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, login);
            pstmt.setInt(2, carId);
            int affectedRows = pstmt.executeUpdate();

            // Check if the insert was successful
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Adding to cart failed");
            e.printStackTrace();
            return false;
        }
    }
}
