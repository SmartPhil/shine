package com.shine.action;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.UserDao;
import com.shine.dao.impl.UserDaoImpl;
import com.shine.dto.User;

public class Action_Login extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String result;
	private Map<String, Object> session;
	
	public String login(){
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUser(username, password);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(user == null){
			map.put("loginResult", "fail");
		}else {
			map.put("loginResult", "success");
			map.put("role", user.getRole());
			session = ActionContext.getContext().getSession();
			session.put("username", user.getUsername());
		}
		result = JSON.toJSONString(map);
		System.out.println(result);
		return SUCCESS;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
