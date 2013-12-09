package com.coolb.wisw.service;

import java.util.List;

import com.coolb.wisw.model.pojo.User;

public interface UserSerivce {

	public List<User> queryUserByUsernameAndPassword(String username, String password);

	public long queryUserCountByUsername(String username);

	public long queryUserCountByNickname(String nickname);

	public void insertUser(User user);
	
}
