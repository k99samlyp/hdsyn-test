package com.cmcc.andedu.hdsyn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cmcc.andedu.hdsyn.domain.EduSynHdDb;
import com.cmcc.andedu.hdsyn.domain.EduSynHdDed;
import com.cmcc.andedu.hdsyn.domain.EduSynHdMthsp;
import com.cmcc.andedu.hdsyn.repository.EduSynHdDbMapper;
import com.cmcc.andedu.hdsyn.repository.EduSynHdDedMapper;
import com.cmcc.andedu.hdsyn.repository.EduSynHdMthspMapper;
import com.cmcc.andedu.hdsyn.service.*;
import com.cmcc.andedu.hdsyn.task.EduHdFilesGeneratorTask;
import com.cmcc.andedu.hdsyn.task.HbbCvsGeneratorTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HdsynApplicationTests2 {

    @Autowired
    HbbCvsGeneratorService hbbCvsGeneratorService;

    @Autowired
    HbbCvsGeneratorTask hbbCvsGeneratorTask;

    @Autowired
    private EduSynHdDbMapper eduSynHdDbpMapper;


    @Autowired
    private EduSynHdDedMapper eduSynHdDedMapper;


    @Autowired
    private EduSynHdMthspMapper eduSynHdMthspMapper;

    @Autowired
    private EduSynHdDbService dbService;

    @Autowired
    private EduSynHdDedService dedService;

    @Autowired
    private EduSynHdMthspService mthspService;

    @Autowired
    private EduSynHdMthspService eduSynHdMthspService;

    @Autowired
    private EduSynHdDedService eduSynHdDedService;

    @Autowired
    private EduSynHdDbService eduSynHdDbService;

    @Autowired
    private EduHdFilesGeneratorTask eduHdFilesGeneratorTask;

    static int allFc = 0;

    public void listcon(File f){

        File [] _fl = f.listFiles();

        for (File _f : _fl){
            if (_f.isDirectory()){
                listcon(_f);
            }
            if (_f.isFile()){
                allFc++;
            }
        }
    }



    public void list(File f){

        File [] _fl = f.listFiles();

        for (File _f : _fl){
            if (_f.isDirectory()){
                list(_f);
            }
            if (_f.isFile()){
                excuteResove(_f);
                System.out.println("filename=" + _f.getName() +"     "+ "filelen=" + _f.length() + "完成解析入库,还有" + (allFc--));
            }
        }

        System.out.println("------全部完成！！！！！！！--------");
    }


    public void excuteResove(File hdfile){

        String fname = hdfile.getName();
        if (fname.indexOf(".999") == -1){
            if (fname.indexOf("B_EDU") > -1){
                dbService.solveingDb(hdfile);
            }
            else if (fname.indexOf("DED") > -1){
                dedService.solveingDed(hdfile);
            }
            else if (fname.indexOf("MTHSP") > -1){
                mthspService.solveingMTHSP(hdfile);
            }
        }
    }


    @Test
    public void contextLoads() throws Exception {

        listcon(new File("/Users/sam/hddata"));

        list(new File("/Users/sam/hddata"));

        //eduHdFilesGeneratorTask.GenerateHdFiles();


        //JSONObject rrrr = new JSONObject();
        //JSON.toJSONString();

       // String rrrr = JSON.toJSONString(eduSynHdMthspService.findAllOrder("898","20180504"));




//        System.out.println("查询数据");
//
//        String rrrr = JSON.toJSONString(eduSynHdDedService.findAllOrder("898","20180717"));
//
//        System.out.println("创建文件");
//
//        File testjsonf = new File("/Users/sam/tsbig.json");
//
//        if (!testjsonf.exists()){
//            testjsonf.createNewFile();
//        }
//
//        FileOutputStream fos = new FileOutputStream(testjsonf);
//
//        System.out.println("开始写入");
//
//        fos.write(rrrr.getBytes());
//
//        System.out.println("完成");
//
//        fos.close();


       // eduSynHdDbService.GeneratedataFile("/Users/sam/db/","20180617");
        //eduSynHdDedService.GeneratedataFile("/Users/sam/ded/","20180717");
        //eduSynHdDedService.GeneratedataFile("20180625");
        //eduSynHdMthspService.GeneratedataFile("20180620");


    }

}
