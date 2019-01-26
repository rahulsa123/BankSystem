package com.icic.view;

import java.io.IOException;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user= request.getParameter("Uname");
		String pword=request.getParameter("Pword");
		Users u =UsersDao.authenticate(user, pword);
		if(u==null) {
			String message ="Invalid Username or Password";
			request.setAttribute("message", message);
			System.out.println("not log in");
			RequestDispatcher rs =request.getRequestDispatcher("LoginForm");
			rs.forward(request, response);
		}else {
			HttpSession session=request.getSession();
			session.setAttribute("Uid", u.getUid());
			session.setAttribute("Uname", user);
			session.setAttribute("Pword", pword);
			response.sendRedirect("LoginSuccess");
			System.out.println("log in");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
