package com.zhanghk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhanghk.dao") //启动项加相关启动扫描
public class ZZUlostfound {

    public static void main(String[] args) {
        SpringApplication.run(ZZUlostfound.class, args);
    }

}
