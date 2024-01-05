# 配置 Keepalived - 副

在另一台备用服务器中安装Keepalived ，步骤同主服务器一致

##### 1、通过命令 vim keepalived.conf 打开配置文件

```
cd /etc/keepalived
vim keepalived.conf
```

编辑配置文件

```
global_defs {
   router_id keep_172
}

vrrp_instance VI_1 {
    # 备用机设置为BACKUP
    state BACKUP
    # 当前实例绑定的网卡
    interface ens33
    # 保证主备节点一致
    virtual_router_id 51
    # 权重低于MASTER，谁的优先级高，在MASTER挂了后就能成为新的MASTER
    priority 80
    # 主备之间同步检查的时间间隔，默认为1秒    
    advert_int 2
    # 认证授权的密码，防止非法节点进入     
    authentication {
        auth_type PASS
        auth_pass 1111
    }
    virtual_ipaddress {
        # 注意：主备两台的vip都是一样的，绑定到同一个vip
        192.168.1.161
    }
}
```

查看网卡内容：

```
ip addr
```

![输入图片说明](../img/08.jpg)

##### 2、启动Keepalived 

```
cd /usr/local/keepalived/sbin

# 启动keepalived
systemctl start keepalived
# 停止keepalived
systemctl stop keepalived
# 重启keepalived
systemctl restart keepalived
```

![输入图片说明](../img/07.jpg)

##### 3、查看进程

```
ps -ef|grep keepalived
```

![输入图片说明](../img/05.jpg)

##### 4、校验高可用

正常访问：

![输入图片说明](../img/12.jpg)

停用主机Keepalived，再次访问：

![输入图片说明](../img/13.jpg)

恢复主机Keepalived，再次访问：

![输入图片说明](../img/12.jpg)

可以保证高可用