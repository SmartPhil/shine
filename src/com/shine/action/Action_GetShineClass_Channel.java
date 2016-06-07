package com.shine.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.ShineClassDao;
import com.shine.dao.impl.ShineClassDaoImpl;
import com.shine.dto.ShineClass;
import com.shine.util.ShineClassUtil;

@SuppressWarnings("serial")
public class Action_GetShineClass_Channel extends ActionSupport {
	private String result;
	
	public String getShineClass() {
		ShineClassDao shineClassDao = new ShineClassDaoImpl();
		List<ShineClass> shineClasses = shineClassDao.selectClass();
		result = ShineClassUtil.ListToJsonString(shineClasses);
		return SUCCESS;
	}

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
