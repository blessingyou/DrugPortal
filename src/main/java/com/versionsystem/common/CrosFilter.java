package com.versionsystem.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class CrosFilter extends OncePerRequestFilter { 
	static final String ORIGIN = "Origin";


@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
	response.setHeader("Access-Control-Allow-Origin", "*");
	response.setHeader("Access-Control-Allow-Credentials", "true");
    response.addHeader("Access-Control-Max-Age", "10");
	response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	String reqHead = request.getHeader("Access-Control-Request-Headers");

    if (!StringUtils.isEmpty(reqHead)) {
        response.addHeader("Access-Control-Allow-Headers", reqHead);
    }
	response.setHeader("Access-Control-Expose-Headers", "x-requested-with"); 
	filterChain.doFilter(request, response);
  
   /* if (request.getHeader(ORIGIN) == null || request.getHeader(ORIGIN).equals("null")) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Max-Age", "10");
        
       
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Expose-Headers", "x-requested-with"); 

        String reqHead = request.getHeader("Access-Control-Request-Headers");

        if (!StringUtils.isEmpty(reqHead)) {
            response.addHeader("Access-Control-Allow-Headers", reqHead);
        }
    }
    if (request.getMethod().equals("OPTIONS")) {
        try {
            response.getWriter().print("OK");
            response.getWriter().flush();
        } catch (IOException e) {
        e.printStackTrace();
        }
    } else{
        filterChain.doFilter(request, response);
    }*/
}
}