package com.coolb.wisw.dao;

import java.util.List;

import com.coolb.wisw.model.pojo.User;

public interface UserDao {

	public List<User> queryUserByUsernameAndPassword(String username, String password);

	public long queryUserCountByNickname(String nickname);

	public long queryUserCountByUsername(String username);

	public void insertUser(User user);

}
