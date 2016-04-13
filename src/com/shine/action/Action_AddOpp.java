package com.shine.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.OpportunityDao;
import com.shine.dao.impl.OpportunityDaoImpl;
import com.shine.dto.Opportunity;

public class Action_AddOpp extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String name;
	private String englishName;
	private String parentName;
	private String contactTel1;
	private String contactTel2;
	private String email;
	private String orderTime;
	private String gender;
	private String age;
	private String birthday;
	private String school;
	private String address;
	private String source;
	private String giveOrg;
	private String result;
	
	public String add() {
		Opportunity opportunity = new Opportunity();
		Date now = new Date();
		int nGender = 0;
		if("1".equals(gender) || "2".equals(gender)){
			nGender = Integer.parseInt(gender);
		}
		int nAge = 0;
		if(!"".equals(age)) {
			nAge = Integer.parseInt(age);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dtOrderTime = new Date();
		if(!"".equals(orderTime)) {
			try {
				dtOrderTime = sdf.parse(orderTime);
			} catch (Exception e) {
				System.out.println("转换预约时间失败：" + e.getMessage());
				//插入失败
			}
		}
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dtBirthday = new Date();
		if(!"".equals(birthday)) {
			try {
				dtBirthday = sdf1.parse(birthday);
			} catch (Exception e) {
				System.out.println("转换生日失败：" + e.getMessage());
				//插入失败
			}
		}
		
		opportunity.setCreateTime(now);
		opportunity.setName(name);
		opportunity.setGender(nGender);
		opportunity.setAge(nAge);
		opportunity.setContactTel1(contactTel1);
		opportunity.setContactTel2(contactTel2);
		opportunity.setSchool(school);
		opportunity.setParentName(parentName);
		opportunity.setAddress(address);
		opportunity.setEmail(email);
		opportunity.setSource(source);
		opportunity.setOrderTime(dtOrderTime);
		opportunity.setIsArrive(0);
		opportunity.setIsDeal(0);
		opportunity.setEnglishName(englishName);
		opportunity.setBirthday(dtBirthday);
		
		OpportunityDao oppDao = new OpportunityDaoImpl();
		boolean insertResult = oppDao.insertOpp(opportunity);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (insertResult) {
			//插入成功
			map.put("result", "success");
		}else {
			//插入失败
			map.put("result", "fail");
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
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getContactTel1() {
		return contactTel1;
	}
	public void setContactTel1(String contactTel1) {
		this.contactTel1 = contactTel1;
	}
	public String getContactTel2() {
		return contactTel2;
	}
	public void setContactTel2(String contactTel2) {
		this.contactTel2 = contactTel2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getGiveOrg() {
		return giveOrg;
	}
	public void setGiveOrg(String giveOrg) {
		this.giveOrg = giveOrg;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
