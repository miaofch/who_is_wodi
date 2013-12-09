package com.coolb.wisw.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wisw/room")
public class RoomAction {

	@RequestMapping(value = "/room.do")
	public String room(HttpServletRequest request, HttpServletResponse response) {
		return "/WEB-INF/wisw/room/room.jsp";
	}
	
}
