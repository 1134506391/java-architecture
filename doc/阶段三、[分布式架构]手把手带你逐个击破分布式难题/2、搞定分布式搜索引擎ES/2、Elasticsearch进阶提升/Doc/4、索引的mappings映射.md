# 索引的mappings映射

## 1、索引分词概念

index:默认true，设罟为false的话，那么这个字段就不会被索引

官网：https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-index.html

## 2、创建索引的同时创建mappings

```
PUT     /index_str
{
    "mappings": {
        "properties": {
            "realname": {
            	"type": "text",
            	"index": true
            },
            "username": {
            	"type": "keyword",
            	"index": false
            }
        }
    }
}
```

## 3、查看分词效果

```
GET         /index_mapping/_analyze
{
	"field": "realname",
	"text": "imooc is good"
}
```

## 4、尝试修改

```
POST        /index_str/_mapping
{
    "properties": {
        "name": {
        	   "type": "long"
        }
    }
}
```

## 5、为已存在的索引创建mappings

```
POST        /index_str/_mapping
{
    "properties": {
        "id": {
        	"type": "long"
        },
        "age": {
        	"type": "integer"
        },
        "nickname": {
            "type": "keyword"
        },
        "money1": {
            "type": "float"
        },
        "money2": {
            "type": "double"
        },
        "sex": {
            "type": "byte"
        },
        "score": {
            "type": "short"
        },
        "is_teenager": {
            "type": "boolean"
        },
        "birthday": {
            "type": "date"
        },
        "relationship": {
            "type": "object"
        }
    }
}
```

注:某个属性一旦被建立，就不能修改了，但是可以新增额外属性

## 6、主要数据类型

- text, keyword, string
- long, integer, short, byte 
- double, float
- boolean
- date
- object
- 数组不能混，类型一致

## 7、字符串

- text：文字类需要被分词被倒排索引的内容，比如 商品名称，商品详情，商品介绍，使用text；
- keyword：不会被分词，不会被倒排索引，直接匹配搜索，比如 订单状态，用户qq， 微信号 ，手机号等这些精确匹配，无需分词。