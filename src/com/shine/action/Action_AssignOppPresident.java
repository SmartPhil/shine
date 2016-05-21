package com.shine.action;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.OpportunityDao;
import com.shine.dao.UserDao;
import com.shine.dao.impl.OpportunityDaoImpl;
import com.shine.dao.impl.UserDaoImpl;
import com.shine.dto.User;

@SuppressWarnings("serial")
public class Action_AssignOppPresident extends ActionSupport {
	private String oppId;
	private String csId;
	private String result;
	
	public String assign(){
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUserById(Integer.valueOf(csId));
		OpportunityDao opportunityDao = new OpportunityDaoImpl();
		boolean assignResult = opportunityDao.assignOpp(Integer.valueOf(oppId), user.getUsername());
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (assignResult) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		result = JSONObject.toJSONString(map);
		return SUCCESS;
	}
	
	public String getOppId() {
		return oppId;
	}
	public void setOppId(String oppId) {
		this.oppId = oppId;
	}
	public String getCsId() {
		return csId;
	}
	public void setCsId(String csId) {
		this.csId = csId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}	
