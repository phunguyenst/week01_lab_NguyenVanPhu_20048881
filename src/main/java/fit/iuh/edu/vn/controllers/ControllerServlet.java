package fit.iuh.edu.vn.controllers;

import fit.iuh.edu.vn.entities.Account;
import fit.iuh.edu.vn.entities.Role;
import fit.iuh.edu.vn.repositories.AccountRepository;
import fit.iuh.edu.vn.repositories.LogRepository;
import fit.iuh.edu.vn.repositories.RoleRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/ControllerServlet","/control"})
public class ControllerServlet extends HttpServlet {
    AccountRepository accountRepository = new AccountRepository();
    LogRepository logRepository = new LogRepository();
    RoleRepository roleRepository = new RoleRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("Login".equals(action)) {

            handleLogin(req, resp);
        } else if ("addAccount".equals(action)) {

            handleAddAccount(req, resp);
        } else if ("updateAccount".equals(action)) {

            handleUpdateAccount(req, resp);
        } else if ("deleteAccount".equals(action)) {

            handleDeleteAccount(req, resp);
        } else if ("displayRolePermissions".equals(action)) {

            handleDisplayRolePermissions(req, resp);
        } else if ("displayAccountsByRole".equals(action)) {

            handleDisplayAccountsByRole(req, resp);
        }else if ("logout".equals(action)) {

            handleLogout(req, resp);
        }
    }

    private void handleLogout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogRepository logRepository = new LogRepository();
        HttpSession session = req.getSession();
        if (session.getAttribute("email") != null) {
            String email = (String) session.getAttribute("email");
            logRepository.addLogoutLog(email);
            List<String> logList = (List<String>) session.getAttribute("logList");
            if (logList == null) {
                logList = new ArrayList<>();
            }
            String logEntry = "Đăng xuất thành công vào lúc: " + new Date();
            logList.add(logEntry);
            session.setAttribute("logList", logList);

            session.invalidate();
            req.getRequestDispatcher("logout.jsp").forward(req, resp);
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String email = req.getParameter("txtText");
        String password = req.getParameter("txtPass");


        if (accountRepository.checkLogin(email, password)) {
            HttpSession sesson = req.getSession();
            List<String> logList = (List<String>) sesson.getAttribute("logList");
            if (logList == null) {
                logList = new ArrayList<>();
            }
            Account account = accountRepository.getAccountByEmail(email);
            String account_id = accountRepository.getAccountIdByEmail(email);
            logRepository.addLoginLog(account_id);
            String logEntry = "Đăng nhập thành công vào lúc: " + new Date();
            logList.add(logEntry);
            sesson.setAttribute("logList", logList);

            sesson.setAttribute("account", account);
            sesson.setAttribute("email", email);

            System.out.println("account_id: " + account_id);
            String userRole = roleRepository.getUserRole(account_id);


            System.out.println(account);
            System.out.println(userRole);
            if ("administrator".equals(userRole)) {
                req.setAttribute("account", account);
                req.getRequestDispatcher("dashboard_admin.jsp").forward(req, resp);
            } else if ("user".equals(userRole)) {
                req.setAttribute("account", account);
                req.getRequestDispatcher("dashboard_user.jsp").forward(req, resp);
            } else {
                System.out.println("lỗi 2");
                req.setAttribute("error", "invalid user or password");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } else {
            System.out.println("lỗi 1");
            req.setAttribute("error", "invalid user or password");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }


    private void handleAddAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       String account_id = req.getParameter("account_id");
       String full_name = req.getParameter("full_name");
       String password = req.getParameter("password");
       String email = req.getParameter("email");
       String phone = req.getParameter("phone");
       Short status = Short.valueOf(req.getParameter("status"));

       Account account = new Account(account_id, full_name, password, email, phone, status);

       accountRepository.addAccount(account);
       resp.sendRedirect("dashboard_admin.jsp");
    }


    private void handleUpdateAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account_id = req.getParameter("account_id");
        String full_name = req.getParameter("full_name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        Short status = Short.valueOf(req.getParameter("status"));

        Account account = new Account(account_id, full_name, password, email, phone, status);

        accountRepository.updateAccount(account);
        resp.sendRedirect("dashboard_admin.jsp");
    }


    private void handleDeleteAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account_id = req.getParameter("account_id");
        accountRepository.deleteAccount(account_id);
        resp.sendRedirect("dashboard_admin.jsp");

    }

    private void handleDisplayRolePermissions(HttpServletRequest req, HttpServletResponse resp) {
        String roleId = req.getParameter("roleId");
        List<Role> rolePermissions = roleRepository.getRole(roleId);
        req.setAttribute("rolePermissions", rolePermissions);
        try {
            req.getRequestDispatcher("role_permissions.jsp").forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleDisplayAccountsByRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleId = req.getParameter("roleId");

        List<Account> accountRole = accountRepository.getAccountsByRole(roleId);
        req.setAttribute("accountRole", accountRole);
        req.getRequestDispatcher("dashboard_user.jsp").forward(req, resp);
    }

}





