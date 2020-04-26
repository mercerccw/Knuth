package com.softhaven.servlet;

import com.softhaven.bean.Arrival;
import com.softhaven.bean.User;
import com.softhaven.dao.ArrivalDao;
import com.softhaven.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/customs")
public class CustomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CustomServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (session.getAttribute("user") == null){
            response.sendRedirect(request.getContextPath() + "/login");
        }
        UserDAO userDao = new UserDAO();
        User revised_user = null;
        ArrivalDao arrivalDao = new ArrivalDao();
        try {
            revised_user = userDao.checkPosition(user.getEmail());
            assert revised_user != null;
            user.setPosition(revised_user.getPosition());
            session.setAttribute("user", user);
            if (!user.getPosition().equals("customs")) {
                response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
            } else {
                List<Arrival> arrivalList = arrivalDao.fetchAllUnApprovedForms();
                if (arrivalList.size() > 0) {
                    request.setAttribute("forms", arrivalList);
                } else {
                    request.setAttribute("forms", null);
                }
                request.getRequestDispatcher("customs.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}