# upstream的指令参数

## 1、max_conns

作用: 限制一台服务器的最大连接数;

默认值: 0,表示不去做任何限制;  

目的: 保护服务器，限流，避免过载

注意：老版本的nginx无法使用此参数（1.11.5只能作用于商业版本）

备注: 使用了多个 worker_processes, 使用多个worker进程，连接总数肯定会大于max_conns

参考配置：

```
# worker进程设置1个，便于测试观察成功的连接数
worker_processes  1;

upstream tomcats {
        server 192.168.1.173:8080 max_conns=2;
        server 192.168.1.174:8080 max_conns=2;
        server 192.168.1.175:8080 max_conns=2;
}
```

## 2、slow_start

作用：缓慢的启动，慢慢加入到集群，会在配置的时间内将权重慢慢升级到配置的权重值，必须要使用在集群里面，必须要和权重一起使用

注意：

1. （商业版本能使用，需要付费）
2. 该参数不能使用在**hash**和**random load balancing**中
3. 如果在 upstream中只有一台server，则该参数失效

参考配置：

```
upstream tomcats {
        server 192.168.1.173:8080 weight=6 slow_start=60s;
        server 192.168.1.174:8080 weight=2;
        server 192.168.1.175:8080 weight=2;
}
```

## 3、down

作用：一种状态，用于标识服务器节点不可用，用户也无法访问

参考配置：

```
upstream tomcats {
        server 192.168.1.173:8080 down;
        server 192.168.1.174:8080 weight=1;
        server 192.168.1.175:8080 weight=1;
}
```

## 4、backup

作用：表明服务器是一台备用机（其他服务挂掉之后，才会被访问）

参考配置：

```
upstream tomcats {
        server 192.168.1.173:8080 backup;
        server 192.168.1.174:8080 weight=1;
        server 192.168.1.175:8080 weight=1;
}
```

 注意：backup 参数不能使用在**hash**和**random load balancing**中

## 5、max_fails

作用：表示失败几次，则标识server已宕机，踢出上游服务

## 6、fail_tiemout

作用：表示失败得重试时间（默认是10s）

假设设置如下：

```
max_fails=2 fail_timeout=15s 
```

则代表在15秒内请求某一server失败达到2次后，则认为该server已经挂了或者宕机了，随后再过15秒，这15秒内不会有新的请求到达刚刚挂掉的节点上，而是会请求到正常运作的server，15秒后会再有新请求尝试连接挂掉的server，如果还是失败，重复上一过程，直到恢复。