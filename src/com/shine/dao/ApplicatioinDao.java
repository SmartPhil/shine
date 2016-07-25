package com.shine.dao;

import java.util.Date;
import java.util.List;

import com.shine.dto.Application;

public interface ApplicatioinDao {
	/**
	 * 插入新的申请
	 * @param application
	 * @return 添加新申请的结果
	 */
	public boolean insertApplication(Application application);
	
	/**
	 * 通过申请的日期和申请人查询申请的记录
	 * @param begin
	 * @param end
	 * @param username
	 * @return List<Application>
	 */
	public List<Application> getApplicationByDateAndApplicant(Date begin, Date end, String username);
}
