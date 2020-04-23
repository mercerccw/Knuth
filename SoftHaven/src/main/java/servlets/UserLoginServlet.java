package servlets;

import com.google.common.hash.Hashing;
import daos.UserDAO;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserLoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hashed_password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();

        UserDAO userDao = new UserDAO();

        try {
            User user = userDao.checkLogin(email, hashed_password);
            String destPage = "login.jsp";

            if (user != null) {
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
                        destPage = "/home";
                }
            } else {
                String message = "Invalid email/password";
                request.setAttribute("message", message);
                request.getRequestDispatcher(destPage).forward(request, response);
            }
            response.sendRedirect(request.getContextPath() + destPage);
            assert user != null;
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
}