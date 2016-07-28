package com.shine.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.ShineClassDao;
import com.shine.dao.impl.ShineClassDaoImpl;
import com.shine.dto.ShineClass;

@SuppressWarnings("serial")
public class Action_GetClassOfTeacher_Teacher extends ActionSupport {
	private String username;
	private String result;
	
	public String getClassOfTheacher(){
		/** 查询班级详情  **/
		ShineClassDao shineClassDao = new ShineClassDaoImpl();
		List<ShineClass> shineClasses = shineClassDao.getClassByTeacher(username);
		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (ShineClass shineClass : shineClasses) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", shineClass.getId());
			map.put("level", shineClass.getLevel());
			map.put("classCode", shineClass.getClassCode());
			map.put("beginDate", sdf.format(shineClass.getBeginDate()));
			map.put("endDate", sdf.format(shineClass.getEndDate()));
			map.put("beginWeek", shineClass.getBeginWeek());
			map.put("beginTime", shineClass.getBeginTime());
			map.put("foreignTeacher", shineClass.getForeignTeacher());
			map.put("chinaTeacher", shineClass.getChinaTeacher());
			map.put("classManager", shineClass.getClassManager());
			map.put("currentNum", String.valueOf(shineClass.getCurrentNum()));
			map.put("fee", String.valueOf(shineClass.getFee()));
			maps.add(map);
		}
		result = JSONArray.toJSONString(maps);
		
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
