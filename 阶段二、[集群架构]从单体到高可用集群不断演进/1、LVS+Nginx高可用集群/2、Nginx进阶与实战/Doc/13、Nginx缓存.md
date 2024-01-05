# Nginx的缓存

## 1、浏览器缓存

加速用户访问，提升单个用户(浏览器访问者)体验，缓存在本地

## 2、Nginx缓存

- 缓存在nginx端，提升所有访问到nginx这一端的用户
- 提升访问上游(upstream)服务器的速度
- 用户访问仍然会产生请求流量

## 3、控制浏览器缓存

参考代码：

修改 nginx.conf 配置文件

```
location /files {
    alias /home/imooc;
    # expires 10s;
    # expires @22h30m;
    # expires -1h;
    # expires epoch;
    # expires off;
    expires max;
}
```

新增cache.html页面：

```
 <html>
    <body>
        <h1>
            Hello, Nginx ~ !~
        </h1>
    </body>
 </html>
```

