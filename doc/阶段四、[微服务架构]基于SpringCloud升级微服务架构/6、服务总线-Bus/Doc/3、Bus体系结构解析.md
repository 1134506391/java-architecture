# Bus体系结构解析

前面我们了解了Bus是做什么的，这一节我们就来了解下Bus最主要的“消息广播”场景，通过广播的推送过程了解Bus的体系结构。

在这之前，我们先来了解一下这个所谓的广播(发布-订阅)模式是个什么东东，其实它就和追星模式是一样一样的。

## 1、追星模式

在这个追星模式中，有以下三个角色：

**1.主办方：**作为消息发布者，是一个中间层，一头对接粉丝另一头对接明星，专门发布”明星到来”的消息；

**2.粉丝团：**作为事件监听者，时刻监听明星们的动态，从主办方获取”明星到来”的消息，然后作出响应；

**3.明星：**作为事件主体，”明星到来“作为一个事件，由主办方发布出去，同时获得关注者的响应。

上面这个模式对应到Bus的休系结构中来说，Bus就是这个主办方，负责发布消息，各个监听消息的服务节点就是粉丝团，时刻等待着响应新消息；配置变更就相当于是个“明星到来”的事件，由Bus广播发布到所有服务节点。

接下来，我们就拿Bus中内置的“配置刷新”事件，分别从消息发布者、监听者和事件一个角色做个介绍。

## 2、事件的结构

在Bus中配置刷新的事件类是RefreshRemoteApplicationEvent，在Bus的规范下，所有广播事件都包含了三个维度的信息：

**Source：** 这是一个必填信息，它可以是一个自定义并且能够被序列化/反序列化的POJO对象，它包含了一个事件的想要传达的信息。

**Original Service：** 消息来源方，通常是事件发布方的机器ID，或者Appld等。

**Destination Service：** 目标机器，Bus会根据Destination Service指定的过滤条件(比如服务名，端口等)，只让指定的监听者响应事件。

## 3、消息发布者

我们所有的“事件”都是通过Bus来发布的，Bus默认提供了两个Endpoint作为消息发布者：

1. **bus-env：**在本地发布EnvironmentChangeRemoteApplicationEvent事件，表示一个远程环境变更事件，进一步查看这个事件的内容，我们发现其中包含了一个`Map<String，String>`属，事件监听者接收到这个事件之后，会将事件中的Map添加到Spring环境变量中（由Spring Cloud 的 EnvironmentManager负责具体处理），从而达到修改环境变量的目的；
2. **bus-refresh：**发布RefreshRemoteApplicationEvent事件，表示一个远程配置刷新事件，这个事件会触发@RefreshScope注解所修饰的Java类中属性的刷新。

以上两个Endpoint就是Bus通过"/actuator”服务对外提供出来的。

## 4、消息监听者

Bus中默认创建了两个消息监听器，分别对应上面的两个消息发布Endpoints。

**1、EnvironmentChangeListener：**用来监听远程环境变更事件，将事件中传递的环境变量挨个加入到本地上下文中；

**2、RefreshListener：**用来监听远程配置刷新事件，其底层是通过触发另外的两Spring Cloud的事件EnvironmentChangeEvent和RefreshScopeRefreshedEvent，最终实现属性刷新。

