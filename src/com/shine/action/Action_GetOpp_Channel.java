package com.shine.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.OpportunityDao;
import com.shine.dao.impl.OpportunityDaoImpl;
import com.shine.dto.Opportunity;
import com.shine.util.OpportunityUtil;

@SuppressWarnings("serial")
public class Action_GetOpp_Channel extends ActionSupport {
	private String beginDate;
	private String endDate;
	private String contactTel;
	private String result;
	
	public String getOpp(){
		Date begin = null;
		Date end = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (!"".equals(beginDate) && beginDate != null) {
			try {
				begin = sdf.parse(beginDate);
			} catch (ParseException e) {
				System.out.println("转换起始日期失败：" + e.getMessage());
			}
		}
		if (!"".equals(endDate) && endDate != null) {
			try {
				end = sdf.parse(endDate);
			} catch (ParseException e) {
				System.out.println("转换截止日期失败：" + e.getMessage());
			}
		}
		OpportunityDao opportunityDao = new OpportunityDaoImpl();
		List<Opportunity> oppList = opportunityDao.getOppByDateAndContact(begin, end, contactTel);
		result = OpportunityUtil.ListToJsonString(oppList);
		System.out.println(result);
		return SUCCESS;
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
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
