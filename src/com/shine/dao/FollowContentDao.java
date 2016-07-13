package com.shine.dao;

import java.util.List;

import com.shine.dto.FollowContent;

public interface FollowContentDao {
	/**
	 * 插入新的跟进内容
	 * @param FollowContent实例
	 * @return 插入结果
	 */
	public boolean insertFollowContent(FollowContent followContent);
	
	/**
	 * 通过商机id查询跟进内容
	 * @param 商机id
	 * @return List<FollowContent>
	 */
	public List<FollowContent> getFollowContentById(int id);
	
	/**
	 * 查询某条商机的最近一次跟进记录
	 * @param id
	 * @return FollowContent
	 */
	public FollowContent getLatestFollowContentByOppId(int id);
	
	/**
	 * 查询某个客服所负责的某条商机的总跟进记录次数
	 * @param id
	 * @return integer 总跟进次数
	 */
	public Integer getFollowContentCountByOppId(int id, String csName);
}
