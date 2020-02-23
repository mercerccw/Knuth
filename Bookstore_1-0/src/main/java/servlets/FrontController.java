 package servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commands.*;

import javax.servlet.ServletContext;
import dao.*;

/**
 * Servlet implementation class FrontController
 */
@WebServlet(urlPatterns= {"/listbooks", "/edit", "/delete", "/save"})
public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	@Inject @JDBC
	BookDAO dao;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost( request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Action action = null;
		
		String operation ;
		Command order;
		operation = getOperation( request.getRequestURI());
		order = CommandManager.getOrder(operation);
		System.out.println(operation);
		if (order!=null) {
			order.setDAO(dao);
			action = order.executeAction(request);
		}
		if (action.hasRedirect()) {
			response.sendRedirect(action.getPath());
		} else {
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/" + action.getPath());
			rd.forward( request, response);
		}
		
		
	}



	private String getOperation( String requestURI) {
		int pos = requestURI.lastIndexOf("/");
		return requestURI.substring(pos+1);
	}
}
