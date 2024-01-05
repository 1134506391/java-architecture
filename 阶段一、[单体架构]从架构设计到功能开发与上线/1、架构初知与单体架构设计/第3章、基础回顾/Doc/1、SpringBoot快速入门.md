# SpringBoot快速入门

## 1.什么是SpringBoot?

Spring Boot 是由 Pivotal 团队提供的全新框架，其设计目的是用来简化新 Spring 应用的初始搭建以及开发过程，该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。Spring Boot 默认配置了很多框架的使用方式，就像 Maven 整合了所有的 Jar 包，Spring Boot 整合了所有的框架。它的核心设计思想是：**约定优于配置**，Spring Boot 所有开发细节都是依据此思想进行实现的，

那什么是约定优于配置呢?

约定优于配置(Convention Over Configuration)，也称作按约定编程，是一种软件设计范式，旨在减少软件开发人员需做决定的数量、获得简单的好处，而又不失灵活性。

本质是说，开发人员仅需规定应用中不符约定的部分。例如，如果模型中有个名为 User 的类，那么数据库中对应的表就会默认命名为user，具有在偏离这一约定时，例如将该表命名为“user info”，才需写有关这个名字的配置。

我们可以按照这个思路来设想，我们约定 Controller 层就是 Web 请求层可以省略 MVC 的配置;我们约定在Service 结尾的类自动注入事务，就可以省略了 Spring 的切面事务配置。

在Spring 体系中，Spring Boot JPA 就是约定优于配置最佳实现之一，不需要关注表结构，我们约定类名即是表名，属性名即是表的字段，String 对应 varchar，long 对应 bigint，只有需要一些特殊要求的属性，我们再单独进行配置，按照这个约定我们可以将以前的工作大大简化。

Spring Boot 体系将约定优于配置的思想展现得淋漓尽致，小到配置文件、中间件的默认配置，大到内置容器、生态中的各种 Starters 无不遵循此设计规则。Spring Boot 鼓励各软件组织方创建自己的 Starter，创建 Starter 的核心组件之一就是autoconfiqure 模块，也是 Starter 的核心功能，在启动的时候进行自动装配属性默认化配置。

可以说正是因为 Sprinq Boot 简化的配置和众多的 Starters 才让 Spring Boot 变得简单、易用、快速上手，也可以说正是约定优于配置的思想彻底落地才让 Spring Boot 走向辉煌。Spring Boot 约定优于配置的思想让 Spring Boot 项目非常容易上手，让编程变得更简单，其实编程本该很简单，简单才是编程的美。

### Starters

Spring Boot Starters 基干约定优于配置的理念来设计，Spring Boot Starter 中有两个核心组件:自动配置代码和提供自动配置模块及其它有用的依赖。也就意味若当我们项目中引入基个 Starter，即拥有了此软件的默认使用能力，除非我们需要特定的配置，一般情况下我仅需要少量的配置或者不配置即可使用组件对应的功能。

Spring Boot 由众多 Starter 组成，随着版本的推移 Starter 家族成员也与日俱增。在传统 Maven 项目中通常将一些层、组件拆分为模块来管理，以便相互依赖复用，在 Spring Boot 项目中我们则可以创建自定义 Spring Boot Starter 来达成该目的。

Spring Boot 拥有强大融合社区开源软件的能力，在没有使用Spring Boot 之前，我们需要按照每个开源软件的特性，将对应的组件包集成到我们的开发项目中，因为每个组件的设计理念和开发团队都不一致，因此会有很多不同的调用风格在我们的项目中。

Spring Boot 整合了主流的开源软件形成了一系列的 Starter，让我们有了一致的编程体验来集成各种软件，Spring Boot 在集成的时候做了大量的优化，让我们在集成的时候往往只需要很少的配置和代码就可以完成。可以说各种 Starters 就是 Spring Boot 最大的优势之一。

Spring Boot 是一套全新的框架，它来自于 Spring 大家族，因此 Spring 所有具备的功能它都有并且更容易使用；同时还简化了基于 Spring 的应用开发，通过少量的代码就能创建一个独立的、产品级别的 Spring 应用。

## 2.Spring Boot 有哪些特性?

- 使用 Spring 项目引导页面可以在几秒构建一个项目;
- 方便对外输出各种形式的服务，如 REST API、WebSocket、Web、Streaming、Tasks;
- 非常简洁的安全策略集成;
- 支持关系数据库和非关系数据库;
- 支持运行期内嵌容器，如 Tomcat、Jetty;。强大的开发包，支持热启动;
- 自动管理依赖;
- 自带应用监控;
- 支持各种IDE，如 IntelliJ IDEA、NetBeans。

## 3.为什么学习 Spring Boot ?

Spring Boot 本身并不提供 Spring 框架的核心特性以及扩展功能，只是用干快速，敏捷地开发新一代基于 Spring框架的应用程序。同时它集成了大量常用的第三方库配置(如Redis、MongoDB、JPA、RabbitMQ、Quartz 等)，SpringBoot 应用中这些第三方库几乎可以零配置进行开箱即用，大部分的 Spring Boot 应用都只需要非常少量的配置代码，开发者能够更加专注于业务逻辑。

使用 Spring Boot 开发项目，有以下几方面优势:

- Sprinq Boot 使开发变得简单，提供了丰富的解决方案，快速集成各种解决方案提升开发效率；

- Spring Boot 使配置变得简单，提供了丰富的 Starters，集成主流开源产品往往只需要简单的配置即可；

- Spring Boot 使部署变得简单，其本身内嵌启动容器，仅仅需要一个命令即可启动项目，结合 Jenkins，Docker 自动化运维非常容易实现；

- Spring Boot 使监控变得简单，自带监控组件，使用 Actuator 轻松监控服务各项状态。


从软件发展的角度来讲，越简单的开发模式越流行，简单的开发模式解放出更多生产力，让开发人员可以避免将精力耗费在各种配置，语法所设置的门槛上，从而更专注于业务。这点上，Spring Boot 已尽可能地简化了应用开发的门槛。

Spring Boot 所集成的技术栈，涵盖了各大互联网公司的主流技术，跟着 Spring Boot 的路线去学习，基本可以了解国内外互联网公司的技术特点。

## 4.Spring、spring Boot 和 Spring Cloud 有什么关系?

Spring最初核心的两大核心功能Spring loC 和Spring Aop成就了Spring，Spring在这两大核心功能上不断地发展才有了Sprinq事务、Spring MVC等一系列伟大的产品，最终成就了 Spring 帝国，到了后期 Spring 几乎可以解决企业开发中的所有问题。

Spring Boot是在强大的 Spring 带国生态基础上面发展而来，发明 Spring Boot 不是为了取代 Spring，是为了让人们更容易的使用 Spring。所以说没有 Spring 强大的功能和生态，就不会有后期 SpringBoot 的火热，Spring Boot 使用约定优于配置的理念，重新重构了 Spring的使用，让 Spring后续的发展更有生命力。

Spring 并没有重复制造轮子，它只是将目前各家公司开发的比较成熟、经得起实际考验的服务框架组合起来，通过 Spring Boot 风格进行再封装并屏蔽掉复杂的配置和实现原理，最终给开发者提供了一套简单易懂，易部署，易维护的分布式系统开发工具包。

Spring Cloud 是一系列框架的有序集合，它利用 Spring Boot 的开发便利性巧妙地简化了分布式系统基础设施的开发。服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用 Spring Boot 的开发风格做到一键启动和部署。

Spring Cloud 是为了解决微服务架构中服务治理而提供的具备一系列功能的开发框架，Spring Cloud 是完全基于 Spring Boot而开发，Sprina Cloud利用Sprina Boot 特性整合了开源行业中优秀的组件，整体对外提供了一套在微服务架构中服务治理的解决方案。

综上我们可以这样来理解，正是由于Spring loC 和 Spring Aop两个强大的功能才有了Spring，Spring 生态不断的发展才有了 Spring Boot，使用 Spring Boot 让 Spring 更易用更有生命力，Spring Cloud 是基于 Spring Boot 开发的一套微服务架构下的服务治理方案。

它们之间的关系:

> Spring loc/Aop> Spring> Spring Boot> Spring Cloud

