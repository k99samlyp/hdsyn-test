package com.cmcc.andedu.hdsyn.controller;

import com.cmcc.andedu.hdsyn.service.EduSynHdDbService;
import com.cmcc.andedu.hdsyn.service.EduSynHdDedService;
import com.cmcc.andedu.hdsyn.service.EduSynHdMthspService;
import com.cmcc.andedu.hdsyn.task.EduHdFilesGeneratorTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by LiYangpan on 2018/8/28  11:13 AM.
 * with INTELLIJ IDEA on rmbp osx 10.11
 * 描述: 处理控制请求，下发控制任务
 */
@RestController
@RequestMapping("/action")
@ConfigurationProperties(prefix = "hdfile")

public class EduSynHdActionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EduSynHdActionController.class);

    private String dbpath;

    private String dedpath;

    private String mthsppath;

    @Autowired
    private EduSynHdDbService eduSynHdDbService;

    @Autowired
    private EduSynHdDedService eduSynHdDedService;

    @Autowired
    private EduSynHdMthspService eduSynHdMthspService;

    @RequestMapping("/genall")
    public void generateAllDataJsonFile() throws IOException {
        EduHdFilesGeneratorTask.READ_LOCK = true;
        LOGGER.info("点播全库开始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        eduSynHdDbService.GenerateAllDataFile(dbpath);
        LOGGER.info("点播全库结束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        LOGGER.info("核减全库开始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        eduSynHdDedService.GenerateAllDataFile(dedpath);
        LOGGER.info("核减全库结束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        LOGGER.info("包月全库开始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        eduSynHdMthspService.GenerateAllDataFile(mthsppath);
        LOGGER.info("包月全库结束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        EduHdFilesGeneratorTask.READ_LOCK = false;
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
