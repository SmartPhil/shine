package com.shine.action;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.ApplicatioinDao;
import com.shine.dao.ShineClassDao;
import com.shine.dao.impl.ApplicationDaoImpl;
import com.shine.dao.impl.ShineClassDaoImpl;
import com.shine.dto.Application;
import com.shine.dto.ShineClass;

@SuppressWarnings("serial")
public class Action_ApproveApplicatoin_President extends ActionSupport {
	private String applicationId;
	private String classCode;
	private String approveResult;
	private String result;
	
	public String approve() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int approveStateCode = 0;
		/** 判断审批的结果，并转换为结果码：1--通过申请；-1 --- 驳回申请    **/
		if ("0".equals(approveResult)) {
			approveStateCode = -1;
		}else if ("1".equals(approveResult)) {
			approveStateCode = 1;
		}
		
		/**  更新class的状态信息  **/
		ShineClassDao shineClassDao = new ShineClassDaoImpl();
		List<ShineClass> shineClasses = shineClassDao.getClassByClassCode(classCode);
		if (shineClasses.size() >= 1) {
			ShineClass shineClass = shineClasses.get(0);
			shineClass.setState(approveStateCode);
			boolean updateResult = shineClassDao.update(shineClass);
			if (!updateResult) {
				/** 更新班级信息出错  ***/
				map.put("result", "updateClassFail");
			}else {
				/** 更新class状态成功后，才继续更新申请记录的状态 **/
				ApplicatioinDao applicatioinDao = new ApplicationDaoImpl();
				List<Application> applications = applicatioinDao.getApplicationById(Integer.parseInt(applicationId));
				if (applications.size() >= 1) {
					Application application = applications.get(0);
					application.setState(approveStateCode);
					boolean updateApplicationResult = applicatioinDao.updateApplication(application);
					if (!updateApplicationResult) {
						/** 更新申请记录信息失败 **/
						map.put("result", "updateApplicationFail");
					}else {
						/** 审批操作成功！ **/
						map.put("result", "success");
					}
				}else {
					/** 查询申请记录信息出错 **/
					map.put("result", "noApplication");
				}
			}
		}else {
			/** 查询班级信息出错  **/
			map.put("result", "noClass");
		}
		result = JSONObject.toJSONString(map);
		return SUCCESS;
	}

	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getApproveResult() {
		return approveResult;
	}
	public void setApproveResult(String approveResult) {
		this.approveResult = approveResult;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
