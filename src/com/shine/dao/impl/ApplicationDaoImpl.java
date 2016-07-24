package com.shine.dao.impl;

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

}
