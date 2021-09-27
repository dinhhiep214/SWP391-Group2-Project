package swp391.controller;

import swp391.bean.Account;
import swp391.dao.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Servlet implementation class UserController
 */
@WebServlet(name = "userAuthorization", urlPatterns = {"/userAuthorization"})
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "home";
        }

        switch (action) {
            case "authorization": {
                authorization(request, response);
                break;
            }
            case "login":{
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;
            }
            case "logout":{
                logout(request, response);
                break;
            }
            case "signup":{
                request.getRequestDispatcher("signup.jsp").forward(request, response);
                break;
            }
            case "home":{
                request.getRequestDispatcher("home.jsp").forward(request, response);
                break;
            }
            case "adminDashboard":{
                break;
            }
            case "shopDashboard":{
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch (action) {
            case "signIn":{
                signIn(request, response);
                break;
            }
            case "signUp":{
                try {
                    signUp(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "changePassword":{
                try {
                    changePassword(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "resetPassword":{
                break;
            }
            case "verify":{
                break;
            }
        }
    }

    private void authorization(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("userAuthorization?action=login");
        } else if (account.getRole().equals("Admin")) {
            response.sendRedirect("userAuthorization?action=adminDashboard");
        } else if (account.getRole().equals("ShopOwner")) {
            response.sendRedirect("userAuthorization?action=shopDashboard");
        } else {
            response.sendRedirect("userAuthorization?action=home");
        }
    }

    private void signIn(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String rememberPassword = request.getParameter("rememberPassword");

            Cookie emailCookie = new Cookie("emailCookie", email);
            Cookie passwordCookie = new Cookie("passwordCookie", password);
            Cookie rememberPasswordCookie = new Cookie("rememberPasswordCookie", "true");

            emailCookie.setMaxAge(60 * 60 * 24 * 30);

            if (rememberPassword != null) {
                passwordCookie.setMaxAge(60 * 60 * 24 * 30);
                rememberPasswordCookie.setMaxAge(60 * 60 * 24 * 30);
            } else {
                passwordCookie.setMaxAge(0);
                rememberPasswordCookie.setMaxAge(0);
            }

            response.addCookie(emailCookie);
            response.addCookie(passwordCookie);
            response.addCookie(rememberPasswordCookie);

            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.findByEmail(email);

            if (account == null || !account.getPassword().equals(password)) {
                session.setAttribute("loginError", "Sai tên đăng nhập hoặc mật khẩu");
                response.sendRedirect("userAuthorization?action=login");
            } else {
                session.setAttribute("loginError", null);
                session.setAttribute("account", account);

                session.setMaxInactiveInterval(60 * 60 * 2);
                response.sendRedirect("userAuthorization?action=authorization");
                return;
            }
        } catch (Exception e) {
            session.setAttribute("loginError", "Sai tên đăng nhập hoặc mật khẩu");
            response.sendRedirect("userAuthorization?action=login");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("account", null);
        response.sendRedirect("userAuthorization?action=home");
    }

    private void signUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
//        Date birthDay = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birhtDay"));
        boolean gender;
        String sex = request.getParameter("gender");
        if (sex.equals("Nam")) {
            gender = false;
        } else gender = true;
        Date createdDate = Date.valueOf(dtf.format(localDate));

        AccountDAO accountDAO = new AccountDAO();
        if (!password.equals(rePassword)) {
            request.setAttribute("rePassAlert", "Mật khẩu và Mật khẩu nhập lại không trùng nhau!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        } else {
            Account accountCheck = accountDAO.findByEmail(email);
            if (accountCheck == null) {
                accountDAO.save(new Account(email, password, firstName, lastName,
                        "Reviewer", gender, "active", createdDate));
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            if (accountCheck != null) {
                request.setAttribute("emailUsed", "Email đã được sử dụng!");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
        }
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("UTF-8");
        AccountDAO accountDAO = new AccountDAO();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String reNewPassword = request.getParameter("reNewPassword");

        Account account = accountDAO.findByEmail(email);
        if (account != null && account.getPassword().equals(password)
            && newPassword.equals(reNewPassword)) {
            accountDAO.changePassword(account.getAccountId(), newPassword);
            response.sendRedirect("change-password.jsp");
        } else {
            if (account == null || account.getPassword().equals(password)) {
                request.setAttribute("messageAlert", "Email hoặc mật khẩu bị sai!");
            }
            if (!newPassword.equals(reNewPassword)) {
                request.setAttribute("newPasswordAlert", "Mật khẩu và Mật khẩu nhập lại không trùng nhau!");
            }
            if (newPassword.equals(account.getPassword())) {
                request.setAttribute("passwordAlert", "Mật khẩu mới và cũ giống nhau!");
            }
        }
    }

    private void resetPassword(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        AccountDAO accountDAO = new AccountDAO();
        String email = request.getParameter("email");
        Account account = accountDAO.findByEmail(email);
    }
}
