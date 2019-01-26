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
 * Servlet implementation class UpdateUserSub
 */
@WebServlet("/UpdateUserSub")
public class UpdateUserSub extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserSub() {
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
out.println("<title>Update Profile</title>");
		RequestDispatcher rs = request.getRequestDispatcher("Header");
		rs.include(request, response);
		rs = request.getRequestDispatcher("Menu");
		rs.include(request, response);
		rs = request.getRequestDispatcher("SubMenu");
		rs.include(request, response);
		UsersDao ud =new UsersDao();
		HttpSession session= request.getSession();
		out.println("<aside>");
		
			out.println("Successfully Update<br>");
			Users u=new Users();
			u.setName(request.getParameter("Name"));
			 u.setMobile(request.getParameter("Mobile"));
			u.setEmail(request.getParameter("Email"));
			u.setAddrees(request.getParameter("Address"));
			 u.setUsername(request.getParameter("NewUname"));
			u.setPassword(request.getParameter("Pword"));
			u.setUid((Integer)session.getAttribute("Uid"));
			ud.edit(u);
		
		out.println("</aside><br>");
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
