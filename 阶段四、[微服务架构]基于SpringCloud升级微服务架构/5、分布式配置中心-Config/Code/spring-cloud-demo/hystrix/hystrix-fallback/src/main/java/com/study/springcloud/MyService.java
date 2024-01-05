package com.study.springcloud;

import com.study.springcloud.hystrix.Fallback;
import org.springframework.cloud.openfeign.FeignClient;


// 报错进入Fallback类中
@FeignClient(name = "feign-client", fallback = Fallback.class)
public interface MyService extends IService {

}
