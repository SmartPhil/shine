package com.shine.action;

import java.util.Date;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.FollowContentDao;
import com.shine.dao.impl.FollowContentDaoImpl;
import com.shine.dto.FollowContent;

@SuppressWarnings("serial")
public class Action_InsertFollowContent extends ActionSupport {
	private String oppId;
	private String followContent;
	private String follower;
	private String result;
	
	public String insertFollowContent(){
		FollowContent followCon = new FollowContent();
		followCon.setOppId(Integer.valueOf(oppId));
		followCon.setContent(followContent);
		followCon.setFollower(follower);
		followCon.setTime(new Date());
		FollowContentDao followContentDao = new FollowContentDaoImpl();
		boolean insertResult = followContentDao.insertFollowContent(followCon);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (insertResult) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		result = JSONObject.toJSONString(map);
		return SUCCESS;
	}

	public String getOppId() {
		return oppId;
	}
	public void setOppId(String oppId) {
		this.oppId = oppId;
	}
	public String getFollowContent() {
		return followContent;
	}
	public void setFollowContent(String followContent) {
		this.followContent = followContent;
	}
	public String getFollower() {
		return follower;
	}
	public void setFollower(String follower) {
		this.follower = follower;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
