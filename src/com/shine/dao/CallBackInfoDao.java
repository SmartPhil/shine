package com.shine.dao;

import com.shine.dto.CallBackInfo;

public interface CallBackInfoDao {
	/**
	 * @description 插入教师回访学员记录
	 * @param callBackInfo
	 * @author Phil Guan 
	 * @return Boolean 插入结果
	 */
	public boolean insert(CallBackInfo callBackInfo);
}
