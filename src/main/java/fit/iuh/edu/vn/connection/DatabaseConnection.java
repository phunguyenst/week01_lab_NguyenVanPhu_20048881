package fit.iuh.edu.vn.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class DatabaseConnection {

    public static Connection getConnection() {
        Connection connection = null;

        // Thông tin kết nối MariaDB
        String url = "jdbc:mariadb://localhost:3307/mydb"; // Thay đổi tên cơ sở dữ liệu của bạn
        String username = "root";
        String password = "sapassword";

        try {
            // Đăng ký MariaDB Connector/J
            Class.forName("org.mariadb.jdbc.Driver");

            // Tạo kết nối
            connection =  DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Kết nối đến CSDL thành công.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM account");

                while (resultSet.next()) {
                    // Xử lý dữ liệu từ kết quả truy vấn
                    String account_id = resultSet.getString("account_id");
                    String full_name = resultSet.getString("full_name");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    int status = resultSet.getInt("status");

                    System.out.println("Account ID: " + account_id);
                    System.out.println("Full Name: " + full_name);
                    System.out.println("Password: " + password);
                    System.out.println("Email: " + email);
                    System.out.println("Phone: " + phone);
                    System.out.println("Status: " + status);
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
