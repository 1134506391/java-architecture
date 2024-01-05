# Redis的数据类型 - zset

## 1、zset

sorted set：排序的set，可以去重可以排序，比如可以根据用户积分做排名，积分作为set的一个数值，根据数值可以做排序。set中的每一个memeber都带有一个分数

## 2、使用

zadd zset 10 value1 20 value2 30 value3：设置member和对应的分数

```
127.0.0.1:21079> zadd zset 10 duck 20 pig 30 chicken 40 beef 50 sheep
# 输出
(integer) 5
```

zrange zset 0 -1：查看所有zset中的内容

```
127.0.0.1:21079> zrange zset 0 -1
# 输出
1) "duck"
2) "pig"
3) "chicken"
4) "beef"
5) "sheep"
```

zrange zset 0 -1 withscores：带有分数

```
127.0.0.1:21079> zrange zset 0 -1 withscores
# 输出
 1) "duck"
 2) "10"
 3) "pig"
 4) "20"
 5) "chicken"
 6) "30"
 7) "beef"
 8) "40"
 9) "sheep"
10) "50"
```

zrank zset value：获得对应的下标

```
127.0.0.1:21079> zrank zset duck
# 输出
(integer) 0
```

zscore zset value：获得对应的分数

```
127.0.0.1:21079> zscore zset duck
# 输出
"10"
```

zcard zset：统计个数

```
127.0.0.1:21079> zcard zset
# 输出
(integer) 5
```

zcount zset 分数1 分数2：统计个数 （分数1>=个数>=分数2 ）

```
127.0.0.1:21079> zcount zset 20 40
# 输出
(integer) 3
```

zrangebyscore zset 分数1 分数2：查询分数之间的member(包含分数1 分数2)

```
127.0.0.1:21079> zrangebyscore zset 20 40
# 输出
1) "pig"
2) "chicken"
3) "beef"

127.0.0.1:21079> zrangebyscore zset 20 40 withscores
# 输出
1) "pig"
2) "20"
3) "chicken"
4) "30"
5) "beef"
6) "40"
```

zrangebyscore zset (分数1 (分数2：查询分数之间的member（不包含分数1 和 分数2）

```
127.0.0.1:21079> zrangebyscore zset 20 (40
# 输出（不包含40）
1) "pig"
2) "chicken"
```

zrangebyscore zset 分数1 分数2 limit start end：查询分数之间的member(包含分数1 分数2)，获得的结果集再次根据下标区间做查询

```
127.0.0.1:21079> zrangebyscore zset 20 40 limit 1 2
# 输出
1) "chicken"
2) "beef"
```

zrem zset value：删除member

```
127.0.0.1:21079> zrem zset pig
# 输出
(integer) 1
# 输出
127.0.0.1:21079> zrange zset 0 -1
1) "duck"
2) "chicken"
3) "beef"
4) "sheep"
```

