package com.shine.dao;

import java.util.List;

import com.shine.dto.ShineClass;

public interface ShineClassDao {
	/**
	 * 插入新班级
	 * @param shineClass
	 * @return 插入结果
	 */
	public boolean insertClass(ShineClass shineClass);
	
	/**
	 * 查询所有班级
	 * @return List<ShineClass>
	 */
	public List<ShineClass> selectClass();
}
