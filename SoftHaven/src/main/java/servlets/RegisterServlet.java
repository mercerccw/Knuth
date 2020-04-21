package servlets;

import com.google.common.hash.Hashing;
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
            String destPage = "register.jsp";
            if (password.equals(confirm_password)) {

                User user = userDao.register(first_name, last_name, email, hashed_password, position);
                if (user == null) {
                    throw new Exception("401: There is already a user for that email!");
                }
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                switch (user.getPosition()) {
                    case "agent":
                        destPage = "agent.jsp";
                        break;
                    case "customs":
                        destPage = "customs.jsp";
                        break;
                    case "master":
                        destPage = "master.jsp";
                        break;
                    default:
                        destPage = "register.jsp";
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
                dispatcher.forward(request, response);
            } else {
                String message = "Passwords don't match";
                request.setAttribute("message", message);
                RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
                response.sendRedirect(request.getContextPath() + "/" + destPage);
//                dispatcher.forward(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
