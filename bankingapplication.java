import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class bankingapplication {
    private static Connection connection;
    public static void main(String[] args) {
        // Connect to the MySQL database
        String url = "jdbc:mysql://localhost:3306/my_database";
        String username = "root";
        String password = "123456789";
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public static boolean authenticateUser(String username, String password) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM users WHERE username =? AND password =?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println("Error authenticating user: " + e.getMessage());
            return false;
        }
    }
}