# Redis的数据类型 - set

## 1、set

set：可以去重的数据集

## 2、使用

sadd set 1 2 3 3 2 4 ：构建一个set

```
127.0.0.1:21079> sadd set 1 2 3 3 2 4
# 输出
(integer) 4
```

smembers set：查看set中的内容

```
127.0.0.1:21079> smembers set
# 输出
1) "1"
2) "2"
3) "3"
4) "4"
```

scard set：统计个数

```
127.0.0.1:21079> scard set
# 输出
(integer) 4
```

sismember set 1：查看值是否在set的内容中

```
127.0.0.1:21079> sismember set 1
# 输出 存在
(integer) 1
127.0.0.1:21079> sismember set 5
# 输出 不存在
(integer) 0
```

srem set 1：删除set中的内容

```
127.0.0.1:21079> srem set 1
# 输出
(integer) 1
127.0.0.1:21079> smembers set
# 输出
1) "2"
2) "3"
3) "4"
```

spop set 1：随机出栈1个

```
127.0.0.1:21079> spop set 1
# 输出
1) "2"

127.0.0.1:21079> smembers set
# 输出
1) "3"
2) "4"
```

srandmember  set  2：随机获取2个值

```
127.0.0.1:21079> srandmember  set  2
# 输出
1) "3"
2) "1"
# 输出
127.0.0.1:21079> smembers set
1) "1"
2) "2"
3) "3"
4) "4"
```

smove set set2 4 ：将set中的4移动到set2中

```
127.0.0.1:21079> sadd set2 5 6 7 8 9
# 输出
(integer) 5
127.0.0.1:21079> smembers set
# 输出
1) "1"
2) "2"
3) "3"
4) "4"
127.0.0.1:21079> smembers set2
# 输出
1) "5"
2) "6"
3) "7"
4) "8"
5) "9"
127.0.0.1:21079> smove set set2 4
# 输出
(integer) 1
127.0.0.1:21079> smembers set2
# 输出
1) "4"
2) "5"
3) "6"
4) "7"
5) "8"
6) "9"
127.0.0.1:21079> smembers set
# 输出
1) "1"
2) "2"
3) "3"
```

sdiff set set2：set和set2的差集(set中有，set2中没有)

```
127.0.0.1:21079> sadd set 2 3 4 5
(integer) 2
127.0.0.1:21079> smembers set
1) "1"
2) "2"
3) "3"
4) "4"
5) "5"
127.0.0.1:21079> smembers set2
1) "4"
2) "5"
3) "6"
4) "7"
5) "8"
6) "9"
127.0.0.1:21079> sdiff set set2
1) "1"
2) "2"
3) "3"
```

sinter set set2：set和set2的交集(set和set2中都有)

```
127.0.0.1:21079> smembers set
1) "1"
2) "2"
3) "3"
4) "4"
5) "5"
127.0.0.1:21079> smembers set2
1) "4"
2) "5"
3) "6"
4) "7"
5) "8"
6) "9"
127.0.0.1:21079> sinter set set2
1) "4"
2) "5"
```

sunion set set2：set和set2的并集(set和set2中全部有的)

```
127.0.0.1:21079> smembers set
1) "1"
2) "2"
3) "3"
4) "4"
5) "5"
127.0.0.1:21079> smembers set2
1) "4"
2) "5"
3) "6"
4) "7"
5) "8"
6) "9"
127.0.0.1:21079> sunion set set2
1) "1"
2) "2"
3) "3"
4) "4"
5) "5"
6) "6"
7) "7"
8) "8"
9) "9"
```

