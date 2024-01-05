# 使用MyBatis数据库逆向生成工具

## 1、pom中引入mapper工具

```xml
<!-- 通用mapper逆向工具 -->
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
    <version>2.1.5</version>
</dependency>
```

## 2、在yml中配置通用mapper

```yml
############################################################
#
# mybatis mapper 配置
#
############################################################
# 通用 Mapper 配置
mapper:
  mappers: com.study.my.mapper.MyMapper
  not-empty: false
  identity: MYSQL
```

## 3、引入MyMapper接口类

```java
package com.study.my.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的MyMapper
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
```

