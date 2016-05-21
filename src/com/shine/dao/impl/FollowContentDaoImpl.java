package com.shine.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shine.dao.FollowContentDao;
import com.shine.dto.FollowContent;
import com.shine.util.HibernateUtil;

public class FollowContentDaoImpl implements FollowContentDao {

	@Override
	public boolean insertFollowContent(FollowContent followContent) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		Integer result = (Integer)session.save(followContent);
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
	public List<FollowContent> getFollowContentById(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from FollowContent where oppId = ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, id);
			List<FollowContent> followConList = (List<FollowContent>) query.list();
			ts.commit();
			session.close();
			return followConList;
		} catch (Exception e) {
			System.out.println("通过商机id查询跟进内容失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return null;
		}
	}
}
