package com.shine.action;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.OpportunityDao;
import com.shine.dao.ShineClassDao;
import com.shine.dao.impl.OpportunityDaoImpl;
import com.shine.dao.impl.ShineClassDaoImpl;
import com.shine.dto.ShineClass;

@SuppressWarnings("serial")
public class Action_MarkToDeal_Channel extends ActionSupport {
	private String id;
	private String classCode;
	private String result;
	
	public String deal() {
		/**  将当前学生标记为已成单   **/
		OpportunityDao opportunityDao = new OpportunityDaoImpl();
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id != null && !"".equals(id)) {
			boolean markResult = opportunityDao.markToDeal(Integer.valueOf(id), classCode);
			if (markResult) {
				/** 如果标记成单成功，则为班级人数加1 **/
				ShineClassDao shineClassDao = new ShineClassDaoImpl();
				ShineClass shineClass = shineClassDao.getClassByClassCode(classCode).get(0);
				shineClass.setCurrentNum(shineClass.getCurrentNum() + 1);
				boolean setNumResult = shineClassDao.update(shineClass);
				if (setNumResult) {
					map.put("result", SUCCESS);
				}else {
					map.put("result", ERROR);
				}
			}else {
				map.put("result", ERROR);
			}
		}else {
			map.put("result", ERROR);
		}
		result = JSONObject.toJSONString(map);
		return SUCCESS;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
