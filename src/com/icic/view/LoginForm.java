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
 * Servlet implementation class LoginForm
 */
@WebServlet("/LoginForm")
public class LoginForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginForm() {
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
		out.println("<title>Login Form</title>");
		RequestDispatcher rs = request.getRequestDispatcher("Header");
		rs.include(request, response);
		
		out.println("<aside>");
		if(request.getAttribute("message")!=null)
			out.println("<h5>"+request.getAttribute("message")+"</h5>");
		out.println("<form method=\"Post\" action=\"/BankSystem/LoginServlet\">");
		out.println("Username : <input type=\"text\" name =\"Uname\" required><br>");
		out.println("Password : <input type=\"password\" name =\"Pword\" required><br>");
		out.println("<input type=\"submit\" style=\"background-color: #d7006b;border-radius: 2em;color:white; padding-left:2%; padding-right:2%;cursor:pointer;\" value=\"Login\">");
		out.println("</form>");
		out.println("<br><a href=\"/BankSystem/NewUser\" style=\"display:inline;\"> New User??</a>");
		out.println("<a href=\"/BankSystem/ForgetPasswordController\" style=\"display:inline;margin-left:15%;\"> Forget Password</a>");
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
