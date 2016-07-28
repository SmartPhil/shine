package com.shine.dto;

import java.util.Date;

public class ShineClass {
	private int id;
	private String level;   //级别
	private String classCode; //班级编码
	private Date beginDate; //开课日期
	private Date endDate;   //结课日期
	private String beginWeek; //上课周期
	private String beginTime;  //上课时间
	private String foreignTeacher; //外教
	private String chinaTeacher;   //中教
	private String classManager;   //班主任
	private int currentNum;     //当前人数
	private float fee;             //学费
	private int state;          //当前班级状态；0--待审批；1--审批通过;-1--审批未通过；
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @return the classCode
	 */
	public String getClassCode() {
		return classCode;
	}
	/**
	 * @param classCode the classCode to set
	 */
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the beginWeek
	 */
	public String getBeginWeek() {
		return beginWeek;
	}
	/**
	 * @param beginWeek the beginWeek to set
	 */
	public void setBeginWeek(String beginWeek) {
		this.beginWeek = beginWeek;
	}
	/**
	 * @return the beginTime
	 */
	public String getBeginTime() {
		return beginTime;
	}
	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * @return the foreignTeacher
	 */
	public String getForeignTeacher() {
		return foreignTeacher;
	}
	/**
	 * @param foreignTeacher the foreignTeacher to set
	 */
	public void setForeignTeacher(String foreignTeacher) {
		this.foreignTeacher = foreignTeacher;
	}
	/**
	 * @return the chinaTeacher
	 */
	public String getChinaTeacher() {
		return chinaTeacher;
	}
	/**
	 * @param chinaTeacher the chinaTeacher to set
	 */
	public void setChinaTeacher(String chinaTeacher) {
		this.chinaTeacher = chinaTeacher;
	}
	/**
	 * @return the classManager
	 */
	public String getClassManager() {
		return classManager;
	}
	/**
	 * @param classManager the classManager to set
	 */
	public void setClassManager(String classManager) {
		this.classManager = classManager;
	}
	public int getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
