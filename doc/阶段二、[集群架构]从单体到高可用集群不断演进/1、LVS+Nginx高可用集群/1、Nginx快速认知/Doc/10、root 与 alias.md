# root与alias

假如服务器路径为：/home/study/files/img/face.png

1. root 路径完全匹配访问，配置的时候为:

    location /study {
        root /home
    }

用户访问的时候请求为: url:port/study/files/img/face.png

2. alias 可以为你的路径做一个别名，对用户透明配置的时候为：

    location /hello {
        alias /home/study
    }
用户访问的时候请求为  url:port/hello/files/img/face.png，如此相当于为目录 study 做一个自定义的别名。