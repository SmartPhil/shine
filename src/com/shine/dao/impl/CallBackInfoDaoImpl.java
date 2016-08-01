package com.shine.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shine.dao.CallBackInfoDao;
import com.shine.dto.CallBackInfo;
import com.shine.util.HibernateUtil;

public class CallBackInfoDaoImpl implements CallBackInfoDao {

	@Override
	public boolean insert(CallBackInfo callBackInfo) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Integer result = (Integer)session.save(callBackInfo);
			transaction.commit();
			session.close();
			if (result.intValue() > 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			transaction.rollback();
			session.close();
			System.out.println("插入教师回访记录失败：" + e.getMessage());
			return false;
		}
	}

}
