package com.shine.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shine.dao.UserDao;
import com.shine.dto.User;
import com.shine.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public User getUser(String username, String password) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "from User where username = ? and password = ?";
			Query query = session.createQuery(hql);
			query.setString(0, username);
			query.setString(1, password);
			@SuppressWarnings("unchecked")
			List<User> users = (ArrayList<User>)query.list();
			transaction.commit();
			session.close();
			if(users.size() > 0){
				return users.get(0);
			}else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("通过用户名以及密码查询用户失败：" + e.getMessage());
			transaction.rollback();
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getCSUser() {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "from User where role = 4";
			Query query = session.createQuery(hql);
			List<User> users = (ArrayList<User>)query.list();
			transaction.commit();
			session.close();
			return users;
		} catch (Exception e) {
			System.out.println("查询所有客服用户失败：" + e.getMessage());
			transaction.rollback();
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserById(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "from User where id = ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, id);
			List<User> users = (ArrayList<User>)query.list();
			transaction.commit();
			session.close();
			if (users.size() >= 0) {
				return users.get(0);
			}else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("通过id查询用户失败：" + e.getMessage());
			transaction.rollback();
			session.close();
			return null;
		}
	}
}
