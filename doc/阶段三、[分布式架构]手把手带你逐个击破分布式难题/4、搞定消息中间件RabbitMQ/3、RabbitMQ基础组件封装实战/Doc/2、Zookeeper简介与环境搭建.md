# Zookeeper简介与环境搭建

##### 1、Zookeeper基础知识、体系结构、数据模型

Zookeeper是一个类似linux、hdfs的树形文件结构，zookeeper可以用来保证数据在(Zookeeper)集群之间的数据的事务性一致性，Zookeeper也是我们常说的CAP理论中的CP(强一致性)；

Zookeeper有一个概念叫watch(也称之为事件)，是一次性触发的，当watch监视的数据发生变化时，通知设置了该watch的client端，即watcher实例对象(用于改变节点的变化而做出相应的行为)；

关于Zookeeper其他相关内容，小伙伴可以参考一些相关的资料，在这里我们仅仅使用Zookeeper做注册中心:

##### 2、Zookeeper的三个角色

Zookeeper有三个角色：Leader，Follower，Observer

**Leader**：数据总控节点，用于接收客户端连接请求，分发给所有的Follower节点后，各个Follower节点进行更新数据操作并返回给Leader节点，如果满足半数以上(所以Zookeeper集群一般是奇数个节点)更新成功则此次操作成功；

**Follower**：相当于跟随者的角色，Zookeeper的Leader宕机(挂掉)时，所有的Follower角色内部会产生选举机制，选举出新的Leader用于总控；

**Observer**：顾名思义，就是我们的客户端，用于观察Zookeeper集群的数据发送变化，如果产生变化则Zookeeper会主动推送watch事件给Observer(客户端)，用于对数据变化的后续处理；当然Observer(客户端)也可以发送数据变更请求。

##### 3、Zookeeper应用场景

1. 统一命名服务(Name Service)
2. 配置管理(Configuration Management)
3. 集群管理(Group Membership)
4. 共享锁(Locks)
5. 队列管理

##### 4、Zookeeper集群环境搭建与配置

```
1. 准备工作：
## 准备3个节点，要求配置好主机名称，服务器之间系统时间保持一致
## 注意 /etc/hostname 和 /etc/hosts 配置主机名称（在这个里我准备bhz125,bhz126,bhz127三节点）
## 特别注意 以下操作3个节点要同时进行操作哦！


2. 上传zk到三台服务器节点
## 注意我这里解压到/usr/local下
2.1 进行解压： tar zookeeper-3.4.6.tar.gz
2.2 重命名： mv zookeeper-3.4.6 zookeeper
2.3 修改环境变量： vim /etc/profile 
## 这里要添加zookeeper的全局变量
export ZOOKEEPER_HOME=/usr/local/zookeeper
export PATH=.:$ZOOKEEPER_HOME/bin
2.4 刷新环境变量： source /etc/profile
2.5 到zookeeper下修改配置文件： 
2.5.1 首先到指定目录： cd /usr/local/zookeeper/conf
2.5.2 然后复制zoo_sample.cfg文件，复制后为zoo.cfg： mv zoo_sample.cfg zoo.cfg
2.5.3 然后修改两处地方, 最后保存退出：
(1) 修改数据的dir
dataDir=/usr/local/zookeeper/data
(2) 修改集群地址
server.0=bhz125:2888:3888
server.1=bhz126:2888:3888
server.2=bhz127:2888:3888

2.6 增加服务器标识配置，需要2步骤，第一是创建文件夹和文件，第二是添加配置内容： 
(1) 创建文件夹： mkdir /usr/local/zookeeper/data
(2) 创建文件myid 路径应该创建在/usr/local/zookeeper/data下面，如下：
	vim /usr/local/zookeeper/data/myid
	注意这里每一台服务器的myid文件内容不同，分别修改里面的值为0，1，2；与我们之前的zoo.cfg配置文	 件里：server.0，server.1，server.2 顺序相对应，然后保存退出；

2.7 到此为止，Zookeeper集群环境大功告成！启动zookeeper命令
	启动路径：/usr/local/zookeeper/bin（也可在任意目录，因为配置了环境变量）
	执行命令：zkServer.sh start (注意这里3台机器都要进行启动，启动之后可以查看状态)
	查看状态：zkServer.sh status (在三个节点上检验zk的mode, 会看到一个leader和俩个follower)
```

##### 5、Zookeeper客户端操作

```
zkCli.sh 进入zookeeper客户端
根据提示命令进行操作： 
查找：ls /   ls /zookeeper
创建并赋值： create /imooc zookeeper
获取： get /imooc 
设值： set /imooc zookeeper1314 
PS1: 任意节点都可以看到zookeeper集群的数据一致性
PS2: 创建节点有俩种类型：短暂（ephemeral） 持久（persistent）, 这些小伙伴们可以查找相关资料，我们这里作为入门不做过多赘述！
```

##### 6、Zookeeper核心配置

核心配置详解：（zoo.cfg 配置文件，扩展内容）

```
（1）tickTime：基本事件单元，以毫秒为单位。这个时间是作为 Zookeeper 服务器之间或客户端与服务器之间维持心跳的时间间隔，也就是每隔 tickTime时间就会发送一个心跳。
				
（2）initLimit：这个配置项是用来配置 Zookeeper 接受客户端初始化连接时最长能忍受多少个心跳时间间隔数，当已经超过 10 个心跳的时间（也就是 tickTime）长度后 Zookeeper 服务器还没有收到客户端的返回信息，那么表明这个客户端连接失败。总的时间长度就是 10*2000=20 秒。
		
（3）syncLimit：这个配置项标识 Leader 与 Follower 之间发送消息，请求和应答时间长度，最长不能超过多少个 tickTime 的时间长度，总的时间长度就是 5*2000=10 秒
				
（4）dataDir：存储内存中数据库快照的位置，顾名思义就是 Zookeeper 保存数据的目录，默认情况下，Zookeeper 将写数据的日志文件也保存在这个目录里。
   
（5）clientPort： 这个端口就是客户端连接 Zookeeper 服务器的端口，Zookeeper 会监听这个端口，接受客户端的访问请求。

（6）至于最后的配置项：server.A = B:C:D: 
A表示这个是第几号服务器,
B 是这个服务器的 ip 地址；
C 表示的是这个服务器与集群中的 Leader 服务器交换信息的端口；
D 表示的是万一集群中的 Leader 服务器挂了，需要一个端口来重新进行选举，选出一个新的 Leader
```

参考资料：

[《Zookeeper集群搭建》]: https://www.cnblogs.com/ysocean/p/9860529.html#_label0%EF%BC%8C%E6%88%91%E7%9C%8B%E5%A5%BD%E5%A4%9A%E8%AF%B4%E6%B2%A1%E6%9C%89%E6%90%AD%E5%BB%BAzookeeper%E7%9A%84%E5%B0%8F%E4%BC%99%E4%BC%B4%EF%BC%8C%E8%BF%99%E4%B8%AA%E7%A1%AE%E5%AE%9E%E5%BE%88%E7%AE%80%E5%8D%95%EF%BC%8C%E8%BF%99%E4%B8%AA%E8%BF%9E%E6%8E%A5%EF%BC%8C%E6%8C%89%E7%85%A7%E6%AD%A5%E9%AA%A4%E6%90%AD%E5%BB%BA%E5%B0%B1%E5%A5%BD%E4%BA%86%EF%BC%8C%E4%BF%9D%E8%AF%81%E4%BD%A0%E8%83%BD%E6%88%90%E5%8A%9F%EF%BC%8C%E5%82%BB%E7%93%9C%E5%BC%8F%E7%9A%84%E6%95%99%E5%AD%A6%EF%BC%8C%E5%BE%88%E8%AF%A6%E7%BB%86%E3%80%82%E8%BF%99%E6%98%AF%E6%9C%AC%E5%9C%B0%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%9A%84%EF%BC%8C%E5%A4%A7%E5%AE%B6%E7%9C%8B%E4%B8%8B%EF%BC%8C%E8%BF%99%E4%B8%AA%E6%98%AF%E6%9E%B6%E6%9E%84%E8%AF%BE%E7%A8%8B%EF%BC%8C%E5%A6%82%E6%9E%9C%E5%BE%88%E5%A4%9A%E7%AE%80%E5%8D%95%E7%9A%84%E4%B8%9C%E8%A5%BF%E8%AE%B2%E8%B5%B7%E6%9D%A5%E7%9A%84%E8%AF%9D%EF%BC%8C%E4%B8%8D%E5%A6%82%E8%AE%A9%E8%80%81%E5%B8%88%E5%A4%9A%E8%8A%B1%E7%82%B9%E6%97%B6%E9%97%B4%E8%AE%B2%E4%B8%80%E4%BA%9B%E6%8B%94%E9%AB%98%E7%9A%84%E4%B8%9C%E8%A5%BF%EF%BC%8C%E6%97%A2%E7%84%B6%E5%AD%A6%E5%88%B0%E8%BF%99%E9%87%8C%E5%A4%A7%E5%AE%B6%E9%83%BD%E6%9C%89%E5%9F%BA%E7%A1%80%E4%BA%86%EF%BC%8C%E8%BF%99%E7%A7%8D%E7%99%BE%E5%BA%A6%E4%B8%80%E4%B8%8B%E5%B0%B1%E8%83%BD%E5%A4%9F%E6%90%9E%E5%AE%9A%E7%9A%84%E4%B8%9C%E8%A5%BF%EF%BC%8C%E5%B0%B1%E4%B8%8D%E8%A6%81%E8%AE%A9%E8%80%81%E5%B8%88%E8%AE%B2%E4%BA%86%E3%80%82

