# Swagger2文档API

为了减少程序员撰写文档时间，提高生产力，Swagger2 应运而生，使用 swagger2 可以减少编写过多的文档，只需要通过代码就能生成文档AP1.提供给前端人员对接，非常方便。

引入依赖

```xml
<!-- swagger2 配置 -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.4.0</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.4.0</version>
</dependency>
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>swagger-bootstrap-ui</artifactId>
    <version>1.6</version>
</dependency>
```

添加配置：

```java
package com.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
// 开启Swagger2
@EnableSwagger2
public class Swagger2 {

//    http://localhost:8088/swagger-ui.html     访问该路径即可查看接口
//    http://localhost:8088/doc.html     原路径

    // 配置swagger2核心配置 docket
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)  // 指定api类型为swagger2
                .apiInfo(apiInfo())                 // 用于定义api文档汇总信息
                .select()
                .apis(RequestHandlerSelectors
                .basePackage("com.study.controller"))   // 指定controller包
                .paths(PathSelectors.any())         // 所有controller
                .build();
    }

    // 用于定义api文档汇总信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("天天吃货 电商平台接口api")        // 文档页标题
                .contact(new Contact("haohao",
                        "https://www.study.com",
                        "abc@study.com"))        // 联系人信息
                .description("专为天天吃货提供的api文档")  // 详细信息
                .version("1.0.1")   // 文档版本号
                .termsOfServiceUrl("https://www.study.com") // 网站地址
                .build();
    }
}
```

