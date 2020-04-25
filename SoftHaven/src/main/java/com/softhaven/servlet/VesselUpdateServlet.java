package com.softhaven.servlet;

import com.softhaven.dao.VesselDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vessels/updateVessel")
public class VesselUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public VesselUpdateServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int imo = Integer.parseInt(request.getParameter("imo"));
        String status = request.getParameter("status");

        VesselDAO vesselDAO = new VesselDAO();
        try {
            vesselDAO.updateVesselStatus(imo, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/vessels");
    }
}

