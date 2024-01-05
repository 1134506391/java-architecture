package com.study.springcloud.hystrix;

import com.study.springcloud.Friend;
import com.study.springcloud.MyService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Request Cache减压测试

@Slf4j
@Service
public class RequestCacheService {

    @Autowired
    private MyService service;

    // 将Friend作为本地内存缓存到Cache中
    @CacheResult
    // 简化配置文件中的方法级别超时时间设置
    @HystrixCommand(commandKey = "cacheKey")
    // 将name作为从缓存中获取信息的key值
    public Friend requestCache(@CacheKey String name) {
        log.info("request cache " + name);
        Friend friend = new Friend();
        friend.setName(name);
        friend = service.sayHiPost(friend);
        log.info("after requesting cache " + name);
        return friend;
    }
}
