package fit.iuh.edu.vn.repositories;

import fit.iuh.edu.vn.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class LogRepository {
    public void addLoginLog(String accountId){
        Connection connection = null;
        PreparedStatement ps = null;


        try {
           connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO log (account_id, login_time, logout_time, notes) VALUES (?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, accountId);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps.setString(4, "User logged in");
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addLogoutLog(String email) {
        Connection connection = null;
        PreparedStatement ps = null;
        connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO `log` (`account_id`, `login_time`, `logout_time`, `notes`) VALUES (?, ?, ?, ?)";
        Date logoutTime = new Date();
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setTimestamp(2, new java.sql.Timestamp(logoutTime.getTime()));
            ps.setTimestamp(3, new java.sql.Timestamp(logoutTime.getTime()));
            ps.setString(4, "User logout");

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Log inserted successfully.");
            }
            ps.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

}
