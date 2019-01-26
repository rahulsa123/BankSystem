package com.icic.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

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
 * Servlet implementation class LoginSuccess
 */
@WebServlet("/LoginSuccess")
public class LoginSuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSuccess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession();
		int uid=0;
		if(session.getAttribute("Uid")!=null)
			uid=(Integer)session.getAttribute("Uid");
		UsersDao ud= new UsersDao();
		Users u =ud.find(uid);
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Login Success</title>");
		RequestDispatcher rs = request.getRequestDispatcher("Header");
		rs.include(request, response);
		rs=request.getRequestDispatcher("Menu");
		rs.include(request, response);
		rs=request.getRequestDispatcher("SubMenu");
		rs.include(request, response);
		out.println("<aside>");
		out.println("<h3>Hello " +u.getName()+"</h2>");
		out.println("<h4> Login successfully at <br>"+new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new java.util.Date()));
		out.println("</h4></aside>");
		rs=request.getRequestDispatcher("Footer");
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
