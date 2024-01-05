package com.study.springcloud;

import com.study.springcloud.rules.MyRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 半仙.
 */
@Configuration
@RibbonClient(name = "eureka-client", configuration = MyRule.class)
public class RibbonConfiguration {
//    @Bean
//    public IRule defaultLBStrategy() {
//        return new RandomRule();
//    }
}

