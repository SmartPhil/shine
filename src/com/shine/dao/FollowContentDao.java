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
}
