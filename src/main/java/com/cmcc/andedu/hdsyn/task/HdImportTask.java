package com.cmcc.andedu.hdsyn.task;

import com.cmcc.andedu.hdsyn.service.EduSynHdDbService;
import com.cmcc.andedu.hdsyn.service.EduSynHdDedService;
import com.cmcc.andedu.hdsyn.service.EduSynHdMthspService;
import com.cmcc.andedu.hdsyn.utils.ReadFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

/**
 * Created by LiYangpan on 2018/9/17  4:08 PM.
 * with INTELLIJ IDEA on rmbp osx 10.11
 * 描述:
 */
public class HdImportTask implements Runnable {

    @Autowired
    private EduSynHdDbService dbService;

    @Autowired
    private EduSynHdDedService dedService;

    @Autowired
    private EduSynHdMthspService mthspService;


    public void excuteResove(File hdfile){
        String fname = hdfile.getName();
        if (".999".indexOf(fname) == -1){
            if ("B_EDU".indexOf(fname) > -1){
                dbService.solveingDb(hdfile);
            }
            else if ("DED".indexOf(fname) > -1){
                dedService.solveingDed(hdfile);
            }
            else if ("MTHSP".indexOf(fname) > -1){
                mthspService.solveingMTHSP(hdfile);
            }
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
//        while (true){
//            String filename = ReadFile.getFile_syn();
//            System.out.println("线程：" + Thread.currentThread().getName() + "处理的文件：" + filename);
//            excuteResove(new File(filename));
//            System.out.println("剩余：" + ReadFile.fileL.size());
//        }
        while (true) {
            System.out.println("为空为空为空为空为空为空为空为空为空为空为空为空");
            try {
                Thread.sleep(1000);
            }
            catch (Exception e){

            }
        }
    }
}
