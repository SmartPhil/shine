package com.shine.dao;

import java.util.Date;
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
	
	/**
	 * 通过客服用户名查询当前客服所跟进的商机
	 * @param csName
	 * @return List<Opportunity>
	 */
	public List<Opportunity> getOppByCS(String csName);
	
	/**
	 * 客服释放商机
	 * @param oppId
	 * @return 释放结果
	 */
	public boolean releaseOpp(int oppId);
	
	/**
	 * 分配商机
	 * @param oppId 商机id
	 * @param CSName 客服用户名
	 * @return 分配结果
	 */
	public boolean assignOpp(int oppId, String CSName);
	
	/**
	 * 通过日期与联系方式查询商机
	 * @param begin
	 * @param end
	 * @param contact
	 * @return 查询结果
	 */
	public List<Opportunity> getOppByDateAndContact(Date begin,Date end,String contact);
	
	/**
	 * 通过商机的id标记为已成单
	 * @param id
	 * @return 标记结果boolean
	 */
	public boolean markToDeal(int id, String classCode);
	
	/**
	 * 通过班级编码查询学员
	 * @param classCode
	 * @return List<Opportunity>
	 */
	public List<Opportunity> getOppByClassCode(String classCode);
}
