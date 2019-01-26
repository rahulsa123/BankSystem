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
 * Servlet implementation class IncomeCatView
 */
@WebServlet("/IncomeCatView")
public class IncomeCatView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncomeCatView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		
		IncomeCategoryDao icd = new IncomeCategoryDao();
		ArrayList<IncomeCategory> array = (ArrayList<IncomeCategory>)request.getAttribute("array");
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Income Category</title>");
		out.println("<script>");
		out.println("function remove(inc_catid){");
		out.println("document.getElementById(\"inc_catid\").value=inc_catid;");
		out.println("document.getElementById(\"operation\").value='remove';");
		out.println("document.IncomeCatForm.submit();");
		out.println("}");
		out.println("function modify(inc_catid,inc_catname,inc_catdetails){");
		out.println("document.getElementById(\"inc_catid\").value=inc_catid;");
		out.println("document.getElementById(\"inc_catname\").value=inc_catname;");
		out.println("document.getElementById(\"inc_catdetails\").value=inc_catdetails;");
		out.println("document.getElementById(\"operation\").value='edit';");
		out.println("document.getElementById(\"insert\").value='Modify';");
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
				"	border-radius: 1em;padding-left: 5%;\";>income Categories</h1>");
		out.println("<form action=\"IncomeCategoryController\" name=\"IncomeCatForm\" method =\"Post\">");
		out.println("Category Name :<input type=\"text\" id=\"inc_catname\" name=\"inc_catname\"><br><br>");
		out.println("Category Details :<br><textarea rows=\"8\" cols=\"40\" id=\"inc_catdetails\" name=\"inc_catdetails\"></textarea><br><br>");
		out.println("<input type=\"submit\" id=\"insert\" value=\"Insert\" onclick=\"submit();\" style=\"background-color: #d7006b;border-radius: 2em;color:white; padding-left:2%; padding-right:2%;\" >");
		out.println("<input type=\"reset\" style=\"background-color: #d7006b;border-radius: 2em;color:white; padding-left:2%; padding-right:2%;\"  value=\"cancel\"><br> <br>");
		out.println("<input type=\"hidden\" id=\"operation\" name=\"operation\" value=\"insert\">");
		out.println("<input type =\"hidden\" id=\"inc_catid\" name=\"inc_catid\">");
		out.println("</form>");
		
		if(array!=null && array.size()>0) {
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>Category Name</th>");
			out.println("<th>Category Details</th>");
			out.println("<th>Edit</th>");
			out.println("<th>Delete</th>");
			out.println("</tr>");
			for(IncomeCategory ic:array) {
				out.println("<tr>");
					out.println("<td>");
					out.println(ic.getInc_catname());
					out.println("</td>");
					out.println("<td>");
					out.println(ic.getInc_catdetails());
					out.println("</td>");
					out.println("<td>");
					out.println("<input style=\"background-color: #d7006b;border-radius: 2em;color:white;\" type=\"button\" value=\"Edit\""
							+ "onclick=\"modify('"+ic.getInc_catid()+"','"+ic.getInc_catname()+"','"+ic.getInc_catdetails()+"');\">");
					out.println("</td>");
					out.println("<td>");
					out.println("<input style=\"background-color: #d7006b;border-radius: 2em;color:white;\"type=\"button\" value=\"Delete\""
							+ "onclick=\"remove('"+ic.getInc_catid()+"');\">");
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
