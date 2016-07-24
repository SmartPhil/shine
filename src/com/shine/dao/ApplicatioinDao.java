package com.shine.dao;

import com.shine.dto.Application;

public interface ApplicatioinDao {
	/**
	 * 插入新的申请
	 * @param application
	 * @return 添加新申请的结果
	 */
	public boolean insertApplication(Application application);
}
