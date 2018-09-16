package com.cmcc.andedu.hdsyn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling   //定时生成CSV功能暂时停用
@SpringBootApplication(scanBasePackages = "com.cmcc.andedu.hdsyn")
@MapperScan("com.cmcc.andedu.hdsyn.repository")

public class HdsynApplication {
    public static void main(String[] args) {
        SpringApplication.run(HdsynApplication.class, args);
    }
}
