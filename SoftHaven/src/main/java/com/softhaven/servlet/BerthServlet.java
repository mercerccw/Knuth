package com.softhaven.servlet;

import com.softhaven.dao.BerthBookingDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/berths/addVessels")
public class BerthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BerthServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Here!!!" + request.getParameter("berthNumber"));
        System.out.println("Here!!!" + request.getParameter("shipImo"));
        BerthBookingDAO berthBookingDAO = new BerthBookingDAO();
        try {
            String message;
            int ship_imo = berthBookingDAO.verifyVessel(request.getParameter("shipImo"));
            if (request.getParameter("shipImo").equals("0")) {
                System.out.println("Here: " + request.getParameter("shipImo"));
                System.out.println(berthBookingDAO.bookBerth(request.getParameter("berthNumber"), Integer.parseInt(request.getParameter("shipImo"))));
                response.sendRedirect(request.getContextPath() + "/agent");
            } else if (ship_imo != 0) {
                System.out.println("Here: " + ship_imo);
                System.out.println(berthBookingDAO.bookBerth(request.getParameter("berthNumber"), Integer.parseInt(request.getParameter("shipImo"))));
                response.sendRedirect(request.getContextPath() + "/agent");
            } else {
                message = "There are no ships with IMO: " + request.getParameter("shipImo");
                PrintWriter writer = response.getWriter();
                writer.println("<html><body>");
                writer.println("<p>" + message + "</p>");
                writer.println("<a href=" + request.getContextPath() + "/agent>Back to Berth Booking</a>");
                writer.println("</body></html>");
                writer.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}