package cn.bupt.zcc.dynamicquartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.bupt.zcc.dynamicquartz.dao")
@SpringBootApplication
public class DynamicquartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicquartzApplication.class, args);
    }
}
