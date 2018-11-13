package com.newIns.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.newIns.web.NiDeliveryStatisticsController;

public class ExcelExportUtil_new {
	// 日志
	private static Logger log = Logger
			.getLogger(NiDeliveryStatisticsController.class);
	/**
	 * 导出投放统计到excel文件
	 * @throws Exception 
	 */
	public static <T> void listToExcel(List<T> niReportCodingConvertsList,
			LinkedHashMap<String, String> fieldMap, String sheetName,HttpServletResponse response) throws Exception{
		
		SXSSFWorkbook wb = new SXSSFWorkbook(100);
		Sheet sh = wb.createSheet(sheetName);
		
		// 设置默认文件名为当前时间：年月日时分秒
		String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();
		
		// 设置response头信息
		response.reset();
		response.setContentType("application/x-download;charset=GBK");
		response.setContentType("application/vnd.ms-excel"); // 改成输出excel文件
		response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
		ServletOutputStream outputStream = response.getOutputStream();
		
		//设置 字体格式
		Font titleFont = wb.createFont();
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		CellStyle titleCellStyle = wb.createCellStyle();
		titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleCellStyle.setFont(titleFont);
		
		
		
		//定义存放中文字段名, 英文字段名的数组
		String[] enFields = new String[fieldMap.size()];
		String[] cnFields = new String[fieldMap.size()];
		
		//填充数组
		int count = 0;
		for(Entry<String, String> entry : fieldMap.entrySet()){
			enFields[count] = entry.getKey();
			cnFields[count] = entry.getValue();
			count++;
		}
		
		//填充表头
		Row createRow = sh.createRow(0);
		for(int i=0; i<cnFields.length;i++){
			Cell cell = createRow.createCell(i);
			cell.setCellValue(cnFields[i]);
			cell.setCellStyle(titleCellStyle);
			if(cnFields[i] != null){
				//设置宽度
				sh.setColumnWidth(0, cnFields[i].getBytes().length*2*172);
			}
		}
		
		// 填充内容
		CellStyle dataCellStyle = wb.createCellStyle();
		dataCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		int rowNo = 1;
		for (int index = 0; index < niReportCodingConvertsList.size(); index++) {
			Row createRow2 = sh.createRow(rowNo);
			// 获取单个对象
			T niReportConvert = niReportCodingConvertsList.get(index);
			for (int i = 0; i < enFields.length; i++) {
				Object objValue = getFieldValueByNameSequence(enFields[i], niReportConvert);
				String fieldValue = objValue == null ? "" : objValue.toString();
				
				Cell newCell = createRow2.createCell(i);
				newCell.setCellStyle(dataCellStyle);
				newCell.setCellValue(fieldValue);
				
				//获取原宽度
				int columnWidth = sh.getColumnWidth(i);
				//获取填充数据宽度
				int propertyValueLength = fieldValue.getBytes().length*2*172;
				//设置宽度
				if(columnWidth<propertyValueLength){
					sh.setColumnWidth(i, propertyValueLength);
				}
				
				// 解决  The maximum column width for an individual cell is 255 characters 问题 
//	            int colWidth = sh.getColumnWidth(i)*2;
//	            if(colWidth<255*256){
//	            	sh.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);    
//	            }else{
//	            	sh.setColumnWidth(i,6000 );
//	            }
				
			}
			
			if(rowNo % 1000 == 0){
				log.info("==========>>填充到excel中的数据一共有:"+niReportCodingConvertsList.size()+"条,------>正在执行第:"+rowNo+"条");
			}
			rowNo++;
		}

		 wb.write(outputStream); 

		 outputStream.close(); 
	}
	
	private static Object getFieldValueByNameSequence(String fieldNameSequence,
			Object o) throws Exception {

		Object value = null;

		// 将fieldNameSequence进行拆分
		String[] attributes = fieldNameSequence.split("\\.");
		if (attributes.length == 1) {
			value = getFieldValueByName(fieldNameSequence, o);
		} else {
			// 根据属性名获取属性对象
			Object fieldObj = getFieldValueByName(attributes[0], o);
			String subFieldNameSequence = fieldNameSequence
					.substring(fieldNameSequence.indexOf(".") + 1);
			value = getFieldValueByNameSequence(subFieldNameSequence, fieldObj);
		}
		return value;

	}
	
	private static Object getFieldValueByName(String fieldName, Object o)
			throws Exception {

		Object value = null;
		Field field = getFieldByName(fieldName, o.getClass());

		if (field != null) {
			field.setAccessible(true);
			value = field.get(o);
		} else {
			throw new ExcelException(o.getClass().getSimpleName() + "类不存在字段名 "
					+ fieldName);
		}

		return value;
	}
	
	private static Field getFieldByName(String fieldName, Class<?> clazz) {
		// 拿到本类的所有字段
		Field[] selfFields = clazz.getDeclaredFields();

		// 如果本类中存在该字段，则返回
		for (Field field : selfFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}

		// 否则，查看父类中是否存在此字段，如果有则返回
		Class<?> superClazz = clazz.getSuperclass();
		if (superClazz != null && superClazz != Object.class) {
			return getFieldByName(fieldName, superClazz);
		}

		// 如果本类和父类都没有，则返回空
		return null;
	}
	
	
	/**
	 * 测试
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args){
		try {
			ExcelExport_test01();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 测试1
	 * @throws IOException
	 */
	public static void ExcelExport_test01() throws IOException{
		SXSSFWorkbook wb = new SXSSFWorkbook(100);
		 Sheet sh = wb.createSheet();

		 for(int rownum = 0; rownum < 100000; rownum++){
	
			 Row row = sh.createRow(rownum);
	
			 for(int cellnum = 0; cellnum < 10; cellnum++){
	
				 Cell cell = row.createCell(cellnum); 
		
//				 String address = new CellReference(cell).formatAsString(); 
		
				 cell.setCellValue("abc"); 
			 } 
		 }
		File file = new File("/sxssf.xlsx");
		FileOutputStream out = new FileOutputStream(file); 
		
		String absolutePath = file.getAbsolutePath();
		System.out.println(absolutePath);
		 wb.write(out); 
		 out.close(); 
	}

}
