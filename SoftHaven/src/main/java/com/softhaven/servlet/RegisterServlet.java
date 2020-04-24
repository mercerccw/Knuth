package com.softhaven.servlet;

import com.google.common.hash.Hashing;
import com.softhaven.dao.UserDAO;
import com.softhaven.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");
        String position = request.getParameter("position");
        String hashed_password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        UserDAO userDao = new UserDAO();

        try {
            String destPage = "/register";
            if (password.equals(confirm_password)) {
                User user = userDao.register(first_name, last_name, email, hashed_password, position);
                if (user == null) {
                    String message = "There is already a user associated with that account";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    switch (user.getPosition()) {
                        case "agent":
                            destPage = "/agent";
                            break;
                        case "customs":
                            destPage = "/customs";
                            break;
                        case "master":
                            destPage = "/master";
                            break;
                        default:
                            destPage = "/register";
                    }
                    response.sendRedirect(request.getContextPath() + destPage);
                }
            } else {
                String message = "Passwords don't match";
                request.setAttribute("message", message);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserDAO userDao = new UserDAO();
        User revised_user = null;
        if (user == null){
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }else {
            try {
                revised_user = userDao.checkPosition(user.getEmail());
                assert revised_user != null;
                user.setPosition(revised_user.getPosition());
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
