package com.icic.design;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Header
 */
@WebServlet("/Header")
public class Header extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Header() {
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
		PrintWriter out =response.getWriter();
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"mystyle.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<header>");
		out.println("<div class =\"title\">");
		out.println("<div class=\"title-main\">");
		out.println("<h1>ICIC Bank</h1>");
		out.println("</div>");
		out.println("<div class=\"title-sub\">");
		out.println("<h2>Always with you</h2>");
		out.println("</div>");
		out.println("</div>");
		out.println("</header>");

	}

}
