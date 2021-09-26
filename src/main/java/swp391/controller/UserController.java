package swp391.controller;

import swp391.bean.Account;
import swp391.dao.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet implementation class UserController
 */
@WebServlet(name = "userAuthorization", urlPatterns = {"/userAuthorization"})
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
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

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch (action) {
            case "signIn":{
                signIn(request, response);
                break;
            }
            case "signUp":{
                break;
            }
            case "changePass":{
                break;
            }
            case "resetPass":{
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
                response.sendRedirect("UserController?action=login");
            } else {
                session.setAttribute("loginError", null);
                session.setAttribute("account", account);

                session.setMaxInactiveInterval(60 * 60 * 2);
                response.sendRedirect("UserController?action=authorization");
                return;
            }
        } catch (Exception e) {
            session.setAttribute("loginError", "Sai tên đăng nhập hoặc mật khẩu");
            response.sendRedirect("UserController?action=login");
        }
    }

    private  void logout(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("account", null);
        response.sendRedirect("userAuthorization?action=home");
    }

}
