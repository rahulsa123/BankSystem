package com.icic.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.icic.daos.UsersDao;
import com.icic.pojos.Users;

/**
 * Servlet implementation class NewUserSub
 */
@WebServlet("/NewUserSub")
public class NewUserSub extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserSub() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>New User registration</title>");
		RequestDispatcher rs = request.getRequestDispatcher("Header");
		rs.include(request, response);
		UsersDao ud =new UsersDao();
		String userName = request.getParameter("NewUname");
		out.println("<aside>");
		if(ud.checkAvailablity(userName)) {
			out.println("Please enter another Username");
			out.println("<input action=\"action\" onclick=\"window.history.go(-1); return false;\" type=\"button\" value=\" Submit Again!!\" />");
		}
		else {
			out.println("Successfull Registeration<br>");
			Users u=new Users();
			u.setName(request.getParameter("Name"));
			 u.setMobile(request.getParameter("Mobile"));
			u.setEmail(request.getParameter("Email"));
			u.setAddrees(request.getParameter("Address"));
			 u.setUsername(request.getParameter("NewUname"));
			u.setPassword(request.getParameter("Pword"));
			ud.create(u);
			
			out.println("<br><a href=\"/BankSystem/LoginForm\"> Login page</a>");
		}
		out.println("</aside><br>");
	rs = request.getRequestDispatcher("Footer");
		rs.include(request, response);
		
	}
	

}
