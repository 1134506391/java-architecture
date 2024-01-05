# Location的匹配规则

1、空格 ： 默认匹配、普通匹配

```
location / {
     root /home;
}
```

2、“=”：精确匹配（不能匹配该路径下的其他文件）

```
location = /study/img/face1.png {
    root /home;
}
```

3、“~*”：匹配正则表达式，不区分大小写

```
#符合图片的显示
location ~* .(GIF|jpg|png|jpeg) {
    root /home;
}
```

4、“~”：匹配正则表达式，区分大小写

```
#GIF必须大写才能匹配到
location ~ .(GIF|jpg|png|jpeg) {
    root /home;
}
```

5、“^~”：以某个字符路径开头

```
location ^~ /study/img {
    root /home;
}

 固定了部分访问路径，此处如果要访问其他非“ /study/img”下的文件则无法访问
```

“^”尖括号表示非

“*”表示不区分大小写
