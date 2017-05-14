package com.sav.autobase.webapp.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.IAuthenticationService;
import com.sav.autobase.services.exception.ServiceException;

@Component
public class BasicAuthFilter implements Filter {

	private IAuthenticationService authService;
	private ApplicationContext appContext;
	
	public static final String userAttribute = "UserLoggin";

	@Override
	public void init(FilterConfig config) throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		authService = context.getBean(IAuthenticationService.class);
		appContext = context;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		if (!isAuthRequired(req)) {
			chain.doFilter(request, response);
			return;
		}

		String[] credentials = resolveCredentials(req);

		boolean isCredentialsResolved = credentials != null && credentials.length == 2;
		if (!isCredentialsResolved) {
			res.sendError(401);
			return;
		}

		String login = credentials[0];
		String password = credentials[1];
		
		try {
			Users user = authService.authenticate(login, password);
			if (user != null) {
				request.setAttribute(userAttribute, user);
				chain.doFilter(request, response);
			} else {
				res.sendError(401);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	private String[] resolveCredentials(HttpServletRequest req) {
		try {
			Enumeration<String> headers = req.getHeaders("Authorization");
			String nextElement = headers.nextElement();
			String base64Credentials = nextElement.substring("Basic".length()).trim();
			String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
			return credentials.split(":", 2);
		} catch (Exception e) {
			return null;
		}
	}

	private boolean isAuthRequired(HttpServletRequest req) {
		if (req.getRequestURI().startsWith("/auth/login")) {
			return false;
		}
		if (req.getRequestURI().startsWith("/auth/register")) {
			return false;
		}
		return true;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}
