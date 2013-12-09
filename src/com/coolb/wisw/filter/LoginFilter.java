package com.coolb.wisw.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolb.wisw.model.pojo.User;
import com.coolb.wisw.scope.WiswApplication;

public class LoginFilter implements Filter {
	
	private List<String> exceptionUrlList;

	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			HttpServletResponse response = (HttpServletResponse) arg1;
			response.sendError(600, "用户尚未登录或登录超时");
		} else {
			// 如果是预设的例外链接，将不会更新用户活跃时间
			if (!exceptionUrlList.contains(request.getRequestURI())) {
				// 更新用户活跃时间
				WiswApplication.userAct(user);
			}
			arg2.doFilter(arg0, arg1);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// 加载例外链接
		exceptionUrlList = new ArrayList<String>();
		String[] exceptionUrls = arg0.getInitParameter("exceptionUrlList").split(",");
		for (int i = 0; i < exceptionUrls.length; i++) {
			exceptionUrlList.add(arg0.getServletContext().getContextPath() + exceptionUrls[i]);
		}
	}

}
