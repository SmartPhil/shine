package com.shine.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shine.dao.OpportunityDao;
import com.shine.dto.Opportunity;
import com.shine.util.HibernateUtil;

public class OpportunityDaoImpl implements OpportunityDao {
	@Override
	public boolean insertOpp(Opportunity opportunity) {
		try {
			Session session = HibernateUtil.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			Integer result = (Integer)session.save(opportunity);
			transaction.commit();
			session.close();
			if(result > 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Opportunity> getUnAssignOpp() {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Opportunity where isAssign = 0";
			Query query = session.createQuery(hql);
			List<Opportunity> oppList = (List<Opportunity>)query.list();
			ts.commit();
			session.close();
			return oppList;
		} catch (Exception e) {
			System.out.println("获取未分配商机失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return null;
		}
	}

	@Override
	public boolean updateOppFollowCSById(int id, String name) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "update Opportunity set followCS = ?,isAssign = 1 where id = ?";
			Query query = session.createQuery(hql);
			query.setString(0, name);
			query.setInteger(1, id);
			int result = query.executeUpdate();
			ts.commit();
			session.close();
			if (result > 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("更新商机跟进人失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return false;
		}
	}

	@Override
	public Opportunity getOppById(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Opportunity where id = ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, id);
			List<Opportunity> oppList = query.list();
			ts.commit();
			session.close();
			if(oppList.size() > 0) {
				return oppList.get(0);
			}else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("通过id查询商机失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return null;
		}
	}
}
