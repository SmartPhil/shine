package com.shine.action;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.UserDao;
import com.shine.dao.impl.UserDaoImpl;

@SuppressWarnings("serial")
public class Action_DeleteUser_President extends ActionSupport {
	private String id;
	private String result;
	
	public String delete(){
		int idInt = Integer.valueOf(id);
		UserDao userDao = new UserDaoImpl();
		boolean deleteResult = userDao.deleteUser(idInt);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (deleteResult) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
