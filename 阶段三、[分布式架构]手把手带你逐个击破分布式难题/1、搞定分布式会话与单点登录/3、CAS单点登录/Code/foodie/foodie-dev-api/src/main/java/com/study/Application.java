package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import tk.mybatis.spring.annotation.MapperScan;

//去除安全自动装配
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
// 扫描 mybatis 通用 mapper 所在的包
@MapperScan(basePackages = "com.study.mapper")
// 扫描所有包以及相关组件包
@ComponentScan(basePackages = {"com.study", "org.n3r.idworker"})
// 开启定时任务
@EnableScheduling
// 开启使用redis作为spring session
@EnableRedisHttpSession
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
