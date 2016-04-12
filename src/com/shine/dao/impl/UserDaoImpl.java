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
		// TODO Auto-generated method stub
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
			System.out.println("≤È—Ø”√ªß ß∞‹£∫" + e.getMessage());
			transaction.rollback();
			session.close();
			return null;
		}
	}

}
