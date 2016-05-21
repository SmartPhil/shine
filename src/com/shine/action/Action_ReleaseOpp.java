package com.shine.action;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.OpportunityDao;
import com.shine.dao.impl.OpportunityDaoImpl;

@SuppressWarnings("serial")
public class Action_ReleaseOpp extends ActionSupport {
	private String oppId;
	private String result;
	
	public String releaseOpp(){
		OpportunityDao opportunityDao = new OpportunityDaoImpl();
		boolean releaseResult = opportunityDao.releaseOpp(Integer.valueOf(oppId));
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (releaseResult) {
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
