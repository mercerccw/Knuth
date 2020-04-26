package com.softhaven.servlet;

import com.softhaven.bean.User;
import com.softhaven.dao.BerthDAO;
import com.softhaven.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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
        if (session.getAttribute("user") == null){
            response.sendRedirect(request.getContextPath() + "/login");
        }
        UserDAO userDao = new UserDAO();
        BerthDAO berthDAO = new BerthDAO();
        User revised_user = null;
        try {
            revised_user = userDao.checkPosition(user.getEmail());
            assert revised_user != null;
            user.setPosition(revised_user.getPosition());
            session.setAttribute("user", user);
            if (!user.getPosition().equals("master")) {
                response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
            } else {
                List<String> emails = userDao.getAllEmails();
                List<String> berths = berthDAO.getAllBerthNumbers();
                if (emails.size() > 0) {
                    request.setAttribute("emails", emails);
                } else {
                    request.setAttribute("agent_message", "There are no agents.");
                }
                if (berths.size() > 0) {
                    request.setAttribute("berths", berths);
                } else {
                    request.setAttribute("berth_message", "There are no available berths.");
                }
                request.getRequestDispatcher("master.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}