package com.cmcc.andedu.hdsyn.service;


import com.alibaba.fastjson.JSON;
import com.cmcc.andedu.hdsyn.domain.EduSynHdDb;
import com.cmcc.andedu.hdsyn.domain.EduSynHdDed;
import com.cmcc.andedu.hdsyn.domain.EduSynHdDedQueryEntity;
import com.cmcc.andedu.hdsyn.domain.MsgBean;
import com.cmcc.andedu.hdsyn.repository.EduSynHdDedMapper;
import com.cmcc.andedu.hdsyn.utils.MongoDb;
import com.cmcc.andedu.hdsyn.utils.ReadFile;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: miaojiaxing
 * @Description:
 * @Date: Created in 2018/7/13 9:32
 * @Modified By:
 * @Version: 1.0
 */
@Service
@ConfigurationProperties(prefix = "hdfile")
public class EduSynHdDedService {

    private static Logger log = LoggerFactory.getLogger(EduSynHdDedService.class);
    static List<Document> eduSynHdDedList = new ArrayList<>(200);

    boolean isDivListInsert = false;   //是否分表插入（mybatis批量插入有限制，数据太长，分批提交）

    int hasIn = 0;

    @Autowired
    MongoClient mongoClientBean;

    private int batchInsertCount;

    @Autowired
    private EduSynHdDedMapper eduSynHdDedMapper;

    /**
     * @Author: miaojiaxing
     * @Description: 解析核减数据数据，并进行入表操作
     * @Date: Created in 2018/7/12 11:24
     * @Modified By:
     * @Version: 1.0
     */
    public int solveingDed(File file) {
        //读取文件内容
        ReadFile.readFile_s(file,false);
        // List<String> dataList = ReadFile.readFile(file);


        //去除最后一行无用的数据
        ReadFile.RDATA.remove(ReadFile.RDATA.size() - 1);
        if (null != ReadFile.RDATA && ReadFile.RDATA.size() > 0) {

            System.out.println("不为空");


            System.out.println("数据长度：" + ReadFile.RDATA.size());

            MongoCollection<Document> cols = mongoClientBean.getDatabase("paydata").getCollection("ded");


            if (ReadFile.RDATA.size() > batchInsertCount){
                isDivListInsert = true;
            }

            for (String listString : ReadFile.RDATA) {
                //将单条数据进行拆分

                Document doc = new Document();
                doc.append("MidFlag","20");//中间记录标记
                doc.append("ServiceType",ReadFile.getField(listString,3,12));//业务类型
                doc.append("CutType",ReadFile.getField(listString,13,14));//核减类型
                doc.append("WoNo",ReadFile.getField(listString,15,34));//工单流水号
                doc.append("FeeDn",ReadFile.getField(listString,35,49));//计费用户号码
                doc.append("SpId",ReadFile.getField(listString,50,69));//SP代码
                doc.append("ServiceCode",ReadFile.getField(listString,70,89));//业务代码
                doc.append("FeeType",ReadFile.getField(listString,90,91));//计费类型
                doc.append("UseTime",ReadFile.getField(listString,92,105));//业务使用时间
                doc.append("CutTime",ReadFile.getField(listString,106,119));//核减时间
                doc.append("CutFee",Integer.parseInt(ReadFile.getField(listString,120,125)));//核减金额
                doc.append("ContentId",ReadFile.getField(listString,126,145));//内容ID
                doc.append("tFlag",ReadFile.getField(listString,146,175));//第三方标识
                doc.append("Seq",ReadFile.getField(listString,176,200));//序列号
                doc.append("AddKey1",ReadFile.getField(listString,201,232));//附加查重关键字一
                doc.append("AddKey2",ReadFile.getField(listString,233,264));//附加查重关键字二
                doc.append("OrderType",ReadFile.getField(listString,265,267));//订购方式
                doc.append("RechargeSeq",ReadFile.getField(listString,268,299));//充值序列号
                doc.append("Province",ReadFile.getField(listString,300,302));//用户归属省代码

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String date = df.format(new Date());
                doc.append("CreateTime",date);//记录创建时间
                doc.append("BatchNum",file.getName().substring(3,11));//批次号

                eduSynHdDedList.add(doc);

                hasIn++;

                if (isDivListInsert && (eduSynHdDedList.size() == batchInsertCount || hasIn == ReadFile.RDATA.size())){
                    try {
                        cols.insertMany(eduSynHdDedList);
                        //eduSynHdDedMapper.batchInsert_mysql(eduSynHdDedList);
                        log.info("插入成功！本次插入：" + eduSynHdDedList.size() + "条,还有" + (ReadFile.RDATA.size() - hasIn) + "条");
                        eduSynHdDedList.clear();
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.info("插入数据异常！");
                        return 0;
                    }
                }
            }

            if(!isDivListInsert){
                try {
                    cols.insertMany(eduSynHdDedList);
                    log.info("插入成功！共计：" + eduSynHdDedList.size() + "条");
                    ReadFile.clearDataArea();
                    eduSynHdDedList.clear();
                    return 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("插入数据异常！");
                    return 0;
                }
            }
            else {
                hasIn = 0;
                ReadFile.clearDataArea();
                return 1;
            }

        } else {
            log.info("文件中无插入数据！");
            return 2;
        }
    }

    /**
     * @Author: yangjianjun
     * @Description: 核减数据数据，按省ID和批次号查询
     * @Date: Created in 2018/7/12 11:24
     * @Modified By:
     * @Version: 1.0
     */
    public List<EduSynHdDedQueryEntity> findAllOrder(String province, String batchnum) {
        List<EduSynHdDedQueryEntity> eduSynHdDebQueryData = eduSynHdDedMapper.findAllItems(province, batchnum);
        return eduSynHdDebQueryData;
    }


    /**
     * 生成当天的数据文件，供省BOSS下载
     * @param batchnum 文件批次号
     */
    public void GeneratedataFile(String dicpath, String batchnum) throws IOException {

        int fileGencount = 0;
        List<EduSynHdDedQueryEntity> eduSynHdDedQueryEntities;
        MsgBean mb = new MsgBean();
        String filename ;
        List<String> provices = eduSynHdDedMapper.getProvicesByBatchnum(batchnum);
        for (String _pro: provices) {
            filename = _pro + "-" + batchnum + ".json";
            log.info("创建核减文件：" + filename);

            File jsonf = new File(dicpath + filename);
            if (!jsonf.exists()){
                jsonf.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(jsonf);
            log.info("开始写入");

            eduSynHdDedQueryEntities = eduSynHdDedMapper.findAllItems(_pro, batchnum);
            mb.setAllcount(eduSynHdDedQueryEntities.size());
            mb.setData(eduSynHdDedQueryEntities);

            fos.write(JSON.toJSONString(mb).getBytes());
            fos.close();
            log.info(filename + "写入完成");
            fileGencount++;
        }

        log.info(batchnum + "的全部核减文件完成生成！共计" + fileGencount +  "个文件");
    }


    /**
     * 生成全库的文件   调用GeneratedataFile
     * @param dicpath  文件存放根路径
     * @throws IOException
     * @see this.GeneratedataFile()
     */
    public void GenerateAllDataFile(String dicpath) throws IOException {
        List<String> batchnum = eduSynHdDedMapper.getAllBatchnum();

        for (String _bat: batchnum) {
            log.info("------------------文件批号：" + _bat + "  开始------------------------");
            this.GeneratedataFile(dicpath,_bat);

            log.info("------------------文件批号：" + _bat + "  完成------------------------");

        }
    }

    public int getBatchInsertCount() {
        return batchInsertCount;
    }

    public void setBatchInsertCount(int batchInsertCount) {
        this.batchInsertCount = batchInsertCount;
    }

}
