package servlets;

import daos.UserDAO;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDao = new UserDAO();

        try {
            User user = userDao.checkLogin(email, password);
            String destPage = "login.jsp";

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                switch (user.getPosition()) {
                    case "agent":
                        destPage = "ship_agent.jsp";
                        break;
                    case "customs":
                        destPage = "customs.jsp";
                        break;
                    case "master":
                        destPage = "ship_master.jsp";
                        break;
                    default:
                        destPage = "home.jsp";
                }
            } else {
                String message = "Invalid email/password";
                request.setAttribute("message", message);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            assert user != null;
            System.out.println("here: " + user.toString());
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
