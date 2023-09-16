package fit.iuh.edu.vn.repositories;

import fit.iuh.edu.vn.connection.DatabaseConnection;
import fit.iuh.edu.vn.entities.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    public void addAccount(Account account) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO account (account_id, full_name, password, email, phone, status) VALUES (?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, account.getAccount_id());
            ps.setString(2, account.getFull_name());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getEmail());
            ps.setString(5, account.getPhone());
            ps.setShort(6, account.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAccount(Account account) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "UPDATE account SET full_name=?, password=?, email=?, phone=?, status=? WHERE account_id=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, account.getFull_name());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getEmail());
            ps.setString(4, account.getPhone());
            ps.setShort(5, account.getStatus());
            ps.setString(6, account.getAccount_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAccount(String accountId) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "DELETE FROM account WHERE account_id=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, accountId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Account> getAccountListFromDatabase() {
        List<Account> accountList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM account";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String account_id = resultSet.getString("account_id");
                String full_name = resultSet.getString("full_name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                short status = Short.parseShort(String.valueOf(resultSet.getShort("status")));
                Account account = new Account(account_id, full_name, password, email, phone, status);
                accountList.add(account);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountList;
    }

    public Boolean checkLogin(String email, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM account WHERE email = ? AND password = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }






    public Account getAccountByEmail(String email){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM account WHERE email = ?";

            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
                String account_id = rs.getString("account_id");
                String full_name = rs.getString("full_name");
                String password = rs.getString("password");
                String email1 = rs.getString("email");
                String phone = rs.getString("phone");
                short status = Short.parseShort(String.valueOf(rs.getShort("status")));
                Account account = new Account(account_id,full_name,password,email1,phone,status);
                return account;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public String getAccountIdByEmail(String email) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT account_id FROM account WHERE email = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("account_id");
            } else {

                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
    public List<Account> getAccountsByRole(String roleId){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Account> listAccount = new ArrayList<>();

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT account_id, full_name FROM account WHERE account_id IN (SELECT account_id FROM grant_access WHERE role_id = ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, roleId);
            rs = ps.executeQuery();
            while (rs.next()){
                Account account = new Account();
                account.setAccount_id(rs.getString("account_id"));
                account.setFull_name(rs.getString("full_name"));
                listAccount.add(account);
            }
            return listAccount;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}