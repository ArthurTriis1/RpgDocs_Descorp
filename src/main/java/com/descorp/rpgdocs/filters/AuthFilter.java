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

@WebFilter(urlPatterns = {"/RpgDocs_Descorp/*","/docs/*", "/boards/*"} ,servletNames = "{Faces Servlet}")
public class AuthFilter implements Filter{

    public void init(FilterConfig fc) throws ServletException {
        
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        User user = (User) request.getSession(true).getAttribute("user");
        
        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString() != null ? request.getQueryString() : "";
        String completeUrl = "";
        String enterBoardUrl = "/boards/enter.xhtml";
        
        
         if (queryString != null) {
            completeUrl  = requestURL.append('?').append(queryString).toString();
         }
         
        
        if(user == null){
            
            String ul = "/sign-in.xhtml?faces-redirect=true";
            
            
            if(completeUrl.contains(enterBoardUrl)) {
                ul = ul+"&magic="+queryString.split("=")[1];
            }
            
            
            response.sendRedirect(request.getContextPath() + ul);
            return;
        }

        fc.doFilter(servletRequest, servletResponse);        
    }

    public void destroy() {
        
    }
    
}