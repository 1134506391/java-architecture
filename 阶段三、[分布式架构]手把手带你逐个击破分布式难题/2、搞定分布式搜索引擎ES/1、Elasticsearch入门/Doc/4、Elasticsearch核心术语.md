# ElasticSearch核心术语

## 1、核心概念

ES->数据库

索引index->表（stu_index）

文档document->行（{}中的内容）

字段fields->列（id、name、age）

```
stu_index
{
	id: 1001,
	name: jason,
	age: 19
},
{
	id: 1002,
	name: tom,
	age: 18
},
{
	id: 1003,
	name: rose,
	age: 22
}
```

## 2、集群相关

简称

>  shard = primary shard(主分片)
>
>  replica = replica shard(备份节点)

分片（shard ）：把索引库拆分为多份，分别放在不同的节点上，比如有3个节点，3个节点的所有数据加在一起是一个完整的索引库。分别保存到三个节点上，目的为了水平扩展，提高吞吐量。

备份（replica ）： 每个shard的备份。

