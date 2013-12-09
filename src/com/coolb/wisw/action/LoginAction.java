package com.coolb.wisw.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coolb.wisw.model.pojo.User;
import com.coolb.wisw.service.UserSerivce;
import com.coolb.wisw.util.JSONUtils;
import com.coolb.wisw.util.StringUtils;

@Controller
public class LoginAction {

	private static final Logger log = Logger.getLogger(LoginAction.class);

	@Autowired
	private UserSerivce userSerivce;

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public void login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (StringUtils.isEmpty(username)) {
			resultMap.put("result", false);
			resultMap.put("message", "用户名不能为空");
			response.getWriter().write(JSONUtils.bean2JsonString(resultMap));
			return;
		}
		if (StringUtils.isEmpty(password)) {
			resultMap.put("result", false);
			resultMap.put("message", "密码不能为空");
			response.getWriter().write(JSONUtils.bean2JsonString(resultMap));
			return;
		}
		List<User> userList = userSerivce.queryUserByUsernameAndPassword(username, password);
		if (userList.size() == 0) {
			resultMap.put("result", false);
			resultMap.put("message", "用户名或密码错误");
		} else {
			request.getSession().setAttribute("user", userList.get(0));
			resultMap.put("result", true);
		}
		response.getWriter().write(JSONUtils.bean2JsonString(resultMap));
	}
	
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public void register(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (!user.getUsername().matches("[a-zA-Z0-9@_-]{4,20}")) {
			resultMap.put("result", false);
			resultMap.put("message", "用户名必须为4-20位，且不能包含特殊字符");
			response.getWriter().write(JSONUtils.bean2JsonString(resultMap));
			return;
		}
		if (!user.getNickname().matches(".{2,10}")) {
			resultMap.put("result", false);
			resultMap.put("message", "昵称必须为2-10位");
			response.getWriter().write(JSONUtils.bean2JsonString(resultMap));
			return;
		}
		long usernameCount = userSerivce.queryUserCountByUsername(user.getUsername());
		if (usernameCount > 0) {
			resultMap.put("result", false);
			resultMap.put("message", "用户名已被占用");
			response.getWriter().write(JSONUtils.bean2JsonString(resultMap));
			return;
		}
		long nicknameCount = userSerivce.queryUserCountByNickname(user.getNickname());
		if (nicknameCount > 0) {
			resultMap.put("result", false);
			resultMap.put("message", "昵称已被占用");
			response.getWriter().write(JSONUtils.bean2JsonString(resultMap));
			return;
		}
		userSerivce.insertUser(user);
		resultMap.put("result", true);
		response.getWriter().write(JSONUtils.bean2JsonString(resultMap));
	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String login(HttpServletRequest request) throws IOException {
		request.getSession().invalidate();
		return "/index.jsp";
	}

}
