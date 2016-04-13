package com.shine.dao.impl;

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
}
