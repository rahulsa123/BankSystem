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
 * Servlet implementation class ExpensesCatView
 */
@WebServlet("/ExpensesCatView")
public class ExpensesCatView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpensesCatView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		ArrayList<ExpensesCategory> array = (ArrayList<ExpensesCategory>) request.getAttribute("array");
		ExpensesCategoryDao ecd=new ExpensesCategoryDao();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Expenses Category</title>");
		out.println("<script>");
		out.print("function modify(exp_catid,exp_catname,exp_catdetails)");
		out.println("{");
		out.println("document.getElementById(\"exp_catid\").value=exp_catid;");
		out.println("document.getElementById(\"exp_catname\").value=exp_catname;");
		out.println("document.getElementById(\"exp_catdetails\").value=exp_catdetails;");
		out.println("document.getElementById(\"operation\").value= 'edit';");
		out.println("document.getElementById(\"insert\").value= 'Modify';");
		out.println("}");
		out.print("function remove(exp_catid)");
		out.println("{");
		out.println("document.getElementById(\"exp_catid\").value=exp_catid;");
		out.println("document.getElementById(\"operation\").value= 'remove';");
		out.println("document.ExpensesCatForm.submit();");
		out.println("}");
		out.println("</script>");
		RequestDispatcher rs = request.getRequestDispatcher("Header");
		rs.include(request, response);
		rs = request.getRequestDispatcher("Menu");
		rs.include(request, response);
		rs = request.getRequestDispatcher("SubMenu");
		rs.include(request, response);
		out.println("<aside>");
		out.println("<h1 	style=\"width:40%;background-color:#ff0080;color:white;" + 
				"	border-radius: 1em;padding-left: 5%;\";>Expenses Categories</h1>");
		out.println("<form action=\"ExpensesCategoryController\" name=\"ExpensesCatForm\" method=\"Post\">");
		out.println("Category Name :<input type=\"text\"  id =\"exp_catname\" name=\"exp_catname\"><br><br>");
		out.println("Category Details :<br><textarea rows=\"8\" id=\"exp_catdetails\" cols=\"40\" name=\"exp_catdetails\"></textarea><br><br>");
		out.println("<input type=\"submit\"  style=\"background-color: #d7006b;border-radius: 2em;color:white;cursor:pointer;\"id=\"insert\" value=\"insert\"class=\"button\" onclick=\"submit();\" >");
		out.println("<input type=\"reset\"  style=\"background-color: #d7006b;border-radius: 2em;color:white;cursor:pointer;\" class=\"button\" ><br> <br>");
		out.println("<input name=\"operation\" id=\"operation\"  value=\"insert\" type=\"hidden\">");
		out.println("<input  id=\"exp_catid\"  name=\"exp_catid\"  type=\"hidden\">");
		out.println("</form>");
		
		if(array!=null && array.size()>0) {
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>Category Name</th>");
			out.println("<th>Category Details</th>");
			out.println("<th>Edit</th>");
			out.println("<th>Delete</th>");
			out.println("</tr>");
			for(ExpensesCategory ec:array) {
				out.println("<tr>");
					out.println("<td>");
					out.println(ec.getExp_catname());
					out.println("</td>");
					out.println("<td>");
					out.println(ec.getExp_catdetails());
					out.println("</td>");
					out.println("<td>");
					out.println("<input class=\"button\" style=\"background-color: #d7006b;border-radius: 2em;color:white;cursor:pointer;\"type=\"button\" value=\"Edit\" "
							+" onclick=\"modify('"+ec.getExp_catid()+"','"+ec.getExp_catname()+"','"+ec.getExp_catdetails()+"');\">");
					out.println("</td>");
					out.println("<td>");
					out.println("<input class=\"button\"  style=\"background-color: #d7006b;border-radius: 2em;color:white;cursor:pointer;\"type=\"button\"  value=\"Delete\" "
							+ " onclick=\"remove('"+ec.getExp_catid()+"');\">");
					out.println("</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		}
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
