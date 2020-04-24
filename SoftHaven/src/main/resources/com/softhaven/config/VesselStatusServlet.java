//package com.softhaven.servlets;
//
//import com.softhaven.daos.UserDAO;
//import models.User;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebServlet("/agent/vessels/updateStatus")
//public class VesselStatusServlet extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//
//    public VesselStatusServlet() {
//        super();
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        UserDAO userDao = new UserDAO();
//        User revised_user = null;
//        try {
//            revised_user = userDao.checkPosition(user.getEmail());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        assert revised_user != null;
//        user.setPosition(revised_user.getPosition());
//        session.setAttribute("user", user);
//        if (!user.getPosition().equals("agent")) {
//            response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
//        } else {
//
//        }
//    }
//}
//
