package com.icic.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForgetPasswordView
 */
@WebServlet("/ForgetPasswordView")
public class ForgetPasswordView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPasswordView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String message=(String)request.getAttribute("message");
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Forget Password</title>");
		out.println("<script>");
		out.println("function sub(){");
		//out.println("alert(document.getElementById(\"mobile\").value!=\"\");");
		out.println("if(document.getElementById(\"mobile\").value !=\"\" || document.getElementById(\"email\").value !=\"\"){");
		//out.println("alert(\"please\");");
		out.println("document.ForgetPasswordForm.submit();"
				+ "}else{");
		out.println("alert(\"please enter mobile number or email id\");");
		out.println("document.location.reload(true);");
		out.println("}}");
		out.println("function updatePassword(){");
		out.println("if(document.getElementById(\"pass1\").value==document.getElementById(\"pass2\").value){");
		out.println("document.getElementById(\"update\").value='true';");
		out.println("document.ForgetPasswordForm.submit();"
				+ "}else{");
		out.println("alert(\"Password does't match\");");
		out.println("document.getElementById(\"pass1\").value='';");
		out.println("document.getElementById(\"pass2\").value='';");
		out.println("}}");
		out.println("</script>");
		RequestDispatcher rs = request.getRequestDispatcher("Header");
		rs.include(request, response);
		out.println("<aside>");
		if(message !=null && (message.equals("Invalid Username")||message.equals("Invalid mobile or email")||message.equals("Password Changed"))){
			out.println("<h5>"+message+"</h5>");
		}
		out.println("<form action=\"ForgetPasswordController\" name=\"ForgetPasswordForm\" method=\"post\">");
		out.println("<h4>User name*:<input type=\"text\" name=\"uname\"  required=\"required\"></h4>");
		out.println("<br>Mobile number : +91 <input type=\"number\" id=\"mobile\" name=\"mobile\">");
		out.println("<h1 align=\"center\"> OR</h1>");
		out.println("Email  :<input type=\"email\" name=\"email\" id=\"email\">");
		out.println("<br><br><input type=\"button\"  value=\"submit\" style=\"background-color: #d7006b;border-radius: 2em;color:white; padding-left:2%; padding-right:2%;margin-left:35%;\"  onclick=\"sub();\">");
		if(message !=null && message.equals("successfull")) {
			out.println("<br><br>Enter new Password: <input type=\"password\" name=\"pass1\" id=\"pass2\"><br>");
			out.println("<br>Retype : <input type=\"password\" name=\"pass2\" id=\"pass1\"required><br>");
			out.println("<input type=\"hidden\" name=\"update\" id=\"update\" required><br>");
			out.println("<br><br><input type=\"button\"  value=\"update\" style=\"background-color: #d7006b;border-radius: 2em;color:white; padding-left:2%; padding-right:2%;margin-left:35%;\"  onclick=\"updatePassword();\">");
		}
		
		
		out.println("</form>");
		
		out.println("<a href=\"/BankSystem/LoginForm\">LoginForm</a>");
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
