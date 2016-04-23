package com.shine.dao;

import java.util.List;

import com.shine.dto.Opportunity;

public interface OpportunityDao {
	/**
	 * �����µ��̻�
	 * @param opportunity
	 * @return boolean �Ƿ����ɹ�
	 */
	public boolean insertOpp(Opportunity opportunity);
	
	/**
	 * ��ȡ����δ������̻�
	 * @return List<Opportunity> �̻��б�
	 */
	public List<Opportunity> getUnAssignOpp();
}
