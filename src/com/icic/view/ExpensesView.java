package com.icic.view;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class ExpensesView
 */
@WebServlet("/ExpensesView")
public class ExpensesView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpensesView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession();
		Integer uid=(Integer)session.getAttribute("Uid");
		ExpensesCategoryDao ecd=new ExpensesCategoryDao();
		
		ArrayList<ExpensesCategory> array =null;
		if(uid!=null) {
			array=ecd.findAll(uid);
		}
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Expenses</title>");
		out.println("<script>");
		out.println("function sub(){");
		out.println("document.getElementById(\"operation\").value='insert';");
		
		out.println("document.ExpensesViewForm.submit();");
		out.println("}");
		out.println("</script>");
		RequestDispatcher rs = request.getRequestDispatcher("Header");
		rs.include(request, response);
		rs = request.getRequestDispatcher("Menu");
		rs.include(request, response);
		rs = request.getRequestDispatcher("SubMenu");
		rs.include(request, response);
		
		out.println("<aside>");
		if(request.getAttribute("message")!=null)
			out.println(request.getAttribute("message"));
		out.println("<h1>Expenses</h1><br>");
		out.println("<form action=\"ExpensesViewController\" id=\"ExpensesViewForm\"method=\"Post\">");
		out.println("Expenses* :<input type=\"text\" required name=\"expenses\" id=\"expenses\" required><br><br>");
		out.println("Category :");
		out.println("<select class=\"list\" id=\"select\"  name=\"exp_catid\" required>");
		if(array!=null && array.size()>0) {
			
			for(ExpensesCategory ec :array) {
				out.println("<option value=\""+ec.getExp_catid()+"\"> "+ec.getExp_catname()+" </option>");
			}
			out.println("</select><br><br>");
		}else {
			out.println("</select>");
			out.println("<h5>Please first add Expenses category</h5>");
		}
		
		out.println("Amount* :<input type=\"number\"  name=\"amount\" id=\"amount\"required><br><br>");
		out.println("Pay By* :<select class=\"list\"id=\"select\"  name=\"payBy\" required>");
		out.println("<option value=\"Cash\">Cash</option>");
		out.println("<option value=\"UPI\">UPI</option>");
		out.println("<option value=\"Credit Card\">Credit Card</option>");
		out.println("<option value=\"Debit Card\">Debit Card</option>");
		out.println("</select><br><br>");
		out.println("Remark* :<input type=\"text\" required name=\"remark\" required><br><br>");
		out.println("Date* :<input type=\"Date\"  required name=\"date\" required><br><br>");
		out.println("<input type=\"submit\" onclick=\"sub();\"style=\"background-color: #d7006b;border-radius: 2em;color:white; padding-left:2%; padding-right:2%;\" >");
		out.println("<input type=\"reset\" style=\"background-color: #d7006b;border-radius: 2em;color:white; padding-left:2%; padding-right:2%;\"  value=\"cancel\"><br> <br>");
		out.println("<input type=\"hidden\" id=\"operation\" name=\"operation\">");
		out.println("</from>");
		out.println("</aside>");
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
