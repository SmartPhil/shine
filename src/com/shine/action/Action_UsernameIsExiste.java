package com.shine.action;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.UserDao;
import com.shine.dao.impl.UserDaoImpl;
import com.shine.dto.User;

@SuppressWarnings("serial")
public class Action_UsernameIsExiste extends ActionSupport {
	private String username;
	private String result;
	
	public String isExiste(){
		UserDao userDao = new UserDaoImpl();
		List<User> users = userDao.getUserByUsername(username);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (users.size() == 0) {
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
