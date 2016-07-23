package com.shine.action;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.UserDao;
import com.shine.dao.impl.UserDaoImpl;
import com.shine.dto.User;

@SuppressWarnings("serial")
public class Action_ModifyUserPsw_All extends ActionSupport {
	private String username;
	private String usedPassword;
	private String newPassword;
	private String result;
	
	public String modifyPassword(){
		/** 首先判断当前用户的旧密码是否正确  **/
		UserDao userDao = new UserDaoImpl();
		List<User> users = userDao.getUserByUsername(username);
		String password = "";
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (users.size() > 0) {
			password = users.get(0).getPassword();
		}else {
			map.put("result", "systemError");
			result = JSONObject.toJSONString(map);
			return SUCCESS;
		}
		if (!password.equals(usedPassword)) {
			map.put("result", "usedPasswordError");
			result = JSONObject.toJSONString(map);
			return SUCCESS;
		}
		
		/** 当前用户密码判断正确,开始修改密码 **/
		users.get(0).setPassword(newPassword);
		User user = users.get(0);
		boolean modifyResult = userDao.modifyUser(user);
		if (modifyResult) {
			map.put("result", "success");
			result = JSONObject.toJSONString(map);
			return SUCCESS;
		}else {
			map.put("result", "modifyError");
			result = JSONObject.toJSONString(map);
			return SUCCESS;
		}
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsedPassword() {
		return usedPassword;
	}
	public void setUsedPassword(String usedPassword) {
		this.usedPassword = usedPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
