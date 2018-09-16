package com.cmcc.andedu.hdsyn.controller;

import com.cmcc.andedu.hdsyn.domain.EduSynHdDbQueryEntity;
import com.cmcc.andedu.hdsyn.domain.MsgBean;
import com.cmcc.andedu.hdsyn.service.EduSynHdDbService;
import com.cmcc.andedu.hdsyn.task.EduHdFilesGeneratorTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/eduSynHdDbQuery")
@ConfigurationProperties(prefix = "hdfile")
public class EduSynHdDbQueryController {
	
	//@Resource
	//private EduSynHdDbQueryConInter eduSynHdDbQueryConInter;

	@Autowired
	private EduSynHdDbService eduSynHdDbService;

    private String dbpath;


    @GetMapping("/getData_countless")
	@ResponseBody
	public List<EduSynHdDbQueryEntity>  getFirstConditons(@RequestParam String province, @RequestParam String batchnum ) {
		return eduSynHdDbService.findAllOrder(province, batchnum) ;
	}

    @GetMapping("/getData")
    @ResponseBody
    public MsgBean getData(@RequestParam String province, @RequestParam String batchnum ) {
        List<EduSynHdDbQueryEntity> eduSynHdDbQueryEntities = eduSynHdDbService.findAllOrder(province, batchnum);
        MsgBean mb = new MsgBean();
        mb.setAllcount(eduSynHdDbQueryEntities.size());
        mb.setData(eduSynHdDbQueryEntities);
        return mb;
    }

    @GetMapping("/getFile")
    @ResponseBody
    public void getFile(@RequestParam String province, @RequestParam String batchnum, HttpServletResponse httpServletResponse) throws IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        String todayBatchnum = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());    //今日最大下载昨日的话单，故为当日日期-1天
        String filename;
        if (Integer.parseInt(batchnum) > Integer.parseInt(todayBatchnum)){
            httpServletResponse.setHeader("content-type", "text/plain;charset=UTF-8");
            httpServletResponse.setContentType("text/plain;charset=UTF-8");
            OutputStream os = httpServletResponse.getOutputStream();
            os.write("该批次点播话单数据还未到下载日期！".getBytes());
        }
        else if (EduHdFilesGeneratorTask.READ_LOCK && todayBatchnum.equals(batchnum)){
            httpServletResponse.setHeader("content-type", "text/plain;charset=UTF-8");
            httpServletResponse.setContentType("text/plain;charset=UTF-8");
            OutputStream os = httpServletResponse.getOutputStream();
            os.write("该批次点播话单数据生成中，请10分钟后再试！".getBytes());
        }
        else {
            filename = province + "-" + batchnum + ".json";
            File _tfile = new File(dbpath + filename);
            if (_tfile.exists()){
                httpServletResponse.setHeader("content-type", "application/octet-stream");
                httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + filename);
                httpServletResponse.setContentType("application/octet-stream");
                httpServletResponse.setContentLengthLong(_tfile.length());

                OutputStream os = httpServletResponse.getOutputStream();
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(_tfile));
                byte[] buff = new byte[1024];
                int i = bis.read(buff);
                while (i != -1){
                    os.write(buff,0, buff.length);
                    os.flush();
                    i = bis.read(buff);
                }
                bis.close();
                os.close();
            }
            else {
                httpServletResponse.setHeader("content-type", "text/plain;charset=UTF-8");
                //httpServletResponse.setHeader("Content-Disposition", "attachment;filename=ts.json");
                httpServletResponse.setContentType("text/plain;charset=UTF-8");
                OutputStream os = httpServletResponse.getOutputStream();
                os.write("您所在省份无该批次点播话单数据！".getBytes());
            }
        }
    }

    public String getDbpath() {
        return dbpath;
    }

    public void setDbpath(String dbpath) {
        this.dbpath = dbpath;
    }
}