package org.example.View;

import org.example.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.View.BaseView.getConnection;

public class UserView {

    static boolean insertUserIntoDatabase(String login, String password, String name, String surname) {

        String sql = "INSERT INTO account (login, password, name, surname,client) VALUES (?, ?, ?, ?,true)";

        try (Connection con=BaseView.getConnection();
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
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM account";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return users;
    }
    public User readUser(int user_id) throws SQLException {
        String sql = "SELECT * FROM account WHERE login = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, user_id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getBoolean("gender"));
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE account SET name = ?, surname = ?, login = ?, password = ? WHERE login = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getSurname());
            pstmt.setString(3, user.getLogin());
            pstmt.setString(1, user.getPassword());
            pstmt.setBoolean(2, user.getGender());
            pstmt.setInt(4, user.getUser_id());

            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(String login) throws SQLException {
        String sql = "DELETE FROM account WHERE login = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, login);

            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean checkUser(String login, String password) throws SQLException{
        String sql = "SELECT * FROM account WHERE login = ? AND password = ?";
        try (Connection con=BaseView.getConnection()) {
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
        try (Connection con = BaseView.getConnection(); // Replace BaseView.getConnection() with your actual connection method
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Assuming client = false means the user is an admin
                return !resultSet.getBoolean("client");
            } else {
                // Username not found in the database
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error checking admin status", e);
        }
    }

}