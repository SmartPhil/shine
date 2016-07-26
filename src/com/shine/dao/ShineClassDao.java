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
	
	/**
	 * 通过教师用户名查询班级
	 * @param teacherName
	 * @return List<ShineClass> 班级信息列表
	 */
	public List<ShineClass> getClassByTeacher(String teacherName);
	
	/**
	 * 通过班级编码查询
	 * @param classCode
	 * @return List<ShineClass>
	 */
	public List<ShineClass> getClassByClassCode(String classCode);
	
	/**
	 * 更新班级信息
	 * @param shineClass
	 * @return boolean 更新结果
	 */
	public boolean update(ShineClass shineClass);
}
