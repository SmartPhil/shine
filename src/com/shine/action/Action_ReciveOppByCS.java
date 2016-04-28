package com.shine.action;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.OpportunityDao;
import com.shine.dao.impl.OpportunityDaoImpl;
import com.shine.dto.Opportunity;

@SuppressWarnings("serial")
public class Action_ReciveOppByCS extends ActionSupport {
	private String name;
	private String id;
	private String result;
	
	public String recive(){
		OpportunityDao oppDao = new OpportunityDaoImpl();
		int ID = 0;
		if (!"".equals(id) && id != null) {
			ID = Integer.parseInt(id);
		}
		HashMap<String, Object> map = new HashMap<String,Object>();
		//首先判断此商机是否已经被接取
		Opportunity opportunity = oppDao.getOppById(ID);
		if (opportunity.getIsAssign() == 0) {
			boolean result = oppDao.updateOppFollowCSById(ID, name);
			if (result) {
				map.put("result", "success");
			}else {
				map.put("result", "fail");
			}
		}else {
			map.put("result", "recived");
		}
		result = JSONObject.toJSONString(map);
		return SUCCESS;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
