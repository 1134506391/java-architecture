# Redis的数据类型 - hash

## 1、hash

hash：类似map，存储结构化数据结构，比如存储一个对象（不能有嵌套对象）

## 2、常用命令

hset key property value：

```
-> hset user name test

-> 创建一个user对象，这个对象中包含name属性，name值为test
```

hget user name：获得用户对象中name的值

```
hget user name
# 输出
"test"
```

hmset：设置对象中的多个键值对

```
-> hset user age 18 phone 139123123
```

hmsetnx：设置对象中的多个键值对，存在则不添加

```
-> hset user age 18 phone 139123123
```

hmget：获得对象中的多个属性

```
-> hmget user name  age phone
# 输出
1) "test"
2) "18"
3) "139123123"
```

hgetall user：获得整个对象的内容

```
-> hgetall user
# 输出
1) "name"
2) "test"
3) "age"
4) "18"
5) "phone"
6) "139123123"
```

hincrby user age 2：累加属性

```
-> hincrby user age 2
# 输出
(integer) 20
```

hincrbyfloat user age 2.2：累加属性

```
->  hincrbyfloat user age 2.2
# 输出
"22.2"
```

hlen user：有多少个属性

```
->  hlen user
# 输出
(integer) 3
```

hexists user age：判断属性是否存在

```
->  hexists user age
# 输出
(integer) 1
```

hkeys user：获得所有属性

```
-> hkeys user
# 输出
1) "name"
2) "age"
3) "phone"
```

hvals user：获得所有值

```
-> hvals user
# 输出
1) "test"
2) "18"
3) "139123123"
```

hdel user：删除对象

```
-> hdel user
# 输出
(error) ERR wrong number of arguments for 'hdel' command
-> hdel user age
# 输出
(integer) 1
-> hkeys user
# 输出
1) "name"
2) "phone"
```

