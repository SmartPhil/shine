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
			System.out.println("查询未分配商机失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return null;
		}
	}
}
