package com.shine.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.shine.dao.OpportunityDao;
import com.shine.dao.impl.OpportunityDaoImpl;
import com.shine.dto.Opportunity;
import com.shine.util.ExcelReader;

public class Action_ImportExcelOpp extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private File file_upload;
	private String fileName;
	private Object result;
	private String username;
	
	public String importExcel(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			if(!"".equals(fileName) && fileName != null) {
				String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/UploadFiles");
				String extensions = fileName.split("\\.")[1];
				File target = null;
				List<String[]> resultList = new ArrayList<String[]>();
				if ("xls".equals(extensions)) {
					target = new File(path, "file_upload.xls");
					FileUtils.copyFile(file_upload, target);
					System.out.println(target.getPath());
					ExcelReader excelReader = new ExcelReader(target.getPath());
					resultList = excelReader.readXLSAllData();
				}else if ("xlsx".equals(extensions)) {
					target = new File(path, "file_upload.xlsx");
					FileUtils.copyFile(file_upload, target);
					System.out.println(target.getPath());
					ExcelReader excelReader = new ExcelReader(target.getPath());
					resultList = excelReader.readXLSXAllData();
				}
				List<Opportunity> oppList = new ArrayList<Opportunity>();
				if(resultList.size() >= 1){
					for (int i = 0; i < resultList.size(); i++) {
						String[] line = resultList.get(i);
						Opportunity opportunity = new Opportunity();
						opportunity.setCreateTime(new Date());
						opportunity.setName(line[0]);
						if ("男".equals(line[1])) {
							opportunity.setGender(0);
						}else if ("女".equals(line[1])) {
							opportunity.setGender(1);
						} 
						if (!"".equals(line[2]) && line[2] != null) {
							opportunity.setAge(Integer.parseInt(line[2]));
						}
						opportunity.setContactTel1(line[3]);
						opportunity.setContactTel2(line[4]);
						opportunity.setSchool(line[5]);
						opportunity.setParentName(line[6]);
						opportunity.setAddress(line[7]);
						opportunity.setEmail(line[8]);
						opportunity.setSource(line[9]);
						SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.US);
						TimeZone tz = TimeZone.getTimeZone("GMT+8");
						sdf.setTimeZone(tz);
						if(!"".equals(line[10]) && line[10] != null) {
							opportunity.setOrderTime(sdf.parse(line[10]));
						}
						if("否".equals(line[11])) {
							opportunity.setIsArrive(0);
						}else if ("是".equals(line[11])) {
							opportunity.setIsArrive(1);
						}
						if(!"".equals(line[12]) && line[12] != null) {
							opportunity.setArriveTime(sdf.parse(line[12]));
						}
						if("否".equals(line[13])) {
							opportunity.setIsDeal(0);
						}else if ("是".equals(line[13])) {
							opportunity.setIsDeal(1);
						}
						opportunity.setEnglishName(line[14]);
						if(!"".equals(line[15]) && line[15] != null) {
							opportunity.setBirthday(sdf.parse(line[15]));
						}
						opportunity.setCreator(username);
						oppList.add(opportunity);
					}
					
					OpportunityDao oppDao = new OpportunityDaoImpl();
					//保存商机
					for (Opportunity opp : oppList) {
						oppDao.insertOpp(opp);
					}
					map.put("result", "success");
				}else {
					System.out.println("上传的是空文件！");
					map.put("result", "null");
				}
			}else {
				map.put("result", "null");
			}
		} catch (Exception e) {
			System.out.println("批量上传商机失败：Action_XDFImportExcelOpp.java:" + e.getMessage());
			map.put("result", "fail");
		}
		result = JSON.toJSON(map);
		return SUCCESS;
	}
	
	public File getFile_upload() {
		return file_upload;
	}
	public void setFile_upload(File file_upload) {
		this.file_upload = file_upload;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
