package com.axon.icloud.host.getconfinfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.jam.mutable.MElement;

import com.axon.icloud.host.javabean.HostToFilBean;

public class GetAllInfoFromExcel {
	private static Logger logger = Logger.getLogger(GetAllInfoFromExcel.class);
	private HashMap<String, HostToFilBean> hostIdMap;
	private HashMap<String, HostToFilBean> appIdMap;

	@SuppressWarnings("all")
	public void getInfoFromExcel() {
		logger.info("获取excel数据");
		File excelFile = null;// Excel文件对象
		InputStream is = null;// 输入流对象
		String cellStr = null;// 单元格，最终按字符串处理

		try {
			excelFile = new File("conf/tag.xlsx");
			is = new FileInputStream(excelFile);// 获取文件输入流
			logger.info("读取xlsx文件    文件流" + is.toString());
			XSSFWorkbook workbook2007 = new XSSFWorkbook(is);// 创建Excel2003文件对象
			XSSFSheet sheet = workbook2007.getSheetAt(0);// 取出第一个工作表，索引是0
			logger.info("sheet是否为空  ：" + sheet.toString());
			hostIdMap = new HashMap<String, HostToFilBean>();
			appIdMap = new HashMap<String, HostToFilBean>();
			// 开始循环遍历行，表头不处理，从1开始
			logger.info("开始解析xlsx文件");
			logger.info("row一共多少行：" + sheet.getLastRowNum());
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				XSSFRow row = sheet.getRow(i);// 获取行对象
				logger.info("获取到了row对象");
				if (row == null) {// 如果为空，不处理
					continue;
				}
				// 循环遍历单元格
				String subId = null;
				String tagName = null;
				String clientId = null;
				String hostId = null;
				for (int j = 0; j < row.getLastCellNum(); j++) {
					XSSFCell cell = row.getCell(j);// 获取单元格对象
					// logger.info("cell::" + cell);
					if (cell == null) {// 单元格为空设置cellStr为空串
						cellStr = "";
					} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理
						cellStr = String.valueOf(cell.getBooleanCellValue());
					} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {// 对数字值的处理
						cellStr = cell.getNumericCellValue() + "";
					} else {// 其余按照字符串处理
						cellStr = cell.getStringCellValue();
					}
					// logger.info("每一个cellStr的值：：："+cellStr);
					if (j == 0) {
						subId = cellStr.substring(0, cellStr.length() - 2);
					} else if (j == 1) {
						tagName = cellStr;
					} else if (j == 2) {
						if (StringUtils.isNotEmpty(cellStr)) {
							String line = cellStr.replaceAll("\\s+", "");
							Pattern pattern = Pattern.compile("[0-9]+");
							Matcher matcher = pattern.matcher(line);
							StringBuilder sb = new StringBuilder();
							while (matcher.find()) {
								sb.append(matcher.group());
								sb.append(",");
							}
							clientId = sb.toString().substring(0,
									sb.length() - 1);
							logger.info("clientId::::::" + clientId);
						}
					} else if (j == 3) {
						if (StringUtils.isNotEmpty(cellStr)) {
							String line = cellStr.replaceAll("\\s+", "");
							Pattern pattern = Pattern.compile("[0-9]+");
							Matcher matcher = pattern.matcher(line);
							StringBuilder sb = new StringBuilder();
							while (matcher.find()) {
								sb.append(matcher.group());
								sb.append(",");
							}
							hostId = sb.toString()
									.substring(0, sb.length() - 1);
							logger.info("hostId::::::" + hostId);
						}
					}
					if (j > 4)
						break;
				}
				if (StringUtils.isNotEmpty(clientId)) {
					HostToFilBean htb = new HostToFilBean();
					htb.setName(tagName);
					htb.setSubId(subId);
					htb.setTagValue(clientId);
					appIdMap.put(subId, htb);
				}
				if (StringUtils.isNotEmpty(hostId)) {
					HostToFilBean htb = new HostToFilBean();
					htb.setName(tagName);
					htb.setSubId(subId);
					htb.setTagValue(hostId);
					hostIdMap.put(subId, htb);
				}
			}
			writeToFile("conf/host.txt",hostIdMap);
			writeToFile("conf/app.txt",appIdMap);
			logger.info("解析完xlsx文件");
		} catch (IOException e) {
			logger.error("文件未获取异常", e);
		} finally {// 关闭文件流
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("关闭异常", e);
				}
			}
		}
		// return studentList;
	}

	/**
	 * 
	 * @param path 输出文件的地址
	 *            
	 * @param hm    要写到文件中的map
	 *           
	 */
	public void writeToFile(String path, HashMap<String, HostToFilBean> hm) {
		File file = new File(path);
		FileWriter fw = null;
		BufferedWriter bfw = null;

		try {
			fw = new FileWriter(file);
			bfw = new BufferedWriter(fw);
			for(Map.Entry<String, HostToFilBean> me :hm.entrySet()){
				StringBuilder sb = new StringBuilder();
				String subId =  me.getKey();
				HostToFilBean htb = me.getValue();
				String name = htb.getName();
				String tagValue = htb.getTagValue();
				sb.append(subId);
				sb.append("-");
				sb.append(name);
				sb.append("-");
				sb.append(tagValue);
				bfw.write(sb.toString());
				bfw.newLine();
			}
		} catch (Exception e) {
             logger.error("写入文件出错", e);
		}finally{
			try {
				bfw.close();
			} catch (IOException e) {
				logger.error("关闭异常", e);
			}
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException,
			FileNotFoundException {

		GetAllInfoFromExcel getAllInfoFromProp = new GetAllInfoFromExcel();
		getAllInfoFromProp.getInfoFromExcel();
		// getAllInfoFromProp.shuchu();
		// getAllInfoFromProp.setHbasetable();

	}
}
