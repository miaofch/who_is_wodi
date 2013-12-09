package com.coolb.wisw.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.coolb.wisw.dao.BaseDao4Ibatis;
import com.coolb.wisw.dao.UserDao;
import com.coolb.wisw.model.pojo.User;

@Repository
public class UserDaoImpl extends BaseDao4Ibatis implements UserDao {

	private static String namespace = "com.coolb.wisw.model.User.";
	
	public List<User> queryUserByUsernameAndPassword(String username, String password) {
		Map<String, Object>	params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);
		return queryForList(namespace + "queryUserByUsernameAndPassword", params);
	}

	public long queryUserCountByNickname(String nickname) {
		return (Long) queryForObject(namespace + "queryUserCountByNickname", nickname);
	}

	public long queryUserCountByUsername(String username) {
		return (Long) queryForObject(namespace + "queryUserCountByUsername", username);
	}

	public void insertUser(User user) {
		insert(namespace + "insertUser", user);
	}

}
