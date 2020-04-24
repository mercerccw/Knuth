package com.softhaven.servlet;

import com.softhaven.bean.User;
import com.softhaven.bean.Vessel;
import com.softhaven.dao.UserDAO;
import com.softhaven.dao.VesselDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/vessels")
public class VesselServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public VesselServlet() {
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
            if (!user.getPosition().equals("agent")) {
                response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
            } else {
                int currentPage = Integer.parseInt(request.getParameter("currentPage"));
                int vesselsPerPage = Integer.parseInt(request.getParameter("vesselsPerPage"));
                if(request.getParameter("currentPage") == null || request.getParameter("vesselsPerPage") == null){
                    currentPage = 1;
                    vesselsPerPage = 100;
                }else {
                    currentPage = Integer.parseInt(request.getParameter("currentPage"));
                    Integer.parseInt(request.getParameter("vesselsPerPage"));
                }
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("vesselsPerPage", vesselsPerPage);
                VesselDAO vesselDAO = new VesselDAO();
                List<Vessel> vessels = new ArrayList<>();
                assert false;
                vessels = vesselDAO.getAllVessels(currentPage, vesselsPerPage);
                int numberOfVessels = vesselDAO.getNumberOfRows();
                request.setAttribute("vessels", vessels);
                int numOfPages = numberOfVessels / vesselsPerPage;
                if (numberOfVessels % vesselsPerPage > 0){
                    numOfPages++;
                }
                request.setAttribute("numOfPages", numOfPages);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("numberOfVessels", numberOfVessels);
                request.getRequestDispatcher("vessels.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int vesselsPerPage = Integer.parseInt(request.getParameter("vesselsPerPage"));
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("vesselsPerPage", vesselsPerPage);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserDAO userDao = new UserDAO();
        User revised_user = null;
        try {
            revised_user = userDao.checkPosition(user.getEmail());
            assert revised_user != null;
            user.setPosition(revised_user.getPosition());
            session.setAttribute("user", user);
            if (!user.getPosition().equals("agent")) {
                response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
            } else {
                VesselDAO vesselDAO = new VesselDAO();
                List<Vessel> vessels = new ArrayList<>();
                assert false;
                vessels = vesselDAO.getAllVessels(currentPage, vesselsPerPage);
                int numberOfVessels = vesselDAO.getNumberOfRows();
                request.setAttribute("vessels", vessels);
                int numOfPages = numberOfVessels / vesselsPerPage;
                if (numberOfVessels % vesselsPerPage > 0){
                    numOfPages++;
                }
                request.setAttribute("numOfPages", numOfPages);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("numberOfVessels", numberOfVessels);
                request.getRequestDispatcher("vessels.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("vessels.jsp").forward(request, response);
    }
}

