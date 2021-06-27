package com.descorp.rpgdocs.filters;

import com.descorp.rpgdocs.models.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/docs/*" ,servletNames = "{Faces Servlet}")
public class AuthFilter implements Filter{

    public void init(FilterConfig fc) throws ServletException {
        
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        User user = (User) request.getSession(true).getAttribute("user");
        
        if(user == null){
            response.sendRedirect(request.getContextPath() + "/sign-in.xhtml?faces-redirect=true");
            return;
        }
        fc.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        
    }
    
}