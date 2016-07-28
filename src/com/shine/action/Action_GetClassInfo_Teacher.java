package com.shine.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.OpportunityDao;
import com.shine.dao.ShineClassDao;
import com.shine.dao.impl.OpportunityDaoImpl;
import com.shine.dao.impl.ShineClassDaoImpl;
import com.shine.dto.Opportunity;
import com.shine.dto.ShineClass;

@SuppressWarnings("serial")
public class Action_GetClassInfo_Teacher extends ActionSupport {
	private String classCode;
	private String result;
	
	public String getClassOfTheacher(){
		/** 查询班级详情  **/
		ShineClassDao shineClassDao = new ShineClassDaoImpl();
		List<ShineClass> shineClasses = shineClassDao.getClassByClassCode(classCode);
		List<HashMap<String, Object>> classMaps = new ArrayList<HashMap<String, Object>>();
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
			classMaps.add(map);
		}
		String classInfoJsonString = JSONArray.toJSONString(classMaps);
		
		/** 查询班级学员详细信息  **/
		OpportunityDao oppDao = new OpportunityDaoImpl();
		List<Opportunity> oppList =  oppDao.getOppByClassCode(classCode);
		List<HashMap<String, Object>> studentMaps = new ArrayList<HashMap<String, Object>>();
		for (Opportunity opportunity : oppList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", opportunity.getId());
			if ("".equals(opportunity.getName()) || opportunity.getName() == null) {
				map.put("name", "无");
			}else {
				map.put("name", opportunity.getName());
			}
			if ("".equals(opportunity.getEnglishName()) || opportunity.getEnglishName() == null) {
				map.put("englishName", "无");
			}else {
				map.put("englishName", opportunity.getEnglishName());
			}
			if ("".equals(opportunity.getParentName()) || opportunity.getParentName() == null) {
				map.put("parentName", "无");
			}else {
				map.put("parentName", opportunity.getParentName());
			}
			if ("".equals(opportunity.getContactTel1()) || opportunity.getContactTel1() == null) {
				map.put("contactTel1", "无");
			}else {
				map.put("contactTel1", opportunity.getContactTel1());
			}
			if ("".equals(opportunity.getContactTel2()) || opportunity.getContactTel2() == null) {
				map.put("contactTel2", "无");
			}else {
				map.put("contactTel2", opportunity.getContactTel2());
			}
			if ("".equals(opportunity.getAddress()) || opportunity.getAddress() == null) {
				map.put("address", "无");
			}else {
				map.put("address", opportunity.getAddress());
			}
			if ("".equals(opportunity.getSchool()) || opportunity.getSchool() == null) {
				map.put("school", "无");
			}else {
				map.put("school", opportunity.getSchool());
			}
			studentMaps.add(map);
		}
		String studentInfoJsonString = JSONArray.toJSONString(studentMaps);
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("classInfoJsonString", classInfoJsonString);
		returnMap.put("studentInfoJsonString", studentInfoJsonString);
		result = JSONObject.toJSONString(returnMap);
		return SUCCESS;
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
