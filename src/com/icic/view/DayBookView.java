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

import com.icic.daos.ExpensesDao;
import com.icic.daos.IncomesDao;
import com.icic.pojos.Expenses;
import com.icic.pojos.Incomes;

/**
 * Servlet implementation class DayBookView
 */
@WebServlet("/DayBookView")
public class DayBookView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DayBookView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		ArrayList<Expenses> earray=null;
		ArrayList<Incomes> iarray=null;
		HttpSession session= request.getSession();
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		if(request.getHeader("Referer")!=null &&sdate!=null &&edate!=null && request.getHeader("Referer").endsWith("DayBookView")) {
			ExpensesDao ed= new ExpensesDao();
			IncomesDao id = new IncomesDao();
			
			earray= ed.findAllDateWise(sdate, edate, (Integer)session.getAttribute("Uid"));
			iarray= id.findAllDateWise(sdate, edate, (Integer)session.getAttribute("Uid"));
		}
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Day Book</title>");
		RequestDispatcher rs = request.getRequestDispatcher("Header");
		rs.include(request, response);
		rs = request.getRequestDispatcher("Menu");
		rs.include(request, response);
		rs = request.getRequestDispatcher("SubMenu");
		rs.include(request, response);
		out.println("<aside>");
		out.println("<table>");
		out.println("<tr>");
		
			out.println("<th>Day Book</th>");
			out.println("<form action=\"\" method=\"Post\">");
			out.println("<th>Date from <br><input type=\"date\"  name=\"sdate\" required>"
					+ "</th>");
			out.println("<th>To <br><input type=\"date\"  name=\"edate\" required>"
					+ "</th>");
			out.println("<th><input type=\"submit\" value=\"show\"></th>");
			out.println("</form>");
		int count=0;
		out.println("<tr>");
		out.println("<th>S. no.");
		out.println("</th>");
		out.println("<th>Date");
		out.println("</th>");
		out.println("<th>Amount.");
		out.println("</th>");
		out.println("<th>Pay/Receive");
		out.println("</th>");
		out.println("</tr>");
		
		if(earray!=null) {
			out.println("<tr>");
			out.println("<th colspan=\"4\"> Expenses");
			out.println("</th>");
			out.println("</tr>");
				for(Expenses e :earray) {
					out.println("<tr>");
					out.println("<td>"+(++count)+"</td>");
					out.println("<td>"+e.getTran_date().getDate()+"-"+(e.getTran_date().getMonth()+1)+"-"+(1900+e.getTran_date().getYear())+"</td>");
					out.println("<td>"+e.getAmount()+"</td>");
					out.println("<td>"+"Pay"+"</td>");
					out.println("</tr>");
				}
		}
		
		if(iarray!=null) {
			out.println("<tr>");
			out.println("<th colspan=\"4\"> Income");
			out.println("</th>");
			out.println("</tr>");
				for(Incomes i :iarray) {
					out.println("<tr>");
					out.println("<td>"+(++count)+"</td>");
					out.println("<td>"+i.getTran_date().getDate()+"-"+(i.getTran_date().getMonth()+1)+"-"+(1900+i.getTran_date().getYear())+"</td>");
					out.println("<td>"+i.getAmount()+"</td>");
					out.println("<td>"+"Recive"+"</td>");
					out.println("</tr>");
				}
		}
	
		out.println("</table>");
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
