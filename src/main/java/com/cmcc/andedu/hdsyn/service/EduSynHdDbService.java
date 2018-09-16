package com.cmcc.andedu.hdsyn.service;


import com.alibaba.fastjson.JSON;
import com.cmcc.andedu.hdsyn.domain.EduSynHdDb;
import com.cmcc.andedu.hdsyn.domain.EduSynHdDbQueryEntity;
import com.cmcc.andedu.hdsyn.domain.EduSynHdMthsp;
import com.cmcc.andedu.hdsyn.domain.MsgBean;
import com.cmcc.andedu.hdsyn.repository.EduSynHdDbMapper;
import com.cmcc.andedu.hdsyn.utils.MongoDb;
import com.cmcc.andedu.hdsyn.utils.ReadFile;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Date: Created in 2018/7/13 9:31
 * @Modified By:
 * @Version: 1.0
 */
@Service
public class EduSynHdDbService {

    private static Logger log = LoggerFactory.getLogger(EduSynHdDbService.class);

    //static List<EduSynHdDb> eduSynHdDbList = new ArrayList<>();

    static List<Document> eduSynHdDbList = new ArrayList<>();

    @Autowired
    private EduSynHdDbMapper eduSynHdDbpMapper;

    @Autowired
    MongoClient mongoClientBean;

    /**
     * @Author: miaojiaxing
     * @Description: 解析点播方式数据，并进行入表操作
     * @Date: Created in 2018/7/12 11:24
     * @Modified By:
     * @Version: 1.0
     */
    public int solveingDb(File file) {

        //读取文件内容
        ReadFile.readFile_s(file,true);
        //List<String> dataList = ReadFile.readFile(file);


        //去除最后一行无用的数据
        ReadFile.RDATA.remove(ReadFile.RDATA.size() - 1);
        if (null != ReadFile.RDATA && ReadFile.RDATA.size() > 0) {

            MongoCollection<Document> cols = mongoClientBean.getDatabase("paydata").getCollection("db");


            for (String listString : ReadFile.RDATA) {
                //将单条数据进行拆分
//                EduSynHdDb eduSynHdDb = new EduSynHdDb();
//
                String stringSplit[] = listString.split(" ");
//                eduSynHdDb.setRecType(stringSplit[0].substring(0, 2));//话单记录标记
//                eduSynHdDb.setMsgId(stringSplit[0].substring(2, 23));//消息序列号
//                eduSynHdDb.setChrgDn(stringSplit[0].substring(23));//计费用户号码
//                eduSynHdDb.setThirdDn(stringSplit[0].substring(23));//第三方号码
//                eduSynHdDb.setUserType(stringSplit[1].substring(0, 1));//计费号码用户类型
//                eduSynHdDb.setOperType(stringSplit[1].substring(1, 3));//业务类型
//                eduSynHdDb.setSaleMode(stringSplit[1].substring(3, 5));//优惠模式
//                eduSynHdDb.setDiscount(stringSplit[1].substring(5, 8));//折扣率
//                eduSynHdDb.setChrgType(stringSplit[1].substring(8, 10));//计费类型
//                eduSynHdDb.setSpCode(stringSplit[2]);//SP代码
//                eduSynHdDb.setOperCode(stringSplit[2]);//业务代码
//                eduSynHdDb.setInfoFee(Integer.parseInt(stringSplit[3].substring(0, 6)));//标准信息费
//                eduSynHdDb.setDiscountFee(Integer.parseInt(stringSplit[3].substring(6, 12)));//优惠后信息费
//                eduSynHdDb.setChrgProv(stringSplit[3].substring(12));//计费用户号码归属省
//                eduSynHdDb.setAccessType(stringSplit[4].substring(0, 2));//定制方式
//                eduSynHdDb.setApplyTime(stringSplit[4].substring(2, 16));//用户申请时间
//                eduSynHdDb.setFinishTime(stringSplit[4].substring(16, 30));//话单记录生成时间
//                eduSynHdDb.setChannelId("");//渠道代码
//                eduSynHdDb.setImsi("");//IMSI
//                eduSynHdDb.setImei("");//手机终端设备标识
//
//                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//                String date = df.format(new Date());
//                eduSynHdDb.setCreateTime(date);//记录创建时间
//
//                eduSynHdDb.setBatchNum(file.getName().substring(6,14));//批次号


                Document doc = new Document();

                doc.append("RecType",stringSplit[0].substring(0, 2));//话单记录标记
                doc.append("MsgId",stringSplit[0].substring(2, 23));//消息序列号
                doc.append("ChrgDn",stringSplit[0].substring(23));//计费用户号码
                doc.append("ThirdDn",stringSplit[0].substring(23));//第三方号码
                doc.append("UserType",stringSplit[1].substring(0, 1));//计费号码用户类型
                doc.append("OperType",stringSplit[1].substring(1, 3));//业务类型
                doc.append("SaleMode",stringSplit[1].substring(3, 5));//优惠模式
                doc.append("Discount",stringSplit[1].substring(5, 8));//折扣率
                doc.append("ChrgType",stringSplit[1].substring(8, 10));//计费类型
                doc.append("SpCode",stringSplit[2]);//SP代码
                doc.append("OperCode",stringSplit[2]);//业务代码
                doc.append("InfoFee",Integer.parseInt(stringSplit[3].substring(0, 6)));//标准信息费
                doc.append("DiscountFee",Integer.parseInt(stringSplit[3].substring(6, 12)));//优惠后信息费
                doc.append("ChrgProv",stringSplit[3].substring(12));//计费用户号码归属省
                doc.append("AccessType",stringSplit[4].substring(0, 2));//定制方式
                doc.append("ApplyTime",stringSplit[4].substring(2, 16));//用户申请时间
                doc.append("FinishTime",stringSplit[4].substring(16, 30));//话单记录生成时间
                doc.append("ChannelId","");//渠道代码
                doc.append("Imsi","");//IMSI

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String date = df.format(new Date());
                doc.append("CreateTime",date);//记录创建时间

                doc.append("BatchNum",file.getName().substring(6,14));//批次号

                eduSynHdDbList.add(doc);
            }

                try {
                    cols.insertMany(eduSynHdDbList);
                    //eduSynHdDbpMapper.batchInsert_mysql(eduSynHdDbList);
                    log.info("插入成功！共计：" + eduSynHdDbList.size() + "条");
                    ReadFile.clearDataArea();
                    eduSynHdDbList.clear();
                    return 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("插入数据异常！");
                    return 0;
                }

        } else {
            log.info("文件中无插入数据！");
            return 2;
        }
    }


    /**
     * @Author: yangjianjun
     * @Description: 点播方式数据，按省ID和批次号查询
     * @Date: Created in 2018/7/12 11:24
     * @Modified By:
     * @Version: 1.0
     */
    public List<EduSynHdDbQueryEntity> findAllOrder(String chrgProv, String batchnum) {
        List<EduSynHdDbQueryEntity> eduSynHdDbQueryData = eduSynHdDbpMapper.findAllItems(chrgProv, batchnum);
        return eduSynHdDbQueryData;
    }


    /**
     * 生成当天的数据文件，供省BOSS下载
     * @param dicpath  文件存放根路径
     * @param batchnum 文件批次号
     */
    public void GeneratedataFile(String dicpath, String batchnum) throws IOException {
        int fileGencount = 0;
        List<EduSynHdDbQueryEntity> eduSynHdDbQueryEntities;
        MsgBean mb = new MsgBean();
        String filename ;
        List<String> provices = eduSynHdDbpMapper.getProvicesByBatchnum(batchnum);
        for (String _pro: provices) {
             filename = _pro + "-" + batchnum + ".json";
             log.info("创建点播文件：" + filename);

        File jsonf = new File(dicpath + filename);
        if (!jsonf.exists()){
            jsonf.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(jsonf);
        log.info("开始写入");
        eduSynHdDbQueryEntities = eduSynHdDbpMapper.findAllItems(_pro, batchnum);
        mb.setAllcount(eduSynHdDbQueryEntities.size());
        mb.setData(eduSynHdDbQueryEntities);

        fos.write(JSON.toJSONString(mb).getBytes());
        fos.flush();
        fos.close();
        log.info(filename + "写入完成");
            fileGencount++;
        }
        log.info(batchnum + "的全部点播文件完成生成！共计" + fileGencount +  "个文件");
    }


    /**
     * 生成全库的文件   调用GeneratedataFile
     * @param dicpath  文件存放根路径
     * @throws IOException
     * @see this.GeneratedataFile()
     */
    public void GenerateAllDataFile(String dicpath) throws IOException {
        List<String> batchnum = eduSynHdDbpMapper.getAllBatchnum();

        for (String _bat: batchnum) {
            log.info("------------------文件批号：" + _bat + "  开始------------------------");
            this.GeneratedataFile(dicpath,_bat);

            log.info("------------------文件批号：" + _bat + "  完成------------------------");

        }
    }
}
