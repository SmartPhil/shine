package com.shine.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shine.dao.ShineClassDao;
import com.shine.dto.ShineClass;
import com.shine.util.HibernateUtil;

public class ShineClassDaoImpl implements ShineClassDao {

	@Override
	public boolean insertClass(ShineClass shineClass) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Integer result = (Integer)session.save(shineClass);
			boolean insertResult = false;
			if (result > 0) {
				insertResult = true;
				transaction.commit();
				session.close();
			}else {
				insertResult = false;
				transaction.rollback();
				session.close();
			}
			return insertResult;
		} catch (Exception e) {
			transaction.rollback();
			session.close();
			System.out.println("插入新班级失败：" + e.getMessage());
			return false;
		}
	}

}
