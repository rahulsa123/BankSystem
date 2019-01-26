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

import com.icic.daos.CashBookDao;

import com.icic.pojos.CashBook;


/**
 * Servlet implementation class CashBookView
 */
@WebServlet("/CashBookView")
public class CashBookView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CashBookView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		ArrayList<CashBook> array=null;
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		String message=null;
		if(request.getHeader("Referer")!=null &&sdate!=null &&edate!=null&& request.getHeader("Referer").endsWith("CashBookView")) {
			HttpSession session = request.getSession();
			Integer userid=(Integer)session.getAttribute("Uid");
			CashBookDao cbd=new CashBookDao();
			array = cbd.findAllDateWise(sdate, edate, userid);
			if(array== null || array.size()==0) {
				message="<h5 style=\"background-color: #ffff00;" + 
						"	color: red;" + 
						"	border-radius: 1em;" + 
						"	padding-left: 5%;\">-->No Record found</h5>";
			}
		}
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Cash Book</title>");

		RequestDispatcher rs = request.getRequestDispatcher("Header");
		rs.include(request, response);
		rs = request.getRequestDispatcher("Menu");
		rs.include(request, response);
		rs = request.getRequestDispatcher("SubMenu");
		rs.include(request, response);
		out.println("<aside>");
		if(message!=null) {
			out.println(message);
		}
		out.println("<table>");
		out.println("<tr>");
			out.println("<th>Cash Book</th>");
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
		double amount=0;
		if(array!=null) {
				for(CashBook e :array) {
					out.println("<tr>");
					out.println("<td>"+(++count)+"</td>");
					out.println("<td>"+e.getTran_date().getDate()+"-"+(e.getTran_date().getMonth()+1)+"-"+(1900+e.getTran_date().getYear())+"</td>");
					out.println("<td>"+e.getAmount()+"</td>");
					out.println("<td>"+e.getOperation()+"</td>");
					if(e.getOperation().equalsIgnoreCase("credit") || e.getOperation().equalsIgnoreCase("recevie")) {
						amount+=e.getAmount();
					}else {
						amount-=e.getAmount();
					}
					out.println("</tr>");
					//System.out.println(e +""+amount);
				}
		}
		
		amount*=100;
		amount=((int)amount)/100;
		out.println("<tr>");
		out.println("<th colspan=\"2\">Closing amount");
		out.println("</th>");
		out.println("<th colspan=\"2\">"+amount);
		out.println("</th>");
		out.println("</tr>");
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
