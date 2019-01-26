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

import com.icic.daos.IncomeCategoryDao;
import com.icic.pojos.IncomeCategory;

/**
 * Servlet implementation class IncomeCategoryController
 */
@WebServlet("/IncomeCategoryController")
public class IncomeCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncomeCategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IncomeCategoryDao icd = new IncomeCategoryDao();
		HttpSession session = request.getSession();
		int uid =0;
		if(session.getAttribute("Uid")!=null) {
			uid=(Integer)session.getAttribute("Uid");
		}
		int inc_catid=0;
		if(request.getParameter("inc_catid")!=null && !request.getParameter("inc_catid").equals(""))
			inc_catid=Integer.parseInt(request.getParameter("inc_catid"));
		String inc_catname=new String();
		if(request.getParameter("inc_catname")!=null)
			inc_catname=request.getParameter("inc_catname");
		String inc_catdetails=new String();
		if(request.getParameter("inc_catdetails")!=null)
			inc_catdetails=request.getParameter("inc_catdetails");
		String operation=new String();
		if(request.getParameter("operation")!=null)
			operation=request.getParameter("operation");
		
		
		if(operation.equals("insert")) {
			IncomeCategory ic = new IncomeCategory(inc_catname, inc_catdetails, uid);
			icd.create(ic);
		}
		if(operation.equals("remove")) {
			IncomeCategory ic = new IncomeCategory(inc_catname, inc_catdetails, uid);
			ic.setInc_catid(inc_catid);
			icd.remove(ic);
		}
		if(operation.equals("edit")) {
			IncomeCategory ic = new IncomeCategory(inc_catname, inc_catdetails, uid);
			ic.setInc_catid(inc_catid);
			icd.edit(ic);
		}
		
		ArrayList<IncomeCategory> array=icd.findAll(uid);
		request.setAttribute("array", array);
		RequestDispatcher rs = request.getRequestDispatcher("IncomeCatView");
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
