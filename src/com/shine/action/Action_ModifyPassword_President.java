package com.shine.action;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.UserDao;
import com.shine.dao.impl.UserDaoImpl;
import com.shine.dto.User;

@SuppressWarnings("serial")
public class Action_ModifyPassword_President extends ActionSupport {
	private String id;
	private String originalPassword;
	private String newPassword;
	private String result;
	
	public String modify(){
		//首先通过id查出需要修改密码的用户
		System.out.println(id);
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUserById(Integer.valueOf(id));
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (user.getPassword().equals(originalPassword)) {
			user.setPassword(newPassword);
			boolean modifyResult = userDao.modifyUser(user);
			if (modifyResult) {
				map.put("result", "success");
			}else {
				map.put("result", "fail");
			}
		}else {
			map.put("result", "originalPswNotCorrect");
		}
		result = JSONObject.toJSONString(map);
		return SUCCESS;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOriginalPassword() {
		return originalPassword;
	}
	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
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
