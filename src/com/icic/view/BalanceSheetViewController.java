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

import com.icic.daos.ExpensesDao;
import com.icic.daos.IncomesDao;
import com.icic.pojos.Expenses;
import com.icic.pojos.Incomes;


/**
 * Servlet implementation class BalanceSheetViewController
 */
@WebServlet("/BalanceSheetViewController")
public class BalanceSheetViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BalanceSheetViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int uid =0;
		HttpSession session= request.getSession();
		if(session.getAttribute("Uid")!=null) {
			uid=(Integer)session.getAttribute("Uid");
		}
		String sdate=null;
		if(request.getParameter("sdate")!=null)
			sdate= request.getParameter("sdate");
		String edate=null;
		if(request.getParameter("edate")!=null)
			edate= request.getParameter("edate");
		//System.out.println(sdate +"    "+edate);
		if(request.getParameter("find")!=null &&request.getParameter("find").equalsIgnoreCase("true")) {
			//System.out.println("inside"+request.getParameter("sdate")+request.getParameter("edate") +sdate+edate);
			IncomesDao id =new IncomesDao();
			ArrayList<Incomes> iarray=id.findAllDateWise(sdate, edate, uid);
			request.setAttribute("iarray", iarray);
			ExpensesDao ed = new ExpensesDao();
			ArrayList<Expenses> earray=ed.findAllDateWise(sdate, edate, uid);
			request.setAttribute("earray", earray);
			if(iarray==null || iarray.size()==0) {
				String imessage="No record Found";
				request.setAttribute("imessage", imessage);
			}
			if(earray==null || earray.size()==0) {
				String emessage="No record Found";
				request.setAttribute("emessage", emessage);
			}
		}
		RequestDispatcher rs = request.getRequestDispatcher("BalanceSheetView");
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
