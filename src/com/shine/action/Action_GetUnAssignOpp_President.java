package com.shine.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.OpportunityDao;
import com.shine.dao.impl.OpportunityDaoImpl;
import com.shine.dto.Opportunity;

@SuppressWarnings("serial")
public class Action_GetUnAssignOpp_President extends ActionSupport {
	private List<Opportunity> oppList;
	
	public String get(){
		OpportunityDao oppDao = new OpportunityDaoImpl();
		oppList = oppDao.getUnAssignOpp();
		return SUCCESS;
	}

	public List<Opportunity> getOppList() {
		return oppList;
	}
	public void setOppList(List<Opportunity> oppList) {
		this.oppList = oppList;
	}
}
