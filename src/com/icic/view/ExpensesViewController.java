package com.icic.view;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icic.daos.ExpensesDao;
import com.icic.pojos.Expenses;


/**
 * Servlet implementation class ExpensesViewController
 */
@WebServlet("/ExpensesViewController")
public class ExpensesViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpensesViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int uid=0;
		if(session.getAttribute("Uid")!=null ) {
			uid=(Integer)session.getAttribute("Uid");
		}
		String expenses = new String();
		if(request.getParameter("expenses")!=null)
			expenses=request.getParameter("expenses");
		int exp_catid = 0;
			if(request.getParameter("exp_catid")!=null && !request.getParameter("exp_catid").isEmpty())
				exp_catid=Integer.parseInt(request.getParameter("exp_catid"));		
		double amount=0;
		if(request.getParameter("amount")!=null && !request.getParameter("amount").isEmpty() )
			amount=Integer.parseInt(request.getParameter("amount"));
		String payBy = new String();
		if(request.getParameter("payBy")!=null)
			payBy=request.getParameter("payBy");
		String remark = new String();
		if(request.getParameter("remark")!=null)
			remark=request.getParameter("remark");
		String date = new String();
		if(request.getParameter("date")!=null)
			date=request.getParameter("date");
		//System.out.println(date);
		String operation = new String();
		if(request.getParameter("operation")!=null)
			operation=request.getParameter("operation");
	
		String message = "";
		if(operation.equals("insert")) {
			java.util.Date date1=null;
			try {
				date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
			}catch (Exception e) {
				System.out.println("unable to convert date in ExpensesViewController -line 72");
			}
		//	System.out.println(date +"    "+date1);
			Expenses e = new Expenses(expenses, uid, exp_catid, amount,date1 , payBy,remark);
			ExpensesDao ed = new ExpensesDao();
			ed.create(e);
			message="<h5 style=\" 	background-color: #ffff00;" + 
					"	color: red;" + 
					"	border-radius: 1em;" + 
					"	padding-left: 5%;\">-->Data is inserted</h5>";
		}
		request.setAttribute("message", message);
		RequestDispatcher rs = request.getRequestDispatcher("ExpensesView");
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
