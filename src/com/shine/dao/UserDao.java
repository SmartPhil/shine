package com.shine.dao;

import com.shine.dto.User;

public interface UserDao {
	/**
	 * ͨ���û����Լ���������û�
	 * @param username
	 * @param password
	 * @return User
	 */
	public User getUser(String username, String password);
}
