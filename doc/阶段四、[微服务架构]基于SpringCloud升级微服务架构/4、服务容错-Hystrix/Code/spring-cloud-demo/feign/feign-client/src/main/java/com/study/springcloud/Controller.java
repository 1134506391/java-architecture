package com.study.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 半仙.
 */
@RestController
@Slf4j
public class Controller implements IService {

    @Value("${server.port}")
    private String port;


    @GetMapping("/sayHi2")
    public String sayHi2() {
        return "This is " + port;
    }

    @Override
    public String sayHi() {
        return "This is " + port;
    }

    @Override

    public Friend sayHiPost(@RequestBody Friend friend) {
        log.info("You are " + friend.getName());
        friend.setPort(port);
        return friend;
    }
    // 添加重试机制
    @Override
    public String retry(@RequestParam(name = "timeout") int timeout) {
        while (--timeout >= 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        log.info("retry " + port);
        return port;
    }
    // hystrix组件测试使用
    @Override
    public String error() {
        throw new RuntimeException("black sheep");
    }
}
