package com.lzw.face;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动类
 * @author jamesluozhiwei
 * @since 2019-12-23
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(value = "com.lzw.face.mapper")
public class FaceSearchApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaceSearchApiApplication.class, args);
    }

}
