/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import urlSanitizer.*;

/**
 *
 * @author suhag
 */
public class SessionTimeoutFilter implements Filter {
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession();

        // if session doesn't exist, forward user to welcome page
        if (session.getAttribute("customerEmail") == null) {
            try {
                req.getRequestDispatcher("/login.jsp").forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return;
        }

        else
        {
            chain.doFilter(new SanitizeWrapper((HttpServletRequest) request), response);
        }
//        chain.doFilter(request, response);
        
    }

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void destroy() {}
    
}
