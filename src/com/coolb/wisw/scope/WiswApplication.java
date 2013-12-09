package com.coolb.wisw.scope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.coolb.wisw.model.bo.HallChatMessage;
import com.coolb.wisw.model.pojo.User;
import com.coolb.wisw.thread.UserActivityMonitor;

public class WiswApplication {
	
	static {
		init();
	}

	/**
	 * 房间id
	 */
	public static int roomId;
	
	/**
	 * 房间列表
	 */
	public static Map<Integer, RoomApplication> roomMap;

	/**
	 * 在线用户列表
	 */
	public static List<User> userList;
	
	/**
	 * 用户最后活跃时间
	 */
	public static Map<Long, Date> userActivityRecord;
	
	/**
	 * 大厅聊天记录
	 */
	public static List<HallChatMessage> hallChatMessageList;

	private static void init() {
		// 初始化全局变量
		roomMap = new HashMap<Integer, RoomApplication>();
		userList = new Vector<User>();
		userActivityRecord = new Hashtable<Long, Date>();
		hallChatMessageList = new Vector<HallChatMessage>();
		// 初始化扫描线程
		UserActivityMonitor.start();
	}

	public static int addRoom(RoomApplication room) {
		int roomId = WiswApplication.roomId++;
		room.setRoomId(roomId);
		WiswApplication.roomMap.put(roomId, room);
		return roomId;
	}

	public static void userAct(User user) {
		boolean containFlag = false;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getId().equals(user.getId())) {
				containFlag = true;
				break;
			}
		}
		if (!containFlag) {
			userList.add(user);
		}
		userActivityRecord.put(user.getId(), new Date());
	}
	
	public static void removeUser(long userId) {
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getId() == userId) {
				userList.remove(i);
				userActivityRecord.remove(userId);
				break;
			}
		}
	}
	
	public synchronized static Date addHallChatMessage(HallChatMessage message) {
		if(hallChatMessageList.size() == 200){
			hallChatMessageList.remove(0);
		}
		Date sendTime = new Date();
		message.setSendTime(sendTime);
		hallChatMessageList.add(message);
		return sendTime;
	}
	
	public static List<HallChatMessage> pullHallChatMessage(Date lastReceiveTime) {
		List<HallChatMessage> unreadMessageList = new ArrayList<HallChatMessage>();
		for (int i = hallChatMessageList.size() - 1; i >= 0; i--) {
			HallChatMessage message = hallChatMessageList.get(i);
			if (lastReceiveTime.before(message.getSendTime())) {
				unreadMessageList.add(message);
			} else {
				break;
			}
		}
		Collections.reverse(unreadMessageList);
		return unreadMessageList;
	}
	
}
