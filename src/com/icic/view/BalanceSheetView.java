package com.icic.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icic.pojos.Expenses;
import com.icic.pojos.Incomes;

/**
 * Servlet implementation class BalanceSheetView
 */
@WebServlet("/BalanceSheetView")
public class BalanceSheetView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BalanceSheetView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Balance Sheet</title>");
		out.println("<script>");
		out.println("function sub(){");
		out.println("document.getElementById(\"find\").value='true';");
		//out.println("alert(document.getElementById(\"sdate\").value);");
		out.println("document.BalanceSheetForm.submit();");
		out.println("}</script>");
		RequestDispatcher rs = request.getRequestDispatcher("Header");
		rs.include(request, response);
		rs = request.getRequestDispatcher("Menu");
		rs.include(request, response);
		 rs = request.getRequestDispatcher("SubMenu");
		rs.include(request, response);
		out.println("<aside>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th colspan=\"6\" rowspan=\"2\"><marquee direction=\"right\" behavior=\"alternate\">Balance Sheet </marquee></th>");
		out.println("</tr>");
		out.println("<tr></tr>");
		out.println("<tr>");
		out.println("<form id=\"BalanceSheetForm\" action=\"BalanceSheetViewController\" method=\"post\">");
		out.println("<td colspan=\"2\" align=\"center\">Date From<br><input type=\"date\" name=\"sdate\" id=\"sdate\" required=\"required\"></td>");
		out.println("<td colspan=\"2\" align=\"center\">to<br><input type=\"date\" name=\"edate\" id=\"edate\" required=\"required\">"
				+ "<input type=\"hidden\" name=\"find\" id=\"find\">"
				+ "</td>");
		out.println("<td colspan=\"2\" align=\"center\"><input type=\"submit\" style=\"background-color: #d7006b;border-radius: 1em;color:white; padding-left:2%; padding-right:2%; width:90%\" "
				+ "onclick =\"sub();\"></td>");
		out.println("</form>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th colspan=\"3\">Incomes</th>");
		out.println("<th colspan=\"3\">Expenses</th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th colspan=\"2\">Incomes</th>");
		out.println("<th>Amount</th>");
		out.println("<th colspan=\"2\">Expenses</th>");
		out.println("<th>Amount</th>");
		out.println("</tr>");
		
		double iamount=0,eamount=0;
		DecimalFormat df = new DecimalFormat(".##");
		//Data table 
		ArrayList<Incomes> iarray = (ArrayList<Incomes>)request.getAttribute("iarray");
		ArrayList<Expenses> earray = (ArrayList<Expenses>)request.getAttribute("earray");
		if(iarray!=null || earray!=null)
		{
			int len_iarray=(iarray==null)?0:iarray.size();
			int len_earray=(earray==null)?0:earray.size();
			for(int i =0;i<Math.max(len_earray, len_iarray);i++) {
			out.println("<tr>");
			
			//for income
			if(len_iarray>i) {
			out.println("<td colspan=\"2\">"+ iarray.get(i).getInc_ac()+"</td>");
			out.println("<td>"+df.format(iarray.get(i).getAmount()) +"</td>");
			iamount+=iarray.get(i).getAmount();
			}else {
				out.println("<td colspan=\"2\">"+ "  "+"</td>");
				out.println("<td>"+" " +"</td>");
			}
			
			
			//for expenses
			if(len_earray>i) {
				out.println("<td colspan=\"2\">"+ earray.get(i).getExp_ac()+"</td>");
				out.println("<td>"+df.format(earray.get(i).getAmount())+"</td>");
				eamount+=earray.get(i).getAmount();
				}else {
						out.println("<td colspan=\"2\">"+ "  "+"</td>");
						out.println("<td>"+" " +"</td>");
				}
			out.println("</tr>");
			}


		}
		out.println("<tr>");
		out.println("<th colspan=\"2\">Total</th>");
		out.println("<th>"+df.format(iamount)+"</th>");
		out.println("<th colspan=\"2\">Total</th>");
		out.println("<th>"+df.format(eamount)+"</th>");
		out.println("</tr>");
		out.println("<tr>");
		String profit_loss="";
		if(iamount>eamount) {
			profit_loss="Profit";
		}else if(iamount<eamount) {
			profit_loss="Loss";
		}
		out.println("<td colspan=\"4\" align=\"right\" style=\"color :white;\"> Total "+profit_loss+"</td>");
		out.println("<td colspan=\"2\">"+df.format((iamount-eamount))+" Rs</td>");
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
