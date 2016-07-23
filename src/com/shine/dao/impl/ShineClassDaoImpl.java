package com.shine.dao.impl;

import java.util.List;

import org.hibernate.Query;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ShineClass> selectClass() {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "from ShineClass";
			Query query = session.createQuery(hql);
			List<ShineClass> shineClasses = (List<ShineClass>)query.list();
			transaction.commit();
			session.close();
			return shineClasses;
		} catch (Exception e) {
			transaction.rollback();
			session.close();
			System.out.println("查询所有班级失败：" + e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShineClass> getClassByTeacher(String teacherName) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "from ShineClass where foreignTeacher = ? or chinaTeacher = ?";
			Query query = session.createQuery(hql);
			query.setString(0, teacherName);
			query.setString(1, teacherName);
			List<ShineClass> shineClasses = (List<ShineClass>)query.list();
			transaction.commit();
			session.close();
			return shineClasses;
		} catch (Exception e) {
			transaction.rollback();
			session.close();
			System.out.println("按照教师姓名查询班级失败：" + e.getMessage());
			return null;
		}
	}

}
