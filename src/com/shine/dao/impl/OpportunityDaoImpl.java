package com.shine.dao.impl;

import java.util.ArrayList;
import java.util.Date;
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

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Opportunity> getOppByCS(String csName) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Opportunity where followCS = ?";
			Query query = session.createQuery(hql);
			query.setString(0, csName);
			List<Opportunity> oppList = query.list();
			ts.commit();
			session.close();
			return oppList;
		} catch (Exception e) {
			System.out.println("通过客服用户名查询商机失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return null;
		}
	}

	@Override
	public boolean releaseOpp(int oppId) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "update Opportunity set isAssign = 0,followCS = '' where id = ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, oppId);
			int result = query.executeUpdate();
			ts.commit();
			session.close();
			if (result >= 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("释放商机失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return false;
		}
	}

	@Override
	public boolean assignOpp(int oppId, String CSName) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "update Opportunity set isAssign = 1,followCS = ? where id = ?";
			Query query = session.createQuery(hql);
			query.setString(0, CSName);
			query.setInteger(1, oppId);
			int result = query.executeUpdate();
			ts.commit();
			session.close();
			if (result >= 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("分配商机失败：" + e.getMessage()); 
			ts.rollback();
			session.close();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Opportunity> getOppByDateAndContact(Date begin, Date end, String contact) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Opportunity where 1=1";
			if (begin != null) {
				hql += " and createTime >=:begin";
			}
			if (end != null) {
				hql += " and createTime <=:end";
			}
			if (!"".equals(contact) && contact != null) {
				hql += " and (contactTel1 =:contact or contactTel2 =:contact)";
			}
			Query query = session.createQuery(hql);
			if (begin != null) {
				query.setDate("begin", begin);
			}
			if (end != null) {
				query.setDate("end", end);
			}
			if (!"".equals(contact) && contact != null) {
				query.setString("contact", contact);
			}
			List<Opportunity> opportunities = (List<Opportunity>)query.list();
			ts.commit();
			session.close();
			return opportunities;
		} catch (Exception e) {
			System.out.println("通过日期与联系方式查询商机失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return null;
		}
	}

	@Override
	public boolean markToDeal(int id, String classCode) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "update Opportunity set isDeal = 1,classCode = :classCode where id = :id";
			Query query = session.createQuery(hql);
			query.setString("classCode", classCode);
			query.setInteger("id", id);
			Integer result = query.executeUpdate();
			ts.commit();
			session.close();
			if (result >= 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("通过日期与联系方式查询商机失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Opportunity> getOppByClassCode(String classCode) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from Opportunity where classCode = ?";
			Query query = session.createQuery(hql);
			query.setString(0, classCode);
			List<Opportunity> opportunities = (ArrayList<Opportunity>)query.list();
			ts.commit();
			session.close();
			return opportunities;
		} catch (Exception e) {
			System.out.println("通过班级编码查询商机失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return null;
		}
	}
}
