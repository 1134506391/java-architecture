# 整合HiKariCP说明

## 1、pom中引入数据源驱动与mybatis依赖

```xml
<!-- mysql驱动 -->
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>5.1.41</version>
</dependency>
<!-- mybatis -->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.0</version>
</dependency>
```

## 2、在yml中配置数据源和mybatis

```yml
############################################################
#
# 配置数据源信息
#
############################################################
spring:
  datasource:                                           # 数据源的相关配置
      type: com.zaxxer.hikari.HikariDataSource          # 数据源类型：HikariCP
      driver-class-name: com.mysql.jdbc.Driver          # mysql驱动
      url: jdbc:mysql://localhost:3306/foodie-shop-dev?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true
      username: root
      password: root
    hikari:
      connection-timeout: 30000       # 等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生SQLException， 默认:30秒
      minimum-idle: 5                 # 最小连接数
      maximum-pool-size: 20           # 最大连接数
      auto-commit: true               # 自动提交
      idle-timeout: 600000            # 连接超时的最大时长（毫秒），超时则被释放（retired），默认:10分钟
      pool-name: DateSourceHikariCP     # 连接池名字
      max-lifetime: 1800000           # 连接的生命时长（毫秒），超时而且没被使用则被释放（retired），默认:30分钟 1800000ms
      connection-test-query: SELECT 1
          
############################################################
#
# mybatis 配置
#
############################################################
mybatis:
  type-aliases-package: com.imooc.pojo          # 所有POJO类所在包路径
  mapper-locations: classpath:mapper/*.xml      # mapper映射文件
  
```

## 3、内置tomcat

```yml
############################################################
#
# web访问端口号  约定：8088
#
############################################################
server:
  port: 8088
  tomcat:
    uri-encoding: UTF-8
  max-http-header-size: 80KB
```

