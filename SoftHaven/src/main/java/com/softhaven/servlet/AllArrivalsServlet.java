package com.softhaven.servlet;

import com.softhaven.bean.Arrival;
import com.softhaven.bean.Berth;
import com.softhaven.bean.User;
import com.softhaven.bean.Vessel;
import com.softhaven.dao.ArrivalDao;
import com.softhaven.dao.BerthDAO;
import com.softhaven.dao.UserDAO;
import com.softhaven.dao.VesselDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/allArrivals")
public class AllArrivalsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AllArrivalsServlet() {
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
            if (!user.getPosition().equals("customs") && !user.getPosition().equals("agent")) {
                response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
            } else {
                if(user.getPosition().equals("customs")){
                    request.getRequestDispatcher("all-forms-customs.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("all-forms-agent.jsp").forward(request, response);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserDAO userDao = new UserDAO();
        User revised_user = null;
        Vessel vessel = new Vessel();
        Berth berth = new Berth();
        BerthDAO berthDAO = new BerthDAO();
        VesselDAO vesselDAO = new VesselDAO();
        ArrivalDao arrivalDao = new ArrivalDao();
        Arrival arrivalForm = new Arrival();
        try {
            revised_user = userDao.checkPosition(user.getEmail());
            assert revised_user != null;
            user.setPosition(revised_user.getPosition());
            session.setAttribute("user", user);
            if (!user.getPosition().equals("customs")) {
                response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
            } else {
                System.out.println("hello");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

