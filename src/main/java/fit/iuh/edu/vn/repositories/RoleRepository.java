package fit.iuh.edu.vn.repositories;

import fit.iuh.edu.vn.connection.DatabaseConnection;
import fit.iuh.edu.vn.entities.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    public List<Role> getRole(String role_id){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Role> listRole = new ArrayList<>();
        try {
            connection = DatabaseConnection.getConnection();
            String sql= "SELECT role_id, role_name, description, status FROM role WHERE role_id IN (SELECT role_id FROM grant_access WHERE role_id = ?)";

            ps = connection.prepareStatement(sql);
            ps.setString(1, role_id);
            rs = ps.executeQuery();
            while(rs.next()){
                String role_id1 = rs.getString("role_id");
                String role_name = rs.getString("role_name");
                String description = rs.getString("description");
                Short status = rs.getShort("status");

                Role role = new Role(role_id1, role_name, description,status);
                listRole.add(role);
            }
            return listRole;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Role> getRolesForAccount(String account_id){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Role> listRole = new ArrayList<>();
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT role.* " +
                    "FROM role " +
                    "INNER JOIN grant_access ON role.role_id = grant_access.role_id " +
                    "WHERE grant_access.account_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, account_id);
            rs = ps.executeQuery();
            while(rs.next()){
                String role_id1 = rs.getString("role_id");
                String role_name = rs.getString("role_name");
                String description = rs.getString("description");
                Short status = rs.getShort("status");

                Role role = new Role(role_id1, role_name, description,status);
                listRole.add(role);
            }
            return listRole;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserRole(String account_id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT role.role_name FROM role INNER JOIN grant_access ON role.role_id = grant_access.role_id WHERE grant_access.account_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, account_id);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("role_name");
            } else {
                return "unknown";
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
