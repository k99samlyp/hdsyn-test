package com.cmcc.andedu.hdsyn;

import com.cmcc.andedu.hdsyn.domain.EduSynHdDb;
import com.cmcc.andedu.hdsyn.domain.EduSynHdDed;
import com.cmcc.andedu.hdsyn.domain.EduSynHdMthsp;
import com.cmcc.andedu.hdsyn.repository.EduSynHdDbMapper;
import com.cmcc.andedu.hdsyn.repository.EduSynHdDedMapper;
import com.cmcc.andedu.hdsyn.repository.EduSynHdMthspMapper;
import com.cmcc.andedu.hdsyn.service.*;
import com.cmcc.andedu.hdsyn.task.HbbCvsGeneratorTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HdsynApplicationTests {

    @Autowired
    HbbCvsGeneratorService hbbCvsGeneratorService;

    @Autowired
    HbbCvsGeneratorTask hbbCvsGeneratorTask;

    @Autowired
    EduSynHdDbQueryService eduSynHdDbQueryService;

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
        //hbbCvsGeneratorService.findAllItems()
        //hbbCvsGeneratorTask.run();

        //eduSynHdDbQueryService.findAllOrder("851","20180523");


        EduSynHdDb eduSynHdDb = new EduSynHdDb();
        eduSynHdDb.setRecType("");//话单记录标记
        eduSynHdDb.setMsgId("");//消息序列号
        eduSynHdDb.setChrgDn("");//计费用户号码
        eduSynHdDb.setThirdDn("");//第三方号码
        eduSynHdDb.setUserType("");//计费号码用户类型
        eduSynHdDb.setOperType("");//业务类型
        eduSynHdDb.setSaleMode("");//优惠模式
        eduSynHdDb.setDiscount("");//折扣率
        eduSynHdDb.setChrgType("");//计费类型
        eduSynHdDb.setSpCode("");//SP代码
        eduSynHdDb.setOperCode("");//业务代码
        eduSynHdDb.setInfoFee(123);//标准信息费
        eduSynHdDb.setDiscountFee(321);//优惠后信息费
        eduSynHdDb.setChrgProv("");//计费用户号码归属省
        eduSynHdDb.setAccessType("");//定制方式
        eduSynHdDb.setApplyTime("");//用户申请时间
        eduSynHdDb.setFinishTime("");//话单记录生成时间
        eduSynHdDb.setChannelId("");//渠道代码
        eduSynHdDb.setImsi("");//IMSI
        eduSynHdDb.setImei("");//手机终端设备标识

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = df.format(new Date());
        eduSynHdDb.setCreateTime(date);//记录创建时间
        eduSynHdDb.setBatchNum(date.substring(0, 8));//批次号


        //eduSynHdDbpMapper.insert(eduSynHdDb);


        EduSynHdDed eduSynHdDed = new EduSynHdDed();
        eduSynHdDed.setMidFlag("");
        eduSynHdDed.setCreateTime("");
        eduSynHdDed.setWoNo("");
        eduSynHdDed.setServiceType("");
        eduSynHdDed.setServiceCode("");
        eduSynHdDed.setCutTime("");
        eduSynHdDed.setCutType("2");
        eduSynHdDed.setCutFee(000);
        eduSynHdDed.setFeeDn("");
        eduSynHdDed.setAddKey1("");
        eduSynHdDed.setAddKey2("");
        eduSynHdDed.setFeeType("");
        eduSynHdDed.setSeq("");
        eduSynHdDed.setOrderType("1");
        eduSynHdDed.setRechargeSeq("");
        eduSynHdDed.setProvince("751");
        eduSynHdDed.setSpId("9800");
        eduSynHdDed.setUseTime("20180101");
        eduSynHdDed.setContentId("444");
        eduSynHdDed.settFlag("2");
        eduSynHdDed.setRechargeSeq("9876123");
        eduSynHdDed.setCreateTime(date);//记录创建时间
        eduSynHdDed.setBatchNum(date.substring(0, 8));//批次号

        //eduSynHdDedMapper.insert(eduSynHdDed);


        EduSynHdMthsp eduSynHdMthsp = new EduSynHdMthsp();

        eduSynHdMthsp.setMidFlag("");
        eduSynHdMthsp.setTdn("11122223333");
        eduSynHdMthsp.setServiceType("");
        eduSynHdMthsp.setServiceCode("");
        eduSynHdMthsp.setFeedN("");
        eduSynHdMthsp.setFeeMonth("");
        eduSynHdMthsp.setSpId("");
        eduSynHdMthsp.setFeeType("");
        eduSynHdMthsp.setMemProperty("");
        eduSynHdMthsp.setChannel("");
        eduSynHdMthsp.setOrderTime(date);
        eduSynHdMthsp.setFee(99823);
        eduSynHdMthsp.setPreferentialFee(98001);
        eduSynHdMthsp.setHdTime("");
        eduSynHdMthsp.setProvince("752");
        eduSynHdMthsp.setHdSeq("");

        eduSynHdMthsp.setCreateDate(date);//记录创建时间
        eduSynHdMthsp.setBatchNum(date.substring(0, 8));//批次号

        //eduSynHdMthspMapper.insert(eduSynHdMthsp);



        //File file = new File("/Users/sam/B_EDU_20180511001.054");
        //eduSynHdDbService.solveingDb(file);


        listcon(new File("/Users/sam/hddata"));

        list(new File("/Users/sam/hddata"));



    }

}
