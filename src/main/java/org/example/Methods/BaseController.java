package org.example.Methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseController {
    private static final String url = "jdbc:postgresql://localhost:5432/endterm";
    private static final String name = "postgres";
    private static final String password = "0000";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,name,password);
    }

}
