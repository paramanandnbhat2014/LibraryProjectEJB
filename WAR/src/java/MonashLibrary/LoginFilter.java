/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pammy
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/faces/secured/*"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.REQUEST, DispatcherType.INCLUDE})
public class LoginFilter implements Filter {
    
    public LoginFilter() {
    }    
    
       /**
     * Checks if user is logged in. If not it redirects to the login.xhtml page.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Get the LoginController from session attribute
        LoginController loginController = (LoginController)((HttpServletRequest)request).getSession().getAttribute("loginController");

        // For the first application request there is no loginController in the session so user needs to log in
        // For other requests loginController is present but we need to check if user has logged in successfully
        if (loginController == null || !loginController.isLoggedIn()) {
            // User is not logged in, so redirect to Login.xhtml.
            String contextPath = ((HttpServletRequest)request).getContextPath();
            ((HttpServletResponse)response).sendRedirect(contextPath + "/faces/Login.xhtml");
        }
        else
            // User is logged in, so just continue request.
            chain.doFilter(request, response);
             
    }
 
    @Override
    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }
 
    @Override
    public void destroy() {
        // Nothing to do here!
    }  
}
