package com.shine.action;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.UserDao;
import com.shine.dao.impl.UserDaoImpl;
import com.shine.dto.User;

@SuppressWarnings("serial")
public class Action_AddUser_President extends ActionSupport {
	private String username;
	private String role;
	private String result;
	public String addUser(){
		UserDao userDao = new UserDaoImpl();
		List<User> users = userDao.getUserByUsername(username);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (users.size() != 0) {
			map.put("result", "had");
			return SUCCESS;
		}
		User user = new User();
		user.setUsername(username);
		user.setPassword("123456");
		user.setRole(Integer.valueOf(role));
		boolean insertResult = userDao.insertUser(user);
		if (insertResult) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		result = JSONObject.toJSONString(map);
		return SUCCESS;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
