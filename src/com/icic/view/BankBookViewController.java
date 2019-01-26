package com.icic.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icic.daos.BankBookDao;
import com.icic.pojos.BankBook;

/**
 * Servlet implementation class BankBookViewController
 */
@WebServlet("/BankBookViewController")
public class BankBookViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankBookViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int uid= 0;
		if(session.getAttribute("Uid")!=null) {
			uid=(Integer)session.getAttribute("Uid");
		}
		String sdate="";
		if(request.getParameter("sdate")!=null)
			sdate= request.getParameter("sdate");
		String edate="";
		if(request.getParameter("edate")!=null)
			edate=request.getParameter("edate");
		String find="";
		if(request.getParameter("find")!=null)
			find=request.getParameter("find");
		if(find.equalsIgnoreCase("true")) {
			//System.out.println("enter in block bankbookviewcontroller line 49");
			BankBookDao bbd =new BankBookDao();
			ArrayList<BankBook> array =bbd.findAllDateWise(sdate, edate, uid);
			if(array== null || array.size()==0) {
				String message="<h5 style=\"background-color: #ffff00;" + 
						"	color: red;" + 
						"	border-radius: 1em;" + 
						"	padding-left: 5%;\">-->No Record Found</h5>";
			request.setAttribute("message", message);
			}
			request.setAttribute("array", array);
		}
		RequestDispatcher rs = request.getRequestDispatcher("BankBookView");
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
