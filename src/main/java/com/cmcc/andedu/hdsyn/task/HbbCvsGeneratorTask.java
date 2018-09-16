package com.cmcc.andedu.hdsyn.task;

import com.cmcc.andedu.hdsyn.domain.HbbOrderGdEntity;
import com.cmcc.andedu.hdsyn.service.HbbCvsGeneratorService;
import com.cmcc.andedu.hdsyn.utils.CSVUtils;
import com.cmcc.andedu.hdsyn.utils.SftpUtils;
import com.jcraft.jsch.ChannelSftp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by LiYangpan on 2018/7/17  3:21 PM.
 * with INTELLIJ IDEA on rmbp osx 10.11
 * 描述: 广东和宝贝，定时取数据生成CSV文件（每次是全库查询，生成一个CSV文件。如果是需要增量查询，需要重构功能）
 */
@Component("HbbCvsGeneratorTask")
@ConfigurationProperties(prefix = "hbbsftp")
public class HbbCvsGeneratorTask implements Runnable{
    private String port;

    private String host;

    private String username;

    private String password;

    private static final Logger LOGGER = LoggerFactory.getLogger(HbbCvsGeneratorTask.class);

    @Autowired
    private HbbCvsGeneratorService hbbCvsGeneratorService;


    private boolean GenerateCvs(){
        try {
            List<HbbOrderGdEntity> cvsData = hbbCvsGeneratorService.findAllItems();

            Calendar calendar = Calendar.getInstance();
            File csvFile = new File(new SimpleDateFormat("yyyyMMddhhmmss").format(calendar.getTime())+"_BABY_ORDER_GROUP.csv");
            if(!csvFile.exists()){
                csvFile.createNewFile();
            }
            List<Object> list = new ArrayList<>();
            list.add(0,"订单ID,时间戳,省份代码,用户ID,组合包资费ID,业务代码,手机号,用户手机号,业务类型,支付流水号,订购时间,退订时间,生效时间,学生ID");
            list.addAll(cvsData);

            if (CSVUtils.exportCsv(csvFile,list,HbbOrderGdEntity.class)){
                LOGGER.info("开始FTP上传");
                //LOGGER.info("--"+ host + "/" + username + "/" + password);

                SftpUtils sftpUtils = new SftpUtils(host,username,password,null,null,Integer.parseInt(port));
                ChannelSftp channelSftp = sftpUtils.connectSFTP();
                LOGGER.info("连接成功，开始上传");
                sftpUtils.upload(".",csvFile,channelSftp);
                sftpUtils.disconnected(channelSftp);
                LOGGER.info("上传成功");
                csvFile.delete();
                LOGGER.info("临时文件删除");
                return true;
            }
            else {
                return false;
            }
        }

        catch (Exception e){
            LOGGER.error("错误发生：" + e.getMessage());
            e.printStackTrace();
            return false;
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
    public void run(){
        LOGGER.info("开始生成");
        GenerateCvs();
        LOGGER.info("生成成功了");
    }



    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
