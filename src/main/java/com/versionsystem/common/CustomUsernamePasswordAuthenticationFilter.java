package com.versionsystem.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {

	@Override
	   public Authentication attemptAuthentication(HttpServletRequest request,
	         HttpServletResponse response) throws AuthenticationException {
	      String locale = request.getParameter("j_locale");
	      String securityCode=request.getParameter("j_securityCode");
	      // System.out.println("locale is " + locale);
	       //System.out.println("securityCode is "+securityCode);
	      request.getSession(true).setAttribute("locale", locale);
	      //request.getSession().setAttribute("validateSecurityCode", false);
	      request.getSession(true).setAttribute("securityCode", securityCode);
	      return super.attemptAuthentication(request, response);
	  }

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		super.unsuccessfulAuthentication(request, response, failed);

		System.out.println("==failed login==");
	}

}