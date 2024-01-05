# Redis的数据类型 - list

## 1、list

list：列表，[a,b,c,d,e,f]

## 2、使用

lpush userList 1 2 3 4 5 ：构建一个list，从左边开始存入数据

```
127.0.0.1:21079> lpush userList 1 2 3 4 5 
# 输出
(integer) 5
```

lrange list start end：获取数据

```
127.0.0.1:21079> lrange userList 0 -1
# 输出
1) "5"
2) "4"
3) "3"
4) "2"
5) "1"

127.0.0.1:21079> lrange userList1 0 -1
# 输出
1) "1"
2) "2"
3) "3"
4) "4"
5) "5"
```

lpop：从左侧开始拿出一个数据

```
127.0.0.1:21079> lpop userList
# 输出
"5"
127.0.0.1:21079> lrange userList 0 -1
# 输出
1) "4"
2) "3"
3) "2"
4) "1"
```

rpop：从右侧开始拿出一个数据

```
127.0.0.1:21079> rpop userList
# 输出
"1"
# 输出
127.0.0.1:21079> lrange userList 0 -1
1) "4"
2) "3"
3) "2"
```

llen list：list长度

```
127.0.0.1:21079> llen userList
# 输出
(integer) 3
```

lindex list index：获取list下标的值

```
127.0.0.1:21079> lindex userList 0
# 输出
"4"
```

lset list index value：把某个下标的值替换

```
127.0.0.1:21079> lset userList 0 1
# 输出
OK
127.0.0.1:21079> lrange userList 0 -1
# 输出
1) "1"
2) "3"
3) "2"
```

linsert list before/after value：插入一个新的值

```
127.0.0.1:21079> linsert userList before 1 0
# 输出
(integer) 4

127.0.0.1:21079> lrange userList 0 -1
# 输出
1) "0"
2) "1"
3) "3"
4) "2"
```

lremlist num value：删除几个相同数据

```
127.0.0.1:21079>  linsert userList before 3 0
# 输出
(integer) 5
127.0.0.1:21079> lrange userList 0 -1
# 输出
1) "0"
2) "1"
3) "0"
4) "3"
5) "2"
# 输出
127.0.0.1:21079> lrem userList 2 0
(integer) 2
# 输出
127.0.0.1:21079> lrange userList 0 -1
1) "2"
2) "3"
3) "1"
```

ltrim list start end：截取值，替换原来的list阿

```
127.0.0.1:21079> ltrim userList 0 1 
# 输出
OK
127.0.0.1:21079> lrange userList 0 -1
# 输出
1) "2"
2) "3"
```

del list : 直接删除list

```
127.0.0.1:21079> del userList1
# 输出
(integer) 1
127.0.0.1:21079> lrange userList1 0 -1
# 输出
(empty array)
127.0.0.1:21079> keys *
# 输出
1) "userList"
```

