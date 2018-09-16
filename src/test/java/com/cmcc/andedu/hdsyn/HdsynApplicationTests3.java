package com.cmcc.andedu.hdsyn;

import com.cmcc.andedu.hdsyn.domain.EduSynHdMthsp;
import com.cmcc.andedu.hdsyn.utils.MongoDb;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HdsynApplicationTests3 {

    @Autowired
    MongoClient mongoClientBean;

    @Test
    public void contextLoads() throws Exception {

//        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
//        MongoCollection<Document>  cols = mongoClient.getDatabase("paydata").getCollection("mthsp");
//
//        for (Document doc : cols.find()){
//            System.out.println(doc.toString());
//        }


        MongoCollection<Document> cols  = mongoClientBean.getDatabase("paydata").getCollection("mthsp");

        cols.find();

//        EduSynHdMthsp eduSynHdMthsp = new EduSynHdMthsp();
//
//        eduSynHdMthsp.setTdn("123131");
//
//        List<Document> ld = new ArrayList<>();
//
//        Document doc = new Document();
//        doc.append("tdn","1234567890");
//        ld.add(doc);
//
//        Document doc1 = new Document();
//        doc1.append("tdn","987654321");
//        ld.add(doc1);
//
//        cols.insertMany(ld);

    }

}
