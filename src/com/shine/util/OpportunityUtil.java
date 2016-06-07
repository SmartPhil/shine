package com.shine.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.shine.dto.Opportunity;

public class OpportunityUtil {
	/**
	 * 将商机列表转化为JSON字符串
	 * @param oppList
	 * @return JSON字符串
	 */
	public static String ListToJsonString(List<Opportunity> oppList) {
		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		for (Opportunity opp : oppList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			//id
			map.put("id", opp.getId());
			//创建时间
			map.put("createTime", sdf1.format(opp.getCreateTime()));
			//姓名
			if (opp.getName() != null && !"".equals(opp.getName())) {
				map.put("name", opp.getName());
			}else {
				map.put("name", "无");
			}
			//英文姓名
			if (opp.getEnglishName() != null && !"".equals(opp.getEnglishName())) {
				map.put("englishName", opp.getEnglishName());
			}else {
				map.put("englishName", "无");
			}
			//性别
			if (opp.getGender() == 0) {
				map.put("gender", "男");
			}else if (opp.getGender() == 1) {
				map.put("gender", "女");
			}else {
				map.put("gender", "无");
			}
			//年龄
			map.put("age", opp.getAge());
			//学校
			if (opp.getSchool() != null && !"".equals(opp.getSchool())) {
				map.put("school", opp.getSchool());
			}else {
				map.put("school", "无");
			}
			//联系方式1
			map.put("contactTel1", opp.getContactTel1());
			//联系方式2
			if (opp.getContactTel2() != null && !"".equals(opp.getContactTel2())) {
				map.put("contactTel2", opp.getContactTel2());
			}else {
				map.put("contactTel2", "无");
			}
			//email
			if (opp.getEmail() != null && !"".equals(opp.getEmail())) {
				map.put("email", opp.getEmail());
			}else {
				map.put("email", "无");
			}
			//家长姓名
			if (opp.getParentName() != null && !"".equals(opp.getParentName())) {
				map.put("parentName", opp.getParentName());
			}else {
				map.put("parentName", "无");
			}
			//家庭住址
			if (opp.getAddress() != null && !"".equals(opp.getAddress())) {
				map.put("address", opp.getAddress());
			}else {
				map.put("address", "无");
			}
			//商机来源
			if (opp.getSource() != null && !"".equals(opp.getSource())) {
				map.put("source", opp.getSource());
			}else {
				map.put("source", "无");
			}
			//预约时间
			if (opp.getOrderTime() != null) {
				map.put("orderTime", sdf1.format(opp.getOrderTime()));
			}else {
				map.put("orderTime", "无");
			}
			//是否到店
			if (opp.getIsArrive() == 0) {
				map.put("isArrive", "否");
			}else if (opp.getIsArrive() == 1) {
				map.put("isArrive", "是");
			}
			//到店时间
			if (opp.getArriveTime() != null) {
				map.put("arriveTime", sdf1.format(opp.getArriveTime()));
			}else {
				map.put("arriveTime", "无");
			}
			//是否已成单
			if (opp.getIsDeal() == 0) {
				map.put("isDeal", "否");
			}else {
				map.put("isDeal", "是");
			}
			//生日
			if (opp.getBirthday() != null) {
				map.put("birthday", sdf.format(opp.getBirthday()));
			}else {
				map.put("birthday", "无");
			}
			//是否已分配
			if (opp.getIsAssign() == 0) {
				map.put("isAssign", "否");
			}else if (opp.getIsAssign() == 1) {
				map.put("isAssign", "是");
			}
			//跟进客服
			if (opp.getFollowCS() != null && !"".equals(opp.getFollowCS())) {
				map.put("followCS", opp.getFollowCS());
			}else {
				map.put("followCS", "无");
			}
			//创建者
			if (opp.getCreator() != null && !"".equals(opp.getCreator())) {
				map.put("creator", opp.getCreator());
			}else {
				map.put("creator", "无");
			}
			//续班情况
			if (opp.getContinueClass() != null && !"".equals(opp.getContinueClass())) {
				map.put("continueClass", opp.getContinueClass());
			}else {
				map.put("continueClass", "无");
			}
			//备注
			if (opp.getComment() != null && !"".equals(opp.getComment())) {
				map.put("comment", opp.getComment());
			}else {
				map.put("comment", "无");
			}
			//班级编码
			if (opp.getClassCode() != null && !"".equals(opp.getClassCode())) {
				map.put("classCode", opp.getClassCode());
			}else {
				map.put("classCode", "无");
			}
			maps.add(map);
		}
		return JSONArray.toJSONString(maps);
	}
}
