package com.xx.study.springbootmybatisplus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootMybatisPlusApplication {
    private final static Logger logger = LoggerFactory.getLogger(SpringbootMybatisPlusApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisPlusApplication.class, args);
        logger.info("SpringbootMybatisPlusApplication run ==============>");
    }
}
