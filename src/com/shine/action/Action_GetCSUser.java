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
public class Action_GetCSUser extends ActionSupport {
	private String result;
	
	public String getCSUser(){
		UserDao userDao = new UserDaoImpl();
		List<User> users = userDao.getCSUser();
		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
		for (User user : users) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getId());
			map.put("name", user.getUsername());
			maps.add(map);
		}
		result = JSONArray.toJSONString(maps);
		return SUCCESS;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
