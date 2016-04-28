package com.shine.dao;

import java.util.List;

import com.shine.dto.Opportunity;

public interface OpportunityDao {
	/**
	 * 插入商机
	 * @param opportunity
	 * @return boolean 插入结果
	 */
	public boolean insertOpp(Opportunity opportunity);
	
	/**
	 * 获取未分配商机
	 * @return List<Opportunity> 未分配商机列表
	 */
	public List<Opportunity> getUnAssignOpp();
	
	/**
	 * 通过id更新商机跟进人
	 * @param id
	 * @return 更新结果
	 */
	public boolean updateOppFollowCSById(int id, String name);
	
	/**
	 * 通过id查询商机
	 * @param id
	 * @return 返回的商机
	 */
	public Opportunity getOppById(int id);
}