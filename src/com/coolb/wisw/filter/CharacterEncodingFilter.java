package com.coolb.wisw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	private String charset;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.setContentType("text/html; charset=" + charset);
		chain.doFilter(request, response);
	}

	public void destroy() {
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		String charset = arg0.getInitParameter("charset");
		if (charset == null) {
			charset = "UTF-8";
		}
		this.charset = charset;
	}

}
