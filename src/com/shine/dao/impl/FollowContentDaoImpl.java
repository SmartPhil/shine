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

	@SuppressWarnings("unchecked")
	@Override
	public FollowContent getLatestFollowContentByOppId(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from FollowContent where oppId = ? order by time DESC";
			Query query = session.createQuery(hql);
			query.setInteger(0, Integer.valueOf(id));
			query.setFirstResult(0);
			query.setMaxResults(1);
			List<FollowContent> followContents = (List<FollowContent>)query.list();
			ts.commit();
			session.close();
			if (followContents.size() > 0) {
				return followContents.get(0);
			}else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询商机最近跟进记录失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return null;
		}
	}

	@Override
	public Integer getFollowContentCountByOppId(int id, String csName) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "select count(*) from FollowContent where oppId = ? and follower = ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, Integer.valueOf(id));
			query.setString(1, csName);
			int result = ((Number)query.uniqueResult()).intValue();
			ts.commit();
			session.close();
			return result;
		} catch (Exception e) {
			System.out.println("查询某个客服某条商机的总跟进记录数失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return 0;
		}
	}
}
