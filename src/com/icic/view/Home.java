package com.icic.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icic.daos.UsersDao;
import com.icic.pojos.Users;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		UsersDao ud = new UsersDao();
		HttpSession session = request.getSession();
		//System.out.println(session.getAttribute("Uid"));
		int uid =(Integer)session.getAttribute("Uid");
		Users user = ud.find(uid);
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Home</title>");
		RequestDispatcher rs = request.getRequestDispatcher("Header");
		rs.include(request, response);
		rs = request.getRequestDispatcher("Menu");
		rs.include(request, response);
		 rs = request.getRequestDispatcher("SubMenu");
		rs.include(request, response);
		out.println("<aside>");
		out.println("Welcome "+user.getName()+" ! ! ! ! ! ! <br>");
		out.println("Current Loged in time :"+ new java.util.Date());
		out.println("</aside>");
		rs = request.getRequestDispatcher("Footer");
		rs.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
