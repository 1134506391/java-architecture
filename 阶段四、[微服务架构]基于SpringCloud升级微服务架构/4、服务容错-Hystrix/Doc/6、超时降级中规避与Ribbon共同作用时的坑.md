# 超时降级中规避与Ribbon共同作用时的坑

我们制定Feign中集成了Ribbion和Hystrix两个组件，它俩都各自有一套超时配置，到底那个超时配置是最终生效的呢？

先来复习一下Ribbion的超时时间计算公式：

> 最大超时时间=
> （连接超时时间+接口超时时间）*（当前节点重试次数+1）*（换节点重试次数+1）

假如经过上述计算，Ribbon的超时时间是2000ms，那么Hystrix的超时时间应该设置成多少才合理呢?我们先来看看Hystrix的默认全局配置：

```
hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=1000
```

以上全局配置设置了Hystrix的熔断时间为1000ms。这里Hystrix的超时时间设置比Ribbon配置的时间短，那么不等Ribbon重试结束，Hystrix判定超时后就会直接执行熔断逻辑。因此，Hystrix和Ribbon是一个共同作用的关系，谁先到达超时指标就会率先起作用。

通常来讲，Hystrix的熔断时间要比Ribbon的最长超时时间设置的略长一些，这样就可以让Ribbon的重试机制充分发挥作用，以免出现还没来得及重试就进入falllback逻辑的情况发生。

那如果我们有一些接口对响应时间的要求特别高，比如说商品详情页接口，元数据必须在2s以内加载返回，那我们怎么针对方法设置更细粒度的Hystrix超时限制?

## 1、Hystrix方法级别超时控制

我们有两个方式针对Method级别做超时判定，我们先来看两个配置例子：

### 1.1、基于方法签名的超时配置

```
hystrix.command.ClassName#methodName(Integer).execution.isolation.thread.timeoutInMilliseconds=1000
```

上面的配置是基于“方法签名”生成的，其中ClassName#methodName(Integer)就是一串类名+方法名+方法参数的组合，对于复杂的方法，人工拼出这一套组合字符串也挺费脑子的，Feign提供了一个简单的工具根据反射机制生成字符串：

```
Feign.configKey(MyService.class, MyService.class.getMethod("findFriend", Integer.class))
```

如果说上面的配置对于你来说太过于麻烦，那你可以采用下面的一种。

### 1.2、基于commandKey的配置

我们在声明@HystrixCommand的时候，可以给方法指定一个CommandKey，就像下面这样：

```
@HystrixCommand(commandKey = "myKey"，fallbackMethod = "fallback")
```

这里我们给方法指定了commandKey为mykey，接下来只要使用myKey来替换方法签名就可以实现同样的效果，是不是更简单了?

```
hystrix.command.myKey.execution.isolation.thread.timeoutInMilliseconds=1000
```

