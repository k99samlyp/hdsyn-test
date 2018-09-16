package com.cmcc.andedu.hdsyn.task;

import com.cmcc.andedu.hdsyn.service.EduSynHdDbService;
import com.cmcc.andedu.hdsyn.service.EduSynHdDedService;
import com.cmcc.andedu.hdsyn.service.EduSynHdMthspService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by LiYangpan on 2018/8/20  11:05 AM.
 * with INTELLIJ IDEA on rmbp osx 10.11
 * 描述:
 */
@Component("eduHdFilesGeneratorTask")
@ConfigurationProperties(prefix = "hdfile")
public class EduHdFilesGeneratorTask implements Runnable {

    private String dbpath;

    private String dedpath;

    private String mthsppath;

    @Autowired
    private EduSynHdDbService eduSynHdDbService;

    @Autowired
    private EduSynHdDedService eduSynHdDedService;

    @Autowired
    private EduSynHdMthspService eduSynHdMthspService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EduHdFilesGeneratorTask.class);

    public static boolean READ_LOCK = false;     //生成文件时变为true，停止话单文件的读取

    public static String TASK_INDEX;

    /**
     * 文件生成：点播，话费，核减
     */
    public void GenerateHdFiles() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);

        String todayBatchnum = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());

        //todayBatchnum = "20180617";

        if (!todayBatchnum.equals(TASK_INDEX)){
            LOGGER.info("今天是：" + new SimpleDateFormat("yyyyMMdd").format(new Date())+ ",   生成的批次号是" + todayBatchnum);
            try {
                READ_LOCK = true;
                LOGGER.info("点播开始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                eduSynHdDbService.GeneratedataFile(dbpath,todayBatchnum);
                LOGGER.info("点播结束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                LOGGER.info("核减开始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                eduSynHdDedService.GeneratedataFile(dedpath,todayBatchnum);
                LOGGER.info("核减结束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                LOGGER.info("包月开始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                eduSynHdMthspService.GeneratedataFile(mthsppath,todayBatchnum);
                LOGGER.info("包月结束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                READ_LOCK = false;
                TASK_INDEX = todayBatchnum;
            }
            catch (Exception e){
                LOGGER.error("错误发生：" + e.getMessage());
                e.printStackTrace();
            }
        }
        else {
            LOGGER.info("今天是：" + new SimpleDateFormat("yyyyMMdd").format(new Date())+ ",   今天的生成任务已经完成了：" + todayBatchnum);

        }

    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        LOGGER.info("话单生成任务开始：------------" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));
        GenerateHdFiles();
        LOGGER.info("话单生成任务结束：------------" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));
    }


    public String getDbpath() {
        return dbpath;
    }

    public void setDbpath(String dbpath) {
        this.dbpath = dbpath;
    }

    public String getDedpath() {
        return dedpath;
    }

    public void setDedpath(String dedpath) {
        this.dedpath = dedpath;
    }

    public String getMthsppath() {
        return mthsppath;
    }

    public void setMthsppath(String mthsppath) {
        this.mthsppath = mthsppath;
    }
}
