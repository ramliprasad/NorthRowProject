package com.northrow.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
/**
 * <h1>FreezerBasicAuthenticationEntryPoint</h1>
 * <p>This program provides an implementation for BasicAuthentication.
 *
 * @author Prasad Ramalingam
 *
 */
@Component
public class FreezerBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		super.commence(request, response, authException);
		String realmHeader = "Basic realm=\"" + getRealmName() + "\"";
		response.addHeader("WWW-Authenticate", realmHeader);
		PrintWriter writer = response.getWriter();
		writer.println("HTTP Status 401 - " + authException.getMessage());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("NorthRow Food Freezer");
		super.afterPropertiesSet();
	}

}