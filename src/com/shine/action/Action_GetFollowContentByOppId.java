package com.shine.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.FollowContentDao;
import com.shine.dao.impl.FollowContentDaoImpl;
import com.shine.dto.FollowContent;

@SuppressWarnings("serial")
public class Action_GetFollowContentByOppId extends ActionSupport {
	private String oppId;
	private String result;
	
	public String getFollowContentByOppId(){
		int id = Integer.valueOf(oppId);
		FollowContentDao followContentDao = new FollowContentDaoImpl();
		List<FollowContent> followContents = followContentDao.getFollowContentById(id);
		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (FollowContent content : followContents) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (content.getTime() != null) {
				map.put("followTime", sdf.format(content.getTime()));
			}else {
				map.put("followTime", "");
			}
			map.put("follower", content.getFollower());
			map.put("followContent", content.getContent());
			maps.add(map);
		}
		result = JSONArray.toJSONString(maps);
		return SUCCESS;
	}
	
	public String getOppId() {
		return oppId;
	}
	public void setOppId(String oppId) {
		this.oppId = oppId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
