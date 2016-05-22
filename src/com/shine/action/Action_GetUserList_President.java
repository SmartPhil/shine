package com.shine.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.UserDao;
import com.shine.dao.impl.UserDaoImpl;
import com.shine.dto.User;

@SuppressWarnings("serial")
public class Action_GetUserList_President extends ActionSupport {
	private String username;
	private String role;
	private String result;
	
	public String getUser(){
		UserDao userDao = new UserDaoImpl();
		List<User> users = userDao.getUserByUserNameAndRole(username, Integer.valueOf(role));
		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
		for (User user : users) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", String.valueOf(user.getId()));
			map.put("username", user.getUsername());
			if (user.getRole() == 1) {
				map.put("role", "校长");
			}else if (user.getRole() == 2) {
				map.put("role", "教师主管");
			}else if (user.getRole() == 3) {
				map.put("role", "行政人员");
			}else if (user.getRole() == 4) {
				map.put("role", "客服人员");
			}else if (user.getRole() == 5) {
				map.put("role", "教师");
			}
			maps.add(map);
		}
		result = JSONArray.toJSONString(maps);
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
