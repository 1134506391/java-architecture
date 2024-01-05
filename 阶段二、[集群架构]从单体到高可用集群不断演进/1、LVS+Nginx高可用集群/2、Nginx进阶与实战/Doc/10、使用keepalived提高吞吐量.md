# 使用keepalived提高吞吐量

**keepalived**： 设置长连接处理的数量
**proxy_http_version**：设置长连接http版本为1.1
**proxy_set_header**：清除connection header 信息

参考配置如下：

```
upstream tomcats {
#       server 192.168.1.173:8080 max_fails=2 fail_timeout=1s;
        server 192.168.1.190:8080;
#       server 192.168.1.174:8080 weight=1;
#       server 192.168.1.175:8080 weight=1;
        keepalive 32;
}

server {
        listen       80;
        server_name  www.tomcats.com;

        location / {
            proxy_pass  http://tomcats;
            proxy_http_version 1.1;
            proxy_set_header Connection "";
        }
    }

```

