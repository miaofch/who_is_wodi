package com.coolb.wisw.dao;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

public abstract class BaseDao4Ibatis extends SqlMapClientTemplate {

	@Resource(name = "sqlMapClient")
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
}
