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
            if (!user.getPosition().equals("agent") && !user.getPosition().equals("customs")) {
                response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
            } else {
                int currentPage;
                int vesselsPerPage;
                if (request.getParameter("currentPage") == null || request.getParameter("vesselsPerPage") == null) {
                    currentPage = 1;
                    vesselsPerPage = 100;
                } else {
                    currentPage = Integer.parseInt(request.getParameter("currentPage"));
                    vesselsPerPage = Integer.parseInt(request.getParameter("vesselsPerPage"));
                    if (vesselsPerPage > 500) {
                        vesselsPerPage = 500;
                    }
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
                if (numberOfVessels % vesselsPerPage > 0) {
                    numOfPages++;
                }
                request.setAttribute("numOfPages", numOfPages);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("numberOfVessels", numberOfVessels);
                if (user.getPosition().equals("agent")) {
                    request.getRequestDispatcher("vessels.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("vessels-customs.jsp").forward(request, response);
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
        int imo = Integer.parseInt(request.getParameter("imo"));
        VesselDAO vesselDAO = new VesselDAO();
        try {
            revised_user = userDao.checkPosition(user.getEmail());
            assert revised_user != null;
            user.setPosition(revised_user.getPosition());
            session.setAttribute("user", user);
            if (!user.getPosition().equals("agent") && !user.getPosition().equals("customs")) {
                response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
            }else{
                int newImo = -1;
                Vessel vessel = vesselDAO.getVessel(imo);
                if (vessel != null) {
                    newImo = vessel.getImo();
                }
                if (imo != newImo) {
                    request.setAttribute("message", "No vessel with IMO: " + imo);
                } else {
                    request.setAttribute("message", "Found vessel: " + imo);
                    request.setAttribute("vessel", vessel);
                }
                if (user.getPosition().equals("customs")){
                    request.getRequestDispatcher("vessels-customs.jsp").forward(request, response);
                }else {
                    request.getRequestDispatcher("vessels.jsp").forward(request, response);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

