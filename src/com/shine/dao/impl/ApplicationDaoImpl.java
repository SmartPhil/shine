package com.shine.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shine.dao.ApplicatioinDao;
import com.shine.dto.Application;
import com.shine.util.HibernateUtil;

public class ApplicationDaoImpl implements ApplicatioinDao {

	@Override
	public boolean insertApplication(Application application) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		Integer result = (Integer)session.save(application);
		ts.commit();
		session.close();
		if (result >= 0) {
			return true;
		}else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplicationByDateAndApplicant(Date begin, Date end, String username) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "from Application where applicant = :username";
			if (begin != null) {
				hql += " and applyTime >= :begin";
			}
			if (end != null) {
				hql += " and applyTime <= :end";
			}
			Query query = session.createQuery(hql);
			if (begin != null) {
				query.setDate("begin", begin);
			}
			if (end != null) {
				query.setDate("end", end);
			}
			query.setString("username", username);
			List<Application> applications = (ArrayList<Application>) query.list();
			transaction.commit();
			session.close();
			return applications;
		} catch (Exception e) {
			System.out.println("按照申请日期以及申请人查询申请记录失败：" + e.getMessage());
			transaction.rollback();
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplicationByDate(Date begin, Date end) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "from Application where 1 = 1";
			if (begin != null) {
				hql += " and applyTime >= :begin";
			}
			if (end != null) {
				hql += " and applyTime <= :end";
			}
			Query query = session.createQuery(hql);
			if (begin != null) {
				query.setDate("begin", begin);
			}
			if (end != null) {
				query.setDate("end", end);
			}
			List<Application> applications = (ArrayList<Application>) query.list();
			transaction.commit();
			session.close();
			return applications;
		} catch (Exception e) {
			System.out.println("按照申请日期查询申请记录失败：" + e.getMessage());
			transaction.rollback();
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplicationById(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql = "from Application where id = ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, id);
			List<Application> applications = (ArrayList<Application>) query.list();
			transaction.commit();
			session.close();
			return applications;
		} catch (Exception e) {
			System.out.println("按照id查询申请记录失败：" + e.getMessage());
			transaction.rollback();
			session.close();
			return null;
		}
	}

	@Override
	public boolean updateApplication(Application application) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(application);
			transaction.commit();
			session.close();
			return true;
		} catch (Exception e) {
			System.out.println("更新申请记录失败：" + e.getMessage());
			transaction.rollback();
			session.close();
			return false;
		}
	}

}
