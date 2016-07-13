package com.shine.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.FollowContentDao;
import com.shine.dao.OpportunityDao;
import com.shine.dao.impl.FollowContentDaoImpl;
import com.shine.dao.impl.OpportunityDaoImpl;
import com.shine.dto.FollowContent;
import com.shine.dto.Opportunity;

@SuppressWarnings("serial")
public class Action_GetMyOpp_CS extends ActionSupport {
	private String username;
	private String result;
	
	public String getMyOpp() {
		OpportunityDao oppDao = new OpportunityDaoImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Opportunity> oppList = oppDao.getOppByCS(username);
		//获取所有opportunity的id
		List<Integer> oppIdList = new ArrayList<Integer>();
		for (Opportunity opportunity : oppList) {
			if (!oppIdList.contains(opportunity.getId())) {
				oppIdList.add(opportunity.getId());
			}
		}
		//获取所有商机id所对应的FollowContent
		FollowContentDao followContentDao = new FollowContentDaoImpl();
		HashMap<String, FollowContent> followContentMap = new HashMap<String, FollowContent>();
		for (Integer id : oppIdList) {
			FollowContent followContent = followContentDao.getLatestFollowContentByOppId(id);
			followContentMap.put(id.toString(), followContent);
		}
		//获取所有商机id所对应的跟进次数（仅限于当前客服）
		HashMap<String, Integer> followContentCountMap = new HashMap<String, Integer>();
		for (Integer id : oppIdList) {
			int followContentCount = followContentDao.getFollowContentCountByOppId(id, username);
			followContentCountMap.put(id.toString(), Integer.valueOf(followContentCount));
		}
		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
		for (Opportunity opportunity : oppList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", opportunity.getId());
			if (opportunity.getName() == null || "".equals(opportunity.getName())) {
				map.put("stuName", "无");
			}else {
				map.put("stuName", opportunity.getName());
			}
			if (opportunity.getEnglishName() == null || "".equals(opportunity.getEnglishName())) {
				map.put("englishName", "无");
			}else {
				map.put("englishName", opportunity.getEnglishName());
			}
			map.put("contactTel1", opportunity.getContactTel1());
			if (opportunity.getContactTel2() == null || "".equals(opportunity.getContactTel2())) {
				map.put("contactTel2", "无");
			}else {
				map.put("contactTel2", opportunity.getContactTel2());
			}
			if (opportunity.getAddress() == null || "".equals(opportunity.getAddress())) {
				map.put("address", "无");
			}else {
				map.put("address", opportunity.getAddress());
			}
			if (opportunity.getOrderTime() != null) {
				map.put("orderTime", sdf.format(opportunity.getOrderTime()));
			}else {
				map.put("orderTime", "");
			}
			if (opportunity.getIsArrive() == 0) {
				map.put("isArrive", "未到店");
			}else if (opportunity.getIsArrive() == 1) {
				map.put("isArrive", "已到店");
			}
			if (opportunity.getArriveTime() != null) {
				map.put("arriveTime", sdf.format(opportunity.getArriveTime()));
			}else {
				map.put("arriveTime", "");
			}
			if (opportunity.getIsDeal() == 0) {
				map.put("isDeal", "未成单");
			}else if (opportunity.getIsDeal() == 1) {
				map.put("isDeal", "已成单");
			}
			if (followContentMap.get(String.valueOf(opportunity.getId())) == null) {
				map.put("latestFollowTime", "无");
				map.put("latestFollowContent", "无");
			}else {
				String latestFollowTime = sdf.format(followContentMap.get(String.valueOf(opportunity.getId())).getTime());
				String latestFollowContent = followContentMap.get(String.valueOf(opportunity.getId())).getContent();
				map.put("latestFollowTime", latestFollowTime);
				map.put("latestFollowContent", latestFollowContent);
			}
			map.put("followContentCount", followContentCountMap.get(String.valueOf(opportunity.getId())));
			maps.add(map);
		}
		result = JSONArray.toJSONString(maps);
		System.out.println(result);
		return SUCCESS;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
