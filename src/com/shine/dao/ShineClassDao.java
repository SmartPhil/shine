package com.shine.dao;

import com.shine.dto.ShineClass;

public interface ShineClassDao {
	/**
	 * 插入新班级
	 * @param shineClass
	 * @return 插入结果
	 */
	public boolean insertClass(ShineClass shineClass);
}
