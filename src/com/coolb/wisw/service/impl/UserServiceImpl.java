package com.coolb.wisw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolb.wisw.dao.TestDao;
import com.coolb.wisw.dao.UserDao;
import com.coolb.wisw.model.pojo.User;
import com.coolb.wisw.service.UserSerivce;

@Service
public class UserServiceImpl implements UserSerivce {
	
	@Autowired
	private UserDao userDao;

	public List<User> queryUserByUsernameAndPassword(String username, String password) {
		return userDao.queryUserByUsernameAndPassword(username, password);
	}

	public long queryUserCountByNickname(String nickname) {
		return userDao.queryUserCountByNickname(nickname);
	}

	public long queryUserCountByUsername(String username) {
		return userDao.queryUserCountByUsername(username);
	}

	public void insertUser(User user) {
		userDao.insertUser(user);
	}

}
