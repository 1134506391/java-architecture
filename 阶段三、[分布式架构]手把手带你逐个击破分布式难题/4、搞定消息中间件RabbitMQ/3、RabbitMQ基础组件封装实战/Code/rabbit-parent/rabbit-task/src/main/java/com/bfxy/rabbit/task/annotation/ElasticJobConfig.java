package com.bfxy.rabbit.task.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 自定义注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticJobConfig {
	//elasticjob的名称
    String name();
	// core表达式
    String cron() default "";
	// 分片总数
    int shardingTotalCount() default 1;
	// 分片序列号
    String shardingItemParameters() default "";
	// 作业自定义参数
    String jobParameter() default "";
	// 是否开启任务执行失效转移
    boolean failover() default false;
	// 是否开启错过任务重新执行
    boolean misfire() default true;
	// 作业信息描述
    String description() default "";
	// 本地配置是否可以覆盖注册中心配置
    boolean overwrite() default false;
	// 是否流式处理数据
    boolean streamingProcess() default false;

    String scriptCommandLine() default "";

    boolean monitorExecution() default false;

    public int monitorPort() default -1;    //must

    public int maxTimeDiffSeconds() default -1;    //must

    public String jobShardingStrategyClass() default "";    //must

    public int reconcileIntervalMinutes() default 10;    //must

    public String eventTraceRdbDataSource() default "";    //must

    public String listener() default "";    //must

    public boolean disabled() default false;    //must

    public String distributedListener() default "";

    public long startedTimeoutMilliseconds() default Long.MAX_VALUE;    //must

    public long completedTimeoutMilliseconds() default Long.MAX_VALUE;        //must

    public String jobExceptionHandler() default "com.dangdang.ddframe.job.executor.handler.impl.DefaultJobExceptionHandler";

    public String executorServiceHandler() default "com.dangdang.ddframe.job.executor.handler.impl.DefaultExecutorServiceHandler";

}
