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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/arrival")
public class ArrivalServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ArrivalServlet() {
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
        try {
            revised_user = userDao.checkPosition(user.getEmail());
            assert revised_user != null;
            user.setPosition(revised_user.getPosition());
            session.setAttribute("user", user);
            if (!user.getPosition().equals("master")) {
                response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
            } else {
                System.out.println("here: ");
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
            if (!user.getPosition().equals("master")) {
                response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
            } else {
                int imo;
                String status;
                vessel = vesselDAO.getVessel(Integer.parseInt(request.getParameter("imo_number")));
                if (vessel != null) {
                    imo = vessel.getImo();
                    status = vessel.getStatus();
                    if (status.equals("berth")) {
                        request.setAttribute("imo_message", "Ship: " + imo + " is already docked.");
                    } else {
                        String agent_email = request.getParameter("agent_email");
                        if(agent_email.charAt(0) == '['){
                            agent_email = agent_email.substring(1, agent_email.length());
                        }
                        if(agent_email.charAt(agent_email.length() - 1) == ']'){
                            agent_email = agent_email.substring(0, agent_email.length() - 1);
                        }
                        String arriving_from = request.getParameter("arriving_from");
                        String berth_number = request.getParameter("berth_number");
                        if(berth_number.charAt(0) == '['){
                            berth_number = berth_number.substring(1, berth_number.length());
                        }
                        if(berth_number.charAt(berth_number.length() - 1) == ']'){
                            berth_number = berth_number.substring(0, berth_number.length() - 1);
                        }
                        LocalDateTime eta = LocalDateTime.parse(request.getParameter("eta"));
                        LocalDateTime etd = LocalDateTime.parse(request.getParameter("etd"));
                        if (eta.isAfter(etd)) {
                            request.setAttribute("date_message", "ETA is after ETD");
                        } else {
                            String formatted_eta = eta.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss[.nnn]"));
                            String formatted_etd = etd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss[.nnn]"));
                            int port_number = berthDAO.getPortNumber(berth_number);
                            String discharging_cargo_description = request.getParameter("discharging_cargo_description");
                            int discharging_cargo_amount = Integer.parseInt(request.getParameter("discharging_cargo_amount"));
                            String loading_cargo_description = request.getParameter("loading_cargo_description");
                            int loading_cargo_amount = Integer.parseInt(request.getParameter("loading_cargo_amount"));
                            int number_of_passengers_arrival = Integer.parseInt(request.getParameter("number_of_passengers_arrival"));
                            int number_of_passengers_departure = Integer.parseInt(request.getParameter("number_of_passengers_departure"));
                            String submitted_by = request.getParameter("user_email");
                            arrivalForm.setImo_number(imo);
                            arrivalForm.setSubmitted_by(submitted_by);
                            arrivalForm.setAgent_email(agent_email);
                            arrivalForm.setArriving_from(arriving_from);
                            arrivalForm.setEta(formatted_eta);
                            arrivalForm.setBerth_number(berth_number);
                            arrivalForm.setNext_port_id(port_number);
                            arrivalForm.setEtd(formatted_etd);
                            arrivalForm.setDischarging_cargo_description(discharging_cargo_description);
                            arrivalForm.setDischarging_cargo_amount(discharging_cargo_amount);
                            arrivalForm.setLoading_cargo_description(loading_cargo_description);
                            arrivalForm.setLoading_cargo_amount(loading_cargo_amount);
                            arrivalForm.setNumber_of_passengers_arrival(number_of_passengers_arrival);
                            arrivalForm.setNumber_of_passengers_departure(number_of_passengers_departure);
                            String returned_message = arrivalDao.submitArrivalForm(arrivalForm);
                            if (returned_message != null) {
                                request.setAttribute("submission_message", returned_message);
                            } else {
                                request.setAttribute("submission_message", "An error occurred");
                            }

                        }
                    }
                } else {
                    request.setAttribute("imo_message", "No ship exists with that IMO:");
                }
                if (request.getAttribute("imo_message") != null || request.getAttribute("date_message") != null) {
                    request.setAttribute("berths", request.getParameter("berths"));
                    request.setAttribute("emails", request.getParameter("emails"));
                    request.getRequestDispatcher("master.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

