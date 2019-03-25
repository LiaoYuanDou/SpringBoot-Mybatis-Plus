package com.xx.study.springbootmybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("com.xx.study.springbootmybatisplus.mapper")
public class SpringbootMybatisPlusApplication {
    private final static Logger logger = LoggerFactory.getLogger(SpringbootMybatisPlusApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisPlusApplication.class, args);
        logger.info("SpringbootMybatisPlusApplication run ==============>");
    }
}
