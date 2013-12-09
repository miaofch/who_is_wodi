package com.coolb.wisw.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coolb.wisw.service.TestService;

@Controller
@RequestMapping("/wisw/test")
public class TestAction {
	
	@Autowired
	private TestService testService;

	@RequestMapping("/test.do")
	public String test(HttpServletRequest request) {
		System.out.println(request.getParameter("a"));
		return "/WEB-INF/test.jsp";
	}
	
}
