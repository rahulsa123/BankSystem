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
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
	//	System.out.println(session.getAttribute("Uid"));
		int uid =(Integer)session.getAttribute("Uid");
		Users user = ud.find(uid);
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Profile</title>");
		RequestDispatcher rs = request.getRequestDispatcher("Header");
		rs.include(request, response);
		rs = request.getRequestDispatcher("Menu");
		rs.include(request, response);
		 rs = request.getRequestDispatcher("SubMenu");
		rs.include(request, response);
		out.println("<aside>");
		out.println("<img src=\"user.png\" alt=\"User\"style=\"width:20%;height:20%; margin-left:30%\"><br>");
		out.println("Name :"+user.getName());
		out.println("<br>Email :"+user.getEmail());
		out.println("<br>Mobile :"+user.getMobile());
		out.println("<br>Address :"+user.getAddrees());
		out.println("<form action=\"/BankSystem/UpdateUserSub\" method=\"Post\" autocomplete=\"on\">");
		out.println("<br><h1>Update Profile</h1>");
		out.println("Name* :<input type=\"text\" name=\"Name\" required value=\""+user.getName()+"\"><br><br>");
		out.println("Address* :<input type=\"text\" name=\"Address\" required value=\""+user.getAddrees()+"\"><br><br>");
		out.println("Mobile* : +91<input type=\"text\" name=\"Mobile\" pattern=\"[789][0-9]{9}\" required value=\""+user.getMobile()+"\"><br><br>");
		out.println("Email* :<input type=\"email\" name=\"Email\" required value=\""+user.getEmail()+"\">");
		out.println(" <input type=\"hidden\" name=\"NewUname\"  value=\""+user.getUsername()+"\"><br><br>");
		out.println("Password* :<input type=\"text\" name=\"Pword\"  required value=\""+user.getPassword()+"\"><br><br>");
		out.println("<input type=\"submit\" style=\"background-color: #d7006b;border-radius: 2em;color:white; padding-left:2%; padding-right:2%;cursor:pointer;\" >"
				+ " <input type=\"reset\" value=\"cancel\" style=\"background-color: #d7006b;border-radius: 2em;color:white; padding-left:2%; padding-right:2%;cursor:pointer;\" > ");
		out.println("</form>");
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
