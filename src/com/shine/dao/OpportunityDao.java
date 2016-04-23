package com.shine.dao;

import java.util.List;

import com.shine.dto.Opportunity;

public interface OpportunityDao {
	/**
	 * 插入新的商机
	 * @param opportunity
	 * @return boolean 是否插入成功
	 */
	public boolean insertOpp(Opportunity opportunity);
	
	/**
	 * 获取所有未分配的商机
	 * @return List<Opportunity> 商机列表
	 */
	public List<Opportunity> getUnAssignOpp();
}
