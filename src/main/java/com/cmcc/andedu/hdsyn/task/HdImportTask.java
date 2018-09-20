package com.cmcc.andedu.hdsyn.task;

import com.cmcc.andedu.hdsyn.service.EduSynHdDbService;
import com.cmcc.andedu.hdsyn.service.EduSynHdDedService;
import com.cmcc.andedu.hdsyn.service.EduSynHdMthspService;
import com.cmcc.andedu.hdsyn.utils.ReadFile;
import java.io.File;

/**
 * Created by LiYangpan on 2018/9/17  4:08 PM.
 * with INTELLIJ IDEA on rmbp osx 10.11
 * 描述:
 */
public class HdImportTask implements Runnable {

    private EduSynHdDbService dbService;

    private EduSynHdDedService dedService;

    private EduSynHdMthspService mthspService;

    public HdImportTask(EduSynHdDbService dbS, EduSynHdDedService dedS,EduSynHdMthspService mthspS){
        this.dbService = dbS;
        this.dedService = dedS;
        this.mthspService = mthspS;
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
        while (!ReadFile.ise()){
            String filename = ReadFile.getFile_syn();
            System.out.println("线程：" + Thread.currentThread().getName() + "处理的文件：" + filename + "--------------------剩余：" + ReadFile.fileQueue.size());
            excuteResove(new File(filename));

//            try {
//                Thread.sleep(500);
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
        }
//        while (true) {
//            System.out.println("线程：" + Thread.currentThread().getName()  + "为空为空为空为空为空为空为空为空为空为空为空为空");
//            try {
//                Thread.sleep(1000);
//            }
//            catch (Exception e){
//
//            }
//        }
    }
}
