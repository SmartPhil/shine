package com.shine.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.shine.dto.ShineClass;

public class ShineClassUtil {
	public static String ListToJsonString(List<ShineClass> shineClasses) {
		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (ShineClass shineClass : shineClasses) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			//id
			map.put("id", shineClass.getId());
			//级别
			if (shineClass.getLevel() != null && !"".equals(shineClass.getLevel())) {
				map.put("level", shineClass.getLevel());
			}else {
				map.put("level", "无");
			}
			//班级编码
			map.put("classCode", shineClass.getClassCode());
			//开课日期
			if (shineClass.getBeginDate() != null) {
				map.put("beginDate", sdf.format(shineClass.getBeginDate()));
			}else {
				map.put("beginDate", "无");
			}
			//结课日期
			if (shineClass.getEndDate() != null) {
				map.put("endDate", sdf.format(shineClass.getEndDate()));
			}else {
				map.put("endDate", "无");
			}
			//上课周期
			if (shineClass.getBeginWeek() != null && !"".equals(shineClass.getBeginWeek())) {
				map.put("beginWeek", shineClass.getBeginWeek());
			}else {
				map.put("beginWeek", "无");
			}
			//上课时间
			if (shineClass.getBeginTime() != null && !"".equals(shineClass.getBeginTime())) {
				map.put("beginTime", shineClass.getBeginTime());
			}else {
				map.put("beginTime", "无");
			}
			//外教
			if (shineClass.getForeignTeacher() != null && !"".equals(shineClass.getForeignTeacher())) {
				map.put("foreignTeacher", shineClass.getForeignTeacher());
			}else {
				map.put("foreignTeacher", "无");
			}
			//中教
			if (shineClass.getChinaTeacher() != null && !"".equals(shineClass.getChinaTeacher())) {
				map.put("chinaTeacher", shineClass.getChinaTeacher());
			}else {
				map.put("chinaTeacher", "");
			}
			//班主任
			if (shineClass.getClassManager() != null && !"".equals(shineClass.getClassManager())) {
				map.put("classManager", shineClass.getClassManager());
			}else {
				map.put("classManager", "无");
			}
			//学费
			map.put("fee", shineClass.getFee());
			maps.add(map);
		}
		return JSONArray.toJSONString(maps);
	}
}
