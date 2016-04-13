package com.shine.dto;

import java.util.Date;

public class Opportunity {
	private int id;                         //索引
	private Date createTime;                //创建时间
	private String name;					//学员姓名
	private String englishName; 			//英文姓名
	private int gender;						//性别
	private int age;						//年龄
	private String school;					//学校
	private String contactTel1;				//联系方式1
	private String contactTel2;				//联系方式2
	private String email;					//email地址
	private String parentName;				//父母姓名
	private String address;					//家庭住址
	private String source;					//商机来源
	private Date orderTime;					//预约时间
	private int isArrive;					//是否到店
	private Date arriveTime;				//到店时间
	private int isDeal;						//是否成单
	private Date birthday;					//生日
	private String followCS;				//跟进人
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
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
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
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
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public int getIsArrive() {
		return isArrive;
	}
	public void setIsArrive(int isArrive) {
		this.isArrive = isArrive;
	}
	public Date getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getFollowCS() {
		return followCS;
	}
	public void setFollowCS(String followCS) {
		this.followCS = followCS;
	}
	public int getIsDeal() {
		return isDeal;
	}
	public void setIsDeal(int isDeal) {
		this.isDeal = isDeal;
	}
}
