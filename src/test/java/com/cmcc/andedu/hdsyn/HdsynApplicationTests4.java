package com.cmcc.andedu.hdsyn;

import com.cmcc.andedu.hdsyn.repository.EduSynHdDbMapper;
import com.cmcc.andedu.hdsyn.repository.EduSynHdDedMapper;
import com.cmcc.andedu.hdsyn.repository.EduSynHdMthspMapper;
import com.cmcc.andedu.hdsyn.service.EduSynHdDbService;
import com.cmcc.andedu.hdsyn.service.EduSynHdDedService;
import com.cmcc.andedu.hdsyn.service.EduSynHdMthspService;
import com.cmcc.andedu.hdsyn.service.HbbCvsGeneratorService;
import com.cmcc.andedu.hdsyn.task.EduHdFilesGeneratorTask;
import com.cmcc.andedu.hdsyn.task.HbbCvsGeneratorTask;
import com.cmcc.andedu.hdsyn.task.HdImportTask;
import com.cmcc.andedu.hdsyn.utils.ReadFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HdsynApplicationTests4 {

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
                ReadFile.fileQueue.add(_f.getAbsolutePath());
                allFc++;
            }
        }
    }


    @Test
    public void contextLoads() throws Exception {

        listcon(new File("/Users/sam/hddata2"));

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(new HdImportTask(dbService,dedService,mthspService));

        //executorService.submit(new HdImportTask(dbService,dedService,mthspService));

        //executorService.submit(new HdImportTask(dbService,dedService,mthspService));

        //executorService.submit(new HdImportTask(dbService,dedService,mthspService));

        executorService.shutdown();

        executorService.awaitTermination(1, TimeUnit.DAYS);

        //executorService.execute(new HdImportTask());

        //executorService.execute(new HdImportTask());

    }

}
