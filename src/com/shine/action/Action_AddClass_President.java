package com.shine.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.ShineClassDao;
import com.shine.dao.impl.ShineClassDaoImpl;
import com.shine.dto.ShineClass;

@SuppressWarnings("serial")
public class Action_AddClass_President extends ActionSupport {
	private String level;
	private String classCode;
	private String beginDate;
	private String endDate;
	private String beginWeek;
	private String beginTime;
	private String foreignTeacher;
	private String chinaTeacher;
	private String classManager;
	private String result;
	
	public String addClass(){
		ShineClass shineClass = new ShineClass();
		shineClass.setLevel(level);
		shineClass.setClassCode(classCode);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (!"".equals(beginDate) && beginDate != null) {
			try {
				shineClass.setBeginDate(sdf.parse(beginDate));
			} catch (ParseException e) {
				System.out.println("转换开课日期失败：" + e.getMessage());
			}
		}
		if (!"".equals(endDate) && endDate != null) {
			try {
				shineClass.setEndDate(sdf.parse(endDate));
			} catch (ParseException e) {
				System.out.println("转换结课日期失败：" + e.getMessage());
			}
		}
		shineClass.setBeginWeek(beginWeek);
		shineClass.setBeginTime(beginTime);
		shineClass.setForeignTeacher(foreignTeacher);
		shineClass.setChinaTeacher(chinaTeacher);
		shineClass.setClassManager(classManager);
		ShineClassDao shineClassDao = new ShineClassDaoImpl();
		boolean insertResult = shineClassDao.insertClass(shineClass);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (insertResult) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		result = JSONObject.toJSONString(map);
		return SUCCESS;
	}

	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getBeginWeek() {
		return beginWeek;
	}
	public void setBeginWeek(String beginWeek) {
		this.beginWeek = beginWeek;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getForeignTeacher() {
		return foreignTeacher;
	}
	public void setForeignTeacher(String foreignTeacher) {
		this.foreignTeacher = foreignTeacher;
	}
	public String getChinaTeacher() {
		return chinaTeacher;
	}
	public void setChinaTeacher(String chinaTeacher) {
		this.chinaTeacher = chinaTeacher;
	}
	public String getClassManager() {
		return classManager;
	}
	public void setClassManager(String classManager) {
		this.classManager = classManager;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
