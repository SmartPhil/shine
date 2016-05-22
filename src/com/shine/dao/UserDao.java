package com.shine.dao;

import java.util.List;

import com.shine.dto.User;

public interface UserDao {
	/**
	 * 通过用户名以及密码查询用户User
	 * @param username
	 * @param password
	 * @return User
	 */
	public User getUser(String username, String password);
	
	/**
	 * 获取所有客服用户
	 * @return List<User>
	 */
	public List<User> getCSUser();
	
	/**
	 * 通过id获取用户
	 * @param id
	 * @return User
	 */
	public User getUserById(int id);
	
	/**
	 * 通过用户名和角色查询用户
	 * @param username
	 * @param role
	 * @return List<User>
	 */
	public List<User> getUserByUserNameAndRole(String username, int role);
}
