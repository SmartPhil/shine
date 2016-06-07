package com.shine.action;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.OpportunityDao;
import com.shine.dao.impl.OpportunityDaoImpl;

@SuppressWarnings("serial")
public class Action_MarkToDeal_Channel extends ActionSupport {
	private String id;
	private String classCode;
	private String result;
	
	public String deal() {
		OpportunityDao opportunityDao = new OpportunityDaoImpl();
		boolean markResult = false;
		if (id != null && !"".equals(id)) {
			markResult = opportunityDao.markToDeal(Integer.valueOf(id), classCode);
		}else {
			markResult = false;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (markResult) {
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
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
}
