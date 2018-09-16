package com.cmcc.andedu.hdsyn.service;


import com.alibaba.fastjson.JSON;
import com.cmcc.andedu.hdsyn.domain.EduSynHdMthsp;
import com.cmcc.andedu.hdsyn.domain.EduSynHdMthspQueryEntity;
import com.cmcc.andedu.hdsyn.domain.MsgBean;
import com.cmcc.andedu.hdsyn.repository.EduSynHdMthspMapper;
import com.cmcc.andedu.hdsyn.utils.MongoDb;
import com.cmcc.andedu.hdsyn.utils.ReadFile;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * @Author: miaojiaxing
 * @Description:
 * @Date: Created in 2018/7/12 10:59
 * @Modified By:
 * @Version: 1.0
 */
@Service
@ConfigurationProperties(prefix = "hdfile")
public class EduSynHdMthspService {
    private static Logger log = LoggerFactory.getLogger(EduSynHdMthspService.class);

    //static List<EduSynHdMthsp> eduSynHdMthspList = new ArrayList<>();

    @Autowired
    MongoClient mongoClientBean;

    static List<Document> eduSynHdMthspList = new ArrayList<>();

    @Autowired
    private EduSynHdMthspMapper eduSynHdMthspMapper;

    private int batchInsertCount;

    boolean isDivListInsert = false;   //是否分表插入（mybatis批量插入有限制，数据太长，分批提交）

    int hasIn = 0;

    /**
     * @Author: miaojiaxing
     * @Description: 解析包月订购数据，并进行入表操作
     * @Date: Created in 2018/7/12 11:24
     * @Modified By:
     * @Version: 1.0
     */
    public int solveingMTHSP(File file) {
        //读取文件内容
        ReadFile.readFile_s(file,true);
        //List<String> dataList = ReadFile.readFile(file);

        //去除最后一行无用的数据
        ReadFile.RDATA.remove(ReadFile.RDATA.size() - 1);
        if (null != ReadFile.RDATA && ReadFile.RDATA.size() > 0) {
            System.out.println("数据长度：" + ReadFile.RDATA.size());

            MongoCollection<Document> cols = mongoClientBean.getDatabase("paydata").getCollection("mthsp");

            if (ReadFile.RDATA.size() > batchInsertCount){
                isDivListInsert = true;
            }

            for (String listString : ReadFile.RDATA) {
                //将单条数据进行拆分
                String stringSplit[] = listString.split(" ");
                //EduSynHdMthsp eduSynHdMthsp = new EduSynHdMthsp();
//
//                eduSynHdMthsp.setMidFlag("20");//中间记录标记
//                eduSynHdMthsp.setServiceType(stringSplit[0].substring(2, stringSplit[0].length()));//业务类型
//                eduSynHdMthsp.setFeedN(stringSplit[1]);//计费用户号码
//                eduSynHdMthsp.setTdn(stringSplit[2]);//第三方号码
//                eduSynHdMthsp.setSpId(stringSplit[3]);//SP代码
//                eduSynHdMthsp.setServiceCode(stringSplit[4]);//业务代码
//                eduSynHdMthsp.setFeeType(stringSplit[5].substring(0, 2));//计费类别
//                eduSynHdMthsp.setMemProperty(stringSplit[5].substring(2, 3));//会员属性
//                eduSynHdMthsp.setChannel(stringSplit[5].substring(3, 5));//订购渠道
//                eduSynHdMthsp.setFeeMonth(stringSplit[5].substring(5, 11));//结算月份
//                eduSynHdMthsp.setOrderTime(stringSplit[5].substring(11, 25));//最后订购时间
//                eduSynHdMthsp.setFee(Integer.parseInt(stringSplit[5].substring(25, 31)));//标准信息费
//                eduSynHdMthsp.setPreferentialFee(Integer.parseInt(stringSplit[5].substring(31, 37)));//优惠后信息费
//                eduSynHdMthsp.setHdTime(stringSplit[5].substring(37, 45));//话单生成日期
//                eduSynHdMthsp.setProvince(stringSplit[5].substring(stringSplit[5].length() - 3));//用户归属省代码
//                eduSynHdMthsp.setHdSeq(stringSplit[6]);//话单序列号
//
//                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//                String date = df.format(new Date());
//                eduSynHdMthsp.setCreateDate(date);//记录创建日期
//                eduSynHdMthsp.setBatchNum(file.getName().substring(5,13));//批次号


                Document doc = new Document();

                doc.append("MidFlag","20");//中间记录标记
                doc.append("ServiceType",stringSplit[0].substring(2));//业务类型
                doc.append("FeedN",stringSplit[1]);//计费用户号码
                doc.append("Tdn",stringSplit[2]);//第三方号码
                doc.append("SpId",stringSplit[3]);//SP代码
                doc.append("ServiceCode",stringSplit[4]);//业务代码
                doc.append("FeeType",stringSplit[5].substring(0, 2));//计费类别
                doc.append("MemProperty",stringSplit[5].substring(2, 3));//会员属性
                doc.append("Channel",stringSplit[5].substring(3, 5));//订购渠道
                doc.append("FeeMonth",stringSplit[5].substring(5, 11));//结算月份
                doc.append("OrderTime",stringSplit[5].substring(11, 25));//最后订购时间
                doc.append("Fee",Integer.parseInt(stringSplit[5].substring(25, 31)));//标准信息费
                doc.append("PreferentialFee",Integer.parseInt(stringSplit[5].substring(31, 37)));//优惠后信息费
                doc.append("HdTime",stringSplit[5].substring(37, 45));//话单生成日期
                doc.append("Province",stringSplit[5].substring(stringSplit[5].length() - 3));//用户归属省代码
                doc.append("HdSeq",stringSplit[6]);//话单序列号

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String date = df.format(new Date());
                doc.append("CreateDate",date);//记录创建日期
                doc.append("BatchNum",file.getName().substring(5,13));//批次号

                eduSynHdMthspList.add(doc);
                hasIn++;

                if (isDivListInsert && (eduSynHdMthspList.size() == batchInsertCount || hasIn == ReadFile.RDATA.size())){
                    try {

                        //MG插入
                        cols.insertMany(eduSynHdMthspList);

                        //eduSynHdMthspMapper.batchInsert_mysql(eduSynHdMthspList);
                        log.info("插入成功！共计：" + eduSynHdMthspList.size() + "条,还有" + (ReadFile.RDATA.size() - hasIn) + "条");
                        eduSynHdMthspList.clear();
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.info("插入数据异常！");
                        return 0;
                    }
                }
            }

            if (!isDivListInsert){
                try {
                    //MG插入
                    cols.insertMany(eduSynHdMthspList);
                    //eduSynHdMthspMapper.batchInsert_mysql(eduSynHdMthspList);
                    log.info("插入成功！共计：" + eduSynHdMthspList.size() + "条");
                    eduSynHdMthspList.clear();
                    ReadFile.clearDataArea();
                    return 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("插入数据异常！");
                    return 0;
                }
            }
            else{
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
     * @Description: 包月订购数据，按省ID和批次号查询
     * @Date: Created in 2018/7/12 11:24
     * @Modified By:
     * @Version: 1.0
     */
    public List<EduSynHdMthspQueryEntity> findAllOrder(String province, String batchnum) {
        List<EduSynHdMthspQueryEntity> eduSynHdDebQueryData = eduSynHdMthspMapper.findAllItems(province, batchnum);
        return eduSynHdDebQueryData;
    }


    public List<Document> findAllOrder_mongo(String province, String batchnum) {
        MongoCollection<Document> cols = mongoClientBean.getDatabase("paydata").getCollection("mthsp");

        FindIterable<Document> findIterable = cols.find(and(eq("Province",province),eq("BatchNum",batchnum)));

        MongoCursor<Document> mongoCursor = findIterable.iterator();

        List<Document> resL = new ArrayList<>();

        while (mongoCursor.hasNext()){
            resL.add(mongoCursor.next());
        }

        return resL;
    }


    /**
     * 生成当天的数据文件，供省BOSS下载
     * @param batchnum 文件批次号
     */
    public void GeneratedataFile(String dicpath, String batchnum) throws IOException {

        int fileGencount = 0;
        List<EduSynHdMthspQueryEntity> eduSynHdMthspQueryEntities;
        MsgBean mb = new MsgBean();
        String filename ;
        List<String> provices = eduSynHdMthspMapper.getProvicesByBatchnum(batchnum);
        for (String _pro: provices) {
            filename = _pro + "-" + batchnum + ".json";
            log.info("创建包月点播文件：" + filename);

            File jsonf = new File(dicpath + filename);
            if (!jsonf.exists()){
                jsonf.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(jsonf);
            log.info("开始写入");
            eduSynHdMthspQueryEntities = eduSynHdMthspMapper.findAllItems(_pro, batchnum);
            mb.setAllcount(eduSynHdMthspQueryEntities.size());
            mb.setData(eduSynHdMthspQueryEntities);
            fos.write(JSON.toJSONString(mb).getBytes());
            fos.close();
            log.info(filename + "写入完成");
            fileGencount++;
        }

        log.info(batchnum + "的全部包月文件完成生成！共计" + fileGencount +  "个文件");
    }


    /**
     * 生成全库的文件   调用GeneratedataFile
     * @param dicpath  文件存放根路径
     * @throws IOException
     * @see this.GeneratedataFile()
     */
    public void GenerateAllDataFile(String dicpath) throws IOException {
        List<String> batchnum = eduSynHdMthspMapper.getAllBatchnum();

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
