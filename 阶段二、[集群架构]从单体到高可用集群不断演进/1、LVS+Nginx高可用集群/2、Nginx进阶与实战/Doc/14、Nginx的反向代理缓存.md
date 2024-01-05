# Nginx的反向代理缓存

相关配置如下：

```
# proxy_cache_path 设置缓存目录
#       keys_zone 设置共享内存以及占用空间大小
#       max_size 设置缓存大小
#       inactive 超过此时间则被清理
#       use_temp_path 临时目录，使用后会影响nginx性能
proxy_cache_path /usr/local/nginx/upstream_cache keys_zone=mycache:5m max_size=1g inactive=1m use_temp_path=off;
```

```
location / {
    proxy_pass  http://tomcats;

    # 启用缓存，和keys_zone一致
    proxy_cache mycache;
    # 针对200和304状态码缓存时间为8小时
    proxy_cache_valid   200 304 8h;
}
```

