# MySQL主从同步配置

## 1、编辑MySQL主的配置文件

编辑MySQL主上的/etc/my.cnf：

```
log-bin=imooc_mysql
server-id=1
```

log-bin：MySQL的 bin-log 的名字

server-id：MySQL实例中全局唯一并且大于0

## 2、编辑MySQL从的配置文件

编辑MySQL从上的/etc/my.cnf：

```
server-id=2
```

server-id : MySQL实例中全局唯一，并且大于0。与主上的 server-id 区分开

## 3、在MySQL主上创建备份账号

在MySQL主上创建用于备份账号：

```
mysql> CREATE USER 'repl'@'%' IDENTIFIED BY 'password'; 
mysql> GRANT REPLICATION SLAVE ON *.* TO 'repl'@'%';
```

## 4、MySQL主加锁

MySQL主上加锁，阻止所有的写入操作：

```
mysql> FLUSH TABLES WITH READ LOCK;
```

## 5、MySQL主查看bin-log

MySQL主上，查看bin-log的文件名和位置：

```
mysql > SHOW MASTER STATUS;
```

## 6、MySQL主导出数据

MySQL主上dump所有数据：

```
mysqldump --all-databases --master-data > dbdump.db -uroot -p
```

## 7、MySQL主进行解锁

MySQL主进行解锁，解锁后，主上可以写入数据：

```
mysql> UNLOCK TABLES;
```

## 8、MySQL从导入数据

MySQL从上导入之前dump的数据：

```
mysql < aa.db -uroot -p
```

## 9、MySQL从配置主从连接信息

MySQL从上配置主从连接信息：

```
mysql> CHANGE MASTER TO
	-> MASTER_HOST='master_host_name', 	
	-> MASTER_PORT=port_num 
	-> MASTER_USER='replication_user_name', 
	-> MASTER_PASSWORD='replication_password', 			        
	-> MASTER_LOG_FILE='recorded_log_file_name',			   
    -> MASTER_LOG_POS=recorded_log_position;
```

master_host_name: MySQL主的地址；

port_num: MySQL主的端口 (数字型)；

replication_user_name：备份账户的用户名

replication_password：备份账户的密码

recorded_log _file_name : bin-log的文件名

recorded_log_position : bin-log的位置（数字型）

bin-log的文件名和位置是 步骤5中的 `show master status` 得到的

## 10、MySQL从上开启同步

MySQL从上开启同步：

```
mysql> START SLAVE;
```

查看MySQL从的状态：

```
show slave status;
```

