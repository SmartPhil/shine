package com.shine.dao;

import com.shine.dto.User;

public interface UserDao {
	/**
	 * 通过用户名以及密码查询用户User
	 * @param username
	 * @param password
	 * @return User
	 */
	public User getUser(String username, String password);
}
