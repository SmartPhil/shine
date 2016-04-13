package com.shine.dao;

import com.shine.dto.Opportunity;

public interface OpportunityDao {
	/**
	 * 插入新的商机
	 * @param opportunity
	 * @return boolean 是否插入成功
	 */
	public boolean insertOpp(Opportunity opportunity);
}
