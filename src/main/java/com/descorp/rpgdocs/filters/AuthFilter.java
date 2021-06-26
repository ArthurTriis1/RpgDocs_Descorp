package com.descorp.rpgdocs.filters;

import com.descorp.rpgdocs.models.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/docs/*" ,servletNames = "{Faces Servlet}")
public class AuthFilter implements Filter{

    public void init(FilterConfig fc) throws ServletException {
        
    }

    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
        
        User user = (User) request.getSession(true).getAttribute("user");
        
        if(user == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/sign-in.xhtml?faces-redirect=true");
            dispatcher.forward(request, sr1);
            return;
        }
        fc.doFilter(sr, sr1);
    }

    public void destroy() {
        
    }
    
}
