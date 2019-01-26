package com.icic.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewUser
 */
@WebServlet("/NewUser")
public class NewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>New User</title>");
		RequestDispatcher rs= request.getRequestDispatcher("Header");
		rs.include(request, response);
		out.println("<aside>");
		out.println("<form action=\"/BankSystem/NewUserSub\" method=\"Post\" autocomplete=\"on\">");
		out.println("Name* :<input type=\"text\" name=\"Name\" required><br><br>");
		out.println("Address* :<input type=\"text\" name=\"Address\" required><br><br>");
		out.println("Mobile* : +91<input type=\"text\" name=\"Mobile\" pattern=\"[789][0-9]{9}\" required><br><br>");
		out.println("Email* :<input type=\"email\" name=\"Email\" required><br><br>");
		out.println("UserName* :<input type=\"text\" name=\"NewUname\" required><br><br>");
		out.println("Password* :<input type=\"password\" name=\"Pword\"  required><br><br>");
		out.println("<input type=\"submit\" >");
		out.println("</form>");
		out.println("<a href=\"/BankSystem/LoginForm\">LoginForm</a>");
		out.println("</aside>");
		rs= request.getRequestDispatcher("Footer");
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
