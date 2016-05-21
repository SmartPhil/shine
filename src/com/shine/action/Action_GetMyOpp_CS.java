package com.shine.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.OpportunityDao;
import com.shine.dao.impl.OpportunityDaoImpl;
import com.shine.dto.Opportunity;

@SuppressWarnings("serial")
public class Action_GetMyOpp_CS extends ActionSupport {
	private String username;
	private String result;
	
	public String getMyOpp() {
		OpportunityDao oppDao = new OpportunityDaoImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Opportunity> oppList = oppDao.getOppByCS(username);
		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
		for (Opportunity opportunity : oppList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", opportunity.getId());
			map.put("stuName", opportunity.getName());
			map.put("parentName", opportunity.getParentName());
			map.put("contactTel1", opportunity.getContactTel1());
			map.put("contactTel2", opportunity.getContactTel2());
			map.put("address", opportunity.getAddress());
			if (opportunity.getOrderTime() != null) {
				map.put("orderTime", sdf.format(opportunity.getOrderTime()));
			}else {
				map.put("orderTime", "");
			}
			if (opportunity.getIsArrive() == 0) {
				map.put("isArrive", "未到店");
			}else if (opportunity.getIsArrive() == 1) {
				map.put("isArrive", "已到店");
			}
			if (opportunity.getArriveTime() != null) {
				map.put("arriveTime", sdf.format(opportunity.getArriveTime()));
			}else {
				map.put("arriveTime", "");
			}
			if (opportunity.getIsDeal() == 0) {
				map.put("isDeal", "未成单");
			}else if (opportunity.getIsDeal() == 1) {
				map.put("isDeal", "已成单");
			}
			maps.add(map);
		}
		result = JSONArray.toJSONString(maps);
		System.out.println(result);
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
