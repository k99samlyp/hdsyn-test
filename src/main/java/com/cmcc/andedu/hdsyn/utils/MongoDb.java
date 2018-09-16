package com.cmcc.andedu.hdsyn.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;

/**
 * Created by LiYangpan on 2018/9/9  5:06 PM.
 * with INTELLIJ IDEA on rmbp osx 10.11
 * 描述:
 */
public class MongoDb {
    @Bean
    public MongoClient mongoClientBean(){
        return MongoClients.create("mongodb://127.0.0.1:27017");
    }
}
