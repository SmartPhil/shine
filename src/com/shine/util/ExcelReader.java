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
    
    //���캯������һ��ExcelReader  
    public ExcelReader(String inputfile) {
    	this.inputfile = inputfile;
    }  
    
    //��ȡXLS excel����
    public List<String[]> readXLSAllData(){
    	//����б�
    	List<String[]> result = new ArrayList<String[]>();
    	try {
    		FileInputStream fileInputStream = new FileInputStream(inputfile);
    		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        	HSSFSheet sheet = workbook.getSheetAt(0);
        	//�õ��ļ����һ�е�numֵ
        	int rowNum = sheet.getLastRowNum() + 1;
        	if(rowNum > 1){
        		for (int i = 1; i < rowNum; i++) {
        			Row row = sheet.getRow(i);
        			//�жϵ绰����1�Ƿ�Ϊ�գ���Ϊ������Դ��С�
        			Cell cell2 = row.getCell(3);
        			if(cell2 != null){
        				cell2.setCellType(Cell.CELL_TYPE_STRING);
            			if("".equals(cell2.getStringCellValue().trim()) || cell2.getStringCellValue() == null){
            				continue;
            			}else {
            				//���绰����1��Ϊ�գ���ʼ��ȡ�������ݡ�
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
			System.out.println("��ȡXLS-Excel�ļ�ʧ�ܣ�" + e.getMessage());
		}
    	return result;
    }
    
    //��ȡXLSX excel����
    public List<String[]> readXLSXAllData(){
    	//����б�
    	List<String[]> result = new ArrayList<String[]>();
    	try {
    		FileInputStream fileInputStream = new FileInputStream(inputfile);
    		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        	XSSFSheet sheet = workbook.getSheetAt(0);
        	//�õ��ļ����һ�е�numֵ
        	int rowNum = sheet.getLastRowNum() + 1;
        	if(rowNum > 1){
        		for (int i = 1; i < rowNum; i++) {
        			Row row = sheet.getRow(i);
        			//�жϵ绰����1�Ƿ�Ϊ�գ���Ϊ������Դ��С�
        			Cell cell2 = row.getCell(3);
        			if(cell2 != null){
        				cell2.setCellType(Cell.CELL_TYPE_STRING);
            			if("".equals(cell2.getStringCellValue().trim()) || cell2.getStringCellValue() == null){
            				continue;
            			}else {
            				//���绰����1��Ϊ�գ���ʼ��ȡ�������ݡ�
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
			System.out.println("��ȡXLSX-Excel�ļ�ʧ�ܣ�" + e.getMessage());
		}
    	return result;
    }
}
