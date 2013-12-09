package com.coolb.wisw.action;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coolb.wisw.model.bo.HallChatMessage;
import com.coolb.wisw.model.pojo.User;
import com.coolb.wisw.scope.WiswApplication;
import com.coolb.wisw.util.JSONUtils;

@Controller
@RequestMapping("/wisw/hall")
public class HallAction {

	private static final Logger log = Logger.getLogger(HallAction.class);

	@RequestMapping("/index.do")
	public String index(HttpServletRequest request, ModelMap model) {
		User user = (User) request.getSession().getAttribute("user");
		model.put("user", user);
		return "/WEB-INF/wisw/hall/index.jsp";
	}

	@RequestMapping("/sendMsg.do")
	public void sendMsg(String message, HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.debug(message);
		if (message.length() > 500) {
			message = message.substring(0, 50);
		}
		User user = (User) request.getSession().getAttribute("user");
		HallChatMessage hallChatMessage = new HallChatMessage();
		hallChatMessage.setNickname(user.getNickname());
		hallChatMessage.setUsername(user.getUsername());
		hallChatMessage.setMessage(message);
		WiswApplication.addHallChatMessage(hallChatMessage);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", true);
		response.getWriter().print(JSONUtils.bean2JsonString(resultMap));
	}

	@RequestMapping("/pullMsg.do")
	public void pullMsg(Long lastReceiveTime, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 最早只读取30分钟前的消息
		if (lastReceiveTime == 0) {
			lastReceiveTime = new Date().getTime() - 10 * 60 * 1000;
		}
		List<HallChatMessage> unreadMessageList = WiswApplication.pullHallChatMessage(new Date(lastReceiveTime));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("unreadMessageList", unreadMessageList);
		resultMap.put("lastReceiveTime", unreadMessageList.size() != 0 ? unreadMessageList.get(unreadMessageList.size() - 1).getSendTime().getTime() : 0);
		response.getWriter().print(JSONUtils.bean2JsonString(resultMap));
	}
	
	@RequestMapping("/pullUserList.do")
	public void pullUserList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<User> userList = WiswApplication.userList;
		Collections.sort(userList, UserComparator.INSTANCE);
		response.getWriter().print(JSONUtils.list2JsonString(userList));
	}
	
	private static enum UserComparator implements Comparator<User> {
		INSTANCE;

		@Override
		public int compare(User o1, User o2) {
			return o1.getId().intValue() - o2.getId().intValue();
		}
		
	}

}
