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

import com.icic.daos.ExpensesCategoryDao;
import com.icic.pojos.ExpensesCategory;


/**
 * Servlet implementation class ExpensesCategoryController
 */
@WebServlet("/ExpensesCategoryController")
public class ExpensesCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpensesCategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ExpensesCategoryDao ecd = new ExpensesCategoryDao();
		
		int uid =0;
		HttpSession session = request.getSession();
			uid=(Integer)(session.getAttribute("Uid"));
		int exp_catid=0;
		if(request.getParameter("exp_catid")!=null  && !request.getParameter("exp_catid").equals("")) {
			exp_catid=Integer.parseInt(request.getParameter("exp_catid"));
		}
		String exp_catname =new String();
		if(request.getParameter("exp_catname")!=null)
			exp_catname=request.getParameter("exp_catname");
		String exp_catdetails= new String();
		if(request.getParameter("exp_catdetails")!=null)
			exp_catdetails=request.getParameter("exp_catdetails");
		String operation = new String();
		if(request.getParameter("operation")!=null)
			operation=request.getParameter("operation");
		
		if(operation.equals("insert")) {
			ExpensesCategory ec = new ExpensesCategory(exp_catname, exp_catdetails, uid);
			ecd.create(ec);
		}
		if(operation.equals("edit")) {
			ExpensesCategory ec = new ExpensesCategory(exp_catname, exp_catdetails, uid);
			ec.setExp_catid(exp_catid);
			ecd.edit(ec);
		}
		if(operation.equals("remove")) {
			ExpensesCategory ec = new ExpensesCategory(exp_catname, exp_catdetails, uid);
			ec.setExp_catid(exp_catid);
			ecd.remove(ec);
		
		}
		ArrayList<ExpensesCategory> array = ecd.findAll(uid);
		request.setAttribute("array", array);
		
		RequestDispatcher rs = request.getRequestDispatcher("ExpensesCatView");
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
