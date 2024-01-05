# Redis的数据类型 - string

## 1、string 字符串

string: 最简单的字符串类型键值对缓存，也是最基本的

## 2、key相关

keys *：查看所有的key (不建议在生产上使用，有性能影响)

type key：key的类型

## 3、string类型

get/set/del：查询/设置/删除

```
# 保存key为test，value为value的string类型数据到redis
set test value
```

set rekey data：设置已经存在的key，会覆盖

setnx rekey data：设置已经存在的key，不会覆盖【加锁】

```
setnx test value1
```

set key value ex time：设置带过期时间的数据

expire key：设置过期时间

ttl：查看剩余时间，-1永不过期，-2过期

```
ttl test   
# 值为-1则表示永不过期
```

append key：合并字符串

```
append test 123
# 值为 value123
```

strlen key：字符串长度

```
strlen test
# 值为 8（value123的长度）
```

incr key：累加1

```
setnx age 18
incr age
# 值为 19
```

decr key：类减1

```
decr  age
# 值为 18
```

incrby key num：累加给定数值

```
incrby age 10
# 值为 28
```

decrby key num：累减给定数值

```
decrby  age 10
# 值为 18
```

getrange key start end：截取数据，end=-1 代表到最后

```
getrange test 0 -1
# 值为 value123
getrange test 0 4
# 值为 value
```

setrange key start newdata：从start位置开始替换数据

```
setrange test 4 321
# 值为 valu3213 
```

mset：连续设值

```
mset k1 aa k2 bb
```

mget：连续取值

```
mget k1 k2

#1) "aa"
 2) "bb"
```

msetnx：连续设置，如果存在则不设置

```
msetnx k2 123 k3 cc
mget k1 k2

#1) "aa"
 2) "bb"
```

## 4、其他

select index：切换数据库，总共默认16个

```
127.0.0.1:21079> select 1
OK
127.0.0.1:21079[1]> 
```

flushdb：删除当前下边db中的数据

```
flushdb
```

flushall：删除所有db中的数据

```
flushall
```

