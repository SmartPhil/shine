package com.shine.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    private String inputfile = ""; 
    
    //构造函数创建一个ExcelReader  
    public ExcelReader(String inputfile) {
    	this.inputfile = inputfile;
    }  
    
    //读取XLS excel数据
    public List<String[]> readXLSAllData(){
    	//结果列表
    	List<String[]> result = new ArrayList<String[]>();
    	try {
    		FileInputStream fileInputStream = new FileInputStream(inputfile);
    		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        	HSSFSheet sheet = workbook.getSheetAt(0);
        	//得到文件最后一行的num值
        	int rowNum = sheet.getLastRowNum() + 1;
        	if(rowNum > 1){
        		for (int i = 1; i < rowNum; i++) {
        			Row row = sheet.getRow(i);
        			//判断电话号码1是否为空，若为空则忽略此行。
        			Cell cell2 = row.getCell(3);
        			if(cell2 != null){
        				cell2.setCellType(Cell.CELL_TYPE_STRING);
            			if("".equals(cell2.getStringCellValue().trim()) || cell2.getStringCellValue() == null){
            				continue;
            			}else {
            				//若电话号码1不为空，则开始读取此行数据。
            				String[] onelineString = new String[16];
            				for (int j = 0; j < 16; j++) {
            					String cellvalue = "";
            					Cell cell = row.getCell(j);
            					if(cell != null){
            						switch (cell.getCellType()) {
            							case Cell.CELL_TYPE_BLANK:
            								cellvalue = "";
            								break;
            							case Cell.CELL_TYPE_BOOLEAN:
            								cellvalue = Boolean.toString(cell.getBooleanCellValue());
            								break;
            							case Cell.CELL_TYPE_NUMERIC:
            								if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){
            									cellvalue = String.valueOf(cell.getDateCellValue());
            								}else {
            									cell.setCellType(Cell.CELL_TYPE_STRING);
            									cellvalue = cell.getStringCellValue();
            								}
            								break;
            							case Cell.CELL_TYPE_STRING:
            								cellvalue = cell.getStringCellValue();
            								break;
            							case Cell.CELL_TYPE_ERROR:
            								cellvalue = "";
            								break;
            							case Cell.CELL_TYPE_FORMULA:
            								cell.setCellType(Cell.CELL_TYPE_STRING);
            								cellvalue = cell.getStringCellValue();
            								break;
            							default:
            								cellvalue = "";
            								break;
            						}
            					}else {
            						cellvalue = "";
            					}
            					onelineString[j] = cellvalue;
            				}
            				result.add(onelineString);
    					}
        			}else {
    					continue;
    				}
    			}
        	}
        	fileInputStream.close();
        	workbook.close();
		} catch (Exception e) {
			System.out.println("读取XLS-Excel文件失败：" + e.getMessage());
		}
    	return result;
    }
    
    //读取XLSX excel数据
    public List<String[]> readXLSXAllData(){
    	//结果列表
    	List<String[]> result = new ArrayList<String[]>();
    	try {
    		FileInputStream fileInputStream = new FileInputStream(inputfile);
    		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        	XSSFSheet sheet = workbook.getSheetAt(0);
        	//得到文件最后一行的num值
        	int rowNum = sheet.getLastRowNum() + 1;
        	if(rowNum > 1){
        		for (int i = 1; i < rowNum; i++) {
        			Row row = sheet.getRow(i);
        			//判断电话号码1是否为空，若为空则忽略此行。
        			Cell cell2 = row.getCell(3);
        			if(cell2 != null){
        				cell2.setCellType(Cell.CELL_TYPE_STRING);
            			if("".equals(cell2.getStringCellValue().trim()) || cell2.getStringCellValue() == null){
            				continue;
            			}else {
            				//若电话号码1不为空，则开始读取此行数据。
            				String[] onelineString = new String[16];
            				for (int j = 0; j < 16; j++) {
            					String cellvalue = "";
            					Cell cell = row.getCell(j);
            					if(cell != null){
            						switch (cell.getCellType()) {
            							case Cell.CELL_TYPE_BLANK:
            								cellvalue = "";
            								break;
            							case Cell.CELL_TYPE_BOOLEAN:
            								cellvalue = Boolean.toString(cell.getBooleanCellValue());
            								break;
            							case Cell.CELL_TYPE_NUMERIC:
            								if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){
            									cellvalue = String.valueOf(cell.getDateCellValue());
            								}else {
            									cell.setCellType(Cell.CELL_TYPE_STRING);
            									cellvalue = cell.getStringCellValue();
            								}
            								break;
            							case Cell.CELL_TYPE_STRING:
            								cellvalue = cell.getStringCellValue();
            								break;
            							case Cell.CELL_TYPE_ERROR:
            								cellvalue = "";
            								break;
            							case Cell.CELL_TYPE_FORMULA:
            								cell.setCellType(Cell.CELL_TYPE_STRING);
            								cellvalue = cell.getStringCellValue();
            								break;
            							default:
            								cellvalue = "";
            								break;
            						}
            					}else {
            						cellvalue = "";
            					}
            					onelineString[j] = cellvalue;
            				}
            				result.add(onelineString);
    					}
        			}else {
    					continue;
    				}
    			}
        	}
        	fileInputStream.close();
        	workbook.close();
		} catch (Exception e) {
			System.out.println("读取XLSX-Excel文件失败：" + e.getMessage());
		}
    	return result;
    }
}
