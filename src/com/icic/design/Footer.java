package com.icic.design;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Footer
 */
@WebServlet("/Footer")
public class Footer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Footer() {
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
		out.println("<footer style=\"position: relative;left: 0;bottom: 0;margin-top: 25%;\">");
		out.println("<hr class=\"footer-hr\">");
		out.println("<div class=\"footer-containt\">");
		out.println("<h2>Contact Us</h2>");
		out.println("<h4>+919988776655</h4>");
		out.println("<h4>Lig 127 Sector B sonagiri </h4>");
		out.println("<h4>Bhopal (MP), 402023</h4>");
		out.println("</div>");
		out.println("</footer>");
		out.println("</body>");
		out.println("</html>");
	}

}
