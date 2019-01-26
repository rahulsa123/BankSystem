package com.icic.design;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SubMenu
 */
@WebServlet("/SubMenu")
public class SubMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		out.println("<section>");
		out.println("<ul class=\"sub-menu-list\">");
		out.println("<ins>Menu</ins>");
		out.println("<li><a href=\"ExpensesCategoryController\">Expenses Category</a></li>");
		out.println("<li><a href=\"IncomeCategoryController\">Income Category</a></li>");
		out.println("<li><a href=\"ExpensesViewController\">Expenses</a></li>");
		out.println("<li><a href=\"IncomeViewController\">Incomes</a></li>");
		out.println("<li><a href=\"CashBookView\">Cash Book</a></li>");
		out.println("<li><a href=\"BankBookViewController\">Bank Book</a></li>");
		out.println("<li><a href=\"DayBookView\">Day Book</a></li>");
		out.println("<li><a href=\"BalanceSheetViewController\"> Balance Sheet</a></li>");
		out.println("</ul>");
		out.println("</section>");
	}

}
