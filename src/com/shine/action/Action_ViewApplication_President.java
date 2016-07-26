package com.shine.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.ApplicatioinDao;
import com.shine.dao.impl.ApplicationDaoImpl;
import com.shine.dto.Application;

@SuppressWarnings("serial")
public class Action_ViewApplication_President extends ActionSupport {
	private String beginDate;
	private String endDate;
	private String result;
	
	public String viewApplication() {
		Date begin;
		Date end;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if ("".equals(beginDate) || beginDate == null) {
				begin = null;
			}else {
				begin = sdf.parse(beginDate);
			}
			if ("".equals(endDate) || endDate == null) {
				end = null;
			}else {
				end = sdf.parse(endDate);
			}
			ApplicatioinDao applicatioinDao = new ApplicationDaoImpl();
			List<Application> applications = applicatioinDao.getApplicationByDate(begin, end);
			List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
			for (Application application : applications) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("id", String.valueOf(application.getId()));
				map.put("classCode", application.getClassCode());
				map.put("applyTime", sdf.format(application.getApplyTime()));
				map.put("applicant", application.getApplicant());
				if (application.getState() == 0) {
					map.put("state", "待审批");
				}else if (application.getState() == 1) {
					map.put("state", "审批通过");
				}else if (application.getState() == -1) {
					map.put("state", "审批未通过");
				}
				maps.add(map);
			}
			result = JSONArray.toJSONString(maps);
		} catch (Exception e) {
			System.out.println("查询申请异常：" + e.getMessage());
		}
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
