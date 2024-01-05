# 配置Keepalived双主热备

规则：以一个虚拟IP分组归为同一个路由

##### 1、主节点配置

进入主服务器的目录：

```
cd /etc/keepalived

vim keepalived.conf
```

修改配置文件：

```
global_defs {
   router_id keep_171
}

vrrp_instance VI_1 {
    state MASTER
    interface ens33
    virtual_router_id 51
    priority 100
    advert_int 1
    authentication {
        auth_type PASS
        auth_pass 1111
    }
    virtual_ipaddress {
        192.168.1.161
    }
}

vrrp_instance VI_2 {
    state BACKUP
    interface ens33
    virtual_router_id 52
    priority 80
    advert_int 1
    authentication {
        auth_type PASS
        auth_pass 1111
    }
    virtual_ipaddress {
        192.168.1.162
    }
}
```

##### 2、备用节点配置

进入副服务器的目录：

```
cd /etc/keepalived

vim keepalived.conf
```

修改配置文件：

```
global_defs {
   router_id keep_172
}

vrrp_instance VI_1 {
    state BACKUP
    interface ens33
    virtual_router_id 51
    priority 80
    advert_int 1
    authentication {
        auth_type PASS
        auth_pass 1111
    }
    virtual_ipaddress {
        192.168.1.161
    }
}

vrrp_instance VI_2 {
    state MASTER
    interface ens33
    virtual_router_id 52
    priority 100
    advert_int 1
    authentication {
        auth_type PASS
        auth_pass 1111
    }
    virtual_ipaddress {
        192.168.1.162
    }
}
```

##### 3、重启Keepalived

```
systemctl restart keepalived
```

##### 4、测试

访问主服务：

![输入图片说明](../img/16.jpg)

访问副服务：

![输入图片说明](../img/17.jpg)

停止171服务器的Keepalived

```
systemctl stop keepalived.service
```

再次访问主服务：

![输入图片说明](../img/18.jpg)

再次访问副服务：

![输入图片说明](../img/17.jpg)

实现高可用