package com.shine.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.OpportunityDao;
import com.shine.dao.impl.OpportunityDaoImpl;
import com.shine.dto.Opportunity;

@SuppressWarnings("serial")
public class Action_GetUnAssignOpp extends ActionSupport {
	private List<Opportunity> oppList;
	
	public String get(){
		OpportunityDao oppDao = new OpportunityDaoImpl();
		oppList = oppDao.getUnAssignOpp();
		/*List<HashMap<String, Object>> maps = new ArrayList<HashMap<String,Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Opportunity opp : oppList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", opp.getName());
			map.put("parentName", opp.getParentName());
			map.put("contactTel1", opp.getContactTel1());
			map.put("contactTel2", opp.getContactTel2());
			map.put("address", opp.getAddress());
			if (opp.getOrderTime() != null) {
				map.put("orderTime", sdf.format(opp.getOrderTime()));
			}else {
				map.put("orderTime", "");
			}
			if (opp.getIsArrive() == 0) {
				map.put("isArrive", "未到店");
			}else if (opp.getIsArrive() == 1) {
				map.put("isArrive", "已到店");
			}
			if (opp.getArriveTime() != null) {
				map.put("arriveTime", sdf.format(opp.getArriveTime()));
			}else {
				map.put("arriveTime", "");
			}
			if (opp.getIsDeal() == 0) {
				map.put("isDeal", "未成单");
			}else if (opp.getIsDeal() == 1) {
				map.put("isDeal", "已成单");
			}
			maps.add(map);
		}
		result = JSONArray.toJSONString(maps);*/
		return SUCCESS;
	}

	public List<Opportunity> getOppList() {
		return oppList;
	}
	public void setOppList(List<Opportunity> oppList) {
		this.oppList = oppList;
	}
}
