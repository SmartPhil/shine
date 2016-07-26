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
	
	/**
	 * 通过申请的日期查询申请记录
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Application> getApplicationByDate(Date begin, Date end);
	
	/**
	 * 通过id查询申请记录
	 * @param id
	 * @return List<Application>
	 */
	public List<Application> getApplicationById(int id);
	
	/**
	 * 更新申请记录
	 * @param application
	 * @return boolean 更新结果
	 */
	public boolean updateApplication(Application application);
}
