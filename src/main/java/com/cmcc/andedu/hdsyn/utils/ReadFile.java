package com.cmcc.andedu.hdsyn.utils;

import com.cmcc.andedu.hdsyn.domain.EduSynHdDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: miaojiaxing
 * @Description:
 * @Date: Created in 2018/7/20 15:07
 * @Modified By:
 * @Version: 1.0
 */
public class ReadFile {

    private static Logger log = LoggerFactory.getLogger(ReadFile.class);

    public static List<String> RDATA = new ArrayList<>(200);

    public static List<String> readFile(File file) {
        //编码格式
        String encoding = "UTF-8";
        String lineTxt;
        List<String> dataList = new ArrayList<>();
        // List<EduSynHdDb> eduSynHdDbList = new ArrayList<>();
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            int i = 0;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                //去除第一行无用的数据
                if (i > 0) {
                    //去除数据中多余的空格，只保留一个
                    dataList.add(lineTxt.replaceAll(" {2,}", " "));
                }
                i++;
            }
            read.close();
        } catch (Exception e) {
            log.info("读取文件出错！");
            e.getStackTrace();
        }

        return dataList;
    }

    /**
     *
     * @param file
     * @param removeSpace 是否去除报文中的多余空格
     */
    public static void readFile_s(File file,boolean removeSpace) {
        log.info("输入文件:" + file.getName());
        //编码格式
        String encoding = "UTF-8";
        String lineTxt;
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            int i = 0;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                //去除第一行无用的数据
                if (i > 0) {
                    //去除数据中多余的空格，只保留一个
                    RDATA.add((removeSpace ? lineTxt.replaceAll(" {2,}", " ") : lineTxt));
                }
                i++;
            }
            read.close();
        } catch (Exception e) {
            log.info("读取文件出错！");
            e.getStackTrace();
        }

    }

    public static void clearDataArea() {
        RDATA.clear();
    }

    /**
     * @author liyp
     * @param line 一行数据
     * @param start 起始位（从1计算，按文档填写即可，会自动定位）
     * @param end 结束位
     * @return 去掉空格的值
     */
    public static String getField(String line, int start,int end){
        return line.substring((start - 1) ,end).replaceAll("\\s*","");
    }
}
