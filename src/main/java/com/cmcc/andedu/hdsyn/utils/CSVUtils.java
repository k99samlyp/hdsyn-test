package com.cmcc.andedu.hdsyn.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

	public CSVUtils() {
	}

	/**
	 * 导出
	 * @param file csv文件(路径+文件名)，csv文件不存在会自动创建
	 * @param dataList 数据
	 * @param class1 
	 * @return
	 */
	public static boolean exportCsv(File file, List<Object> dataList, Class clazz) {
		boolean isSucess = false;
		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			out = new FileOutputStream(file);
			osw = new OutputStreamWriter(out);
			bw = new BufferedWriter(osw);
			if (dataList != null && !dataList.isEmpty()) {
				for (Object data : dataList) {//data 如果用的是普通bean , 需重写toString方法
					if(data instanceof JSONObject){
						data = JSONObject.parseObject(data.toString(), clazz);//clazz需要重写toString方法
					}
					bw.append(data.toString()).append("\r");
				}
			}
			isSucess = true;
		} catch (Exception e) {
			isSucess = false;
		} finally {
			if (bw != null) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (osw != null) {
				try {
					osw.close();
					osw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return isSucess;
	}

	/**
	 * 导入
	 * @param file csv文件(路径+文件)
	 * @return
	 */
	public static List<String> importCsv(File file) {
		List<String> dataList = new ArrayList<String>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				dataList.add(line);
			}
		} catch (Exception e) {
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return dataList;
	}
}
