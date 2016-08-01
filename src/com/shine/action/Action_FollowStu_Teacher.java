package com.shine.action;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.CallBackInfoDao;
import com.shine.dao.impl.CallBackInfoDaoImpl;
import com.shine.dto.CallBackInfo;

@SuppressWarnings("serial")
public class Action_FollowStu_Teacher extends ActionSupport {
	private String stuId;
	private String follower;
	private String followContent;
	private String result;
	
	public String follow(){
		/** 组装插入实例 ***/
		CallBackInfo callBackInfo = new CallBackInfo();
		callBackInfo.setFollowContent(followContent);
		callBackInfo.setFollower(follower);
		callBackInfo.setFollowTime(new Date());
		callBackInfo.setOppId(Integer.valueOf(stuId));
		
		/** 
		 * 持久化数据
		 */
		CallBackInfoDao callBackInfoDao = new CallBackInfoDaoImpl();
		boolean insertResult = callBackInfoDao.insert(callBackInfo);
		/*HashMap<String, Object> map = new HashMap<String, Object>();
		if (insertResult) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}*/
		JSONObject resultJson = new JSONObject();
		if (insertResult) {
			resultJson.put("result", "success");
		}else {
			resultJson.put("result", "fail");
		}
		result = resultJson.toJSONString();
		return SUCCESS;
	}

	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getFollower() {
		return follower;
	}
	public void setFollower(String follower) {
		this.follower = follower;
	}
	public String getFollowContent() {
		return followContent;
	}
	public void setFollowContent(String followContent) {
		this.followContent = followContent;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
