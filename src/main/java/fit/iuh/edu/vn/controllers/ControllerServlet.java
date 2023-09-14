package fit.iuh.edu.vn.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/ControllerServlet","/control"})
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String url = req.getParameter("txtText");
        out.println(url);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("Login".equals(action)) {
            String tam = req.getParameter("txtText");
            System.out.println(action);
            // Xử lý đăng nhập
            handleLogin(req, resp);
        } else if ("addAccount".equals(action)) {
            // Xử lý thêm tài khoản
            handleAddAccount(req, resp);
        } else if ("updateAccount".equals(action)) {
            // Xử lý cập nhật tài khoản
            handleUpdateAccount(req, resp);
        } else if ("deleteAccount".equals(action)) {
            // Xử lý xóa tài khoản
            handleDeleteAccount(req, resp);
        } else if ("displayAccountInfo".equals(action)) {
            // Xử lý hiển thị thông tin tài khoản
            handleDisplayAccountInfo(req, resp);
        } else if ("displayRolePermissions".equals(action)) {
            // Xử lý hiển thị quyền của role
            handleDisplayRolePermissions(req, resp);
        } else if ("displayAccountsByRole".equals(action)) {
            // Xử lý hiển thị account của role
            handleDisplayAccountsByRole(req, resp);
        } else if ("grantPermissions".equals(action)) {
            // Xử lý cấp quyền cho account
            handleGrantPermissions(req, resp);
        } else if ("logLogin".equals(action)) {
            // Xử lý ghi log đăng nhập
            handleLogLogin(req, resp);
        } else if ("logLogout".equals(action)) {
            // Xử lý ghi log đăng xuất
            handleLogLogout(req, resp);
        } else if ("logout".equals(action)) {
            // Xử lý đăng xuất
            handleLogout(req, resp);
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String userName = req.getParameter("txtText");
        String passWord = req.getParameter("txtPass");
        System.out.println(userName);
        System.out.println(passWord);
        if("admin".equals(userName) && "123".equals(passWord)){
            HttpSession session = req.getSession();
            session.setAttribute("username", userName);
            resp.sendRedirect("dashboard.jsp");
            System.out.println(userName);
            System.out.println(passWord);
        }
        else{
            req.setAttribute("error","Invalid username or password");
            req.getRequestDispatcher("jogin.jsp").forward(req, resp);
        }
    }
    private void handleAddAccount(HttpServletRequest req, HttpServletResponse resp) {
    }
    private void handleUpdateAccount(HttpServletRequest req, HttpServletResponse resp) {
    }
    private void handleDeleteAccount(HttpServletRequest req, HttpServletResponse resp) {
    }
    private void handleDisplayAccountInfo(HttpServletRequest req, HttpServletResponse resp) {
    }
    private void handleDisplayRolePermissions(HttpServletRequest req, HttpServletResponse resp) {
    }
    private void handleDisplayAccountsByRole(HttpServletRequest req, HttpServletResponse resp) {
    }
    private void handleGrantPermissions(HttpServletRequest req, HttpServletResponse resp) {
    }
    private void handleLogLogin(HttpServletRequest req, HttpServletResponse resp) {
    }
    private void handleLogLogout(HttpServletRequest req, HttpServletResponse resp) {
    }
    private void handleLogout(HttpServletRequest req, HttpServletResponse resp) {
    }


}
