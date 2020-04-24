package com.softhaven.servlet;

import com.softhaven.dao.UserDAO;
import com.softhaven.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/master")
public class MasterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MasterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserDAO userDao = new UserDAO();
        User revised_user = null;
        try {
            revised_user = userDao.checkPosition(user.getEmail());
            assert revised_user != null;
            user.setPosition(revised_user.getPosition());
            session.setAttribute("user", user);
            if (!user.getPosition().equals("master")) {
                response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
            } else {
                request.getRequestDispatcher("master.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}