package com.coolb.wisw.scope;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import com.coolb.wisw.model.bo.RoomChatMessage;
import com.coolb.wisw.model.bo.Testimony;
import com.coolb.wisw.model.codetype.GameRole;
import com.coolb.wisw.model.pojo.User;
import com.coolb.wisw.model.pojo.WordSet;

public class RoomApplication {
	
	private int roomId;
	
	private int status;
	
	private List<Testimony> testimonyList;
	
	private List<RoomChatMessage> roomChatMessageList;
	
	private Map<User, GameRole> userRoleMap;
	
	private List<User> playerList;
	
	private User judge;
	
	private WordSet wordSet;
	
	private Timer chairman;
	
	public RoomApplication(int roomId) {
		this.roomId = roomId;
		status = 0;
		testimonyList = new Vector<Testimony>();
		roomChatMessageList = new Vector<RoomChatMessage>();
		userRoleMap = new Hashtable<User, GameRole>();
		playerList = new Vector<User>();
		chairman = getChairman();
	}
	
	private Timer getChairman() {
		Timer chairman = new Timer();
		chairman.schedule(new RoomChairmanTask(), 0, 500);
		return chairman;
	}
	
	private class RoomChairmanTask extends TimerTask {

		@Override
		public void run() {
			switch (status) {
			case 0:
				
				break;

			default:
				break;
			}
		}
		
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Testimony> getTestimonyList() {
		return testimonyList;
	}

	public void setTestimonyList(List<Testimony> testimonyList) {
		this.testimonyList = testimonyList;
	}

	public List<RoomChatMessage> getRoomChatMessageList() {
		return roomChatMessageList;
	}

	public void setRoomChatMessageList(List<RoomChatMessage> roomChatMessageList) {
		this.roomChatMessageList = roomChatMessageList;
	}

	public Map<User, GameRole> getUserRoleMap() {
		return userRoleMap;
	}

	public void setUserRoleMap(Map<User, GameRole> userRoleMap) {
		this.userRoleMap = userRoleMap;
	}

	public List<User> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<User> playerList) {
		this.playerList = playerList;
	}

	public User getJudge() {
		return judge;
	}

	public void setJudge(User judge) {
		this.judge = judge;
	}

	public WordSet getWordSet() {
		return wordSet;
	}

	public void setWordSet(WordSet wordSet) {
		this.wordSet = wordSet;
	}

	public void setChairman(Timer chairman) {
		this.chairman = chairman;
	}

}
