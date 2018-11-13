package com.newIns.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 上传问卷类
 * @author Sang
 *
 */
public class ExcelTool {
	
		// 判断excel版本
		@SuppressWarnings("resource")
		public static Workbook openWorkbook(InputStream in, String filename,
				String fileFileName) throws IOException {
			Workbook wb = null;
			if (fileFileName.endsWith(".xlsx")) {
				wb = new XSSFWorkbook(in);// Excel 2007
			} else {
				wb = (Workbook) new HSSFWorkbook(in);// Excel 2003
			}
			return wb;
		}

		/**
		 * 读取excel问卷模板,将数据拼接成字符串,返回
		 * @param fileName
		 * @param fileFileName
		 * @return
		 * @throws Exception
		 */
		public static StringBuffer getExcelData(String fileName,String fileFileName) throws Exception {
			InputStream in = new FileInputStream(fileName); // 创建输入流
			Workbook wb = openWorkbook(in, fileName, fileFileName);// 获取Excel文件对象
			StringBuffer sb = new StringBuffer();
			String queNum = "";
			Sheet sheetAt0 = null;
			//获取 调查问卷题目数量
			try {
				sheetAt0 = wb.getSheetAt(0);
				Row row2 = sheetAt0.getRow(2);
				Cell cell2 = row2.getCell(1);
				queNum = cell2.getNumericCellValue() + "";
				queNum = queNum.substring(0, queNum.length()-2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 获取 问卷描述信息
			for(int x=0;x<23;x++){
				Row row0 = sheetAt0.getRow(x);
				Cell cell = row0.getCell(1);
				String cellValue = "";
				if (null != cell) {
					// 以下是判断数据的类型
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						cellValue = cell.getNumericCellValue() + "";
						cellValue = cellValue.substring(0, cellValue.length()-2);
						break;
					case HSSFCell.CELL_TYPE_STRING: // 字符串
						cellValue = cell.getStringCellValue();
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
						cellValue = cell.getBooleanCellValue() + "";
						break;
					case HSSFCell.CELL_TYPE_FORMULA: // 公式
						cellValue = cell.getCellFormula() + "";
						break;
					case HSSFCell.CELL_TYPE_BLANK: // 空值
						cellValue = "";
						break;
					case HSSFCell.CELL_TYPE_ERROR: // 故障
						cellValue = "非法字符";
						break;
					default:
						cellValue = "未知类型";
						break;
					}
					//如果未录入问卷人
					if(x == 22){
						if(StrUtils.isEmpty(cellValue)){
							sb.append(cellValue+"无备注|");
						}else{
							sb.append(cellValue+"|");
						}
					}else{
						sb.append(cellValue+"|");
					}
					
				}
			}

			sb.append("$#");
			for(int y=1;y<Integer.valueOf(queNum)+1;y++){
				Sheet sheet = wb.getSheetAt(y);// 获取文件的指定工作表m 默认的第一个
				Row row = null;
				Cell cell = null;
				int totalRows = 0; // 总行数 
				//从 选项数量 中读取总行数
				Cell cell2 = sheet.getRow(0).getCell(5);
				String optionNum = cell2.getNumericCellValue()+"";  //选项数量
				String newOptionNum = optionNum.substring(0, optionNum.length()-2);
				if(StrUtils.isNotEmpty(newOptionNum)){
					totalRows = Integer.valueOf(newOptionNum) + 1;
				}

				for (int r = 0; r < totalRows; r++) {  // r 为行   
					if( r == 0){
						//当  第一行时 列数固定为 20  
						row = sheet.getRow(r);
							for (int c = 1; c < 22; c = c+2) {  //c 为列
								if(row!=null){
									cell = row.getCell(c);
									String cellValue = "";
									if (null != cell) {
										// 以下是判断数据的类型
										switch (cell.getCellType()) {
										case HSSFCell.CELL_TYPE_NUMERIC: // 数字
											cellValue = cell.getNumericCellValue() + "";
											cellValue = cellValue.substring(0, cellValue.length()-2);
											break;
										case HSSFCell.CELL_TYPE_STRING: // 字符串
											cellValue = cell.getStringCellValue();
											break;
										case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
											cellValue = cell.getBooleanCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_FORMULA: // 公式
											cellValue = cell.getCellFormula() + "";
											break;
										case HSSFCell.CELL_TYPE_BLANK: // 空值
											cellValue = "";
											break;
										case HSSFCell.CELL_TYPE_ERROR: // 故障
											cellValue = "非法字符";
											break;
										default:
											cellValue = "未知类型";
											break;
										}
										sb.append(cellValue+"|");
										
									}
								}
							}
						}
					else{
						// 不是第一行,列数为 14
						row = sheet.getRow(r);
						for (int c = 1; c < 16; c = c+2) {  //c 为列
							if(row!=null){
								cell = row.getCell(c);
								String cellValue = "";
								if (null != cell) {
									// 以下是判断数据的类型
									switch (cell.getCellType()) {
									case HSSFCell.CELL_TYPE_NUMERIC: // 数字
										cellValue = cell.getNumericCellValue() + "";
										cellValue = cellValue.substring(0, cellValue.length()-2);
										break;
									case HSSFCell.CELL_TYPE_STRING: // 字符串
										cellValue = cell.getStringCellValue();
										break;
									case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
										cellValue = cell.getBooleanCellValue() + "";
										break;
									case HSSFCell.CELL_TYPE_FORMULA: // 公式
										cellValue = cell.getCellFormula() + "";
										break;
									case HSSFCell.CELL_TYPE_BLANK: // 空值
										cellValue = "";
										break;
									case HSSFCell.CELL_TYPE_ERROR: // 故障
										cellValue = "非法字符";
										break;
									default:
										cellValue = "未知类型";
										break;
									}
									//循环到最后一行,最后一列
									if(r == (totalRows-1) && c == 15){
										if(StrUtils.isEmpty(cellValue)){
											sb.append(cellValue+"0|");
										}else{
											sb.append(cellValue+"|");
										}
									}else{
										sb.append(cellValue+"|");
									}
									
								}
							}
						}
						
					}
					
				}
				sb.append("$#");
			}
			
			return sb;
		}
		
		/**
		 * 针对测评问卷中,不同部分,简单测评内容部分
		 * @param fileName
		 * @param fileFileName
		 * @return
		 * @throws Exception
		 */
		public static StringBuffer getAssExcelData(String fileName,String fileFileName) throws Exception {
			// 创建输入流
			InputStream in = new FileInputStream(fileName); 
			// 获取Excel文件对象
			Workbook wb = openWorkbook(in, fileName, fileFileName);
			StringBuffer sb = new StringBuffer();
			String queNum = "";
			Sheet sheetAt0 = null;
			//获取 调查问卷题目数量
			try {
				sheetAt0 = wb.getSheetAt(0);
				Row row2 = sheetAt0.getRow(2);
				Cell cell2 = row2.getCell(1);
				queNum = cell2.getNumericCellValue() + "";
				queNum = queNum.substring(0, queNum.length()-2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 获取 问卷描述信息
			for(int x=0;x<23;x++){
				Row row0 = sheetAt0.getRow(x);
				Cell cell = row0.getCell(1);
				String cellValue = "";
				if (null != cell) {
					// 以下是判断数据的类型
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						cellValue = cell.getNumericCellValue() + "";
						cellValue = cellValue.substring(0, cellValue.length()-2);
						break;
					case HSSFCell.CELL_TYPE_STRING: // 字符串
						cellValue = cell.getStringCellValue();
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
						cellValue = cell.getBooleanCellValue() + "";
						break;
					case HSSFCell.CELL_TYPE_FORMULA: // 公式
						cellValue = cell.getCellFormula() + "";
						break;
					case HSSFCell.CELL_TYPE_BLANK: // 空值
						cellValue = "";
						break;
					case HSSFCell.CELL_TYPE_ERROR: // 故障
						cellValue = "非法字符";
						break;
					default:
						cellValue = "未知类型";
						break;
					}
					
					if(x==21){
						if(StrUtils.isEmpty(cellValue)){
							sb.append(cellValue+"无备注|");
						}else{
							sb.append(cellValue+"|");
						}
					}else{
						sb.append(cellValue+"|");
					}
					
				}
			}
			//循环遍历 简单测评模型
			//获取区间数量
			String cell40 = sheetAt0.getRow(41).getCell(1)+"";
			String substring = cell40.substring(0, cell40.length()-2);
			Integer valueOf = 0;
			if(StrUtils.isNotEmpty(substring)){
				valueOf = Integer.valueOf(substring);
			}
			sb.append(substring+"|");
			for(int x=43;x<=valueOf+42;x++){
				for(int y=1;y<=4;y++){
					Row row = sheetAt0.getRow(x);
					Cell cell = row.getCell(y);
					sb.append(cell+"|");
				}
			}
			
			//多维度加总型模型
			//获取纬度数量
			String dimensionNum0 = sheetAt0.getRow(61).getCell(1)+"";
			String substring2 = dimensionNum0.substring(0, dimensionNum0.length()-2);
			Integer dimensionNum = 0;
			if(StrUtils.isNotEmpty(substring2)){
				dimensionNum = Integer.valueOf(substring2);
			}
			sb.append(substring2+"|");
			for(int x=63;x<=dimensionNum+62;x++){
				for(int y=0;y<=2;y++){
					Row row = sheetAt0.getRow(x);
					Cell cell = row.getCell(y);
					sb.append(cell+"|");
				}
			}
			
			sb.append("$#");
			for(int y=1;y<Integer.valueOf(queNum)+1;y++){
				Sheet sheet = wb.getSheetAt(y);// 获取文件的指定工作表m 默认的第一个
				Row row = null;
				Cell cell = null;
				//总行数 为选项数量 加 1 
				int totalRows = 0; // 总行数
				String optionCell = sheet.getRow(0).getCell(5).getNumericCellValue()+""; //选项数量
				String optionNum = optionCell.substring(0, optionCell.length()-2);
				if(StrUtils.isNotEmpty(optionNum)){
					totalRows = Integer.valueOf(optionNum) + 1;
				}
				for (int r = 0; r < totalRows; r++) {
					row = sheet.getRow(r);
					if(r == 0){
						//第一行
						for (int c = 1; c < 22; c = c+2) {
							if(row!=null){
								cell = row.getCell(c);
								String cellValue = "";
								if (null != cell) {
									// 以下是判断数据的类型
									switch (cell.getCellType()) {
									case HSSFCell.CELL_TYPE_NUMERIC: // 数字
										cellValue = cell.getNumericCellValue() + "";
										cellValue = cellValue.substring(0, cellValue.length()-2);
										break;
									case HSSFCell.CELL_TYPE_STRING: // 字符串
										cellValue = cell.getStringCellValue();
										break;
									case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
										cellValue = cell.getBooleanCellValue() + "";
										break;
									case HSSFCell.CELL_TYPE_FORMULA: // 公式
										cellValue = cell.getCellFormula() + "";
										break;
									case HSSFCell.CELL_TYPE_BLANK: // 空值
										cellValue = "";
										break;
									case HSSFCell.CELL_TYPE_ERROR: // 故障
										cellValue = "非法字符";
										break;
									default:
										cellValue = "未知类型";
										break;
									}
									sb.append(cellValue+"|");
								}
							}
						}
					}
					else{
						//不是第一行
						for (int c = 1; c < 16; c = c+2) {
							if(row!=null){
								cell = row.getCell(c);
								String cellValue = "";
								if (null != cell) {
									// 以下是判断数据的类型
									switch (cell.getCellType()) {
									case HSSFCell.CELL_TYPE_NUMERIC: // 数字
										cellValue = cell.getNumericCellValue() + "";
										cellValue = cellValue.substring(0, cellValue.length()-2);
										break;
									case HSSFCell.CELL_TYPE_STRING: // 字符串
										cellValue = cell.getStringCellValue();
										break;
									case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
										cellValue = cell.getBooleanCellValue() + "";
										break;
									case HSSFCell.CELL_TYPE_FORMULA: // 公式
										cellValue = cell.getCellFormula() + "";
										break;
									case HSSFCell.CELL_TYPE_BLANK: // 空值
										cellValue = "";
										break;
									case HSSFCell.CELL_TYPE_ERROR: // 故障
										cellValue = "非法字符";
										break;
									default:
										cellValue = "未知类型";
										break;
									}
									
									//测评问卷在用  找到每一选项,最后一个备注
									if(r == (totalRows-1) && c == 15){
										if(StrUtils.isEmpty(cellValue)){
											sb.append(cellValue+"0|");
										}else{
											sb.append(cellValue+"|");
										}
									}else{
										sb.append(cellValue+"|");
									}
									
									
								}
							}
						}
					}
					
				}
				sb.append("$#");
			}
			
			return sb;
		}

	    /** 
	     * @param byteArray 
	     *                  byte数组 
	     * @return 转后后的32位的字符串 
	     */  
	    private static String byteArrayToHex(byte[] byteArray) {  
	        // 首先初始化一个字符数组，用来存放每个16进制字符  
	        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
	  
	        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））  
	        char[] resultCharArray = new char[byteArray.length * 2];// 每个字节用 16 进制表示的话，使用两个字符  
	  
	        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去  
	        int index = 0;  
	        // 从第一个字节开始，对MD5的每一个字节转换成16进制字符的转换  
	        for (byte b : byteArray) {  
	            // 取字节中高 4 位的数字转换  
	            //>>> 为逻辑右移（即无符号右移），将符号位一起右移  
	            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];  
	            // 取字节中低4位的数字转换   
	            resultCharArray[index++] = hexDigits[b & 0xf];  
	        }  
	        // 字符数组组合成字符串返回  
	        return new String(resultCharArray);  
	    }


		
}
