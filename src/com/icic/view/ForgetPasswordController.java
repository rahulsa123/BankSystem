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
 * Servlet implementation class ForgetPasswordController
 */
@WebServlet("/ForgetPasswordController")
public class ForgetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession();
		int userid=-1;
		if(session.getAttribute("userid")!=null)
			userid=(Integer)session.getAttribute("userid");
		String uname =null;
		if(request.getParameter("uname")!=null) {
			uname=request.getParameter("uname");
		}
		String mobile =null;
		if(request.getParameter("mobile")!=null) {
			mobile=request.getParameter("mobile");
		}
		String email =null;
		if(request.getParameter("email")!=null) {
			email=request.getParameter("email");
		}
		Users u=null;
		UsersDao ud = new UsersDao();
		String message=null;
		if(uname!=null) {
			
			if(ud.checkAvailablity(uname)) {
				int uid=ud.findUserId(uname);
				if(uid==-1) {
					message="Invalid Username";
				}else {
					 u = ud.find(uid);
					if(u.getUid()!=-1&&(mobile.equals(u.getMobile()) || email.equals(u.getEmail()))){
						session.setAttribute("userid",u.getUid());
						message ="successfull";
					}else {
						message ="Invalid mobile or email";
					}
				}
			}else {
				message="Invalid Username";
			}
		}
		if(request.getParameter("update")!=null&&request.getParameter("update").equals("true")&&userid!=-1) {
			u=ud.find(userid);
			u.setPassword(request.getParameter("pass1"));
			ud.edit(u);
			message="Password Changed";
		}
		request.setAttribute("message", message);
	//	System.out.println(message+uname+mobile+email+request.getParameter("pass1")+request.getParameter("update"));
		RequestDispatcher rs = request.getRequestDispatcher("ForgetPasswordView");
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
