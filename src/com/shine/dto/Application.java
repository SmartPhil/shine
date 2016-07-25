package com.shine.dto;

import java.util.Date;

public class Application {
	private int id;  //主键
	private String classCode; //班级编码
	private Date applyTime;   //申请时间
	private String applicant; //申请人
	private int state;        //申请状态;0--待审批；1--审批通过；-1--审批未通过
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
