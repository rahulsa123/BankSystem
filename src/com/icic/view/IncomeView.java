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

import com.icic.daos.IncomeCategoryDao;
import com.icic.pojos.IncomeCategory;

/**
 * Servlet implementation class IncomeView
 */
@WebServlet("/IncomeView")
public class IncomeView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncomeView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession();
		Integer uid=(Integer)session.getAttribute("Uid");
		IncomeCategoryDao ecd=new IncomeCategoryDao();
		ArrayList<IncomeCategory> array =null;
		if(uid!=null) {
			array=ecd.findAll(uid);
		}
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Income</title>");
		out.println("<script>");
		out.println("function sub(){");
		out.println("document.getElementById(\"operation\").value='insert';");
		
		out.println("document.IncomeViewForm.submit();");
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
		out.println("<h1>Income</h1><br>");
		out.println("<form action=\"IncomeViewController\" id=\"IncomeViewForm\" method=\"Post\">");
		out.println("Income* :<input type=\"text\" required name=\"income\" required><br><br>");
		out.println("Category :<select id=\"select\"  name=\"inc_catid\" required>");
		if(array!=null && array.size()>0) {
			for(IncomeCategory ec :array) {
				out.println("<option value=\""+ec.getInc_catid()+"\"> "+ec.getInc_catname()+" </option>");
			}
			out.println("</select><br><br>");
		}else {
			out.println("</select>");
			out.println("<h5>Please first add Income category</h5>");
		}
		
		out.println("Amount* :<input type=\"number\"  name=\"amount\" required><br><br>");
		out.println("Receive By :<select id=\"select\"  name=\"receiveBy\" required>");
		out.println("<option value=\"Cash\">Cash</option>");
		out.println("<option value=\"UPI\">UPI</option>");
		out.println("<option value=\"Credit Card\">Credit Card</option>");
		out.println("<option value=\"Debit Card\">Debit Card</option>");
		out.println("</select><br><br>");
		out.println("Remark* :<input type=\"text\" required name=\"remark\" required><br><br>");
		out.println("Date* :<input type=\"Date\" required name=\"date\" required><br><br>");
		out.println("<input type=\"submit\" onclick=\"sub();\" style=\"background-color: #d7006b;border-radius: 2em;color:white; padding-left:2%; padding-right:2%;\" >");
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
