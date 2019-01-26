package com.icic.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icic.daos.UsersDao;




/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	private ServletContext context=null;
    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("Authentication Filter initialized");
	}

    
    /**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req= (HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		
		String uri=req.getRequestURI();
		if(uri.endsWith("LoginForm") || uri.endsWith("LoginServlet") || uri.endsWith("html") || uri.endsWith("js")
		|| uri.endsWith("css") || uri.endsWith("png") || uri.endsWith("NewUser") || uri.endsWith("NewUserSub")||uri.endsWith("ForgetPasswordController")
				||uri.endsWith("ForgetPasswordView")) {
		chain.doFilter(request, response);}
		else {
			HttpSession session=req.getSession();
			Integer uid=0;
			String uname=new String();
			String pword=new String();
			if(session!=null) {
				Integer Uid=(Integer)session.getAttribute("Uid");
				uname=(String)session.getAttribute("Uname");
				pword=(String)session.getAttribute("Pword");
				if(Uid!=null && uname!=null && pword!=null) {
					uid=UsersDao.checkAvailablity(uname, pword);
					if(uid==Uid) {
						chain.doFilter(request, response);
					}
					else {
						res.sendRedirect("LoginForm");
					}
				}else {
					res.sendRedirect("LoginForm");
				}
			}
		}
	}


}
