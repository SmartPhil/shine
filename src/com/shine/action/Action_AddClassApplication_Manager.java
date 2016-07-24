package com.shine.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.ApplicatioinDao;
import com.shine.dao.ShineClassDao;
import com.shine.dao.impl.ApplicationDaoImpl;
import com.shine.dao.impl.ShineClassDaoImpl;
import com.shine.dto.Application;
import com.shine.dto.ShineClass;

@SuppressWarnings("serial")
public class Action_AddClassApplication_Manager extends ActionSupport {
	private String level;
	private String classCode;
	private String beginDate;
	private String endDate;
	private String beginWeek;
	private String beginTime;
	private String foreignTeacher;
	private String chinaTeacher;
	private String classManager;
	private String fee;
	private String applicant;
	private String result;
	
	public String addClass(){
		System.out.println(applicant);
		
		/** 处理提交添加班级申请业务 **/
		/** 首先在shineClass表中添加一个状态为0（待审批）的班级 **/
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
		if (!"".equals(fee) && fee != null) {
			shineClass.setFee(Float.valueOf(fee));
		}
		shineClass.setState(0);
		ShineClassDao shineClassDao = new ShineClassDaoImpl();
		boolean insertResult = shineClassDao.insertClass(shineClass);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (!insertResult) {
			map.put("result", "addClassFail");
			result = JSONObject.toJSONString(map);
			return SUCCESS;
		}
		/** 在申请表中添加一条新的申请 **/
		Application application = new Application();
		application.setApplicant(applicant);
		application.setApplyTime(new Date());
		application.setClassCode(classCode);
		application.setState(0);
		ApplicatioinDao applicatioinDao = new ApplicationDaoImpl();
		boolean insertApplyResult = applicatioinDao.insertApplication(application);
		if (insertApplyResult) {
			map.put("result", "success");
		}else {
			map.put("result", "addApplicationFail");
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
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
}
