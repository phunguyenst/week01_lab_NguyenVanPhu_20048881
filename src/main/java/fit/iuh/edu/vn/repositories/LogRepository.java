package fit.iuh.edu.vn.repositories;

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
            String sql = "INSERT INTO log (account_id, login_time,  notes) VALUES (?, ?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, accountId);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setString(3, "User logged in");
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

        String sql = "INSERT INTO `log` (`account_id`, `logout_time`, `notes`) VALUES ( ?, ?, ?)";
        Date logoutTime = new Date();
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setTimestamp(2, new java.sql.Timestamp(logoutTime.getTime()));
            ps.setString(3, "User logout");
            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
