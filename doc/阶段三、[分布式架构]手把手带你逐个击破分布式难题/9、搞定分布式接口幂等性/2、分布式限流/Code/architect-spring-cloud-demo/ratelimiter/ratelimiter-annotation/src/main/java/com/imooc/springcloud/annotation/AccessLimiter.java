package com.imooc.springcloud.annotation;

import java.lang.annotation.*;

/**
 * Created by 半仙.
 */
@Target({ElementType.METHOD})
// 运行级别
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimiter {

    int limit();

    String methodKey() default "";

}
